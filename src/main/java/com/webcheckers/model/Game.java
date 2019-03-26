package com.webcheckers.model;

import java.util.Map;

public class Game {
    //Enumeration for the View Mode
    public enum ViewMode{
        PLAY,
        SPECTATE
    }

    private Player redPlayer;
    private Player whitePlayer;
    private ViewMode viewMode;
    private Board checkerBoard;
    private Map<String, Object> modeOptionsAsJSON;

    public Game(Player redPlayer, Player whitePlayer, ViewMode viewMode){
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.viewMode = viewMode;
        this.checkerBoard = new Board();
    }

    //Accessors
    public Player getRedPlayer() {
        return redPlayer;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public ViewMode getViewMode() {
        return viewMode;
    }

    public Board getCheckerBoard(){
        return checkerBoard;
    }

    public Player.PlayerColor getPlayerColor(Player currentPlayer){
        return currentPlayer.getPlayerColor();
    }

    public void initializeGame(){
        redPlayer.joinGame(Player.PlayerColor.RED);
        whitePlayer.joinGame(Player.PlayerColor.WHITE);
    }

}
