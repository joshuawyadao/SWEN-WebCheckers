package com.webcheckers.appl;

import com.webcheckers.model.*;
import java.util.*;

/**
 * GameCenter Class
 * GameCenter retains all available Games that are currently being played.
 */
public class GameCenter {

    //Enumeration for the View Mode
    public enum ViewMode{
        PLAY,
        SPECTATOR,
        REPLAY
    }

    //
    // Private fields
    //

    private HashMap<String, Game> currentGames;
    private int gamesPlayed;
    private HashMap<String, ReplayGame> previousGames;
    private int gamesCompleted;

    /**
     * GameCenter constructor
     */
    public GameCenter( ){
        this.currentGames = new HashMap<>();
        this.gamesPlayed = 0;
        this.previousGames = new HashMap<>();
        this.gamesCompleted = 0;
    }

    /**
     * newGame creates a new checkers game that players can interact with
     * @param redPlayer the player to be red
     * @param whitePlayer the player to be white
     * @return a gameID, that is, a unique string to identify the newly made game
     */
    public String newGame(Player redPlayer, Player whitePlayer){
        gamesPlayed++;
        String gameId = createGameId();
        Game newGame = new Game(redPlayer, whitePlayer, gameId);
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

    /**
     * Returns a copy of a past game to replay and review
     * @param gameId the Id of the game to replay
     * @return a new instance of the game with the same values as the original
     */
    public ReplayGame getReplayGame(String gameId) {
        return previousGames.get(gameId).cloneGame();
    }

    /**
     * Removes a game from the hashmap of current games
     * @param gameId the Id of the game to be removed
     */
    public void removeGame(String gameId) {
        currentGames.remove(gameId);
    }

    /**
     * Creates a new, unique ID to identify a game with
//     * @param redPlayer the redPlayer
//     * @param whitePlayer the whitePlayer
     * @return a new ID, that is, a string
     */
    private String createGameId(){
        return "Game " + gamesPlayed;
//        return redPlayer.getName() + "Vs" + whitePlayer.getName();
    }

    /**
     * Creates the Id of a finished game to be stored in the replayGames hashmap
     * @return the String key value
     */
    private String createFinishedGameId(){
        return "Game #" + gamesCompleted;
    }

    /**
     * Moves a piece of a game
     * @param gameId the gameID of the game
     * @param move how is the piece going to be moved
     * @return true if the movement is successful, false if not
     */
    public boolean requestMove(String gameId, Move move){
        Game game = currentGames.get(gameId);
        return game.makeMove(move);
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
     * Determines if 2 players are in a game or not
     * @param player1 the 1st player to determine if they have a game
     * @param player2 the 2nd player to determine if they have a game
     * @return true if either one of the 2 players have a game, and false if both players do not have a game
     */
    public boolean hasGame(Player player1, Player player2) {
        for(Game game: currentGames.values()){
            if(player1.equals(game.getRedPlayer()) && player2.equals(game.getWhitePlayer()))
                return true;
            else if(player2.equals(game.getRedPlayer()) && player1.equals(game.getWhitePlayer()))
                return true;
        }

        return false;
    }

    /**
     * Determines if a player is in a game or not
     * @param player the player in question
     * @return true if the player is within a game, false if they are not in a game
     */
    public boolean isInAnyGame(Player player) {
        for( Game game : currentGames.values() ) {
            if( game.isInGame(player)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if it's a player's turn
     * @param gameId the unique identifier for the game that the player is in
     * @param currentPlayer the current player in question
     * @return true if it is the current player's turn, false if it is not the current player's turn
     */
    public boolean isMyTurn(String gameId, Player currentPlayer){
        Game game = currentGames.get(gameId);
        return game.getActivePlayer().equals(currentPlayer);
    }

    /**
     * Adds a game to the list of finished games
     * @param game the game to be added
     * @param gameId the subsequent game ID
     */
    public void addToPreviousGames(Game game, String gameId){
        gamesCompleted++;

        String previousGameId = createFinishedGameId();
        ReplayGame previousGame = new ReplayGame(game.getRedPlayer(), game.getWhitePlayer(),
                                                 game.getPreviousTurns(), previousGameId);

        previousGames.put(previousGameId, previousGame);
    }

    /**
     * Goes through the list of games finished and sorts them by game ID
     * @return a new list the the newly sorted games
     */
    public ArrayList<ReplayGame> sortPreviousGames(){
        ArrayList<ReplayGame> sortedPreviousGames = new ArrayList<>();
        ReplayGame tempGame;

        for(int i = 1; i < previousGames.size() + 1; i++){
            tempGame = previousGames.get("Game #" + i);
            sortedPreviousGames.add(tempGame);
        }

        Collections.reverse(sortedPreviousGames);

        return sortedPreviousGames;
    }

    /**
     * Determine if the list of finished games has any games.
     * @return true if the list has any games
     */
    public boolean hasPreviousGames(){
        return !previousGames.isEmpty();
    }

    /**
     * Gets the values of the current games
     * @return all the values in the hashmap of current games
     */
    public Collection<Game> getCurrentGames(){
        return currentGames.values();
    }

    /**
     * Checks to see if there is any current games going on
     * @return true if there is any games
     */
    public boolean hasCurrentGames() {return !currentGames.isEmpty();}

    /**
     * Checks to see if the spectator was updated
     * @param gameId the Id of the game to be checked
     * @param spectator the spectator to be checked
     * @return true if the spectator's view was updated
     */
    public boolean isSpectatorUpdated(String gameId, Player spectator){
        Game game = currentGames.get(gameId);

        return game.isSpectatorUpdated(spectator);
    }

    /**
     * Remove spectator from viewing a game and cleans up game if there is no spectators left and the game has ended
     * @param gameId the Id of the game to be examined
     * @param spectator the spectator to be removed
     * @return true if the spectator was removed from the game
     */
    public boolean removeSpectator(String gameId, Player spectator){
        Game game = currentGames.get(gameId);

        game.removeSpectator(spectator);

        if(game.getSpectatorNum() == 0 && ((game.isResigned()) || (game.completedGame() != null)))
            currentGames.remove(gameId);

        return game.removeSpectator(spectator);
    }

    /**
     * Updates the spectator's view based on the current game
     * @param gameId the Id of the game to be examined
     * @param spectator the spectator to have their view updated
     * @return true if the spectator's was successfully updated
     */
    public boolean updateSpectator(String gameId, Player spectator){
        Game gameToSpec = currentGames.get(gameId);
        gameToSpec.updateSpectator(spectator);

        return true;
    }

    /**
     * Gets the game Id of the player's current game
     * @param player the player in the game
     * @return the game Id of the game the player is currently in
     */
    public String getPlayerGameId(Player player) {
        for( Game game : currentGames.values() ) {
            if( game.isInGame(player)) {
                return game.getGameId();
            }
        }

        return null;
    }

    /**
     * Handles what happens if the game is over (resigned or won/lost)
     * @param gameId the game to be examined
     * @param currentUser the user to be examined
     * @return the modeOptions statement based on the results
     */
    public Map<String, Object> endGame(String gameId, Player currentUser){
        Map<String, Object> modeOptions = new HashMap<>(2);
        Game endedGame = currentGames.get(gameId);
        String gameResult = endedGame.getGameResult(currentUser);

        modeOptions.put("isGameOver", true);
        modeOptions.put("gameOverMessage", gameResult);

        currentUser.leaveGame();

        if(!(endedGame.getRedPlayer().isPlaying()) && !(endedGame.getWhitePlayer().isPlaying())){
            addToPreviousGames(endedGame, gameId);

            if(endedGame.getSpectatorNum() == 0)
                currentGames.remove(gameId);
        }

        return modeOptions;
    }

}
