package com.webcheckers.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("Model-tier")
public class PlayerTester {


    private Player player;

    public PlayerTester(){
        player = new Player("TestPlayer");
    }

    @Test
    public void testGetName(){
        assertEquals("TestPlayer", player.getName(), "testGetName has failed.");
    }

    @Test
    public void testJoinGame(){

    }

    @Test
    public void testLeaveGame(){

    }

    @Test
    public void testGetPlayerColor(){

    }

    @Test
    public void testIsPlaying(){

    }

    @Test
    public void testEquals(){

    }

    @Test
    public void testHashCode(){

    }





}
