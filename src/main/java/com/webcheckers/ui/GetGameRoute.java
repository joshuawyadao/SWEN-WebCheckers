package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;
import sun.misc.VM;

import java.util.*;
import java.util.logging.Logger;

import static com.webcheckers.model.Player.PlayerColor.WHITE;

public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());

    private static final Message WELCOME_MSG = Message.info("Welcome to the game of Online Checkers.");

    private final PlayerLobby playerLobby;
    private final TemplateEngine templateEngine;

    private final String COLOR_WHITE = "WHITE";
    private final String COLOR_RED = "RED";

    public enum viewMode{
        PLAY
    }

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetGameRoute(final PlayerLobby playerLobby, final TemplateEngine templateEngine) {
        this.playerLobby = Objects.requireNonNull(playerLobby, "templateEngine is required");
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        //
        LOG.config("GetGameRoute is initialized.");
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
        Map<String, Object> vm = new HashMap<>();
        Session httpSession = request.session();

        Player currentUser = httpSession.attribute("currentUser");
        vm.put("currentUser", currentUser);

        vm.put("viewMode", viewMode.PLAY);
        //Map<String,Object> modeOptionAsJSON = new HashMap<>();
        //modeOptionAsJSON.put("Nothing", null);
        //vm.put("modeOptionsAsJSON", modeOptionAsJSON);

        if(httpSession.attribute("redPlayer") == null && httpSession.attribute("whitePlayer") == null){
            Player opponent = new Player(request.queryParams("opponent"));
            boolean isPlayerOne = request.queryParams("isPlayerOne").equals("true");

            if(isPlayerOne){
                httpSession.attribute("redPlayer", currentUser);
                httpSession.attribute("whitePlayer", opponent);

                vm.put("redPlayer", currentUser);
                vm.put("whitePlayer", opponent);
                vm.put("activeColor", COLOR_RED);
                
            }else{
                httpSession.attribute("redPlayer", opponent);
                httpSession.attribute("whitePlayer", currentUser);

                vm.put("redPlayer", opponent);
                vm.put("whitePlayer", currentUser);
                vm.put("activeColor", COLOR_WHITE);
            }

        }

        vm.put("title", "Enjoy Your Game!");

        BoardView boardView = new BoardView();
        vm.put("board", boardView);
        //vm.put("message","TEST_MESSAGE");

        // render the View
        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }

}
