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
    void setup() {
        this.CuT = new GameCenter();
    }

    @Test
    void testNewGame() {
        Player player1 = new Player("redPlayer");
        Player player2 = new Player("whitePlayer");
        GameCenter.ViewMode viewMode = GameCenter.ViewMode.PLAY;

        assertEquals(GAME_ID_TEST, CuT.newGame(player1, player2) );
    }

    @Test
    void testGetGame() {
        Player player1 = new Player("redPlayer");
        Player player2 = new Player("whitePlayer");
        GameCenter.ViewMode viewMode = GameCenter.ViewMode.PLAY;

        CuT.newGame(player1, player2);

        assertSame(player1, CuT.getGame(GAME_ID_TEST).getRedPlayer());
        assertSame(player2, CuT.getGame(GAME_ID_TEST).getWhitePlayer());
    }

    @Test
    void testRedPlayerHasGame() {
        Player player1 = new Player("redPlayer");
        Player player2 = new Player("whitePlayer");
        GameCenter.ViewMode viewMode = GameCenter.ViewMode.PLAY;

        CuT.newGame(player1, player2);

        assertTrue( CuT.hasGame(player1, player2) );

    }

    @Test
    void testWhitePlayerHasGame(){
        Player player1 = new Player("redPlayer");
        Player player2 = new Player("whitePlayer");
        GameCenter.ViewMode viewMode = GameCenter.ViewMode.PLAY;

        CuT.newGame(player1, player2);

        assertTrue( CuT.hasGame(player1, player2) );
    }

    @Test
    void testDoesNotHasGame(){
        Player player1 = new Player("redPlayer");
        assertFalse( CuT.hasGame(player1, null) );
    }

    @Test
    void testRequestMove() {
        Player player1 = new Player("redPlayer");
        Player player2 = new Player("whitePlayer");

        String cutGame = CuT.newGame(player1, player2);

        Move move = new Move( new Position(2,1), new Position(3,2) );

        assertTrue(CuT.requestMove(cutGame, move));
    }

    @Test
    void testSubmitTurn() {
        Player player1 = new Player("redPlayer");
        Player player2 = new Player("whitePlayer");

        String cutGame = CuT.newGame(player1, player2);

        assertTrue(CuT.submitTurn(cutGame));
    }

    @Test
    void testBackupMove() {
        Player player1 = new Player("redPlayer");
        Player player2 = new Player("whitePlayer");

        String cutGame = CuT.newGame(player1, player2);

        assertTrue(CuT.backupMove(cutGame));
    }

    @Test
    void testIsMyTurnRED() {
        Player player1 = new Player("redPlayer");
        Player player2 = new Player("whitePlayer");

        CuT.newGame(player1, player2);

        assertSame(player1, CuT.getGame(GAME_ID_TEST).getRedPlayer());
        assertSame(player2, CuT.getGame(GAME_ID_TEST).getWhitePlayer());

        assertTrue( CuT.isMyTurn(GAME_ID_TEST, player1));
        assertFalse( CuT.isMyTurn(GAME_ID_TEST, player2));
    }

    @Test
    void testIsMyTurnWHITE() {
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
    void testRemoveGame() {
        Player player1 = new Player("redPlayer");
        Player player2 = new Player("whitePlayer");

        CuT.newGame(player1, player2);

        assertSame(player1, CuT.getGame(GAME_ID_TEST).getRedPlayer());
        assertSame(player2, CuT.getGame(GAME_ID_TEST).getWhitePlayer());

        CuT.removeGame(GAME_ID_TEST);

        assertNull(CuT.getGame(GAME_ID_TEST));
    }

    @Test
    void testIsInAnyGame() {

        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        Player p3 = new Player("p3");
        Player p4 = new Player("p4");
        Player p5 = new Player("p5");
        Player p6 = new Player("p6");

        Game game0 = new Game(p1, p2, "1");
        Game game1 = new Game(p3, p4, "2");
        Game game2 = new Game(p5, p6, "3");

        

    }

    @Test
    void testIsMyTurn() {

    }

    @Test
    void testAddToPreviousGames() {

    }

    @Test
    void testHasPreviousGames() {

    }

    @Test
    void testGetCurrentGames() {

    }

    @Test
    void sortPreviousGames() {

    }



}