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
        final Map<String, Object> modeOptions = new HashMap<>(2);
        Session currentSession = request.session();
        Player currentUser = currentSession.attribute(GetHomeRoute.CURRENT_USER_ATTR);

        if(currentSession.attribute(GAME_ID_ATTR) == null){
            String opponentName  = request.queryParams(OPPONENT_NAME);
            Session opponentSession = playerLobby.getPlayerSessionByName(opponentName);
            Player opponent = opponentSession.attribute(GetHomeRoute.CURRENT_USER_ATTR);

            //
            //
            if (gameCenter.hasGame(opponent)) {
                // FIX: Error output message

                //Message message = new Message(" GOT ERROR ", Message.Type.ERROR);

                Message message = Message.error("GOT ERROR");
                message = Message.info("GOT ERROR");
                vm.put("message", message);

                // Home.ftl requirements
                vm.put(GetHomeRoute.CURRENT_USER_ATTR, currentUser);
                vm.put("title", "Welcome!");
                vm.put("players", playerLobby.getPlayers());

                response.redirect(WebServer.HOME_URL);
                halt();
                return null;

            } else {
                String newGameId = gameCenter.newGame(currentUser, opponent, Game.ViewMode.PLAY);
                currentSession.attribute(GAME_ID_ATTR, newGameId);
                opponentSession.attribute(GAME_ID_ATTR, newGameId);
            }
            //
            //

        }

        String gameId = currentSession.attribute(GAME_ID_ATTR);
        Game currentGame = gameCenter.getGame(gameId);


        vm.put("redPlayer", currentGame.getRedPlayer());
        vm.put("whitePlayer", currentGame.getWhitePlayer());
        vm.put("activeColor", currentGame.getActivePlayer().getPlayerColor());
        vm.put("viewMode", currentGame.getViewMode());

        boolean isRed = currentGame.getPlayerColor(currentUser) == Player.PlayerColor.RED;
        BoardView boardView = new BoardView(currentGame.getCheckerBoard(), isRed);
        vm.put("board", boardView);

        if (currentGame.isResigned()){
            modeOptions.put("isGameOver", true);

            if (currentGame.getResignedPlayer().equals(currentUser)){
                // display a user message in the Home page
                vm.put("message", GetHomeRoute.WELCOME_MSG);
                currentUser.leaveGame();
                currentSession.attribute(GetGameRoute.GAME_ID_ATTR, null);
                response.redirect(WebServer.HOME_URL);
                halt();
                return null;
            } else {
                if (isRed){
                    modeOptions.put("gameOverMessage", currentGame.getWhitePlayer().getName() + " has resigned.");
                    vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
                    currentUser.leaveGame();
                    currentSession.attribute(GetGameRoute.GAME_ID_ATTR, null);
                    gameCenter.removeGame(gameId);
                } else{
                    modeOptions.put("gameOverMessage", currentGame.getRedPlayer().getName() + " has resigned.");
                    vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
                    currentUser.leaveGame();
                    currentSession.attribute(GetGameRoute.GAME_ID_ATTR, null);
                    gameCenter.removeGame(gameId);
                }
            }
        }

        //boolean testBool = true;
        //line below is conditional to use instead of testBool
        //currentGame.getCheckerBoard().finishedGame()
        if (currentGame.getCheckerBoard().finishedGame()){
            Player winner = currentGame.completedGame();

            //Player winner = currentGame.getWhitePlayer();
            modeOptions.put("isGameOver", true);
            if (winner == currentGame.getRedPlayer()){
                if (currentGame.getRedPlayer().equals(currentUser)){
                    modeOptions.put("gameOverMessage", "You have captured all of "
                            + currentGame.getWhitePlayer().getName()
                            + "'s pieces. Congratulations, you win!");
                    vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
                    currentGame.getRedPlayer().leaveGame();
                    currentSession.attribute(GetGameRoute.GAME_ID_ATTR, null);
                } else{
                    modeOptions.put("gameOverMessage", currentGame.getRedPlayer().getName()
                            + " has captured all of your pieces. You lose.");
                    vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
                    currentGame.getWhitePlayer().leaveGame();
                    currentSession.attribute(GetGameRoute.GAME_ID_ATTR, null);
                }
            } else{
                if (currentGame.getRedPlayer().equals(currentUser)){
                    modeOptions.put("gameOverMessage", currentGame.getWhitePlayer().getName()
                            + " has captured all of your pieces. You lose.");
                    vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
                    currentGame.getRedPlayer().leaveGame();
                    currentSession.attribute(GetGameRoute.GAME_ID_ATTR, null);
                } else{
                    modeOptions.put("gameOverMessage", "You have captured all of "
                            + currentGame.getRedPlayer().getName()
                            + "'s pieces. Congratulations, you win!");
                    vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
                    currentGame.getWhitePlayer().leaveGame();
                    currentSession.attribute(GetGameRoute.GAME_ID_ATTR, null);
                }
            }
            gameCenter.removeGame(gameId);
        }

        vm.put(GetHomeRoute.CURRENT_USER_ATTR, currentUser);
        vm.put("title", "Enjoy Your Game!");

        // render the View
        return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
    }

}
