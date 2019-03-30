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
        return this.redPlayer;
    }

    public Player getWhitePlayer() {
        return this.whitePlayer;
    }

    public ViewMode getViewMode() {
        return this.viewMode;
    }

    public Board getCheckerBoard(){
        return this.checkerBoard;
    }

    public Player.PlayerColor getPlayerColor( Player currentPlayer ){
        return currentPlayer.getPlayerColor();
    }

    public void initializeGame() {
        this.redPlayer.joinGame( Player.PlayerColor.RED );
        this.whitePlayer.joinGame( Player.PlayerColor.WHITE );
    }

    public boolean makeMove( Player player, Position startingPos, Position endingPos ) {
        int typeOfMove = startingPos.difference( endingPos );

        if( this.checkerBoard.validateMove( startingPos, endingPos, typeOfMove ) ) {
            this.checkerBoard.movePiece( startingPos, endingPos, player.playerColorToPieceColor() );
            return true;
        }

        return false;
    }

}
