package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Board;
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
    public void testCreateBoardView_Red(){
        final boolean isRed = true;
        final Board testBoard = new Board();
        CuT = new BoardView(testBoard, isRed);

        assertNotNull(CuT, "BoardView created unsuccessfully.");
    }

    @Test
    public void testCreateBoardView_White(){
        final boolean isRed = false;
        final Board testBoard = new Board();
        CuT = new BoardView(testBoard, isRed);

        assertNotNull(CuT, "BoardView created unsuccessfully.");
    }

    @Test
    public void testIteratorNotNull(){
        final boolean isRed = false;
        final Board testBoard = new Board();
        CuT = new BoardView(testBoard, isRed);

        assertNotNull(CuT.iterator(), "BoardView Iterator created unsuccessfully.");
    }

}
