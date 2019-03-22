package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tester for {@link Player}
 */
@Tag("Model-tier")
public class PlayerTester {

    /**
     * Testing creation of a player all of the aspects of the player for non-NULL values
     * Tested:
     *      name: NULL (String)
     *      playerColor: NULL (PlayerColor)
     *      isPlaying: false (boolean)
     *      CuT: not-NULL (Object)
     */
    @Test
    public void testPlayerNullity() {
        final Player CuT = new Player("TestPlayer");

        assertEquals("TestPlayer", CuT.getName(), "TestPlayer name is set correctly");
        assertEquals(Player.PlayerColor.NONE, CuT.getPlayerColor(), "TestPlayer has no color");
        assertFalse(CuT.isPlaying(), "TestPlayer is not playing a game");
        assertNotNull(CuT, "TestPlayer is not null");
    }

    /**
     * Testing if the name of a player without a space has been saved correctly
     * Tested:
     *      name: "TestPlayer" (String)
     */
    @Test
    public void testGetNameNoSpace() {
        final Player CuT = new Player("TestPlayer");

        assertEquals("TestPlayer", CuT.getName(), "TestPlayer name is set correctly");
    }

    /**
     * Testing if the name of a player with a space has been saved correctly
     * Tested:
     *      name: "Test Player" (String)
     */
    @Test
    public void testGetNameWithSpace() {
        final Player CuT = new Player("Test Player");

        assertEquals("Test Player", CuT.getName(), "Test Player name is set correctly");
    }

    /**
     * Testing all aspects of a NONE player joining a game
     * Tested:
     *      playerColor: NONE (PlayerColor)
     *      isPlaying: false (boolean)
     */
    @Test
    public void testJoinGameNone() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.NONE);

        assertEquals(Player.PlayerColor.NONE, CuT.getPlayerColor(), "TestPlayer (NONE) joined game correctly");
        assertFalse(CuT.isPlaying(), "TestPlayer (NONE) is not playing");
    }

    /**
     * Testing all aspects of a RED player joining a game
     * Tested:
     *      playerColor: RED (PlayerColor)
     *      isPlaying: true (boolean)
     */
    @Test
    public void testJoinGameRed() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.RED);

        assertEquals(Player.PlayerColor.RED, CuT.getPlayerColor(), "TestPlayer (RED) joined game correctly");
        assertTrue(CuT.isPlaying(), "TestPlayer (RED) is playing");
    }

    /**
     * Testing all aspects of a WHITE player joining a game
     * Tested:
     *      playerColor: WHITE (PlayerColor)
     *      isPlaying: true (boolean)
     */
    @Test
    public void testJoinGameWhite() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.WHITE);

        assertEquals(Player.PlayerColor.WHITE, CuT.getPlayerColor(), "TestPlayer (WHITE) joined game correctly");
        assertTrue(CuT.isPlaying(), "TestPlayer (WHITE) is playing");
    }

    /**
     * Testing all aspects of a NONE player leaving a game
     * Tested:
     *      playerColor: NONE (PlayerColor)
     *      isPlaying: false (boolean)
     */
    @Test
    public void testLeaveGameNone() {
        final Player CuT = new Player("TestPlayer");

        CuT.leaveGame();

        assertEquals(Player.PlayerColor.NONE, CuT.getPlayerColor(),"TestPlayer (NONE) has no color");
        assertFalse(CuT.isPlaying(), "TestPlayer (NONE) is not playing");
    }

    /**
     * Testing all aspects of a RED player leaving a game
     * Tested:
     *      playerColor: RED (PlayerColor)
     *      isPlaying: false (boolean)
     */
    @Test
    public void testLeaveGameRed() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.RED);
        CuT.leaveGame();

        assertEquals(Player.PlayerColor.NONE, CuT.getPlayerColor(),"TestPlayer (RED) has no color");
        assertFalse(CuT.isPlaying(), "TestPlayer (RED) is not playing");
    }

    /**
     * Testing all aspects of a WHITE player leaving a game
     * Tested:
     *      playerColor: WHITE (PlayerColor)
     *      isPlaying: false (boolean)
     */
    @Test
    public void testLeaveGameWhite() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.WHITE);
        CuT.leaveGame();

        assertEquals(Player.PlayerColor.NONE, CuT.getPlayerColor(),"TestPlayer (WHITE) has no color");
        assertFalse(CuT.isPlaying(), "TestPlayer (WHITE) is not playing");
    }

    /**
     * Testing if the name of a NONE player was set
     * Tested:
     *      playerColor: NONE (PlayerColor)
     */
    @Test
    public void testGetPlayerColorNone() {
        final Player CuT = new Player("TestPlayer");

        assertEquals(Player.PlayerColor.NONE, CuT.getPlayerColor(), "TestPlayer (NONE) has no color");
    }

    /**
     * Testing if the name of a RED player was set
     * Tested:
     *      playerColor: RED (PlayerColor)
     */
    @Test
    public void testGetPlayerColorRed() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.RED);

        assertEquals(Player.PlayerColor.RED, CuT.getPlayerColor(), "TestPlayer (RED) has no color");
    }

    /**
     * Testing if the name of a WHITE player was set
     * Tested:
     *      playerColor: WHITE (PlayerColor)
     */
    @Test
    public void testGetPlayerColorWhite() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.WHITE);

        assertEquals(Player.PlayerColor.WHITE, CuT.getPlayerColor(), "TestPlayer (WHITE) has no color");
    }

    /**
     * Testing if a NONE player can play a game
     * Tested:
     *      isPlaying: false (boolean)
     */
    @Test
    public void testIsPlayingNone() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.NONE);

        assertFalse(CuT.isPlaying(), "TestPlayer (NONE) is playing");
    }

    /**
     * Testing if a RED player can play a game
     * Tested:
     *      isPlaying: false (boolean)
     */
    @Test
    public void testIsPlayingRed() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.RED);

        assertTrue(CuT.isPlaying(), "TestPlayer (RED) is playing");
    }

    /**
     * Testing if a WHITE player can play a game
     * Tested:
     *      isPlaying: false (boolean)
     */
    @Test
    public void testIsPlayingWhite() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.WHITE);

        assertTrue(CuT.isPlaying(), "TestPlayer (WHITE) is playing");
    }

    /**
     * Testing if a player is not playing a game
     * Tested:
     *      isPlaying: false (boolean)
     */
    @Test
    public void testIsNotPlaying() {
        final Player CuT = new Player("TestPlayer");

        assertFalse(CuT.isPlaying(), "TestPlayer is not playing");
    }

    /**
     * Testing if two of the same exact objects are equal
     * Tested:
     *      equals: true (boolean)
     */
    @Test
    public void testEqualsSameObject() {
        final Player CuT = new Player("TestPlayer");

        assertTrue(CuT.equals(CuT), "TestPlayer is the same as TestPlayer");
    }

    /**
     * Testing if two different types of objects are equal
     * Tested:
     *      equals: false (boolean)
     */
    @Test
    public void testEqualsNotSameTypeObject() {
        final Player CuT = new Player("TestPlayer");
        final int num = 1;

        assertFalse(CuT.equals(num), "TestPlayer is not the same as int");
    }

    /**
     * Testing if two different NONE Player objects of the same name are equal
     * Tested:
     *      equals: true (boolean)
     */
    @Test
    public void testEqualsSameNameNONE() {
        final Player CuT = new Player("TestPlayer");
        final Player CuT2 = new Player("TestPlayer");

        assertTrue(CuT.equals(CuT2), "TestPlayer (NONE) is the same as TestPlayer (NONE)");
    }

    /**
     * Testing if two different RED Player objects of the same name are equal
     * Tested:
     *      equals: true (boolean)
     */
    @Test
    public void testEqualsSameNameRED() {
        final Player CuT = new Player("TestPlayer");
        final Player CuT2 = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.RED);


        assertTrue(CuT.equals(CuT2), "TestPlayer (RED) is the same as TestPlayer (RED)");
    }

    /**
     * Testing if two different RED Player objects of the same name are equal
     * Tested:
     *      equals: true (boolean)
     */
    @Test
    public void testEqualsSameNameWHITE() {
        final Player CuT = new Player("TestPlayer");
        final Player CuT2 = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.WHITE);


        assertTrue(CuT.equals(CuT2), "TestPlayer (WHITE) is the same as TestPlayer (WHITE)");
    }

    /**
     * Testing the hashCode of a NONE player
     * Tested:
     *      hashCode: name.hashCode() + playerColor.hashCode() (int)
     */
    @Test
    public void testHashCodeNone() {
        final Player CuT = new Player("TestPlayer");

        assertEquals(CuT.getName().hashCode() +
                CuT.getPlayerColor().hashCode(), CuT.hashCode(), "TestPlayer (NONE) hashCode works");
    }

    /**
     * Testing the hashCode of a RED player
     * Tested:
     *      hashCode: name.hashCode() + playerColor.hashCode() (int)
     */
    @Test
    public void testHashCodeRed() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.RED);

        assertEquals(CuT.getName().hashCode() +
                CuT.getPlayerColor().hashCode() + 1, CuT.hashCode(), "TestPlayer (RED) hashCode works");
    }

    /**
     * Testing the hashCode of a WHITE player
     * Tested:
     *      hashCode: name.hashCode() + playerColor.hashCode() (int)
     */
    @Test
    public void testHashCodeWhite() {
        final Player CuT = new Player("TestPlayer");

        CuT.joinGame(Player.PlayerColor.WHITE);

        assertEquals(CuT.getName().hashCode() +
                CuT.getPlayerColor().hashCode() + 1, CuT.hashCode(), "TestPlayer (WHITE) hashCode works");
    }


}
