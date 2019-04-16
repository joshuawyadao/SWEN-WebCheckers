package com.webcheckers.ui.Spectate;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.ui.Home.GetHomeRoute;
import com.webcheckers.ui.WebServer;
import spark.*;

import java.util.Objects;

import static spark.Spark.halt;

public class GetSpectatorStopWatchingRoute implements Route {


    private final GameCenter gameCenter;

    public GetSpectatorStopWatchingRoute(final GameCenter gameCenter) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
    }

    @Override
    public Object handle(Request request, Response response) {
        String gameID = request.queryParams("gameID");

        Session currentSession = request.session();
        Player currentUser = currentSession.attribute(GetHomeRoute.CURRENT_USER_ATTR);

        gameCenter.removedSpectator(gameID, currentUser);

        response.redirect(WebServer.HOME_URL);
        halt();
        return null;
    }
}
