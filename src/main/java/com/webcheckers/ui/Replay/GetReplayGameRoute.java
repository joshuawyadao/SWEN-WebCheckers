package com.webcheckers.ui.Replay;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.*;
import com.webcheckers.ui.*;
import com.webcheckers.ui.Home.GetHomeRoute;
import com.webcheckers.util.Message;
import spark.*;

import java.util.*;
import java.util.logging.Logger;

public class GetReplayGameRoute implements Route {

    private static final Logger LOG = Logger.getLogger(GetReplayGameRoute.class.getName());

    public static final Message WELCOME_MSG = Message.info("Welcome to the game of Online Checkers.");
    private static final String VIEW_NAME = "game.ftl";
    public static final String GAME_ID_ATTR = "gameId";
    private static final String OPPONENT_NAME = "opponent";

    private final GameCenter gameCenter;
    private final TemplateEngine templateEngine;
    private final Gson gson;

    public GetReplayGameRoute(final GameCenter gameCenter, final TemplateEngine templateEngine, final Gson gson) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gson = gson;
    }

    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetReplayGameRoute is invoked.");
        final Map<String, Object> vm = new HashMap<>();
        Map<String, Object> modeOptions = new HashMap<>();
        Session currentSession = request.session();
        Player currentUser = currentSession.attribute(GetHomeRoute.CURRENT_USER_ATTR);

        String gameID = request.queryParams("gameID");
        ReplayGame replayGame;

        if(currentSession.attribute("replayGame") == null){
            replayGame = gameCenter.getReplayGame(gameID);
            currentSession.attribute("replayGame", replayGame);
        }else {
            replayGame = currentSession.attribute("replayGame");
        }

        // Upon entering the replayed game in question,
        // this route will go through the following routes
        // and update the modeOptions map.
        // This will allow respective Next and Previous buttons.
        //
        // Upon hitting the Next or Previous button (which is handled
        // separately than this route), it will encounter a refresh
        // to the replay game page. This enables a second run through
        // of the following if-statements, and cycle again.
        if(replayGame.hasNext()){
            modeOptions.put("hasNext", true);
            vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
        } else {
            modeOptions.put("hasNext", false);
            vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
        }

        if(replayGame.hasPrevious()){
            modeOptions.put("hasPrevious", true);
            vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
        } else {
            modeOptions.put("hasPrevious", false);
            vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
        }

        //game.ftl requirements for Replay:
        vm.put("viewMode", GameCenter.ViewMode.REPLAY);// MUST BE REPLAY
        vm.put("redPlayer", replayGame.getRedPlayer());
        vm.put("whitePlayer", replayGame.getWhitePlayer());
        vm.put("activeColor", replayGame.getActiveColor());

        vm.put("board", new BoardView(replayGame.getCurrentTurn(), true));
        vm.put(GetHomeRoute.CURRENT_USER_ATTR, currentUser);
        vm.put("title", "Enjoy the Replay!");

        // render the View
        return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
    }

}
