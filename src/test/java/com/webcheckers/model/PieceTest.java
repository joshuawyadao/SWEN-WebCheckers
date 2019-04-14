package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    private Piece CuT;

    @BeforeEach
    void setUp(){
        this.CuT = new Piece(Piece.TYPE.SINGLE, Piece.COLOR.RED);
    }

    @Test
    void getType() {
        assertEquals(Piece.TYPE.SINGLE, CuT.getType());
    }

    @Test
    void getColor() {
        assertEquals(Piece.COLOR.RED, CuT.getColor());
    }

    @Test
    void equals() {
        Piece kingPiece = new Piece(Piece.TYPE.KING, Piece.COLOR.RED);
        Piece piece = new Piece(Piece.TYPE.SINGLE, Piece.COLOR.RED);
        Piece whitePiece = new Piece(Piece.TYPE.SINGLE, Piece.COLOR.WHITE);
        Piece kingWPiece = new Piece(Piece.TYPE.KING, Piece.COLOR.WHITE);
        Board board = new Board();

        assertFalse( CuT.equals(board) );
        assertTrue( CuT.equals(piece) );
        assertFalse( CuT.equals(kingPiece) );
        assertFalse( CuT.equals(whitePiece) );
        assertFalse( CuT.equals(kingWPiece) );

    }

}