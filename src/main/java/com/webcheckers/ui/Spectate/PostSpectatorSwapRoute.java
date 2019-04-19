package com.webcheckers.ui.Spectate;

import com.webcheckers.ui.WebServer;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import static spark.Spark.halt;

public class PostSpectatorSwapRoute implements Route {

    public PostSpectatorSwapRoute(){

    }

    @Override
    public Object handle(Request request, Response response) {
        Session currentSession = request.session();

//        Boolean isRed = currentSession.attribute("isRed");
//        if (isRed == null || isRed){
//            currentSession.attribute("isRed", false);
//        } else{
//            currentSession.attribute("isRed", true);
//        }
        response.redirect(WebServer.SPECTATOR_GAME_URL);
        halt();
        return null;
    }
}
