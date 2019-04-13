package com.webcheckers.ui.Replay;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Board;
import com.webcheckers.model.Player;
import com.webcheckers.model.ReplayGame;
import com.webcheckers.ui.Home.GetHomeRoute;
import com.webcheckers.ui.PlayGame.GetGameRoute;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class PostReplayPreviousTurnRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostReplayPreviousTurnRoute.class.getName());

    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;
    private final TemplateEngine templateEngine;
    private final Gson gson;

    public PostReplayPreviousTurnRoute(final PlayerLobby playerLobby, final GameCenter gameCenter, final TemplateEngine templateEngine, final Gson gson) {
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gson = gson;
    }

    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("PostReplayPreviousTurnRoute is invoked.");
        Map<String, Object> vm = new HashMap<>();
        Session currentSession = request.session();

        Player currentUser = currentSession.attribute(GetHomeRoute.CURRENT_USER_ATTR);
        String gameId = currentSession.attribute(GetReplayGameRoute.GAME_ID_ATTR);

        //TODO: MOVE THE GAME TO THE PREVIOUS STATE

        ReplayGame replayGame = this.gameCenter.getReplayGame(gameId);
        Board nextTurn = replayGame.getPreviousTurn();

        // If clicking next button was succesful
        Message successInfo;

        if( nextTurn != null ){
            successInfo = Message.info("true");
        }else{
            successInfo = Message.info("false");
        }

        return gson.toJson(successInfo);
    }
}
