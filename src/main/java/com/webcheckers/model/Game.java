package com.webcheckers.model;

import java.util.Map;
import java.util.Stack;

public class Game {

    //Enumeration for the View Mode
    public enum ViewMode{
        PLAY,
        SPECTATE
    }

    private Player redPlayer;
    private Player whitePlayer;
    private Player activePlayer;
    private ViewMode viewMode;
    private Board checkerBoard;
    private Map<String, Object> modeOptionsAsJSON;
    private Stack<Board> previousMoves;

    public Game(Player redPlayer, Player whitePlayer, ViewMode viewMode){
        this.redPlayer = redPlayer;
        this.activePlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.viewMode = viewMode;
        this.checkerBoard = new Board();
        this.previousMoves = new Stack<>();
        previousMoves.push(checkerBoard);
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

    public Player getActivePlayer() {
        return activePlayer;
    }

    public Board getRecentTurn() { return this.previousMoves.peek(); }

    public Player.PlayerColor getPlayerColor( Player currentPlayer ){
        return currentPlayer.getPlayerColor();
    }

    public void initializeGame() {
        this.redPlayer.joinGame( Player.PlayerColor.RED );
        this.whitePlayer.joinGame( Player.PlayerColor.WHITE );
    }

    public boolean makeMove( Player player, Position startingPos, Position endingPos ) {
        int typeOfMove = startingPos.difference( endingPos );
        Board turn = new Board();
        turn.copyBoard( getRecentTurn() );
        Piece selectPiece = turn.getBoard()[startingPos.getRow()][startingPos.getCell()].getPiece();

        if( turn.validateMove( startingPos, endingPos, typeOfMove, selectPiece ) ) {
            turn.movePiece( startingPos, endingPos, player.playerColorToPieceColor() );
            previousMoves.push( turn );
            return true;
        }

        return false;
    }

    public boolean submitTurn() {
        this.checkerBoard = this.previousMoves.peek();
        this.previousMoves = new Stack<>();
        this.previousMoves.push(checkerBoard);

        if( this.activePlayer.equals(this.redPlayer) ) {
            this.activePlayer = this.whitePlayer;
        } else {
            this.activePlayer = this.redPlayer;
        }

        return true;
    }

}
