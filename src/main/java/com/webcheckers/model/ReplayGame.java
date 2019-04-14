package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Objects;

public class ReplayGame {

    private Player redPlayer, whitePlayer;
    private Player.PlayerColor activeColor;
    private int currentTurn;
    private final ArrayList<Board> previousTurns;
    private String gameEndTime;
    private String gameId;

    public ReplayGame(Player redPlayer, Player whitePlayer, ArrayList<Board> previousTurns, String gameEndTime, String gameId){
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.activeColor = Player.PlayerColor.RED;
        this.gameEndTime = gameEndTime;
        this.gameId = gameId;
        this.currentTurn = 0;

        this.previousTurns = Objects.requireNonNull(previousTurns, "previousTurns is required");
    }

    public String getGameEndTime() {
        return gameEndTime;
    }

    public Player.PlayerColor getActiveColor() {
        return activeColor;
    }

    public Player getRedPlayer() {
        return redPlayer;
    }

    public String getGameId(){
        return gameId;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Board getPreviousTurn(){
        currentTurn--;

        if(currentTurn % 2 == 0)
            activeColor = Player.PlayerColor.RED;
        else
            activeColor = Player.PlayerColor.WHITE;

        return previousTurns.get(currentTurn);
    }

    public Board getCurrentTurn(){
        return previousTurns.get(currentTurn);
    }

    public Board getNextTurn(){
        currentTurn++;

        if(currentTurn % 2 == 0)
            activeColor = Player.PlayerColor.RED;
        else
            activeColor = Player.PlayerColor.WHITE;

        return previousTurns.get(currentTurn);
    }

    public boolean hasPrevious(){
        return currentTurn != 0;
    }

    public boolean hasNext(){
        return currentTurn != (previousTurns.size() - 1);
    }

}
