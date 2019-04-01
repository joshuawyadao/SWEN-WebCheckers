package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PostSignInRouteTest {

    private PostSignInRoute CuT;

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

        this.CuT = new PostSignInRoute(playerLobby, engine);
    }

}