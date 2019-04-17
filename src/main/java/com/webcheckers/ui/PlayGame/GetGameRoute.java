package com.webcheckers.ui.PlayGame;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.ui.BoardView;
import com.webcheckers.ui.Home.GetHomeRoute;
import com.webcheckers.ui.WebServer;
import com.webcheckers.util.Message;
import spark.*;

import java.util.*;
import java.util.logging.Logger;

import static spark.Spark.halt;

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
        final Map<String, Object> vm = new HashMap<>();
        Map<String, Object> modeOptions = new HashMap<>(2);
        Session currentSession = request.session();
        Player currentUser = currentSession.attribute(GetHomeRoute.CURRENT_USER_ATTR);

        if(currentSession.attribute(GAME_ID_ATTR) == null){
            String opponentName = request.queryParams(OPPONENT_NAME);
            Session opponentSession = playerLobby.getPlayerSessionByName(opponentName);
            Player opponent = opponentSession.attribute(GetHomeRoute.CURRENT_USER_ATTR);

            // Is the opponent in question already in a game?
            if (gameCenter.hasGame(opponent, currentUser)) {
                currentSession.attribute(GetHomeRoute.ERROR_MSG, Message.error( opponent.getName() + " is already in a game!"));

                response.redirect(WebServer.HOME_URL);
                halt();
                return null;
            } else {
                String newGameId = gameCenter.newGame(currentUser, opponent);
                currentSession.attribute(GAME_ID_ATTR, newGameId);
            }
        }

        String gameId = currentSession.attribute(GAME_ID_ATTR);
        Game currentGame = gameCenter.getGame(gameId);

        vm.put("redPlayer", currentGame.getRedPlayer());
        vm.put("whitePlayer", currentGame.getWhitePlayer());
        vm.put("activeColor", currentGame.getActivePlayer().getPlayerColor());
        vm.put("viewMode", GameCenter.ViewMode.PLAY);

        boolean isRed = currentGame.getPlayerColor(currentUser) == Player.PlayerColor.RED;
        BoardView boardView = new BoardView(currentGame.getCheckerBoard(), isRed);
        vm.put("board", boardView);

        vm.put(GetHomeRoute.CURRENT_USER_ATTR, currentUser);
        vm.put("title", "Enjoy Your Game!");

        if (currentGame.isResigned()){
            modeOptions = this.gameCenter.endGame(gameId);
            vm.put("modeOptionsAsJSON", this.gson.toJson(modeOptions));

            currentSession.removeAttribute(GAME_ID_ATTR);
        }

        //boolean testBool = true;
        //line below is conditional to use instead of testBool
        //currentGame.getCheckerBoard().finishedGame()


//        if (currentGame.getCheckerBoard().finishedGame()){
//            Player winner = currentGame.completedGame();
//
//            //Player winner = currentGame.getWhitePlayer();
//            modeOptions.put("isGameOver", true);
//            if (winner == currentGame.getRedPlayer()){
//                if (currentGame.getRedPlayer().equals(currentUser)){
//                    modeOptions.put("gameOverMessage", "You have captured all of "
//                            + currentGame.getWhitePlayer().getName()
//                            + "'s pieces. Congratulations, you win!");
//                    vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
//                    currentGame.getRedPlayer().leaveGame();
//                    currentSession.attribute(GetGameRoute.GAME_ID_ATTR, null);
//                } else{
//                    modeOptions.put("gameOverMessage", currentGame.getRedPlayer().getName()
//                            + " has captured all of your pieces. You lose.");
//                    vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
//                    currentGame.getWhitePlayer().leaveGame();
//                    currentSession.attribute(GetGameRoute.GAME_ID_ATTR, null);
//                    gameCenter.removeGame(gameId);
//                }
//            } else{
//                if (currentGame.getRedPlayer().equals(currentUser)){
//                    modeOptions.put("gameOverMessage", currentGame.getWhitePlayer().getName()
//                            + " has captured all of your pieces. You lose.");
//                    vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
//                    currentGame.getRedPlayer().leaveGame();
//                    currentSession.attribute(GetGameRoute.GAME_ID_ATTR, null);
//                    gameCenter.removeGame(gameId);
//                } else{
//                    modeOptions.put("gameOverMessage", "You have captured all of "
//                            + currentGame.getRedPlayer().getName()
//                            + "'s pieces. Congratulations, you win!");
//                    vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
//                    currentGame.getWhitePlayer().leaveGame();
//                    currentSession.attribute(GetGameRoute.GAME_ID_ATTR, null);
//                }
//            }
//
//            gameCenter.addToPreviousGames(currentGame, gameId);
//        }



        // render the View
        return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
    }

}
