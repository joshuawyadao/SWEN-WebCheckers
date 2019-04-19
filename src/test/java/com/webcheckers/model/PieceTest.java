package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    private Piece CuT;

    @BeforeEach
    void setUp() {
        CuT = new Piece(Piece.TYPE.SINGLE, Piece.COLOR.RED);
    }

    @Test
    void testGetType() {
        assertEquals(Piece.TYPE.SINGLE, CuT.getType());
    }

    @Test
    void testGetColor() {
        assertEquals(Piece.COLOR.RED, CuT.getColor());
    }

    @Test
    void testEquals() {
        assertTrue( CuT.equals(CuT) );
        assertFalse( CuT.equals( new Board()) );
        Piece piece = new Piece(Piece.TYPE.SINGLE, Piece.COLOR.RED);
        assertTrue( CuT.equals(piece) );
    }

    @Test
    void testHashCode() {
        int hashCode = (int)Math.pow(Piece.COLOR.RED.hashCode() , Piece.TYPE.SINGLE.hashCode());
        assertEquals(hashCode, CuT.hashCode());
    }

}