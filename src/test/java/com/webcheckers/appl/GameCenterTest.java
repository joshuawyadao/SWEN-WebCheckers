package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@Tag("Application-tier")
class GameCenterTest {

    private GameCenter CuT;

    private String GAME_ID_TEST = "redPlayerVswhitePlayer";

    @BeforeEach
    public void setup() {
        this.CuT = new GameCenter();
    }

    @Test
    public void newGame() {
        Player player1 = new Player("redPlayer");
        Player player2 = new Player("whitePlayer");
        Game.ViewMode viewMode = Game.ViewMode.PLAY;

        assertEquals(GAME_ID_TEST, CuT.newGame(player1, player2, viewMode) );
    }

    @Test
    void getGame() {
        Player player1 = new Player("redPlayer");
        Player player2 = new Player("whitePlayer");
        Game.ViewMode viewMode = Game.ViewMode.PLAY;

        CuT.newGame(player1, player2, viewMode);

        assertSame(player1, CuT.getGame(GAME_ID_TEST).getRedPlayer());
        assertSame(player2, CuT.getGame(GAME_ID_TEST).getWhitePlayer());
    }

    @Test
    void requestMove() {
    }

    @Test
    void submitTurn() {
    }

    @Test
    void backupMove() {
    }

    @Test
    void hasGame() {
        Player player1 = new Player("redPlayer");
        Player player2 = new Player("whitePlayer");
        Game.ViewMode viewMode = Game.ViewMode.PLAY;

        CuT.newGame(player1, player2, viewMode);

        assertTrue( CuT.hasGame(player1) );
        assertTrue( CuT.hasGame(player2) );

    }

    @Test
    void doesNotHasGame(){
        Player player1 = new Player("redPlayer");
        assertFalse( CuT.hasGame(player1) );
    }
}