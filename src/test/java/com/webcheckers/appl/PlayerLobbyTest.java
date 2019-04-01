package com.webcheckers.appl;

import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import spark.Session;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Application-tier")
public class PlayerLobbyTest {
    private final static Message TEST_INVALID_NAME_MSG = Message.error("ERROR: Name MUST contain at least one " +
            "alphanumeric character, and can optionally contain spaces.");

    private final static Message TEST_LOGIN_SUCCESSFUL = Message.info("Login Successful!");

    private static final int ONE_PLAYER_SIZE = 1;
    private static final int TWO_PLAYER_SIZE = 2;
    private Session testPlayerSession;
    private PlayerLobby CuT;

    @BeforeEach
    public void setup() {
        testPlayerSession = mock(Session.class);
        CuT = new PlayerLobby();
    }

    @Test
    public void testCreateLobby(){
        final PlayerLobby CuT = new PlayerLobby();

        assertNotNull(CuT, "PlayerLobby created unsuccessfully.");
    }

    @Test
    public void testValidPlayer_NoSpace(){
        final Player validPlayer = new Player("validPlayer");

        assertEquals(TEST_LOGIN_SUCCESSFUL.toString(), CuT.signInPlayer(validPlayer, testPlayerSession).toString());
    }

    @Test
    public void testValidPlayer_WithSpace(){
        final Player validPlayer = new Player("Valid Player");

        assertEquals(TEST_LOGIN_SUCCESSFUL.toString(), CuT.signInPlayer(validPlayer, testPlayerSession).toString());
    }

    @Test
    public void testInvalidPlayer_WithSymbol(){
        final Player invalidPlayer = new Player("InvalidPlayer!");

        assertEquals(TEST_INVALID_NAME_MSG.toString(), CuT.signInPlayer(invalidPlayer, testPlayerSession).toString());
    }

    @Test
    public void testInvalidPlayer_EmptyString(){
        final Player invalidPlayer = new Player("");

        assertEquals(TEST_INVALID_NAME_MSG.toString(), CuT.signInPlayer(invalidPlayer, testPlayerSession).toString());
    }

    @Test
    public void testInvalidPlayer_NoAlphaNumeric(){
        final Player invalidPlayer = new Player(" ");

        assertEquals(TEST_INVALID_NAME_MSG.toString(), CuT.signInPlayer(invalidPlayer, testPlayerSession).toString());
    }

    @Test
    public void testPlayerSignIn_OnePlayer(){
        final Player player1 = new Player("playerOne");

        assertEquals(TEST_LOGIN_SUCCESSFUL.toString(), CuT.signInPlayer(player1, testPlayerSession).toString());

        assertEquals(ONE_PLAYER_SIZE, CuT.getNumOfPlayers());
        assertTrue(CuT.getPlayers().contains(player1));
    }

    @Test
    public void testPlayerSignIn_TwoPlayers(){
        final Player player1 = new Player("playerOne");
        final Player player2 = new Player("PlayerTwo");

        assertEquals(TEST_LOGIN_SUCCESSFUL.toString(), CuT.signInPlayer(player1, testPlayerSession).toString());
        assertEquals(TEST_LOGIN_SUCCESSFUL.toString(), CuT.signInPlayer(player2, testPlayerSession).toString());

        assertEquals(TWO_PLAYER_SIZE, CuT.getNumOfPlayers());
        assertTrue(CuT.getPlayers().contains(player1));
        assertTrue(CuT.getPlayers().contains(player2));
    }

    @Test
    public void testInvalidPlayers_SameName(){
        final Player player1 = new Player("playerOne");
        final Player player2 = new Player("playerOne");
        final Message TEST_SAME_NAME_MSG = Message.error(String.format("ERROR: Sorry, '%s' is already taken by another user.", player2.getName()));

        assertEquals(TEST_LOGIN_SUCCESSFUL.toString(), CuT.signInPlayer(player1, testPlayerSession).toString());
        assertEquals(TEST_SAME_NAME_MSG.toString(), CuT.signInPlayer(player2, testPlayerSession).toString());

        assertEquals(ONE_PLAYER_SIZE, CuT.getNumOfPlayers());
        assertTrue(CuT.getPlayers().contains(player1));
    }

    @Test
    public void testGetPlayerSession(){
        final Player player1 = new Player("PlayerOne");

        assertEquals(TEST_LOGIN_SUCCESSFUL.toString(), CuT.signInPlayer(player1, testPlayerSession).toString());
        assertEquals(testPlayerSession, CuT.getPlayerSession(player1));
    }

    @Test
    public void testGetPlayerSessionByName_Found(){
        final String player1Name = "PlayerOne";
        final Player player1 = new Player(player1Name);

        assertEquals(TEST_LOGIN_SUCCESSFUL.toString(), CuT.signInPlayer(player1, testPlayerSession).toString());

        assertEquals(testPlayerSession, CuT.getPlayerSessionByName(player1Name));
    }

    @Test
    public void testGetPlayerSessionByName_NotFound(){
        final String player1Name = "PlayerOne";
        final String player2Name = "PlayerTwo";
        final Player player1 = new Player(player1Name);

        assertEquals(TEST_LOGIN_SUCCESSFUL.toString(), CuT.signInPlayer(player1, testPlayerSession).toString());

        assertNull(CuT.getPlayerSessionByName(player2Name));
    }

}
