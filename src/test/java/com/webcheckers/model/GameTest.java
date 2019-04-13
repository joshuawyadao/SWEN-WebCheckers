package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Player redPlayer;
    private Player whitePlayer;
    private Game CuT;

    @BeforeEach
    void setUp(){
        this.redPlayer = new Player("red");
        this.whitePlayer = new Player("white");
        Game.ViewMode viewMode = Game.ViewMode.PLAY;
        CuT = new Game(redPlayer,whitePlayer,viewMode);
    }

    @Test
    void getRedPlayer() {
        assertSame( redPlayer, CuT.getRedPlayer() );
    }

    @Test
    void getWhitePlayer() {
        assertSame( whitePlayer, CuT.getWhitePlayer() );
    }

    @Test
    void getViewMode() {
        assertEquals( Game.ViewMode.PLAY, CuT.getViewMode() );
    }

    @Test
    void getCheckerBoard() {
        assertNotNull( CuT.getCheckerBoard() );
    }

    @Test
    void getActivePlayer() {
        assertSame( redPlayer, CuT.getActivePlayer() );
    }

    @Test
    void getPlayerColor() {
        assertEquals(Player.PlayerColor.NONE, CuT.getPlayerColor(redPlayer));
    }

    @Test
    void initializeGame() {
        assertEquals( Player.PlayerColor.NONE, CuT.getRedPlayer().getPlayerColor());
        assertEquals( Player.PlayerColor.NONE, CuT.getWhitePlayer().getPlayerColor());
        CuT.initializeGame();
        assertNotNull(CuT.getRedPlayer().getPlayerColor());
        assertNotNull(CuT.getWhitePlayer().getPlayerColor());
    }

    @Test
    void makeMove() {
        Position src = new Position(2,1);
        Position dst = new Position(3,0);

        Move move = new Move(src, dst);

        assertTrue( CuT.makeMove(redPlayer, move) );

    }

    @Test
    void validateTurn() {
        assertTrue( CuT.validateTurn() );
    }

    @Test
    void completedGame() {
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        Board testBoard = new Board(false);

        Player winner = CuT.completedGame();
        assertNull(winner);

    }

    @Test
    void playerResigned(){
        CuT.initializeGame();
        CuT.playerResigned(redPlayer);
        assertTrue(CuT.isResigned());
    }

    @Test
    void isResigned(){
        assertFalse(CuT.isResigned());
        CuT.initializeGame();
        CuT.playerResigned(redPlayer);
        assertTrue(CuT.isResigned());
    }

    @Test
    void getResignedPlayer(){
        CuT.initializeGame();
        Player subRed = CuT.getRedPlayer();

        CuT.playerResigned(redPlayer);
        assertSame( subRed, CuT.getResignedPlayer() );
    }
}