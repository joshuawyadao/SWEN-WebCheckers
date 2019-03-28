package com.webcheckers.model;

import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import com.webcheckers.model.Space;

public class Board {

    private Space[][] board;
    private final int BOARD_SIDE = 8;
    private final int RED_SIDE = 3;
    private final int WHITE_SIDE = 4;

    /**
     * BoardModel Constructor
     * Initializes the players for the game and sets up the board itself
     */
    public Board(){
        SetUpGame();
    }

    /**
     * Initializes the game board, specifically how the board looks
     * as well as the piece layout
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

        // Set up the pieces

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

    public String makeMove( Position startPos, Position endingPos ) {
        return "";
    }

    private int playerPiecesLeft( Piece.COLOR color ) {
        int numOfPieces = 0;

        for( int row = 0; row < BOARD_SIDE; row++ ) {
            for( int col = 0; col < BOARD_SIDE; col++ ) {
                if( this.board[row][col].getPiece().getColor() == color ) {
                    numOfPieces++;
                }
            }
        }

        return numOfPieces;

    }

    public boolean finishedGame() {
        return playerPiecesLeft( Piece.COLOR.RED ) == 0 || playerPiecesLeft( Piece.COLOR.WHITE ) == 0;
    }

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

}


