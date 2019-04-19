package com.webcheckers.ui.Home;

import spark.*;

import java.util.*;
import java.util.logging.Logger;

public class GetSignInRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());

    public static final String VIEW_NAME = "signin.ftl";
    public static final String SIGNIN_TITLE_ATTR = "signin_title";
    public static final String SIGNIN_TITLE = "Please Sign In";

    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetSignInRoute(final TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        //
        LOG.config("GetSignInRoute is initialized.");
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
        LOG.finer("GetSignInRoute is invoked.");
        //
        Map<String, Object> vm = new HashMap<>();
        vm.put(SIGNIN_TITLE_ATTR, SIGNIN_TITLE);

        // render the View
        return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
    }
}
