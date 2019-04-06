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
    private Player resignedPlayer;
    private int typeOfMove;

    /**
     * Creates a new game instance
     * @param redPlayer the red player
     * @param whitePlayer the white player
     * @param viewMode the view mode of this game
     */
    public Game(Player redPlayer, Player whitePlayer, ViewMode viewMode){
        this.redPlayer = redPlayer;
        this.activePlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.viewMode = viewMode;
        this.checkerBoard = new Board();
        this.previousMoves = new Stack<>();
        previousMoves.push(checkerBoard);
        this.resignedPlayer = null;
        this.typeOfMove = 0;
    }

    //Accessors

    /**
     * Returns the red player
     * @return the red player
     */
    public Player getRedPlayer() {
        return this.redPlayer;
    }

    /**
     * Returns the white player
     * @return the white player
     */
    public Player getWhitePlayer() {
        return this.whitePlayer;
    }

    /**
     * Returns the view mode of this game
     * @return the view mode of this game
     */
    public ViewMode getViewMode() {
        return this.viewMode;
    }

    /**
     * Gets the initial layout of the board before any player moves on their turn
     * @return the initial layout of the board
     */
    public Board getCheckerBoard(){
        return this.checkerBoard;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    /**
     * Return the move recent turn
     * @return the board object of the most recent turn
     */
    public Board getRecentTurn() { return this.previousMoves.peek(); }

    /**
     * Gets the current player's color
     * @param currentPlayer the current player
     * @return the color of the current player
     */
    public Player.PlayerColor getPlayerColor( Player currentPlayer ){
        return currentPlayer.getPlayerColor();
    }

    /**
     * Starts a game
     */
    public void initializeGame() {
        this.redPlayer.joinGame( Player.PlayerColor.RED );
        this.whitePlayer.joinGame( Player.PlayerColor.WHITE );
    }

    /**
     * Makes a move within a game
     * @param player the player to make a move
     * @param startingPos the starting position of the move
     * @param endingPos the ending position of the move
     * @return if the move was valid
     */
    public boolean makeMove( Player player, Position startingPos, Position endingPos ) {
        this.typeOfMove = startingPos.difference( endingPos );
        Board turn = new Board();
        //CHANGED: 'getRecentedTurn()' to 'this.checkerboard' so that multiple single moves
        //are not longer allowed
        turn.copyBoard( this.checkerBoard );
        Piece selectPiece = turn.getBoard()[startingPos.getRow()][startingPos.getCell()].getPiece();

        if( turn.validateMove( startingPos, endingPos, typeOfMove, selectPiece, getRecentTurn() )) {
            if( Math.abs(typeOfMove) == 1 ) {
                turn.movePiece(startingPos, endingPos, player.playerColorToPieceColor(), selectPiece.getType());
                previousMoves.push(turn);
                return true;
            } else
                {
                turn.movePiece(startingPos, endingPos, player.playerColorToPieceColor(), selectPiece.getType());
                previousMoves.push(turn);
                return true;
            }
        }

        return false;
    }


    /**
     * Checks to see if a turn was valid
     * @return if the turn is valid
     */
    public boolean validateTurn() {
        for( Board turn : previousMoves ) {
            if( !turn.equals( previousMoves.peek() ) && !turn.movedPieceCorrectly() ) {
                return false;
            }
        }

        return false;
    }

    /**
     * Submits a turn to be checked and resets the previous moves stack
     * @return if the turn was a valid turn
     */
    public boolean submitTurn() {
//        if( !validateTurn() ) {
//            return false;
//        }

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

    /**
     * Backups a single move
     * @return if the turn was successfully backed-up
     */
    public boolean backup(){
       Board previousTurn = null;
       previousTurn = this.previousMoves.pop();

//        if(previousMoves.size() > 1) {
//        previousTurn = this.previousMoves.pop();
//        }

        return previousTurn != null;
    }

    /**
     * Returns the player who won the game
     * @return the player who won
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

    /**
     * Resigns a player
     * @param resignedPlayer the player to resign
     */
    public void playerResigned(Player resignedPlayer){
        this.resignedPlayer = resignedPlayer;
    }

    /**
     * Checks to see if there is a resigned player
     * @return if a player is resigned
     */
    public boolean isResigned(){
        return this.resignedPlayer != null;
    }

    /**
     * Returns the player that resigned
     * @return the resigned player
     */
    public Player getResignedPlayer(){
        return this.resignedPlayer;
    }

}
