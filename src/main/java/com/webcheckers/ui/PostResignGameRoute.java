package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static spark.Spark.halt;

public class PostResignGameRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostResignGameRoute.class.getName());

    private final Gson gson;
    private final GameCenter gameCenter;
    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;

    public PostResignGameRoute(final Gson gson, final GameCenter gameCenter, final TemplateEngine templateEngine,
                               final PlayerLobby playerLobby) {
        this.gson = gson;
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
        this.playerLobby = playerLobby;
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

        Session opponentSession = playerLobby.getPlayerSession(opponent);
        currentUser.leaveGame();
        opponent.leaveGame();
        gameCenter.removeGame(gameId);
        currentSession.attribute(GetGameRoute.GAME_ID_ATTR, null);
        opponentSession.attribute(GetGameRoute.GAME_ID_ATTR, null);

        // display a user message in the Home page
        vm.put("message", GetHomeRoute.WELCOME_MSG);
        Message resignInfo = Message.info("Resigned.");
        //response.redirect(WebServer.HOME_URL);
        return gson.toJson(resignInfo);
    }
}
