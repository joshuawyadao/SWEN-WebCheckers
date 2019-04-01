package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import javafx.geometry.Pos;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

public class PostCheckTurnRoute implements Route {

    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;
    private Gson gson;

    public PostCheckTurnRoute(GameCenter gameCenter, Gson gson, PlayerLobby playerLobby){
        this.gameCenter = Objects.requireNonNull(gameCenter, "GameCenter is required.");
        this.gson = Objects.requireNonNull(gson, "gson is required");
        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required.");

        LOG.config("PostCheckTurnRoute is initialized");
    }

    @Override
    public Object handle(Request request, Response response){
        LOG.config("PostCheckTurnRoute is invoked.");

        // Create vm
        Map<String, Object> vm = new HashMap<>();

        // Request player
        Session currentSession = request.session();
        Player currentUser = currentSession.attribute("currentUser");

        // Get Game
        String gameId = currentSession.attribute(GetGameRoute.GAME_ID_ATTR);
        Game currentGame = gameCenter.getGame(gameId);

        // Get gson
        String JSONTurnString = createCorrectJSONFormat( gson.toJson(request.queryParams(gameId)) );

        System.out.println("\t\tCorrect JSON String: " + JSONTurnString);

        final Map<String, Object> modeOptions = new HashMap<>(2);
        modeOptions.put("isGameOver",true);
        modeOptions.put("gameOverMessage", Message.info("GAME OVER"));
        vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));

        if(currentGame.getRedPlayer() == null ||
            currentGame.getWhitePlayer() == null ){

            response.redirect(WebServer.GAME_URL);
            halt();
            return gson.toJson( Message.info("Opponent resigned"));
        }

        if(currentGame.getActivePlayer().equals(currentUser)){
            response.redirect(WebServer.GAME_URL);
            halt();
            return gson.toJson( Message.info("true") );
        } else if( !(currentGame.getActivePlayer().equals(currentUser)) ) {
            return gson.toJson( Message.info("false") );
        }


        return gson.toJson(Message.info("false"));
    }



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
