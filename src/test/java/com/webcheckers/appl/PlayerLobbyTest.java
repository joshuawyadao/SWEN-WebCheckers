package com.webcheckers.appl;

import com.webcheckers.model.Player;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import spark.Session;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Application-tier")
public class PlayerLobbyTest {
    private static final int ONE_PLAYER_SIZE = 1;

    @Test
    public void testCreateLobby(){
        final PlayerLobby CuT = new PlayerLobby();

        assertNotNull(CuT, "PlayerLobby created unsuccessfully.");
    }

    @Test
    public void testValidPlayer_NoSpace(){
        final PlayerLobby CuT = new PlayerLobby();
        final Player validPlayer = new Player("ValidPlayer");

        assertTrue(CuT.isValidPlayer(validPlayer));
    }

    @Test
    public void testValidPlayer_WithSpace(){
        final PlayerLobby CuT = new PlayerLobby();
        final Player validPlayer = new Player("Valid Player");

        assertTrue(CuT.isValidPlayer(validPlayer));
    }

    @Test
    public void testInvalidPlayer_WithSymbol(){
        final PlayerLobby CuT = new PlayerLobby();
        final Player invalidPlayer = new Player("InvalidPlayer!");

        assertFalse(CuT.isValidPlayer(invalidPlayer));
    }

    @Test
    public void testInvalidPlayer_EmptyString(){
        final PlayerLobby CuT = new PlayerLobby();
        final Player invalidPlayer = new Player("");

        assertFalse(CuT.isValidPlayer(invalidPlayer));
    }

    @Test
    public void testInvalidPlayer_NoAlphaNumeric(){
        final PlayerLobby CuT = new PlayerLobby();
        final Player invalidPlayer = new Player(" ");

        assertFalse(CuT.isValidPlayer(invalidPlayer));
    }

    @Test
    public void testPlayerSignIn(){
        final PlayerLobby CuT = new PlayerLobby();
        final Player player1 = new Player("PlayerOne");
        final Session player1Session = mock(Session.class);

        CuT.signInPlayer(player1, player1Session);

        assertEquals(ONE_PLAYER_SIZE, CuT.getNumOfPlayers());
        assertTrue(CuT.getPlayers().contains(player1));
    }

    @Test
    public void testGetPlayerSession(){
        final PlayerLobby CuT = new PlayerLobby();
        final Player player1 = new Player("PlayerOne");
        final Session player1Session = mock(Session.class);
        CuT.signInPlayer(player1, player1Session);

        assertEquals(CuT.getPlayerSession(player1), player1Session);
    }

    @Test
    public void testGetPlayerSessionByName(){
        final PlayerLobby CuT = new PlayerLobby();
        final String player1Name = "PlayerOne";
        final Player player1 = new Player(player1Name);
        final Session player1Session = mock(Session.class);
        CuT.signInPlayer(player1, player1Session);

        assertEquals(CuT.getPlayerSessionByName(player1Name), player1Session);
    }
}
