package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.logging.Logger;

import static spark.Spark.halt;

/**
 * The UI Controller to POST the SignOut request
 */
public class PostSignOutRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSignOutRoute.class.getName());

    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;

    /**
     * Constructor for the Route
     * @param playerLobby Lobby where all current players, both in
     *                    and out of game are listed
     * @param gameCenter Center that keeps track of all running games
     */
    public PostSignOutRoute(final PlayerLobby playerLobby, final GameCenter gameCenter){
        this.playerLobby = playerLobby;
        this.gameCenter = gameCenter;
        LOG.config("PostSignOutRoute is initialized");
    }

    /**
     * Post the SignOut and handle signing out
     * @param request the HTTP request
     * @param response the HTTP response
     * @return
     */
    @Override
    public Object handle(Request request, Response response) {
        Session currentSession = request.session();
        Player currentPlayer = currentSession.attribute(GetHomeRoute.CURRENT_USER_ATTR);
        if (gameCenter.isInAnyGame(currentPlayer)){
            String gameId = currentSession.attribute(GetGameRoute.GAME_ID_ATTR);
            gameCenter.getGame(gameId).playerResigned(currentPlayer);
            currentPlayer.leaveGame();
            currentSession.attribute(GetGameRoute.GAME_ID_ATTR, null);
        }
        playerLobby.signOut(currentPlayer);
        currentSession.attribute(GetHomeRoute.CURRENT_USER_ATTR, null);
        response.redirect(WebServer.HOME_URL);
        halt();
        return null;
    }
}
