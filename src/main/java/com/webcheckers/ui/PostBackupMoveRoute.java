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

        Session currentSession = request.session();
        String gameId = currentSession.attribute(GetGameRoute.GAME_ID_ATTR);
        boolean isValid = gameCenter.backupMove(gameId);

        Message moveInfo;
        if (isValid) {
            moveInfo = Message.info("Backed up move successfully");
        } else {
            moveInfo = Message.error("Could not successfully back up move");
        }

        return gson.toJson(moveInfo);
    }
}
