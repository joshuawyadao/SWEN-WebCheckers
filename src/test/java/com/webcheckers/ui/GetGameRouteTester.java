package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import javafx.print.PageLayout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@Tag("UI-tier")
public class GetGameRouteTester {
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

        CuT = new GetGameRoute(lobby, engine);
    }

    @Test
    public void testLoadingSigninPage(){
        final TemplateEngineTester testHelper = new TemplateEngineTester();
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
