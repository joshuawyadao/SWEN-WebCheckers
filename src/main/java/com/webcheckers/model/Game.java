package com.webcheckers.model;

import java.util.Map;
import java.util.Stack;

public class Game {

    //Enumeration for the View Mode
    public enum ViewMode{
        PLAY,
        SPECTATE,
        REPLAY
    }

    private Player redPlayer, whitePlayer, activePlayer, resignedPlayer;
    private ViewMode viewMode;
    private Board checkerBoard;
    private Map<String, Object> modeOptionsAsJSON;
    private Stack<Board> previousMoves;
    private boolean validTurn;

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
        this.validTurn = false;
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
     *
     * @param player
     * @param move
     * @return
     */
    public boolean makeMove( Player player, Move move ) {
        Board turn = new Board();
        //CHANGED: 'getRecentedTurn()' to 'this.checkerboard' so that multiple single moves
        //are not longer allowed
        turn.copyBoard( this.previousMoves.peek() );
        Piece selectPiece = turn.getBoard()[move.getStartRow()][move.getStartCell()].getPiece();
        Move.TYPE_OF_MOVE moveType = move.typeOfMove(selectPiece);

        if( moveType == Move.TYPE_OF_MOVE.ERROR ) {
            return false;
        }

        switch ( moveType ) {
            case SIMPLE:
                if( move.validSimpleMove( turn ) && ( this.previousMoves.size() == 1 ) ) {
                    movePiece( move, turn, selectPiece );
                    this.previousMoves.push(turn);
                    this.validTurn = validateTurn();
                    return true;
                }
                break;
            case JUMP:
                Position between = move.validSimpleJump( turn );
                if( between != null ) {
                    movePiece( move, turn, selectPiece );
                    turn.getBoard()[between.getRow()][between.getCell()].setPiece( null );
                    this.previousMoves.push(turn);

                    Piece endPiece = previousMoves.peek().getBoard()[move.getEndRow()][move.getEndCell()].getPiece();
                    this.validTurn = !(canJump(move.getEnd(), previousMoves.peek(), getNeighbors(endPiece, move.getEndRow(), move.getEndCell())));
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;

    }


    /**
     *
     * @param move
     * @param turn
     * @param piece
     */
    public void movePiece( Move move, Board turn, Piece piece ) {
        turn.getBoard()[move.getEndRow()][move.getEndCell()].setPiece( new Piece( piece.getType(), piece.getColor() ) );
        turn.getBoard()[move.getStartRow()][move.getStartCell()].setPiece( null );
    }

    public boolean canJump( Position startPos, Board turn, Position[] neighbors ) {
        for( Position position : neighbors ) {
            if( position.inBounds() ) {
                Move move = new Move( startPos, position );
                Position between = move.validSimpleJump( turn );
                if( between != null ) {
                    return true;
                }
            }
        }

        return false;
    }

    public Position[] getNeighbors(Piece piece, int row, int col){
        Piece.TYPE pieceType = piece.getType();
        Piece.COLOR pieceColor = piece.getColor();

        if (pieceType == Piece.TYPE.SINGLE ) {
            if (pieceColor == Piece.COLOR.RED) {
                return new Position[]{new Position(row + 2, col - 2),
                        new Position(row + 2, col + 2)};
            }
            if (pieceColor == Piece.COLOR.WHITE) {
                return new Position[]{new Position(row - 2, col - 2),
                        new Position(row - 2, col + 2)};
            }
        }else if( pieceType == Piece.TYPE.KING ) {
            return new Position[]{new Position(row + 2, col - 2),
                    new Position(row + 2, col + 2),
                    new Position(row - 2, col - 2),
                    new Position(row - 2, col + 2)};
        }

        return null;
    }

    public boolean validateTurn() {
        Space[][] board = this.checkerBoard.getBoard();
        for( int row = 0; row < 8; row++ ) {
            for( int col = 0; col < 8; col++ ) {
                Space selectedSpace = board[row][col];

                if( selectedSpace.validSpaceWithPiece( this.activePlayer ) ) {

                    Piece selectedPiece = selectedSpace.getPiece();
                    Position[] neighbors = getNeighbors(selectedPiece, row, col);
                    Position startPos = new Position( row, col );

                    if( canJump( startPos, checkerBoard, neighbors ) ){
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Submits a turn to be checked and resets the previous moves stack
     * @return if the turn was a valid turn
     */
    public boolean submitTurn() {
        if(this.validTurn) {
            this.checkerBoard = this.previousMoves.pop();
            this.checkerBoard.kingPieces();
            this.previousMoves = new Stack<>();
            this.previousMoves.push(checkerBoard);
            this.validTurn = false;

            if (this.activePlayer.equals(this.redPlayer)) {
                this.activePlayer = this.whitePlayer;
            } else {
                this.activePlayer = this.redPlayer;
            }

            return true;
        }else{
            return false;
        }
    }

    /**
     * Backups a single move
     * @return if the turn was successfully backed-up
     */
    public boolean backup(){
        return this.previousMoves.pop() != null;
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
