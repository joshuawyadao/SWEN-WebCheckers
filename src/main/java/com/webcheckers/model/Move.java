package com.webcheckers.model;

public class Move {

    private Board checkerBoard;
    private Position start;
    private Position end;

    public Move(Board board) {
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

    private boolean checkStartSpace( Position startingPos ) {
        Space[][] board = this.checkerBoard.getBoard();
        return !board[startingPos.getRow()][startingPos.getCell()].isValid();
    }

    private boolean checkEndSpace ( Position endingPos ) {
        Space[][] board = this.checkerBoard.getBoard();
        return board[endingPos.getRow()][endingPos.getCell()].isValid();
    }

    public boolean validSimpleMove( Position startingPos, Position endingPos ) {
        int diffRow = Math.abs( startingPos.getRow() - endingPos.getRow() );
        int diffCell = Math.abs (startingPos.getCell() - endingPos.getCell() );

        if ( ( diffRow == 1 && diffCell == 1 ) && !startingPos.equals( endingPos ) &&
                checkStartSpace( startingPos ) &&
                checkEndSpace( endingPos ) ) {
            this.start = startingPos;
            this.end = endingPos;
            return true;
        } else {
            return false;
        }
    }

    public boolean validSimpleJump( Position startingPos, Position endingPos ) {
        int diffRow = Math.abs( startingPos.getRow() - endingPos.getRow() );
        int diffCell = Math.abs (startingPos.getCell() - endingPos.getCell() );

        if ( ( diffRow == 2 && diffCell == 2 ) && !startingPos.equals( endingPos ) &&
                checkStartSpace( startingPos ) &&
                checkEndSpace( endingPos ) ) {
            this.start = startingPos;
            this.end = endingPos;
            return true;
        } else {
            return false;
        }
    }


}
