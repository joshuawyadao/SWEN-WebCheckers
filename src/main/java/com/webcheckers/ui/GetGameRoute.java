package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.*;
import java.util.logging.Logger;

public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());

    public static final Message WELCOME_MSG = Message.info("Welcome to the game of Online Checkers.");
    public static final String VIEW_NAME = "game.ftl";

    private final PlayerLobby playerLobby;
    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetGameRoute(final PlayerLobby playerLobby, final TemplateEngine templateEngine) {
        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        //
        LOG.config("GetGameRoute is initialized.");
    }

    /**
     * Render the WebCheckers Home page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Home page
     */
    @Override
    public Object handle(Request request, Response response) {
        Map<String, Object> vm = new HashMap<>();
        Session currentSession = request.session();
        Player currentUser = currentSession.attribute("currentUser");

        if(currentSession.attribute("thisCheckersGame") == null){
            String opponentName  = request.queryParams("opponent");
            Session opponentSession = playerLobby.getPlayerSessionByName(opponentName);
            Player opponent = opponentSession.attribute("currentUser");
//            GameCenter gameCenter = new GameCenter(currentUser, opponent, Game.ViewMode.PLAY, playerLobby);

//            gameCenter.initializeGame();
//            currentSession.attribute("thisCheckersGame", gameCenter);
//            opponentSession.attribute("thisCheckersGame", gameCenter);
        }

        GameCenter gameCenter = currentSession.attribute("thisCheckersGame");

//        vm.put("redPlayer", gameCenter.getRedPlayer());
//        vm.put("whitePlayer", gameCenter.getWhitePlayer());
//        vm.put("activeColor", currentUser.getPlayerColor());
//        vm.put("viewMode", gameCenter.getViewMode());
//        vm.put("board", gameCenter.getCheckerBoard());

        vm.put("currentUser", currentUser);
        vm.put("title", "Enjoy Your Game!");

        // render the View
        return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
    }

}
