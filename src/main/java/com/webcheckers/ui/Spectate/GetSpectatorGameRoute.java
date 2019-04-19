package com.webcheckers.ui.Spectate;

import com.google.gson.Gson;
import com.webcheckers.appl.*;
import com.webcheckers.model.*;
import com.webcheckers.ui.BoardView;
import com.webcheckers.ui.Home.GetHomeRoute;
import com.webcheckers.util.Message;
import spark.*;

import java.util.*;
import java.util.logging.Logger;

/**
 * The UI Controller to GET the Spectator Game page
 */
public class GetSpectatorGameRoute implements Route {

    private static final Logger LOG = Logger.getLogger(GetSpectatorGameRoute.class.getName());

    private static final String VIEW_NAME = "game.ftl";

    private final TemplateEngine templateEngine;

    private final GameCenter gameCenter;


    /**
     * Create the Spark Route (UI Controller) to handle all {@code GET /} HTTP requests.
     * @param templateEngine the HTML Rendering Engine
     * @param gameCenter class that holds all current active games and replay games
     * @param playerLobby class that holds all online players
     */
    public GetSpectatorGameRoute(final TemplateEngine templateEngine, final GameCenter gameCenter) {
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
        LOG.config("GetSpectatorGameRoute is initialized.");
    }

    /**
     * Render the WebCheckers Spectator Page
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Spectator Game page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetSpectatorGameRoute is invoked.");
        final Map<String, Object> vm = new HashMap<>();
        Session currentSession = request.session();
        Player currentUser = currentSession.attribute(GetHomeRoute.CURRENT_USER_ATTR);

        String gameID;
        if (currentSession.attribute("specID") == null) {
            gameID = request.queryParams("gameID");
            currentSession.attribute("specID", gameID);
        } else {
            gameID = currentSession.attribute("specID");
        }

        Game gameToSpec = gameCenter.getGame(gameID);

        gameCenter.updateSpectator(gameID, currentUser);

        vm.put(GetHomeRoute.CURRENT_USER_ATTR, currentUser);
        vm.put("viewMode", GameCenter.ViewMode.SPECTATOR);
        vm.put("redPlayer", gameToSpec.getRedPlayer());
        vm.put("whitePlayer", gameToSpec.getWhitePlayer());
        vm.put("activeColor", gameToSpec.getActivePlayer().getPlayerColor());

        boolean isRed = true;
        if (currentSession.attribute("isRed") != null){
            isRed = currentSession.attribute("isRed");
        }

        vm.put("board", new BoardView(gameToSpec.getSpectatorBoard(currentUser), isRed));

        if((gameToSpec.isResigned()) || (gameToSpec.completedGame() != null)){
            vm.put("message", Message.info(gameToSpec.getGameResult(currentUser)));
        }

        vm.put("title", "Enjoy watching your game!");
        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }
}
