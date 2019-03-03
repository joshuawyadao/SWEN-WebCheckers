package com.webcheckers.model;

public class Space {

    enum COLOR { LIGHT, DARK }

    private int cellIdx; // { 0 to 7 }
    private Piece piece;
    private COLOR color;

    /**
     * General construtor for a space
     * @param cellIdx the cell number for the row
     * @param piece the piece on the space
     * @param color the color of the space
     */
    public Space( int cellIdx, Piece piece, COLOR color ) {
        this.cellIdx = cellIdx;
        this.piece = piece;
        this.color = color;
    }

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
        return ( this.color == COLOR.DARK && this.piece == null );
    }

    /**
     * The piece that resides on this space, if any
     * @return the piece
     */
    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece( Piece piece ) {
        this.piece = piece;
    }

    // delete later
    public COLOR getColor() { return this.color; }

}
