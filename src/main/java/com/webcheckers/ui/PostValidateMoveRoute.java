package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Move;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class PostValidateMoveRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private final GameCenter gameCenter;
    private final Gson gson;
    private final TemplateEngine templateEngine;

    public PostValidateMoveRoute(final GameCenter gameCenter, final Gson gson, final TemplateEngine templateEngine ) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.gson = Objects.requireNonNull(gson, "gson is required");
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        //
        LOG.config("GetHomeRoute is initialized.");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("PostSignInRoute is invoked.");

        Map<String, Object> vm = new HashMap<>();
        String moveAsJSONString = gson.toJson(request.queryParams("actionData"));
        Move move = gson.fromJson(moveAsJSONString, Move.class);

        System.out.println("Move Start Row = " + move.getStart().getRow());
        System.out.println("Move Start Cell = " + move.getStart().getCell());
        System.out.println("Move End Row = " + move.getEnd().getRow());
        System.out.println("Move End Cell = " + move.getEnd().getRow());

        // render the View
        return templateEngine.render(new ModelAndView(vm , GetGameRoute.VIEW_NAME));
    }
}
