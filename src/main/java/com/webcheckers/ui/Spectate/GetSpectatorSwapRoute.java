package com.webcheckers.ui.Spectate;

import com.webcheckers.ui.WebServer;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.logging.Logger;

import static spark.Spark.halt;

/**
 * The UI Controller to swap the perspective and GET the spectator game page
 */
public class GetSpectatorSwapRoute implements Route {

    private static final Logger LOG = Logger.getLogger(GetSpectatorSwapRoute.class.getName());

    /**
     * Create the Spark Route (UI Controller) to handle  all {@code GET /} HTTP requests.
     */
    public GetSpectatorSwapRoute(){
        LOG.config("GetSpectatorSwapRoute is initialized.");
    }

    /**
     * Render the WebCheckers Spectator Game page after swapping perspectives
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Spectator Game page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetSpectatorSwapRoute is invoked.");
        Session currentSession = request.session();

        String gameID = currentSession.attribute("specID");

        Boolean isRed = currentSession.attribute("isRed");
        if (isRed == null || isRed){
            currentSession.attribute("isRed", false);
        } else{
            currentSession.attribute("isRed", true);
        }


        response.redirect(WebServer.SPECTATOR_GAME_URL + "?gameID="
                + gameID);
        halt();
        return null;
    }
}
