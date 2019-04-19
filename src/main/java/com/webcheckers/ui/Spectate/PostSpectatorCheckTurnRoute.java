package com.webcheckers.ui.Spectate;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.*;
import com.webcheckers.ui.Home.GetHomeRoute;
import com.webcheckers.util.Message;
import spark.*;

import java.util.logging.Logger;

/**
 * The UI Controller to GET the Home page.
 */
public class PostSpectatorCheckTurnRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSpectatorCheckTurnRoute.class.getName());

    private final Gson gson;

    private final GameCenter gameCenter;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     * @param gameCenter the class that contains all active games and replay games
     * @param gson
     */
    public PostSpectatorCheckTurnRoute(final GameCenter gameCenter, final Gson gson) {
        this.gson = gson;
        this.gameCenter = gameCenter;
        LOG.config("PostSpectatorCheckTurnRoute is initialized.");
    }

    /**
     * Update the BoardView
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the gson
     */
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
