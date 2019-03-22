package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import spark.ModelAndView;

@Tag("Model-tier")
public class SpaceTest {

    private static final int DEFAULT_IDX = 0;
    private static final Piece DEFAULT_PIECE = null;
    private static final Space.COLOR DEFAULT_COLOR = Space.COLOR.DARK;
    private static final Piece DUMMY_PIECE = new Piece(Piece.TYPE.SINGLE, Piece.COLOR.RED);

    @Test
    public void testSpaceCreation(){
        final Space CuT = new Space(DEFAULT_IDX, DEFAULT_PIECE, DEFAULT_COLOR);
        assertNotNull(CuT, "Space is not null");
    }

    @Test
    public void testCellIdx(){
        final Space CuT = new Space(DEFAULT_IDX, DEFAULT_PIECE, DEFAULT_COLOR);
        assertEquals(DEFAULT_IDX, CuT.getCellIdx(), "Space CellIdx is 0");
    }

    @Test
    public void testGetPiece(){
        final Space CuT = new Space(DEFAULT_IDX, DUMMY_PIECE, DEFAULT_COLOR);
        assertEquals(new Piece(Piece.TYPE.SINGLE, Piece.COLOR.RED), CuT.getPiece(), "Space Piece is equal");
    }

//    @Test
//    public void testSpaceLightColor(){
//        final Space CuT = new Space(DEFAULT_IDX, DEFAULT_PIECE, Space.COLOR.LIGHT);
//        assertEquals(Space.COLOR.LIGHT, CuT.getColor(), "Space Light color works");
//    }
//
//    @Test
//    public void testSpaceDarkColor(){
//        final Space CuT = new Space (DEFAULT_IDX, DEFAULT_PIECE, DEFAULT_COLOR);
//        assertEquals(Space.COLOR.DARK, CuT.getColor(), "Space Dark color works");
//    }

}
