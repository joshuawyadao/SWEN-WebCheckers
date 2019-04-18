package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReplayGameTest {

    private ArrayList<Board> finishedGames;
    private Player redPlayer, whitePlayer;
    private Player.PlayerColor activeColor;
    private int currentTurn;
    private String gameId;
    private ReplayGame CuT;

    @BeforeEach
    void setUp() {
        this.finishedGames = new ArrayList<>();
        this.redPlayer = new Player("red");
        this.whitePlayer = new Player("white");
        this.activeColor = Player.PlayerColor.RED;
        this.currentTurn = 0;
        this.gameId = "CuT";
        this.CuT = new ReplayGame(redPlayer, whitePlayer, finishedGames, gameId);
    }

    @Test
    void getActiveColor() {
        assertSame( Player.PlayerColor.RED, CuT.getActiveColor() );
    }

    @Test
    void getRedPlayer() {
        assertSame( redPlayer, CuT.getRedPlayer() );
    }

    @Test
    void getWhitePlayer() {
        assertSame(whitePlayer, CuT.getWhitePlayer() );
    }

    @Test
    void getGameId() {

    }

    @Test
    void hasPrevious() {
        Board board1 = new Board();
        Board board2 = new Board();
        Board board3 = new Board();

        finishedGames.add(board1);
        finishedGames.add(board2);
        finishedGames.add(board3);

        assertFalse(CuT.hasPrevious());

        CuT.getNextTurn();

        assertTrue(CuT.hasPrevious());
    }

    @Test
    void hasNext() {
        Board board1 = new Board();
        Board board2 = new Board();
        Board board3 = new Board();

        finishedGames.add(board1);
        finishedGames.add(board2);
        finishedGames.add(board3);

        assertTrue(CuT.hasNext());

        CuT.getNextTurn();

        assertTrue(CuT.hasNext());

        CuT.getNextTurn();

        assertFalse(CuT.hasNext());
    }

    @Test
    void getPreviousTurn() {
        Board board1 = new Board();
        Board board2 = new Board();
        Board board3 = new Board();

        finishedGames.add(board1);
        finishedGames.add(board2);
        finishedGames.add(board3);

        assertEquals(board1, CuT.getCurrentTurn());

        CuT.getNextTurn(); // 0 -> 1
        assertEquals(Player.PlayerColor.WHITE, CuT.getActiveColor());

        assertTrue(CuT.hasPrevious());
        assertSame(board1, CuT.getPreviousTurn());

        CuT.getNextTurn(); // 1 -> 2
        assertEquals(board2, CuT.getCurrentTurn());

        CuT.getNextTurn(); // 2 -> 3
        assertEquals(Player.PlayerColor.RED, CuT.getActiveColor());

        assertTrue(CuT.hasPrevious());
        assertSame(board2, CuT.getPreviousTurn());

    }

    @Test
    void getCurrentTurn() {
        Board board0 = new Board();

        finishedGames.add(board0);

        assertSame(board0, CuT.getCurrentTurn());
    }

    @Test
    void getNextTurn() {
        Board board0 = new Board();
        Board board1 = new Board();
        Board board2 = new Board();

        finishedGames.add(board0);
        finishedGames.add(board1);
        finishedGames.add(board2);

        assertSame(board0, CuT.getCurrentTurn());

        CuT.getNextTurn();
        assertSame(board1, CuT.getCurrentTurn());
        assertEquals(Player.PlayerColor.WHITE, CuT.getActiveColor());

        CuT.getNextTurn();
        assertSame(board2, CuT.getCurrentTurn());
        assertEquals(Player.PlayerColor.RED, CuT.getActiveColor());


    }


}