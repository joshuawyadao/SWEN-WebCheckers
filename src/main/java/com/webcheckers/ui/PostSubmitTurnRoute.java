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

public class PostSubmitTurnRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSubmitTurnRoute.class.getName());

    private final GameCenter gameCenter;
    private final Gson gson;

    public PostSubmitTurnRoute(final GameCenter gameCenter, final Gson gson ) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.gson = Objects.requireNonNull(gson, "gson is required");
        //
        LOG.config("PostSubmitTurnRoute is initialized.");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("PostSubmitTurnRoute is invoked.");

        Session currentSession = request.session();
        String gameId = currentSession.attribute(GetGameRoute.GAME_ID_ATTR);
        boolean isValid = gameCenter.submitTurn(gameId);

        Message moveInfo = Message.error("ERROR: Crashed Server");

        if(isValid){
            moveInfo = Message.info("Submitted a Turn");
        }else{
            moveInfo = Message.error("Invalid turn!");
        }

        return gson.toJson(moveInfo);
    }
}
