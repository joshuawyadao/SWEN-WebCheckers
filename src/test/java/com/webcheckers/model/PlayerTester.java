package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tester for {@link Player}
 */
@Tag("Model-tier")
public class PlayerTester {

    @Test
    public void testPlayerNullity() {
        final Player CuT = new Player("TestPlayer");

        assertEquals("TestPlayer", CuT.getName(), "TestPlayer name is set correctly");
        assertEquals(Player.PlayerColor.NONE, CuT.getPlayerColor(), "TestPlayer has no color");
        assertFalse(CuT.isPlaying(), "TestPlayer is not playing a game");
        assertNotNull(CuT, "TestPlayer is not null");
    }

    @Test
    public void testGetName() {
        final Player CuT = new Player("TestPlayer");

        assertEquals("TestPlayer", CuT.getName(), "TestPlayer name is set correctly");
    }

    @Test
    public void testJoinGameNone() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.NONE);

        assertEquals(Player.PlayerColor.NONE, CuT.getPlayerColor(), "TestPlayer joined game correctly");
        assertFalse(CuT.isPlaying(), "TestPlayer is not playing");
    }

    @Test
    public void testJoinGameRed() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.RED);

        assertEquals(Player.PlayerColor.RED, CuT.getPlayerColor(), "TestPlayer joined game correctly");
        assertTrue(CuT.isPlaying(), "TestPlayer is playing");
    }

    @Test
    public void testJoinGameWhite() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.WHITE);

        assertEquals(Player.PlayerColor.WHITE, CuT.getPlayerColor(), "TestPlayer joined game correctly");
        assertTrue(CuT.isPlaying(), "TestPlayer is playing");
    }

    @Test
    public void testLeaveGameNone() {
        final Player CuT = new Player("TestPlayer");

        CuT.leaveGame();

        assertEquals(Player.PlayerColor.NONE, CuT.getPlayerColor(),"TestPlayer has no color");
        assertFalse(CuT.isPlaying(), "TestPlayer is not playing");
    }

    @Test
    public void testLeaveGameRed() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.RED);
        CuT.leaveGame();

        assertEquals(Player.PlayerColor.NONE, CuT.getPlayerColor(),"TestPlayer has no color");
        assertFalse(CuT.isPlaying(), "TestPlayer is not playing");
    }

    @Test
    public void testLeaveGameWhite() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.WHITE);
        CuT.leaveGame();

        assertEquals(Player.PlayerColor.NONE, CuT.getPlayerColor(),"TestPlayer has no color");
        assertFalse(CuT.isPlaying(), "TestPlayer is not playing");
    }

    @Test
    public void testGetPlayerColorNone() {
        final Player CuT = new Player("TestPlayer");

        assertEquals(Player.PlayerColor.NONE, CuT.getPlayerColor(), "TestPlayer has no color");
    }

    @Test
    public void testGetPlayerColorRed() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.RED);

        assertEquals(Player.PlayerColor.RED, CuT.getPlayerColor(), "TestPlayer has no color");
    }

    @Test
    public void testGetPlayerColorWhite() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.WHITE);

        assertEquals(Player.PlayerColor.WHITE, CuT.getPlayerColor(), "TestPlayer has no color");
    }

    @Test
    public void testIsPlayingNone() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.NONE);

        assertFalse(CuT.isPlaying(), "TestPlayer is playing");
    }

    @Test
    public void testIsPlayingRed() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.RED);

        assertTrue(CuT.isPlaying(), "TestPlayer is playing");
    }

    @Test
    public void testIsPlayingWhite() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.WHITE);

        assertTrue(CuT.isPlaying(), "TestPlayer is playing");
    }

    @Test
    public void testIsNotPlaying() {
        final Player CuT = new Player("TestPlayer");

        assertFalse(CuT.isPlaying(), "TestPlayer is not playing");
    }

    @Test
    public void testEqualsSameObject() {
        final Player CuT = new Player("TestPlayer");

        assertTrue(CuT.equals(CuT), "TestPlayer is the same as TestPlayer");
    }

    @Test
    public void testEqualsNotSameObject() {
        final Player CuT = new Player("TestPlayer");
        final int num = 1;

        assertFalse(CuT.equals(num), "TestPlayer is not the same as int");
    }

    @Test
    public void testEqualsSameName() {
        final Player CuT = new Player("TestPlayer");
        final Player CuT2 = new Player("TestPlayer");

        assertTrue(CuT.equals(CuT2), "TestPlayer is the same as TestPlayer");
    }

    @Test
    public void testHashCodeNone() {
        final Player CuT = new Player("TestPlayer");

        assertEquals(CuT.getName().hashCode() +
                CuT.getPlayerColor().hashCode(), CuT.hashCode(), "TestPlayer hashCode works");
    }

    @Test
    public void testHashCodeRed() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.RED);

        assertEquals(CuT.getName().hashCode() +
                CuT.getPlayerColor().hashCode() + 1, CuT.hashCode(), "TestPlayer hashCode works");
    }

    @Test
    public void testHashCodeWhite() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.WHITE);

        assertEquals(CuT.getName().hashCode() +
                CuT.getPlayerColor().hashCode() + 1, CuT.hashCode(), "TestPlayer hashCode works");
    }


}
