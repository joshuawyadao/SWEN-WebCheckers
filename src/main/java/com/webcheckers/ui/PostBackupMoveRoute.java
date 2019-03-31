package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class PostBackupMoveRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());

    private final GameCenter gameCenter;
    private final Gson gson;

    public PostBackupMoveRoute(final GameCenter gameCenter, final Gson gson ) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.gson = Objects.requireNonNull(gson, "gson is required");
        //
        LOG.config("PostBackupMoveRoute is initialized.");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("PostBackupMoveRoute is invoked.");

        Map<String, Object> vm = new HashMap<>();
        Session currentSession = request.session();
        Player currentUser = currentSession.attribute("currentUser");
        String gameId = currentSession.attribute(GetGameRoute.GAME_ID_ATTR);
        boolean isValid = gameCenter.backupMove(gameId);

        Game currentGame = gameCenter.getGame(gameId);
        vm.put("redPlayer", currentGame.getRedPlayer());
        vm.put("whitePlayer", currentGame.getWhitePlayer());
        vm.put("activeColor", currentGame.getPlayerColor(currentUser));
        vm.put("viewMode", currentGame.getViewMode());

        boolean isRed = currentGame.getPlayerColor(currentUser) == Player.PlayerColor.RED;
        BoardView boardView = new BoardView(currentGame.getRecentTurn(), isRed);
        vm.put("board", boardView);

        vm.put(GetHomeRoute.CURRENT_USER_ATTR, currentUser);
        vm.put("title", "Enjoy Your Game!");

        Message moveInfo;
        if (isValid) {
            moveInfo = Message.info("Backed up move successfully");
        } else {
            moveInfo = Message.error("Could not successfully back up move");
        }

        return gson.toJson(moveInfo);
    }
}
