package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
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
    private static final String GAME_ID_ATTR = "gameId";
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
        final Map<String, Object> modeOptions = new HashMap<>();
        Session currentSession = request.session();
        Player currentUser = currentSession.attribute(GetHomeRoute.CURRENT_USER_ATTR);

        // modeOptions = new map<String,Boolean>
        // assuming we have an array of moves to go through
        // iterate through the array and set the values in
        // modeOptions respectively

        String gameId = currentSession.attribute(GAME_ID_ATTR);
        Game currentGame = gameCenter.getGame(gameId);

        //game.ftl requirements for Replay:
        vm.put("currentUser", currentUser);
        vm.put("viewMode", Game.ViewMode.REPLAY); // MUST BE REPLAY

        vm.put("modeOptionsAsJSON", null);

        // vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));

        vm.put("redPlayer", currentGame.getRedPlayer());
        vm.put("whitePlayer", currentGame.getWhitePlayer());
        vm.put("activeColor", currentGame.getActivePlayer().getPlayerColor());

        boolean isRed = currentGame.getPlayerColor(currentUser) == Player.PlayerColor.RED;
        BoardView boardView = new BoardView(currentGame.getCheckerBoard(), isRed);
        vm.put("board", boardView);

        vm.put(GetHomeRoute.CURRENT_USER_ATTR, currentUser);
        vm.put("title", "Enjoy the replay!");

        // render the View
        return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
    }
}
