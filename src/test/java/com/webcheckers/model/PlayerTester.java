package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model-tier")

public class PlayerTester {

    @Test
    public void testPlayerCreation() {
        final Player CuT = new Player("Test Player");
        assertNotNull(CuT, "Player is not null");
    }

    @Test
    public void testPlayerName() {
        final Player CuT = new Player("Test Player");
        assertEquals("Test Player", CuT.getName(), "Player name set correctly");
    }

    @Test
    public void testPlayerNoColor() {
        final Player CuT = new Player("Test Player");
        assertEquals(Player.PlayerColor.NOT_IN_GAME, CuT.getPlayerColor());
    }

    @Test
    public void testPlayerOneColor() {
        final Player CuT = new Player("Test Player");
        CuT.setPlayerColor(true);
        assertEquals(Player.PlayerColor.RED, CuT.getPlayerColor());
    }

    @Test
    public void testPlayerTwoColor() {
        final Player CuT = new Player("Test Player");
        CuT.setPlayerColor(false);
        assertEquals(Player.PlayerColor.WHITE, CuT.getPlayerColor());
    }
}
