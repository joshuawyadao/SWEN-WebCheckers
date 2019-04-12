package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PostCheckTurnRouteTest {

    private PostCheckTurnRoute CuT;

    private Request request;
    private Session session;
    private PlayerLobby playerLobby;
    private TemplateEngine engine;
    private Response response;
    private GameCenter gameCenter;
    private Gson gson;

    @BeforeEach
    void setUp() {
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        playerLobby = new PlayerLobby();
        gameCenter = new GameCenter();
        engine = mock(TemplateEngine.class);
        gson = new Gson();

        this.CuT = new PostCheckTurnRoute(gameCenter, gson, playerLobby);
    }

    @Test
    void testCheckTurn(){

        final TemplateEngineTest testHelper = new TemplateEngineTest();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        when(session.attribute("currentUser")).thenReturn(new Player("currentUser"));
        when(session.attribute(GetGameRoute.GAME_ID_ATTR)).thenReturn("gameId");

        CuT.handle(request, response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();


    }

}