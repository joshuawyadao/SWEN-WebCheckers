package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Objects;

public class ReplayGame {

    private Player redPlayer, whitePlayer, activePlayer;
    private int currentTurn;
    private final ArrayList<Board> previousTurns;

    public ReplayGame(Player redPlayer, Player whitePlayer, ArrayList<Board> previousTurns){
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.activePlayer = redPlayer;
        this.currentTurn = 0;

        this.previousTurns = Objects.requireNonNull(previousTurns, "previousTurns is required");
    }

    public Player getRedPlayer() {
        return redPlayer;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public Board getPreviousTurn(){
        currentTurn--;

        if(currentTurn % 2 == 0)
            activePlayer = redPlayer;
        else
            activePlayer = whitePlayer;

        return previousTurns.get(currentTurn);
    }

    public Board getCurrentTurn(){
        return previousTurns.get(currentTurn);
    }

    public Board getNextTurn(){
        currentTurn++;

        if(currentTurn % 2 == 0)
            activePlayer = redPlayer;
        else
            activePlayer = whitePlayer;

        return previousTurns.get(currentTurn);
    }

    public boolean hasPrevious(){
        return currentTurn != 0;
    }

    public boolean hasNext(){
        return currentTurn != (previousTurns.size() - 1);
    }

}
