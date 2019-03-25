package com.webcheckers.model;

public class Game {
    //Enumeration for the View Mode
    public enum ViewMode{
        PLAY,
        SPECTATE
    }

    private String gameId;
    private Player redPlayer;
    private Player whitePlayer;
    private ViewMode viewMode;
    private Board checkerBoard;

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



}
