package com.webcheckers.model;

public class Position {

    private int row;  // { 0 to 7 }
    private int cell;  // { 0 to 7 }

    public Position( int row, int cell ) {
        this.row = row;
        this.cell = cell;
    }

    /**
     * The row index of this position
     * @return an int from 0 to 7
     */
    public int getRow() {
        return this.row;
    }


    /**
     * The cell (column) index of this position
     * @return an int from 0 to 7
     */
    public int getCell() {
        return this.cell;
    }

    public boolean equals( Object pos ) {
        if(pos == this) return true;
        if(!(pos instanceof Position)) return false;

        final Position that = (Position) pos;

        return this.row == that.getRow() && this.cell == that.getCell();
    }
}
