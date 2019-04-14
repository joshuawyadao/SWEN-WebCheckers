package com.webcheckers.ui.Replay;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.model.ReplayGame;
import com.webcheckers.ui.BoardView;
import com.webcheckers.ui.Home.GetHomeRoute;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class GetReplayGameRoute implements Route {

    private static final Logger LOG = Logger.getLogger(GetReplayGameRoute.class.getName());

    public static final Message WELCOME_MSG = Message.info("Welcome to the game of Online Checkers.");
    private static final String VIEW_NAME = "game.ftl";
    public static final String GAME_ID_ATTR = "gameId";
    private static final String OPPONENT_NAME = "opponent";

    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;
    private final TemplateEngine templateEngine;
    private final Gson gson;

    public GetReplayGameRoute(final PlayerLobby playerLobby, final GameCenter gameCenter, final TemplateEngine templateEngine, final Gson gson) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
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

//        String gameId = currentSession.attribute(GAME_ID_ATTR);
        String gameId = request.queryParams("gameId");
        currentSession.attribute("gameId", gameId);
        //Game currentGame = gameCenter.getGame(gameId);
        ReplayGame replayGame = gameCenter.getReplayGame(gameId);

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

//        boolean isRed = currentGame.getPlayerColor(currentUser) == Player.PlayerColor.RED;
//        BoardView boardView = new BoardView(currentGame.getCheckerBoard(), isRed);

        vm.put("board", new BoardView(replayGame.getCurrentTurn(), true));
        vm.put(GetHomeRoute.CURRENT_USER_ATTR, currentUser);
        vm.put("title", "Enjoy the replay!");

        // render the View
        return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
    }

}
