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
    public GetGameRoute(final TemplateEngine templateEngine) {
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
        LOG.finer("GetGameRoute is invoked.");

        PlayerLobby playerLobby = new PlayerLobby();

        Set<Player> players = playerLobby.getPlayers();

        // We have a key set of players
        // We would have to go through the players and grab their sessions....
        // and also change the players sessions whenver a game is triggered...


        Map<String, Object> vm = new HashMap<>();


        Session httpSession = request.session();

        vm.put("title","WEB CHECKERS");

        if(httpSession.attribute("currentUser") != null) {
            Player currentUser = httpSession.attribute("currentUser");
            vm.put("currentUser", currentUser);
            vm.put("viewMode", viewMode.PLAY);

            System.out.println(request.queryParams("opponent"));

            Map<String,Object> emptyMap = new HashMap<>();
            //vm.put("modeOptionsAsJSON", )
            vm.put("redPlayer", currentUser);
            vm.put("whitePlayer", currentUser);
            vm.put("activeColor", WHITE);
            BoardView boardView = new BoardView();
            vm.put("board", boardView);
            //vm.put("message","TEST_MESSAGE");
        }



        // render the View
        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }

}
