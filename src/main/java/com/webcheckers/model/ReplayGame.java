package com.webcheckers.model;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class ReplayGame {

    private Player redPlayer, whitePlayer;
    private Player.PlayerColor activeColor;
    private int currentTurn;
    private final ArrayList<Board> previousTurns;
    private String gameEndTime;
    private String gameId;

    /**
     * ReplayGame constructor
     * @param redPlayer the player that was red
     * @param whitePlayer the player that was white
     * @param previousTurns an arraylist containing previously made movements
     * @param gameId a unique identifier for the game
     */
    public ReplayGame(Player redPlayer, Player whitePlayer, ArrayList<Board> previousTurns, String gameId){
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.activeColor = Player.PlayerColor.RED;
        this.gameId = gameId;
        this.currentTurn = 0;

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy 'at' hh:mm a");
        Date date = new Date();
        this.gameEndTime = dateFormat.format(date);

        this.previousTurns = Objects.requireNonNull(previousTurns, "previousTurns is required");
    }

    public ReplayGame cloneGame(){
        Player redPlayer = new Player(this.redPlayer.getName());
        Player whitePlayer = new Player(this.whitePlayer.getName());

        ArrayList<Board> previousTurns = new ArrayList<>();
        previousTurns.addAll(this.previousTurns);

        return new ReplayGame(redPlayer, whitePlayer, previousTurns, this.gameId);
    }

    /**
     * Get the game's end time
     * @return the game's end time
     */
    public String getGameEndTime() {
        return this.gameEndTime;
    }

    /**
     * Get the game's active player color
     * @return the game's active player color
     */
    public Player.PlayerColor getActiveColor() {
        return this.activeColor;
    }

    /**
     * Get the game's red player
     * @return the game's red player
     */
    public Player getRedPlayer() {
        return this.redPlayer;
    }

    /**
     * Get the game's white player
     * @return the game's white player
     */
    public Player getWhitePlayer() {
        return this.whitePlayer;
    }

    /**
     * Get the game's ID
     * @return the game's ID
     */
    public String getGameId(){
        return this.gameId;
    }

    public ArrayList<Board> getPreviousTurns() { return this.previousTurns; }

    /**
     * Changes the index pointing to the list of successful turns (i.e. previousTurns)
     * by -1
     * @return the previous successful move
     */
    public Board getPreviousTurn(){
        currentTurn--;

        if(currentTurn % 2 == 0)
            activeColor = Player.PlayerColor.RED;
        else
            activeColor = Player.PlayerColor.WHITE;

        return previousTurns.get(currentTurn);
    }

    /**
     * return the current state of the board
     * @return the current state of the board
     */
    public Board getCurrentTurn(){
        return previousTurns.get(currentTurn);
    }

    /**
     * Changes the index pointing to the list of successful turns (i.e. previousTurns)
     * by 1
     * @return the next successful move
     */
    public Board getNextTurn(){
        currentTurn++;

        if(currentTurn % 2 == 0)
            activeColor = Player.PlayerColor.RED;
        else
            activeColor = Player.PlayerColor.WHITE;

        return previousTurns.get(currentTurn);
    }

    /**
     * Determines if the list has any previous moves
     * @return true if the list has a previous move, false if the list has no moves (i.e. the start of the game state)
     */
    public boolean hasPrevious(){
        return currentTurn > 0;
    }

    /**
     * Determines if the list has a next move
     * @return true if the list has a next move of it, false if the list has no moves (i.e. the game has been finished/resigned)
     */
    public boolean hasNext(){
        return currentTurn != (previousTurns.size() - 1);
    }

}
