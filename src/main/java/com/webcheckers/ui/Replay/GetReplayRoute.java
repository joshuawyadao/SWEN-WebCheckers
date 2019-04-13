package com.webcheckers.ui.Replay;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.model.ReplayGame;
import com.webcheckers.ui.Home.GetHomeRoute;
import spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class GetReplayRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetReplayRoute.class.getName());

    public static final String VIEW_NAME = "replay.ftl";

    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;
    private final TemplateEngine templateEngine;

    public GetReplayRoute(final PlayerLobby playerLobby, final GameCenter gameCenter, final TemplateEngine templateEngine) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");

        LOG.config("GetReplayRoute is initialized.");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetReplayRoute is invoked.");

        Session httpSession = request.session();
        final Map<String, Object> vm = new HashMap<>();
        Player currentUser = httpSession.attribute(GetHomeRoute.CURRENT_USER_ATTR);

        vm.put("title", "Replay Mode");
        vm.put("currentUser", currentUser);
        vm.put("hasPreviousGames", gameCenter.hasPreviousGames());
        vm.put("previousGames", gameCenter.sortPreviousGames());

        return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
    }

}
