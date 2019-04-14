package com.webcheckers.ui.Spectate;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.logging.Logger;

public class PostSpectatorCheckTurnRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostSpectatorCheckTurnRoute.class.getName());

    private final Gson gson;

    private final GameCenter gameCenter;

    public PostSpectatorCheckTurnRoute(final GameCenter gameCenter, final Gson gson) {
        this.gson = gson;
        this.gameCenter = gameCenter;
    }

    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("PostSpectatorCheckTurnRoute is invoked.");
        String gameID = request.queryParams("gameID");
        Message info = Message.info("false");
        return gson.toJson(info);
    }
}
