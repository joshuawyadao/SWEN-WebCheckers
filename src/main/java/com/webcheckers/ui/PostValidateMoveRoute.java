package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Move;
import spark.*;

import java.io.BufferedReader;
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

        //See createCorrectJSONFormat method for more information
        String correctJSONMoveString = createCorrectJSONFormat(moveAsJSONString);
        Move move = gson.fromJson(correctJSONMoveString, Move.class);

        System.out.println("Move Start Row = " + move.getStart().getRow());
        System.out.println("Move Start Cell = " + move.getStart().getCell());
        System.out.println("Move End Row = " + move.getEnd().getRow());
        System.out.println("Move End Cell = " + move.getEnd().getCell());

        // render the View
        return templateEngine.render(new ModelAndView(vm , GetGameRoute.VIEW_NAME));
    }

    //The JSON String returned by 'actionData' is not immediately in the correct format to be
    //converted from a JSON Object into an instance of the Move Class. This method correctly
    //formats the JSON String so it can be immediately converted into an instance of the Move class.
    //Incorrect Format Example (the string received):
    //  "{\"start\":{\"row\":2,\"cell\":3},\"end\":{\"row\":3,\"cell\":2}}"
    //Correct Format Example (returned by this method):
    //  {'start':{'row':2,'cell':3},'end':{'row':3,'cell':2}}
    private static String createCorrectJSONFormat(String incorrectJSONString){
        //1.) Replace all backslashes (\) with apostrophes (')
        String correctJSONString = incorrectJSONString.replace("\\", "'");
        //2.) Replace all instances of an apostrophe followed by a double- quote ('") with a single
        //    apostrophe ('). This was the result of the replacements that occurred in Step 1
        correctJSONString = correctJSONString.replaceAll("'\"", "'");
        //3.) Replace all instances of only double- quotes(") with a the empty string (""),
        //    therefore removing all instances of only double-quotes. This occurs at the
        //    beginning and the end of the incorrect string recieved by 'actionData'
        correctJSONString = correctJSONString.replace("\"", "");

        //4.) Return the correctly formatted string
        return correctJSONString;
    }
}
