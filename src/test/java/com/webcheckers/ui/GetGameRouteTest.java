package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@Tag("UI-tier")
public class GetGameRouteTest {

    /**
     * The component-under-test (CuT).
     *
     */
    private GetGameRoute CuT;

    // mock objects
    private Request request;
    private Session session;
    private PlayerLobby lobby;
    private TemplateEngine engine;
    private Response response;
    private GameCenter gameCenter;

    /**
     * Setup new mock objects for each test.
     */
    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);
        lobby = mock(PlayerLobby.class);
        gameCenter = mock(GameCenter.class);

        CuT = new GetGameRoute(lobby, gameCenter, engine);
    }

    /**
     * Testing aspects of the GetGameRoute class
     * Tested:
     *      ViewModel: Non-NULL (Object)
     *      ViewModel is a map: Map<String, Object> (boolean)
     *      View_Name: "game.ftl" (String)
     *
     */
    @Test
    public void testLoadingGamePage(){
        final TemplateEngineTest testHelper = new TemplateEngineTest();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test
        CuT.handle(request, response);

        // Analyze the results:
        //   * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        //   * test view name
        testHelper.assertViewName(GetGameRoute.VIEW_NAME);
    }
}
