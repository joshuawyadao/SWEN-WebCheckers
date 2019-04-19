package com.webcheckers.ui.Spectate;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.ui.Home.GetHomeRoute;
import com.webcheckers.util.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PostSpectatorCheckTurnRouteTest {
    /**
     * The component-under-test (CuT).
     *
     */
    private PostSpectatorCheckTurnRoute CuT;

    // mock objects
    private Request request;
    private Session session;
    private final GameCenter gameCenter = new GameCenter();
    private final Gson gson = new Gson();
    private Response response;
    private Player spectator;
    private String gameId;

    /**
     * Setup new mock objects for each test.
     */
    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        spectator = mock(Player.class);
        gameId = gameCenter.newGame(new Player("test1"), new Player("test2"));

        when(session.attribute(GetHomeRoute.CURRENT_USER_ATTR)).thenReturn(spectator);
        when(request.queryParams("gameID")).thenReturn(gameId);

        gameCenter.updateSpectator(gameId, spectator);

        CuT = new PostSpectatorCheckTurnRoute(gameCenter, gson);
    }

    @Test
    public void testNeedUpdate(){
//        when(gameCenter.isSpectatorUpdated(gameId, mock)).thenReturn(false);

        // Invoke the test
        assertEquals(gson.toJson(Message.info("false")), CuT.handle(request, response));
    }

    @Test
    void handle() {
    }
}