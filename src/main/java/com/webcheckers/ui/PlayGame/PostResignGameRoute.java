package com.webcheckers.ui.PlayGame;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.ui.Home.GetHomeRoute;
import com.webcheckers.util.Message;
import spark.*;

import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;

/**
 * The UI Controller to POST resignation
 */
public class PostResignGameRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostResignGameRoute.class.getName());

    private final Gson gson;
    private final GameCenter gameCenter;
    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;

    /**
     * Create the Spark Route to handle Resignation from matches
     * @param gson gson to post to the /game page
     * @param gameCenter class that handles all active games
     * @param templateEngine engine that renders HTML
     * @param playerLobby class that lists out all players, both
     *                    in match and not
     */
    public PostResignGameRoute(final Gson gson, final GameCenter gameCenter, final TemplateEngine templateEngine,
                               final PlayerLobby playerLobby) {
        this.gson = gson;
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
        this.playerLobby = playerLobby;
        LOG.config("PostResignGameRoute is initialized.");
    }

    /**
     * Send the Post to resign from your current match
     * @param request the HTTP request
     * @param response the HTTP response
     * @return Json of resignation info
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("PostResignGameRoute is invoked.");
        Map<String, Object> vm = new HashMap<>();
        Session currentSession = request.session();

        Player currentUser = currentSession.attribute(GetHomeRoute.CURRENT_USER_ATTR);
        String gameId = currentSession.attribute(GetGameRoute.GAME_ID_ATTR);

        gameCenter.resignGame(currentUser, gameId);
        vm.put("message", GetHomeRoute.WELCOME_MSG);
        Message resignInfo = Message.info("Resigned.");

        return gson.toJson(resignInfo);
    }
}
