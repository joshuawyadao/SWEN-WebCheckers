package com.webcheckers.model;

public class Piece {

    enum TYPE { SINGLE, KING }
    enum COLOR { RED, WHITE }

    private TYPE type;
    private COLOR color;

    /**
     * Constructor for piece
     * @param pieceType the type of this piece
     * @param pieceColor the color of this piece
     */
    public Piece( TYPE pieceType, COLOR pieceColor ) {
        this.type = pieceType;
        this.color = pieceColor;
    }

    /**
     * The type of this piece
     * Enumeration values must exactly match case and spelling
     * an enumeration of { SINGLE or KING }
     * @return the type of piece
     */
    public TYPE getType() {
        return this.type;
    }

    /**
     * The color of this piece
     * Enumeration values must exactly match case and spelling
     * an enumeration of { RED or WHITE }
     * @return the color of the piece
     */
    public COLOR getColor() {
        return this.color;
    }



}
