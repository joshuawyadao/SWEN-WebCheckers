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

    /**
     * Game constructor
     * @param redPlayer the player to be red
     * @param whitePlayer the player to be white
     * @param viewMode the view mode of the players
     */
    public Game(Player redPlayer, Player whitePlayer, ViewMode viewMode){
        this.redPlayer = redPlayer;
        this.activePlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.viewMode = viewMode;
        this.checkerBoard = new Board();
        this.previousMoves = new Stack<>();
        previousMoves.push(checkerBoard);
    }

    //
    //Accessors
    //

    /**
     * Gets the red player
     * @return the red player
     */
    public Player getRedPlayer() {
        return this.redPlayer;
    }

    /**
     * Gets the white player
     * @return the white player
     */
    public Player getWhitePlayer() {
        return this.whitePlayer;
    }

    /**
     * Gets the view mode
     * @return the view mode
     */
    public ViewMode getViewMode() {
        return this.viewMode;
    }

    /**
     * Gets the checker board
     * @return the checker board
     */
    public Board getCheckerBoard(){
        return this.checkerBoard;
    }

    /**
     * Gets the active player
     * @return the active player
     */
    public Player getActivePlayer() {
        return activePlayer;
    }

    /**
     * Gets the most recent turn played
     * @return a board consisting of the earliest turn
     */
    public Board getRecentTurn() { return this.previousMoves.peek(); }

    /**
     * Gets the currrnt player's color
     * @param currentPlayer the currentplayer
     * @return the color of the current player
     */
    public Player.PlayerColor getPlayerColor( Player currentPlayer ){
        return currentPlayer.getPlayerColor();
    }

    /**
     * Initializes the game for 2 players
     */
    public void initializeGame() {
        this.redPlayer.joinGame( Player.PlayerColor.RED );
        this.whitePlayer.joinGame( Player.PlayerColor.WHITE );
    }

    /**
     * Makes a piece movement
     * @param player the player making the movement
     * @param startingPos the starting position
     * @param endingPos the ending position
     * @return true if successful, false if not
     */
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

    /**
     * Creates a history of the board whenever submitted a turn
     * @return true if succesful, false otherwise
     */
    public boolean submitTurn() {
        this.checkerBoard = this.previousMoves.pop();
        this.previousMoves = new Stack<>();
        this.previousMoves.push(checkerBoard);

        if( this.activePlayer.equals(this.redPlayer) ) {
            this.activePlayer = this.whitePlayer;
        } else {
            this.activePlayer = this.redPlayer;
        }

        return true;
    }

    /**
     * Return to a previous state of the board
     * @return true if successful, false otherwise
     */
    public boolean backup(){
        this.previousMoves.pop();
        return true;
    }

    /**
     * Determine if the game has been completed
     * @return true if the game is finished, false if the game has not been finished, and null otherwise
     */
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
