package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.*;
import java.util.logging.Logger;

public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());

    public static final Message WELCOME_MSG = Message.info("Welcome to the game of Online Checkers.");
    public static final String VIEW_NAME = "game.ftl";
    public static final String GAME_ID_ATTR = "gameId";
    private static final String OPPONENT_NAME = "opponent";

    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;
    private final TemplateEngine templateEngine;
    private final Gson gson;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetGameRoute(final PlayerLobby playerLobby, final GameCenter gameCenter, final TemplateEngine templateEngine, final Gson gson) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gson = gson;
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
        Session currentSession = request.session();
        Player currentUser = currentSession.attribute(GetHomeRoute.CURRENT_USER_ATTR);

        if(currentSession.attribute(GAME_ID_ATTR) == null){
            String opponentName  = request.queryParams(OPPONENT_NAME);
            Session opponentSession = playerLobby.getPlayerSessionByName(opponentName);
            Player opponent = opponentSession.attribute(GetHomeRoute.CURRENT_USER_ATTR);

            String newGameId = gameCenter.newGame(currentUser, opponent, Game.ViewMode.PLAY);
            currentSession.attribute(GAME_ID_ATTR, newGameId);
            opponentSession.attribute(GAME_ID_ATTR, newGameId);
        }

        String gameId = currentSession.attribute(GAME_ID_ATTR);
        Game currentGame = gameCenter.getGame(gameId);

        boolean testBool = true;
        //line below is conditional to use instead of testBool
        //currentGame.getCheckerBoard().finishedGame()
        if (testBool){
            //Player winner = currentGame.completedGame();

            Player winner = currentGame.getWhitePlayer();
            final Map<String, Object> modeOptions = new HashMap<>(2);
            modeOptions.put("isGameOver", true);
            if (winner == currentGame.getRedPlayer()){
                if (currentGame.getRedPlayer().equals(currentUser)){
                    modeOptions.put("gameOverMessage", "You have captured all of "
                            + currentGame.getWhitePlayer().getName()
                            + "'s pieces. Congratulations, you win!");
                    vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
                } else{
                    modeOptions.put("gameOverMessage", currentGame.getWhitePlayer().getName()
                            + " has captured all of your pieces.");
                    vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
                }
            } else{
                if (currentGame.getRedPlayer().equals(currentUser)){
                    modeOptions.put("gameOverMessage", currentGame.getWhitePlayer().getName()
                            + " has captured all of your pieces.");
                    vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
                } else{
                    modeOptions.put("gameOverMessage", "You have captured all of "
                            + currentGame.getWhitePlayer().getName()
                            + "'s pieces. Congratulations, you win!");
                    vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
                }
            }
        }

        vm.put("redPlayer", currentGame.getRedPlayer());
        vm.put("whitePlayer", currentGame.getWhitePlayer());
        vm.put("activeColor", currentGame.getPlayerColor(currentUser));
        vm.put("viewMode", currentGame.getViewMode());

        boolean isRed = currentGame.getPlayerColor(currentUser) == Player.PlayerColor.RED;
        BoardView boardView = new BoardView(currentGame.getCheckerBoard(), isRed);
        vm.put("board", boardView);




        vm.put(GetHomeRoute.CURRENT_USER_ATTR, currentUser);
        vm.put("title", "Enjoy Your Game!");

        // render the View
        return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
    }

}
