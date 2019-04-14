package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import com.webcheckers.model.Position;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
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
    public void testNewGame() {
        Player player1 = new Player("redPlayer");
        Player player2 = new Player("whitePlayer");
        GameCenter.ViewMode viewMode = GameCenter.ViewMode.PLAY;

        assertEquals(GAME_ID_TEST, CuT.newGame(player1, player2) );
    }

    @Test
    public void testGetGame() {
        Player player1 = new Player("redPlayer");
        Player player2 = new Player("whitePlayer");
        GameCenter.ViewMode viewMode = GameCenter.ViewMode.PLAY;

        CuT.newGame(player1, player2);

        assertSame(player1, CuT.getGame(GAME_ID_TEST).getRedPlayer());
        assertSame(player2, CuT.getGame(GAME_ID_TEST).getWhitePlayer());
    }

    @Test
    public void testRedPlayerHasGame() {
        Player player1 = new Player("redPlayer");
        Player player2 = new Player("whitePlayer");
        GameCenter.ViewMode viewMode = GameCenter.ViewMode.PLAY;

        CuT.newGame(player1, player2);

        assertTrue( CuT.hasGame(player1, player2) );

    }

    @Test
    public void testWhitePlayerHasGame(){
        Player player1 = new Player("redPlayer");
        Player player2 = new Player("whitePlayer");
        GameCenter.ViewMode viewMode = GameCenter.ViewMode.PLAY;

        CuT.newGame(player1, player2);

        assertTrue( CuT.hasGame(player1, player2) );
    }

    @Test
    public void testDoesNotHasGame(){
        Player player1 = new Player("redPlayer");
        assertFalse( CuT.hasGame(player1, null) );
    }

    @Test
    public void testRequestMove() {
        Player player1 = new Player("redPlayer");
        Player player2 = new Player("whitePlayer");

        String cutGame = CuT.newGame(player1, player2);

        Move move = new Move( new Position(2,1), new Position(3,2) );

        assertTrue(CuT.requestMove(cutGame, move));
    }

    @Test
    public void testSubmitTurn() {
        Player player1 = new Player("redPlayer");
        Player player2 = new Player("whitePlayer");

        String cutGame = CuT.newGame(player1, player2);

        assertTrue(CuT.submitTurn(cutGame));
    }

    @Test
    public void testBackupMove() {
        Player player1 = new Player("redPlayer");
        Player player2 = new Player("whitePlayer");

        String cutGame = CuT.newGame(player1, player2);

        assertTrue(CuT.backupMove(cutGame));
    }

    @Test
    public void testIsMyTurnRED() {
        Player player1 = new Player("redPlayer");
        Player player2 = new Player("whitePlayer");

        CuT.newGame(player1, player2);

        assertSame(player1, CuT.getGame(GAME_ID_TEST).getRedPlayer());
        assertSame(player2, CuT.getGame(GAME_ID_TEST).getWhitePlayer());

        assertTrue( CuT.isMyTurn(GAME_ID_TEST, player1));
        assertFalse( CuT.isMyTurn(GAME_ID_TEST, player2));
    }

    @Test
    public void testIsMyTurnWHITE() {
        Player player1 = new Player("redPlayer");
        Player player2 = new Player("whitePlayer");

        CuT.newGame(player1, player2);

        assertSame(player1, CuT.getGame(GAME_ID_TEST).getRedPlayer());
        assertSame(player2, CuT.getGame(GAME_ID_TEST).getWhitePlayer());

        CuT.getGame(GAME_ID_TEST).submitTurn();

        assertFalse( CuT.isMyTurn(GAME_ID_TEST, player1));
        assertTrue( CuT.isMyTurn(GAME_ID_TEST, player2));
    }

    @Test
    public void testRemoveGame() {
        Player player1 = new Player("redPlayer");
        Player player2 = new Player("whitePlayer");

        CuT.newGame(player1, player2);

        assertSame(player1, CuT.getGame(GAME_ID_TEST).getRedPlayer());
        assertSame(player2, CuT.getGame(GAME_ID_TEST).getWhitePlayer());

        CuT.removeGame(GAME_ID_TEST);

        assertNull(CuT.getGame(GAME_ID_TEST));
    }



}