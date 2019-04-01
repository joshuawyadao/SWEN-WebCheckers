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
    void getRecentTurn() {

    }

    @Test
    void getPlayerColor() {
        assertEquals(Player.PlayerColor.NONE, CuT.getPlayerColor(redPlayer));
    }

    @Test
    void initializeGame() {
    }

    @Test
    void makeMove() {

        Board board = CuT.getCheckerBoard();

        Position src = new Position(0,0);
        Position dst = new Position(1,1);

        //assertTrue( CuT.makeMove(CuT.getRedPlayer(), src, dst) );

    }

    @Test
    void submitTurn() {
    }

    @Test
    void backup() {
    }

    @Test
    void completedGame() {
    }

    @Test
    void playerResigned(){
        CuT.initializeGame();
        CuT.playerResigned(redPlayer);
        assertTrue(CuT.isResigned());
    }

    @Test
    void isResigned(){
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