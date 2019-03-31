package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PostResignGameRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostResignGameRoute.class.getName());

    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;
    private final TemplateEngine templateEngine;

    public PostResignGameRoute(final PlayerLobby playerLobby, final GameCenter gameCenter, final TemplateEngine templateEngine ) {
        this.playerLobby = playerLobby;
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
        LOG.config("PostResignGameRoute is initialized.");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("PostResignGameRoute is invoked.");
        Map<String, Object> vm = new HashMap<>();
        Session currentSession = request.session();

        Player currentUser = currentSession.attribute(GetHomeRoute.CURRENT_USER_ATTR);
        String gameId = currentSession.attribute(GetGameRoute.GAME_ID_ATTR);
        Player opponent;

        if (currentUser.getPlayerColor() == Player.PlayerColor.RED){
            opponent = gameCenter.getGame(gameId).getWhitePlayer();
        } else {
            opponent = gameCenter.getGame(gameId).getRedPlayer();
        }

        gameCenter.removeGame(gameId);
        currentUser.leaveGame();
        opponent.leaveGame();

        return true;
    }
}
