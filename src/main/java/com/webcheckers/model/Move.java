package com.webcheckers.model;

public class Move {

    private Position start;
    private Position end;

    private final int ORIGIN = 0;

    public Move() {
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

//    /**
//     * Sets start and end to a Position value if move is validated
//     * Otherwise start and end remain null
//     * @param startingPos
//     * @param endingPos
//     */
//    public void movePiece( Position startingPos, Position endingPos ) {
//        if( validMove( startingPos, endingPos ) ) {
//            this.start = startingPos;
//            this.end = endingPos;
//        }
//    }

    /**
     * Only covering basic movement for now
     * @param startingPos
     * @param endingPos
     * @return
     */
    private boolean validMove( Position startingPos, Position endingPos ) {
        int diffRow = Math.abs( startingPos.getRow() - endingPos.getRow() );
        int diffCell = Math.abs (startingPos.getCell() - endingPos.getCell() );

        if ( ( diffRow <= 1 && diffCell <= 1 ) && !startingPos.equals( endingPos ) ) {
            this.start = startingPos;
            this.end = endingPos;
            return true;
        } else {
            return false;
        }
    }


}
