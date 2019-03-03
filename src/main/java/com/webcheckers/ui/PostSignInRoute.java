package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

public class PostSignInRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());

    private final static String USERNAME_PARAM = "myUsername";

    //private static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");

    private final PlayerLobby playerLobby;
    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public PostSignInRoute(final PlayerLobby playerLobby, final TemplateEngine templateEngine) {
        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        //
        LOG.config("PostSignInRoute is initialized.");
    }

    /**
     * Render the WebCheckers Home page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Home page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("PostSignInRoute is invoked.");
        //

        String usernameAttempt = request.queryParams(USERNAME_PARAM);

        Map<String, Object> vm = new HashMap<>();

        if(playerLobby.isValidPlayer(usernameAttempt)){
            Session session = request.session();

            playerLobby.signInPlayer(usernameAttempt);
            session.attribute("name", usernameAttempt);

            //add currentUser to VM
            vm.put("currentUser", playerLobby.getPlayer(usernameAttempt));

            vm.put("title", "Welcome!");

            // display a user message in the Home page
            vm.put("message", GetHomeRoute.WELCOME_MSG);

            response.redirect(WebServer.HOME_URL);
            halt();
            return null;
        }else{
            vm.put("signin_title", "Please Sign In");

            response.redirect("signin.ftl");

            return templateEngine.render(new ModelAndView(vm , "signin.ftl"));
        }

    }

}