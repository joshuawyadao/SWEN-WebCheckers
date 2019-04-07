package com.webcheckers.model;

public class Move {

    public enum TYPE_OF_MOVE {
        SIMPLE,
        JUMP,
        ERROR
    }

   private enum MOVE_COLOR {
        RED,
        WHITE,
        EMPTY
    }

    private Position start;
    private Position end;


    public TYPE_OF_MOVE typeOfMove( Piece piece ) {
        int difference = this.end.getRow() - this.start.getRow();
        TYPE_OF_MOVE moveType;
        MOVE_COLOR moveColor;

        switch (difference) {
            case 1:
                moveType = TYPE_OF_MOVE.SIMPLE;
                moveColor = MOVE_COLOR.RED;
                break;
            case 2:
                moveType = TYPE_OF_MOVE.JUMP;
                moveColor = MOVE_COLOR.RED;
                break;
            case -1:
                moveType = TYPE_OF_MOVE.SIMPLE;
                moveColor = MOVE_COLOR.WHITE;
                break;
            case -2:
                moveType = TYPE_OF_MOVE.JUMP;
                moveColor = MOVE_COLOR.WHITE;
                break;
            default:
                moveType = TYPE_OF_MOVE.ERROR;
                moveColor = MOVE_COLOR.EMPTY;
        }

        if( piece.getType() == Piece.TYPE.SINGLE ) { // if a single piece is moving the correct direction
            Piece.COLOR pieceColor = piece.getColor();
            if( pieceColor == Piece.COLOR.RED && moveColor != MOVE_COLOR.RED ) {
                return TYPE_OF_MOVE.ERROR;
            }
            if( pieceColor == Piece.COLOR.WHITE && moveColor != MOVE_COLOR.WHITE ) {
                return TYPE_OF_MOVE.ERROR;
            }
        }

        /*
         * assume either:
         *      Piece is single and moving correct direction
         *      Piece is king and can move any direction
         */

        return moveType;
    }
    /**
     * The starting position of the move
     * @return the position
     */
    public Position getStart() {
        return this.start;
    }

    /**
     * The ending position of the move
     * @return the position
     */
    public Position getEnd() {
        return this.end;
    }

    public int getStartRow() {
        return this.start.getRow();
    }

    public int getStartCell() {
        return this.start.getCell();
    }

    public int getEndRow() {
        return this.end.getRow();
    }

    public int getEndCell() {
        return this.end.getCell();
    }

    /**
     *
     * @param board
     * @return
     */
    public boolean validSimpleMove( Board board ) {
        Space[][] checkerBoard = board.getBoard();

        if ( !checkerBoard[getStartRow()][getStartCell()].isValid() && checkerBoard[getEndRow()][getEndCell()].isValid() ) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param board
     * @return
     */
    public Position validSimpleJump( Board board ) {
        Position between = this.start.between( this.end );
        Space[][] checkerBoard = board.getBoard();

        if ( !checkerBoard[getStartRow()][getStartCell()].isValid() && // checks if piece at start
             !checkerBoard[between.getRow()][between.getCell()].isValid() && // checks if piece in between
              checkerBoard[getEndRow()][getEndCell()].isValid() ) { // checks if no piece at end

            Piece betweenPiece = checkerBoard[between.getRow()][between.getCell()].getPiece();
            Piece startingPiece = checkerBoard[getStartRow()][getStartCell()].getPiece();

            if (betweenPiece.getColor() != startingPiece.getColor()) { // checks if pieces are different color
                return between;
            }
        }
        return null;

    }

}
