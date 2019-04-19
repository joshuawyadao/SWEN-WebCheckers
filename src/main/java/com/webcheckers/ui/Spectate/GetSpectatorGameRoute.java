package com.webcheckers.ui.Spectate;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.ui.BoardView;
import com.webcheckers.ui.Home.GetHomeRoute;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class GetSpectatorGameRoute implements Route {

    private static final Logger LOG = Logger.getLogger(GetSpectatorGameRoute.class.getName());

    private static final String VIEW_NAME = "game.ftl";

    private final TemplateEngine templateEngine;

    private final PlayerLobby playerLobby;

    private final GameCenter gameCenter;

    private final Gson gson;

    public GetSpectatorGameRoute(final TemplateEngine templateEngine, final GameCenter gameCenter
    , final PlayerLobby playerLobby, final Gson gson) {
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
        this.playerLobby = playerLobby;
        this.gson = gson;
        LOG.config("GetSpectatorGameRoute is initialized.");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetSpectatorGameRoute is invoked.");
        final Map<String, Object> vm = new HashMap<>();
        Session currentSession = request.session();
        Player currentUser = currentSession.attribute(GetHomeRoute.CURRENT_USER_ATTR);

//        Set<String> qp = request.queryParams();
//        for (String s : qp){
//            System.out.println(s);
//        }
        String gameID;
        if (currentSession.attribute("specID") == null) {
            gameID = request.queryParams("gameID");
            currentSession.attribute("specID", gameID);
        } else {
            gameID = currentSession.attribute("specID");
        }
        //System.out.println(gameID);

        Game gameToSpec = gameCenter.getGame(gameID);

        gameCenter.updateSpectator(gameID, currentUser);

        vm.put(GetHomeRoute.CURRENT_USER_ATTR, currentUser);
        vm.put("viewMode", GameCenter.ViewMode.SPECTATOR);
        vm.put("redPlayer", gameToSpec.getRedPlayer());
        vm.put("whitePlayer", gameToSpec.getWhitePlayer());
        vm.put("activeColor", gameToSpec.getActivePlayer().getPlayerColor());

        boolean isRed = true;
        if (currentSession.attribute("isRed") != null){
            isRed = currentSession.attribute("isRed");
        }
//        if (gameToSpec.getActivePlayer().equals(gameToSpec.getRedPlayer())) isRed = true;
//        else isRed = false;
        vm.put("board", new BoardView(gameToSpec.getSpectatorBoard(currentUser), isRed));

        if((gameToSpec.isResigned()) || (gameToSpec.completedGame() != null)){
            vm.put("message", Message.info(gameToSpec.getGameResult(currentUser)));
        }

        vm.put("title", "Enjoy watching your game!");
        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }
}
