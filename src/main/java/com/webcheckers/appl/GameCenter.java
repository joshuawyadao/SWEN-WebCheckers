package com.webcheckers.appl;

import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;

import java.util.HashMap;

public class GameCenter {
    HashMap<String, Game> currentGames;

    public GameCenter( ){
        currentGames = new HashMap<>();
    }

    public String newGame(Player redPlayer, Player whitePlayer, Game.ViewMode viewMode){
        String gameId = createGameId(redPlayer, whitePlayer);
        Game newGame = new Game(redPlayer, whitePlayer, viewMode);
        newGame.initializeGame();

        currentGames.put(gameId, newGame);

        return gameId;
    }

    public Game getGame(String gameId){
        return currentGames.get(gameId);
    }

    public void removeGame(String gameId) {
        currentGames.remove(gameId);
    }

    public boolean requestMove(String gameId, Player currentPlayer, Move move){
        Game game = currentGames.get(gameId);
        return game.makeMove(currentPlayer, move.getStart(), move.getEnd());
    }

    public boolean submitTurn(String gameId){
        Game game = currentGames.get(gameId);

        return game.submitTurn();
    }

    public boolean backupMove(String gameId){
        Game game = currentGames.get(gameId);

        return game.backup();
    }

    private static String createGameId(Player redPlayer, Player whitePlayer){
        return redPlayer.getName() + "Vs" + whitePlayer.getName();
    }
}
