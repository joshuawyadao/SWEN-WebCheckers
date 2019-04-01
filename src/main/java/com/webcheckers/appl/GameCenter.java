package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * GameCenter Class
 * GameCenter retains all available Games that are currently being played.
 */
public class GameCenter {

    //
    // Private fields
    //

    private HashMap<String, Game> currentGames;

    /**
     * GameCenter constructor
     */
    public GameCenter( ){
        currentGames = new HashMap<>();
    }

    /**
     * newGame creates a new checkers game that players can interact with
     * @param redPlayer the player to be red
     * @param whitePlayer the player to be white
     * @param viewMode the mode to situate the game with
     * @return a gameID, that is, a unique string to identify the newly made game
     */
    public String newGame(Player redPlayer, Player whitePlayer, Game.ViewMode viewMode){
        String gameId = createGameId(redPlayer, whitePlayer);
        Game newGame = new Game(redPlayer, whitePlayer, viewMode);
        newGame.initializeGame();

        currentGames.put(gameId, newGame);

        return gameId;
    }

    /**
     * Gets an active game by their unique gameID
     * @param gameId the unique string to find the game with
     * @return the game identified by the gameID
     */
    public Game getGame(String gameId){
        return currentGames.get(gameId);
    }

    public void removeGame(String gameId) {
        currentGames.remove(gameId);
    }

    /**
     * Creates a new, unique ID to identify a game with
     * @param redPlayer the redPlayer
     * @param whitePlayer the whitePlayer
     * @return a new ID, that is, a string
     */
    private static String createGameId(Player redPlayer, Player whitePlayer){
        return redPlayer.getName() + "Vs" + whitePlayer.getName();
    }

    /**
     * Moves a piece of a game
     * @param gameId the gameID of the game
     * @param currentPlayer the current player
     * @param move how is the piece going to be moved
     * @return true if the movement is successful, false if not
     */
    public boolean requestMove(String gameId, Player currentPlayer, Move move){
        Game game = currentGames.get(gameId);
        return game.makeMove(currentPlayer, move.getStart(), move.getEnd());
    }

    /**
     * Submits a turn so the other player can play
     * @param gameId the game ID of the game
     * @return true if successful, false if it is not
     */
    public boolean submitTurn(String gameId){
        Game game = currentGames.get(gameId);
        return game.submitTurn();
    }

    /**
     * Backs-up a move
     * @param gameId the game ID of the game
     * @return true if successful, false if not
     */
    public boolean backupMove(String gameId){
        Game game = currentGames.get(gameId);
        return game.backup();
    }


    /**
     * Searches through the list of current games to determine
     * if a given player is within one.
     * @param player The player to be searched for
     * @return true if the player is within a current game. False otherwise
     */
    public boolean hasGame(Player player) {
        for (Map.Entry<String, Game> game : currentGames.entrySet()) {
            if (game.getValue().getWhitePlayer().equals(player)
                    || (game.getValue().getRedPlayer().equals(player))) {
                return true;
            }
        }

        return false;

    }

    public boolean isMyTurn(String gameId, Player currentPlayer){
        Game game = currentGames.get(gameId);

        if((game.getActivePlayer()).equals(currentPlayer))
            return true;
        else
            return false;
    }


}
