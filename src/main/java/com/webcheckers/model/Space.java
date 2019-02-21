package com.webcheckers.model;

public class Space {

    private int cellIdx; // { 0 to 7 }

    /**
     * The index of this space (a cell within a row) within the board
     * int from 0 to 7
     * @return cell index
     */
    public int getCellIdx() {
        return this.cellIdx;
    }

    /**
     * The method will return true if the space is a valid location to place a piece
     * That is, it is a dark square and has no other piece on it
     * @return if valid
     */
    public boolean isValid() {
        return false;
    }

    /**
     * The piece that resides on this space, if any
     * @return the piece
     */
    public Piece getPiece() {
        return new Piece();
    }
}
