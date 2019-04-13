package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.ui.PlayGame.PostResignGameRoute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PostResignGameRouteTest {

    private PostResignGameRoute CuT;

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

        this.CuT = new PostResignGameRoute(gson, gameCenter, engine, playerLobby);
    }

    @Test
    void testResignGame(){

    }

}