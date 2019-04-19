package com.webcheckers.ui.Spectate;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.ui.Home.GetHomeRoute;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.logging.Logger;

public class PostSpectatorCheckTurnRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSpectatorCheckTurnRoute.class.getName());

    private final Gson gson;

    private final GameCenter gameCenter;

    public PostSpectatorCheckTurnRoute(final GameCenter gameCenter, final Gson gson) {
        this.gson = gson;
        this.gameCenter = gameCenter;
        LOG.config("PostSpectatorCheckTurnRoute is initialized.");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("PostSpectatorCheckTurnRoute is invoked.");
        //String gameID = request.queryParams("gameID");

        Session currentSession = request.session();
        String gameID = currentSession.attribute("specID");
        Player currentUser = currentSession.attribute(GetHomeRoute.CURRENT_USER_ATTR);

        boolean isUpdated = gameCenter.isSpectatorUpdated(gameID, currentUser);
        Game gameToSpec = gameCenter.getGame(gameID);


        Message updateInfo;
        if(!isUpdated || gameToSpec.isResigned())
            updateInfo = Message.info("true");
        else
            updateInfo = Message.info("false");

        return gson.toJson(updateInfo);
    }
}
