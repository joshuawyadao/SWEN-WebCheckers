package com.webcheckers.model;

public class Piece {

    private enum type { SINGLE, KING; }

    private enum color { RED, WHITE; }

    /**
     * The type of this piece
     * Enumeration values must exactly match case and spelling
     * an enumeration of { SINGLE or KING }
     * @return the type of piece
     */
    public type getType() {
        return type.SINGLE;
    }

    /**
     * The color of this piece
     * Enumeration values must exactly match case and spelling
     * an enumeration of { RED or WHITE }
     * @return the color of the piece
     */
    public color getColor() {
        return color.RED;
    }



}
