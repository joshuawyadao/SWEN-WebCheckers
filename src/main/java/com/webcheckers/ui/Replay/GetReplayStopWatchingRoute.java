package com.webcheckers.ui.Replay;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.ui.Home.GetHomeRoute;
import com.webcheckers.ui.WebServer;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

public class GetReplayStopWatchingRoute implements Route {

    private static final Logger LOG = Logger.getLogger(GetReplayStopWatchingRoute.class.getName());

    public static final Message WELCOME_MSG = Message.info("Welcome to the game of Online Checkers.");
    public static final String VIEW_NAME = "game.ftl";
    public static final String GAME_ID_ATTR = "gameId";
    private static final String OPPONENT_NAME = "opponent";

    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;
    private final TemplateEngine templateEngine;
    private final Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param templateEngine the HTML template rendering engine
     */
    public GetReplayStopWatchingRoute(final PlayerLobby playerLobby, final GameCenter gameCenter, final TemplateEngine templateEngine, final Gson gson) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gson = gson;
        //
        LOG.config("GetReplayStopWatchingRoute is initialized.");
    }

    /**
     * Request to stop replay mode
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Home page
     */
    @Override
    public Object handle(Request request, Response response) {
        //final Map<String, Object> vm = new HashMap<>();
        //final Map<String, Object> modeOptions = new HashMap<>(2);
        //Session currentSession = request.session();
        //Player currentUser = currentSession.attribute(GetHomeRoute.CURRENT_USER_ATTR);
        response.redirect(WebServer.REPLAY_URL);
        halt();
        return null;
    }
}
