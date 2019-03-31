package com.webcheckers.model;

import java.awt.image.SinglePixelPackedSampleModel;

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

    /**
     * Sets a piece on a space.
     * @param piece the piece to be placed
     */
    public void setPiece( Piece piece ) {
        this.piece = piece;
    }

    public Space copySpace() {
        Piece tempPiece;
        if( this.piece != null ) {
            tempPiece = new Piece( this.piece.getType(), this.piece.getColor() );
        } else {
            tempPiece = null;
        }
        return new Space( this.cellIdx, tempPiece, this.color );
    }

    @Override
    public boolean equals( Object other ) {
        if (this == other)return true;
        if (!(other instanceof Space))return false;
        Space that = (Space)other;
        if( this.piece == null && that.piece == null ) {
            return true;
        }
        if( that.piece == null || this.piece == null ) {
            return false;
        }
        return this.color == that.color && this.piece.equals(that.piece);
    }
}
