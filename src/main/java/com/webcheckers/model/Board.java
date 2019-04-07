package com.webcheckers.model;

import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import com.webcheckers.model.Space;
import javafx.geometry.Pos;

import java.util.*;

public class Board {

    private final int BOARD_SIDE = 8;
    private final int RED_SIDE = 3;
    private final int WHITE_SIDE = 4;

    private Space[][] board;

    /**
     * Board Constructor
     * Initializes the players for the game and sets up the board itself
     */
    public Board(){
        SetUpGame();
        setUpPieces();
    }

    /**
     * Secondary Board Constructor
     * Used primarily for testing
     * @param setUpPieces true if you want the pieces initialized, false otherwise
     */
    public Board(boolean setUpPieces){
       if(!setUpPieces){
           SetUpGame();
       } else {
           SetUpGame();
           setUpPieces();
       }
    }

    /**
     * Initializes the game board, specifically how the board looks.
     * Note that the pieces are not set-up from this method
     */
    private void SetUpGame(){

        // Set up the board - with no pieces

        this.board = new Space[BOARD_SIDE][BOARD_SIDE];

        for( int r = 0; r < BOARD_SIDE; r++ ) {
            for( int c = 0; c < BOARD_SIDE; c++ ) {
                if(r % 2 == 0) {
                    if(c % 2 == 0) {
                        board[r][c] = new Space(c, null, Space.COLOR.LIGHT);
                    } else {
                        board[r][c] = new Space(c, null, Space.COLOR.DARK);
                    }

                } else {
                    if(c % 2 == 1) {
                        board[r][c] = new Space(c, null, Space.COLOR.LIGHT);
                    } else {
                        board[r][c] = new Space(c, null, Space.COLOR.DARK);
                    }

                }
            }
        }

    }

    /**
     * Sets up the pieces for both players
     */
    private void setUpPieces(){
        for( int r = 0; r < BOARD_SIDE; r++ ){
            for( int c = 0; c < BOARD_SIDE; c++ ){
                if( r < RED_SIDE ) {
                    if( board[r][c].isValid() ) {
                        board[r][c].setPiece(new Piece(Piece.TYPE.SINGLE, Piece.COLOR.RED));
                    }
                }
                else if( r > WHITE_SIDE ) {
                    if( board[r][c].isValid() ){
                        board[r][c].setPiece(new Piece(Piece.TYPE.SINGLE, Piece.COLOR.WHITE));
                    }
                }
            }
        }
    }

    /**
     * Get the entire board
     * @return the entire board
     */
    public Space[][] getBoard() {
        return board;
    }

    /**
     * Get a specific row at an index from the board
     * @param rowNum the index value
     * @return the row at the index
     */
    public Space[] getRow(int rowNum){
        return board[rowNum];
    }

    public boolean equals(Board other) {
        for( int row = 0; row < BOARD_SIDE; row++ ) {
            for( int col = 0; col < BOARD_SIDE; col ++ ) {
                Space currentBoardSpace = this.board[row][col];
                Space otherSpace = other.getBoard()[row][col];
                if( !currentBoardSpace.equals(otherSpace) ) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Goes through all the pieces and checks to see if there is any pieces for a player left
     * @param color the color of pieces to be looked for
     * @return how many pieces are left for a player
     */
    private int playerPiecesLeft( Piece.COLOR color ) {
        int numOfPieces = 0;

        for( int row = 0; row < BOARD_SIDE; row++ ) {
            for( int col = 0; col < BOARD_SIDE; col++ ) {
                Piece piece = this.board[row][col].getPiece();
                if( piece != null && piece.getColor() == color ) {
                    numOfPieces++;
                }
            }
        }

        return numOfPieces;

    }

    /**
     * Checks to see if the game is finisheds\\
     * @return if the game is finished
     */
    public boolean finishedGame() {
        return playerPiecesLeft( Piece.COLOR.RED ) == 0 || playerPiecesLeft( Piece.COLOR.WHITE ) == 0;
    }

    /**
     * Returns the color of the player who won
     * @return the color of the winning player
     */
    public Player.PlayerColor winnerColor() {
        if( finishedGame() ) {
            if( playerPiecesLeft( Piece.COLOR.RED ) == 0 ) {
                return Player.PlayerColor.WHITE;
            } else {
                return Player.PlayerColor.RED;
            }
        } else {
            return Player.PlayerColor.NONE;
        }
    }

    /**
     * Copies the another board onto this board
     * @param source the board to be copied
     */
    public void copyBoard( Board source ) {
        Space[][] sourceBoard = source.getBoard();
        for( int row = 0; row < BOARD_SIDE; row++ ) {
            for( int col = 0; col < BOARD_SIDE; col++ ) {
                this.board[row][col] = sourceBoard[row][col].copySpace();
            }
        }
    }


    /**
     * Kings pieces that are in the opposing end zone
     */
    public void kingPieces() {
        int row = 0;
        Piece piece;
        for( int col = 0; col < BOARD_SIDE; col++ ) {
            piece = this.board[row][col].getPiece();

            if( piece != null && piece.getColor() == Piece.COLOR.WHITE ) {
                this.board[row][col].setPiece( new Piece( Piece.TYPE.KING, Piece.COLOR.WHITE ) );
            }
        }

        row = 7;
        for( int col = 0; col < BOARD_SIDE; col++ ) {
            piece = this.board[row][col].getPiece();

            if( piece != null && piece.getColor() == Piece.COLOR.RED ) {
                this.board[row][col].setPiece( new Piece( Piece.TYPE.KING, Piece.COLOR.RED ) );
            }
        }

    }

}


