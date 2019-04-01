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

public class PostSignOutRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSignOutRoute.class.getName());

    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;

    public PostSignOutRoute(final PlayerLobby playerLobby, final GameCenter gameCenter){
        this.playerLobby = playerLobby;
        this.gameCenter = gameCenter;
        LOG.config("PostSignOutRoute is initialized");
    }

    @Override
    public Object handle(Request request, Response response) {
        Session currentSession = request.session();
        Player currentPlayer = currentSession.attribute(GetHomeRoute.CURRENT_USER_ATTR);
        if (gameCenter.hasGame(currentPlayer)){
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
