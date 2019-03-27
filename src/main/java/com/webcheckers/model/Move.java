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

    private boolean checkSpace( Position pos ) {
        return this.checkerBoard[pos.getRow()][pos.getCell()].isValid();
    }

    private boolean differentPieces( Position firstPos, Position secondPos ) {
        Piece firstPiece = this.checkerBoard[firstPos.getRow()][firstPos.getCell()].getPiece();
        Piece secondPiece = this.checkerBoard[secondPos.getRow()][firstPos.getCell()].getPiece();

        return firstPiece.getColor() != secondPiece.getColor();
    }

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

    public boolean validSimpleJump( Position startingPos, Position endingPos ) {
        int diffRow = Math.abs( startingPos.getRow() - endingPos.getRow() );
        int diffCell = Math.abs( startingPos.getCell() - endingPos.getCell() );
        Position between = null;

        if( startingPos.compare( endingPos ) == 1 ) {
            between = new Position(startingPos.getRow() + 1, startingPos.getCell() + 1);
        } else {
            between = new Position( startingPos.getRow() - 1, startingPos.getCell() - 1 );
        }

        if ( ( diffRow == 2 && diffCell == 2 ) && !startingPos.equals( endingPos ) &&
                !checkSpace( startingPos ) && !checkSpace( between ) && checkSpace( endingPos ) &&
                differentPieces( startingPos, between ) ) {
            this.start = startingPos;
            this.end = endingPos;

            return true;
        } else {
            return false;
        }
    }


}
