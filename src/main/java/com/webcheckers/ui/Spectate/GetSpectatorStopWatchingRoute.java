package com.webcheckers.ui.Spectate;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.ui.Home.GetHomeRoute;
import com.webcheckers.ui.WebServer;
import spark.*;

import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

/**
 * The UI Controller to GET back to the home page
 */
public class GetSpectatorStopWatchingRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetSpectatorStopWatchingRoute.class.getName());

    private final GameCenter gameCenter;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     * @param gameCenter class that holds all current active games and all replayable games
     */
    public GetSpectatorStopWatchingRoute(final GameCenter gameCenter) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        LOG.config("GetSpectatorStopWatchingRoute is initialized.");
    }

    /**
     * Render the WebCheckers home page and clean up spectator backend
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Home page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetSpectatorStopWatchingRoute is invoked.");
        Session currentSession = request.session();
        String gameID = currentSession.attribute("specID");
        Player currentUser = currentSession.attribute(GetHomeRoute.CURRENT_USER_ATTR);

        gameCenter.removeSpectator(gameID, currentUser);

        response.redirect(WebServer.HOME_URL);
        halt();
        return null;
    }
}
