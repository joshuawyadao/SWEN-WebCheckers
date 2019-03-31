package com.webcheckers.model;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.ui.BoardView;
import com.webcheckers.ui.Row;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Session;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@Tag("UI-tier")
public class BoardViewTest {
    private final static int TEST_MAX_ROWS = 8;

    private BoardView CuT;

    @Test
    public void testCreateBoardView(){
        final boolean isRed = true;
        Board testBoard = new Board();
        CuT = new BoardView(testBoard, isRed);

        assertNotNull(CuT, "BoardView created unsuccessfully.");
    }

}
