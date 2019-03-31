package com.webcheckers.model;

import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import com.webcheckers.model.Space;
import javafx.geometry.Pos;

import java.util.Stack;

public class Board {

    private final int BOARD_SIDE = 8;
    private final int RED_SIDE = 3;
    private final int WHITE_SIDE = 4;

    private Space[][] board;

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

    public boolean validateMove(Position startPos, Position endingPos, int typeOfMove, Piece piece, Board viewBoard ) {
        Move playerMove = new Move( viewBoard.getBoard() );
        boolean validMove = false;

        //CHANGED: add this if statement to prevent null pointer exeception when
        //moving a piece that this not currently in game's 'this.checkerboard'
        //this allows a simple to only occur oncein one turn
        if(piece == null)
            return false;

        if( piece.getType() == Piece.TYPE.SINGLE && piece.getColor() == Piece.COLOR.RED ) { // Red single move
            if (typeOfMove == -1) {
                if (playerMove.validSimpleMove(startPos, endingPos)) {
                    validMove = true;
                }
            } else if (typeOfMove == -2) {
                Position captured = playerMove.validSimpleJump(startPos, endingPos);
                if (captured != null) {
                    this.board[captured.getRow()][captured.getCell()].setPiece(null);
                    validMove = true;
                }
            }
        } else if( piece.getType() == Piece.TYPE.SINGLE && piece.getColor() == Piece.COLOR.WHITE ){ // White single move
            if (typeOfMove == 1) {
                if (playerMove.validSimpleMove(startPos, endingPos)) {
                    validMove = true;
                }
            } else if (typeOfMove == 2) {
                Position captured = playerMove.validSimpleJump(startPos, endingPos);
                if (captured != null) {
                    this.board[captured.getRow()][captured.getCell()].setPiece(null);
                    validMove = true;
                }
            }
        } else { // King movement
            if (Math.abs( typeOfMove ) == 1) {
                if (playerMove.validSimpleMove(startPos, endingPos)) {
                    validMove = true;
                }
            } else if (Math.abs(typeOfMove) == 2) {
                Position captured = playerMove.validSimpleJump(startPos, endingPos);
                if (captured != null) {
                    this.board[captured.getRow()][captured.getCell()].setPiece(null);
                    validMove = true;
                }
            }
        }
        return validMove;
    }

    public void movePiece( Position startPos, Position endingPos, Piece.COLOR pieceColor ) {
        this.board[endingPos.getRow()][endingPos.getCell()].setPiece( new Piece( Piece.TYPE.SINGLE, pieceColor ) );
        this.board[startPos.getRow()][startPos.getCell()].setPiece( null );
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

    public void copyBoard( Board source ) {
        Space[][] sourceBoard = source.getBoard();
        for( int row = 0; row < BOARD_SIDE; row++ ) {
            for( int col = 0; col < BOARD_SIDE; col++ ) {
                this.board[row][col] = sourceBoard[row][col].copySpace();
            }
        }
    }

}


