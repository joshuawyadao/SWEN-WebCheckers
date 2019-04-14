package com.webcheckers.ui.Spectate;

import com.webcheckers.ui.WebServer;
import spark.*;

import static spark.Spark.halt;

public class GetSpectatorStopWatchingRoute implements Route {

    public GetSpectatorStopWatchingRoute() {

    }

    @Override
    public Object handle(Request request, Response response) {
        response.redirect(WebServer.HOME_URL);
        halt();
        return null;
    }
}
