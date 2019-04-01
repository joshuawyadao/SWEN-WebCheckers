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
     * BoardModel Constructor
     * Initializes the players for the game and sets up the board itself
     */
    public Board(){
        SetUpGame();
    }

    public Board(boolean setUpPieces, boolean isRed){
        if( !setUpPieces ) {
            setUpBoard(isRed);
        }
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

    private void setUpBoard(boolean isRed){
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

        if(isRed){
            board[0][1] = new Space(1, new Piece(Piece.TYPE.SINGLE, Piece.COLOR.RED), Space.COLOR.DARK);
        } else {
            board[7][2] = new Space(2, new Piece(Piece.TYPE.SINGLE, Piece.COLOR.WHITE), Space.COLOR.DARK);
            board[6][1] = new Space(1, new Piece(Piece.TYPE.SINGLE, Piece.COLOR.RED), Space.COLOR.DARK);
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
     * Checks to see if the move was validated
     * @param startPos the starting position of the move
     * @param endingPos the ending position of the move
     * @param typeOfMove the type of move that was made
     *                   1: single move
     *                   2: single jump
     *                   -1: backwards single move
     *                   -2: backwards single jump
     * @param piece the piece to be validated
     * @param viewBoard the board to be used
     * @return if the move is valid
     */
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

    /**
     * Moves the piece on the board
     * @param startPos the start position of the movement
     * @param endingPos the ending position of the movement
     * @param pieceColor the color of the piece moved
     * @param type the type of piece moved
     */
    public void movePiece( Position startPos, Position endingPos, Piece.COLOR pieceColor, Piece.TYPE type ) {
        this.board[endingPos.getRow()][endingPos.getCell()].setPiece( new Piece( type, pieceColor ) );
        this.board[startPos.getRow()][startPos.getCell()].setPiece( null );
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
     * Goes through every piece on the board to see if any piece could have be captured when it was not
     * @return if the piece was move correctly
     */
    public boolean movedPieceCorrectly() {
        Position pos;
        for( int row = 0; row < BOARD_SIDE; row++ ) {
            for( int col = 0; col < BOARD_SIDE; col++ ) {
                if( this.board[row][col].getPiece() != null ) {
                    pos = new Position(row, col);
                    if (!pieceMovedCorrectDirection(pos)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Checks to see if a piece could have captured and did not
     * @param position the position of the piece
     * @return if the piece was moved correctly
     */
    public boolean pieceMovedCorrectDirection( Position position ) {
        List<Position> adjacentSpaces = adjacentSpaces( position );
        Piece currentPiece = this.board[position.getRow()][position.getCell()].getPiece();;

        for( Position pos : adjacentSpaces ) {
            Piece piece = this.board[pos.getRow()][pos.getCell()].getPiece();

            if( currentPiece.getColor() != piece.getColor() ) {
                return false;
            }
        }

        return true;
    }

    /**
     * Adds another Space object to a list of all spaces that could be captured,
     *      regardless of the color of both pieces
      * @param board the board to be looked at
     * @param row the row to be looked at
     * @param cell the cell to be looked at
     * @param rowAdd the additional rows to be looked at
     * @param cellAdd the additional cols to be looked at
     * @param adjList the adjacency list to be added onto
     */
    private void addAdjacentSpace( Space[][] board, int row, int cell, int rowAdd, int cellAdd, List<Position> adjList ) {
        if( !board[row + rowAdd][cell + cellAdd].isValid() && board[row + ( rowAdd * 2 )][cell + ( cellAdd * 2 )].isValid() ) {
            adjList.add( new Position( row + rowAdd, cell + cellAdd ) );
        }
    }

    /**
     * Check surrounding valid Spaces to see if there was a piece that could have been captured,
     *      regardless of the color of both pieces
     * @param position the position to be looked at
     * @return a list of possible positions to look at
     */
    public List<Position> adjacentSpaces( Position position ) {
        List<Position> adjacentSpaces = new ArrayList<>();
        int row = position.getRow();
        int cell = position.getCell();
        Piece piece = this.board[row][cell].getPiece();

        if ( piece.getColor() == Piece.COLOR.RED ) { // red pieces
            if( cell > 1 && cell < 6 && row < 6 && row > 1 ) { // left boundary
                addAdjacentSpace(this.board, row, cell, 1, 1, adjacentSpaces );
                addAdjacentSpace(this.board, row, cell, 1, -1, adjacentSpaces );
                addAdjacentSpace(this.board, row, cell, -1, -1, adjacentSpaces );
                addAdjacentSpace(this.board, row, cell, -1, 1, adjacentSpaces );
            }
            if( cell < 2 && row < 6 && row > 1 ) {
                addAdjacentSpace(this.board, row, cell, 1, 1, adjacentSpaces );
                addAdjacentSpace(this.board, row, cell, -1, 1, adjacentSpaces );
            }
            if( cell > 5 && row < 6 && row > 1 ) {
                addAdjacentSpace(this.board, row, cell, 1, -1, adjacentSpaces );
                addAdjacentSpace(this.board, row, cell, -1, -1, adjacentSpaces );
            }
        } else if( piece.getColor() == Piece.COLOR.WHITE ){ // white pieces
            if( cell > 1 && cell < 6 && row > 1 && row < 6 ) { // left boundary
                addAdjacentSpace(this.board, row, cell, -1, 1, adjacentSpaces );
                addAdjacentSpace(this.board, row, cell, -1, -1, adjacentSpaces );
                addAdjacentSpace(this.board, row, cell, 1, -1, adjacentSpaces );
                addAdjacentSpace(this.board, row, cell, 1, 1, adjacentSpaces );
            }
            if( cell < 2 && row > 1 && row < 6 ) {
                addAdjacentSpace(this.board, row, cell, -1, 1, adjacentSpaces );
                addAdjacentSpace(this.board, row, cell, -1, 1, adjacentSpaces );
            }
            if( cell > 5 && row > 1 ) {
                addAdjacentSpace(this.board, row, cell, -1, -1, adjacentSpaces );
                addAdjacentSpace(this.board, row, cell, -1, -1, adjacentSpaces );
            }
        }

        return adjacentSpaces;
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


