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
        //CHANGED: 'getRecentedTurn()' to 'this.checkerboard' so that multiple single moves
        //are not longer allowed
        turn.copyBoard( this.checkerBoard );
        Piece selectPiece = turn.getBoard()[startingPos.getRow()][startingPos.getCell()].getPiece();

        if( turn.validateMove( startingPos, endingPos, typeOfMove, selectPiece, getRecentTurn() ) ) {
            turn.movePiece( startingPos, endingPos, player.playerColorToPieceColor() );
            previousMoves.push( turn );
            return true;
        }

        return false;
    }

    public boolean validateTurn() {
        Board selectedBoard = this.checkerBoard;
        for( Board turn : previousMoves ) {
            Position movedPos = selectedBoard.differentPiece(turn.getBoard());

            if (movedPos != null && !selectedBoard.pieceMovedCorrectDirection(movedPos)) {
                return false;
            }

            selectedBoard = turn;
        }

        return true;
    }

    public boolean submitTurn() {
        if( !validateTurn() ) {
            return false;
        }
        this.checkerBoard = this.previousMoves.pop();
        this.checkerBoard.kingPieces();
        this.previousMoves = new Stack<>();
        this.previousMoves.push(checkerBoard);

        if( this.activePlayer.equals(this.redPlayer) ) {
            this.activePlayer = this.whitePlayer;
        } else {
            this.activePlayer = this.redPlayer;
        }

        return true;
    }

    public boolean backup(){
        this.previousMoves.pop();
        return true;
    }

    public Player completedGame() {
        if( this.checkerBoard.finishedGame() ) {
            Player.PlayerColor winnerColor = this.checkerBoard.winnerColor();
            if( winnerColor == Player.PlayerColor.RED ) {
                return this.redPlayer;
            } else {
                return this.whitePlayer;
            }
        }
        return null;
    }

}
