package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.HashMap;

public class GameCenter {
    HashMap<String, Game> currentGames;

    public GameCenter( ){
        currentGames = new HashMap<>();
    }

    public static String createGameId(Player redPlayer, Player whitePlayer){
        return redPlayer.getName() + "Vs" + whitePlayer.getName();
    }

    public String newGame(Player redPlayer, Player whitePlayer){
        return "";
    }

    public void initializeGame(){
//        redPlayer.joinGame(Player.PlayerColor.RED);
//        whitePlayer.joinGame(Player.PlayerColor.WHITE);
    }
}
