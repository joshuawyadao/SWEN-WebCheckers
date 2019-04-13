package com.webcheckers.ui.Replay;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.Objects;

public class PostReplayPreviousTurnRoute implements Route {

    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;
    private final TemplateEngine templateEngine;

    public PostReplayPreviousTurnRoute(final PlayerLobby playerLobby, final GameCenter gameCenter, final TemplateEngine templateEngine) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    }

    @Override
    public Object handle(Request request, Response response) {
        return null;
    }
}
