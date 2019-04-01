package com.webcheckers.model;

public class Move {

    private Space[][] checkerBoard;
    private Position start;
    private Position end;

    public Move( Space[][] board ) {
        this.checkerBoard = board;
        this.start = null;
        this.end = null;
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

    /**
     * Checks to see if the space at a certain position is valid
     * @param pos the position of the space
     * @return if the space is a valid space to move
     */
    private boolean checkSpace( Position pos ) {
        return this.checkerBoard[pos.getRow()][pos.getCell()].isValid();
    }

    /**
     * Checks the validity of a simple move and sets start and end if the move is valid
     * @param startingPos the starting position to be considered
     * @param endingPos the ending position to be considered
     * @return if the simple move is valid
     */
    public boolean validSimpleMove( Position startingPos, Position endingPos ) {
        int diffRow = Math.abs( startingPos.getRow() - endingPos.getRow() );
        int diffCell = Math.abs( startingPos.getCell() - endingPos.getCell() );

        if ( ( diffRow == 1 && diffCell == 1 ) && !startingPos.equals( endingPos ) &&
                !checkSpace( startingPos ) && checkSpace( endingPos ) ) {
            this.start = startingPos;
            this.end = endingPos;

            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks the validity of a simple jump move and sets start and if the move is valid
     * @param startingPos the starting position to be considered
     * @param endingPos the ending position to be considered
     * @return the position if the move is valid
     */
    public Position validSimpleJump( Position startingPos, Position endingPos ) {
        int diffRow = Math.abs( startingPos.getRow() - endingPos.getRow() );
        int diffCell = Math.abs( startingPos.getCell() - endingPos.getCell() );
        Position between = startingPos.between( endingPos );

        if ( ( diffRow == 2 && diffCell == 2 ) && !startingPos.equals( endingPos ) &&
                !checkSpace( startingPos ) && !checkSpace( between ) && checkSpace( endingPos ) ) {
            Piece betweenPiece = this.checkerBoard[between.getRow()][between.getCell()].getPiece();
            Piece startingPiece = this.checkerBoard[startingPos.getRow()][startingPos.getCell()].getPiece();
            if( betweenPiece.getColor() != startingPiece.getColor() ) {
                this.start = startingPos;
                this.end = endingPos;


                return between;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


}
