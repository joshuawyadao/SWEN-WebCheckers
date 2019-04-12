package com.webcheckers.model;

import javafx.geometry.Pos;
import org.junit.jupiter.api.Test;

import javax.management.loading.ClassLoaderRepository;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    @Test
    public void testMoveNullity() {
        final Space[][] board = new Space[8][8];
        final Move CuT = new Move( board );

        assertNotNull( CuT, "Move is not null");
    }

    @Test
    public void testValidSimpleMoveTrueRED_RIGHT() {
        final Board board = new Board();
        final Position startPos = new Position(2, 1);
        final Position endPos = new Position(3,2);

        final Move CuT = new Move(board.getBoard());

        assertTrue(CuT.validSimpleMove(startPos, endPos), "Simple Move is true");
    }

    @Test
    public void testValidSimpleMoveTooLargeJumpRED_RIGHT() {
        final Board board = new Board();
        final Position startPos = new Position(2, 1);
        final Position endPos = new Position(4,2);

        final Move CuT = new Move(board.getBoard());

        assertFalse(CuT.validSimpleMove(startPos, endPos), "Simple Move is false");
    }

    @Test
    public void testValidSimpleMoveNotPieceRED_RIGHT() {
        final Board board = new Board();
        final Position startPos = new Position(2, 2);
        final Position endPos = new Position(2,3);

        final Move CuT = new Move(board.getBoard());

        assertFalse(CuT.validSimpleMove(startPos, endPos), "Simple Move is false");
    }

    @Test
    public void testValidSimpleJumpTrueRED_RIGHT() {
        final Board board = new Board();
        final Position startPos = new Position(2, 1);
        final Position endPos = new Position(4,3);
        board.getBoard()[3][2].setPiece(new Piece(Piece.TYPE.SINGLE, Piece.COLOR.WHITE));

        final Move CuT = new Move(board.getBoard());
        final Position between = new Position(3, 2);

        assertEquals(between, CuT.validSimpleJump(startPos, endPos), "Simple Jump equals between");
    }


    @Test
    public void testValidSimpleJumpTooFarRED_RIGHT() {
        final Board board = new Board();
        final Position startPos = new Position(2, 1);
        final Position endPos = new Position(5,4);
        board.getBoard()[3][2].setPiece(new Piece(Piece.TYPE.SINGLE, Piece.COLOR.WHITE));

        final Move CuT = new Move(board.getBoard());

        assertNull(CuT.validSimpleJump(startPos, endPos), "Simple Jump too large");
    }


    @Test
    public void testValidSimpleJumpSameColorRED_RIGHT() {
        final Board board = new Board();
        final Position startPos = new Position(2, 1);
        final Position endPos = new Position(4,3);
        board.getBoard()[3][2].setPiece(new Piece(Piece.TYPE.SINGLE, Piece.COLOR.RED));

        final Move CuT = new Move(board.getBoard());

        assertNull(CuT.validSimpleJump(startPos, endPos), "Simple Jump equals between");
    }

    @Test
    public void testValidSimpleMoveTrueRED_LEFT() {
        final Board board = new Board();
        final Position startPos = new Position(2, 5);
        final Position endPos = new Position(3,4);

        final Move CuT = new Move(board.getBoard());

        assertTrue(CuT.validSimpleMove(startPos, endPos), "Simple Move is true");
    }

    @Test
    public void testValidSimpleMoveTooLargeJumpRED_LEFT() {
        final Board board = new Board();
        final Position startPos = new Position(2, 5);
        final Position endPos = new Position(4,3);

        final Move CuT = new Move(board.getBoard());

        assertFalse(CuT.validSimpleMove(startPos, endPos), "Simple Move is false");
    }

    @Test
    public void testValidSimpleMoveNotPieceRED_LEFT() {
        final Board board = new Board();
        final Position startPos = new Position(2, 4);
        final Position endPos = new Position(3,3);

        final Move CuT = new Move(board.getBoard());

        assertFalse(CuT.validSimpleMove(startPos, endPos), "Simple Move is false");
    }

    @Test
    public void testValidSimpleJumpTrueRED_LEFT() {
        final Board board = new Board();
        final Position startPos = new Position(2, 5);
        final Position endPos = new Position(4,3);
        board.getBoard()[3][4].setPiece(new Piece(Piece.TYPE.SINGLE, Piece.COLOR.WHITE));

        final Move CuT = new Move(board.getBoard());
        final Position between = new Position(3, 4);

        assertEquals(between, CuT.validSimpleJump(startPos, endPos), "Simple Jump equals between");
    }


    @Test
    public void testValidSimpleJumpTooFarRED_LEFT() {
        final Board board = new Board();
        final Position startPos = new Position(2, 5);
        final Position endPos = new Position(5,2);
        board.getBoard()[3][4].setPiece(new Piece(Piece.TYPE.SINGLE, Piece.COLOR.WHITE));

        final Move CuT = new Move(board.getBoard());

        assertNull(CuT.validSimpleJump(startPos, endPos), "Simple Jump too large");
    }


    @Test
    public void testValidSimpleJumpSameColorRED_LEFT() {
        final Board board = new Board();
        final Position startPos = new Position(2, 5);
        final Position endPos = new Position(4,3);
        board.getBoard()[3][4].setPiece(new Piece(Piece.TYPE.SINGLE, Piece.COLOR.RED));

        final Move CuT = new Move(board.getBoard());

        assertNull(CuT.validSimpleJump(startPos, endPos), "Simple Jump equals between");
    }

    @Test
    public void testValidSimpleMoveTrueWHITE_RIGHT() {
        final Board board = new Board();
        final Position startPos = new Position(5, 2);
        final Position endPos = new Position(4,3);

        final Move CuT = new Move(board.getBoard());

        assertTrue(CuT.validSimpleMove(startPos, endPos), "Simple Move is true");
    }

    @Test
    public void testValidSimpleMoveTooLargeJumpWHITE_RIGHT() {
        final Board board = new Board();
        final Position startPos = new Position(5, 2);
        final Position endPos = new Position(3,4);

        final Move CuT = new Move(board.getBoard());

        assertFalse(CuT.validSimpleMove(startPos, endPos), "Simple Move is false");
    }

    @Test
    public void testValidSimpleMoveNotPieceWHITE_RIGHT() {
        final Board board = new Board();
        final Position startPos = new Position(5, 3);
        final Position endPos = new Position(4,4);

        final Move CuT = new Move(board.getBoard());

        assertFalse(CuT.validSimpleMove(startPos, endPos), "Simple Move is false");
    }

    @Test
    public void testValidSimpleJumpTrueWHITE_RIGHT() {
        final Board board = new Board();
        final Position startPos = new Position(5, 2);
        final Position endPos = new Position(3,4);
        board.getBoard()[4][3].setPiece(new Piece(Piece.TYPE.SINGLE, Piece.COLOR.RED));

        final Move CuT = new Move(board.getBoard());
        final Position between = new Position(4, 3);

        assertEquals(between, CuT.validSimpleJump(startPos, endPos), "Simple Jump equals between");
    }


    @Test
    public void testValidSimpleJumpTooFarWHITE_RIGHT() {
        final Board board = new Board();
        final Position startPos = new Position(5, 2);
        final Position endPos = new Position(2,5);
        board.getBoard()[4][3].setPiece(new Piece(Piece.TYPE.SINGLE, Piece.COLOR.RED));

        final Move CuT = new Move(board.getBoard());

        assertNull(CuT.validSimpleJump(startPos, endPos), "Simple Jump too large");
    }


    @Test
    public void testValidSimpleJumpSameColorWHITE_RIGHT() {
        final Board board = new Board();
        final Position startPos = new Position(5, 2);
        final Position endPos = new Position(3,4);
        board.getBoard()[4][3].setPiece(new Piece(Piece.TYPE.SINGLE, Piece.COLOR.WHITE));

        final Move CuT = new Move(board.getBoard());

        assertNull(CuT.validSimpleJump(startPos, endPos), "Simple Jump equals between");
    }

    @Test
    public void testValidSimpleMoveTrueWHITE_LEFT() {
        final Board board = new Board();
        final Position startPos = new Position(5, 6);
        final Position endPos = new Position(4,5);

        final Move CuT = new Move(board.getBoard());

        assertTrue(CuT.validSimpleMove(startPos, endPos), "Simple Move is true");
    }

    @Test
    public void testValidSimpleMoveTooLargeJumpWHITE_LEFT() {
        final Board board = new Board();
        final Position startPos = new Position(5, 6);
        final Position endPos = new Position(3,4);

        final Move CuT = new Move(board.getBoard());

        assertFalse(CuT.validSimpleMove(startPos, endPos), "Simple Move is false");
    }

    @Test
    public void testValidSimpleMoveNotPieceWHITE_LEFT() {
        final Board board = new Board();
        final Position startPos = new Position(5, 5);
        final Position endPos = new Position(4,4);

        final Move CuT = new Move(board.getBoard());

        assertFalse(CuT.validSimpleMove(startPos, endPos), "Simple Move is false");
    }

    @Test
    public void testValidSimpleJumpTrueWHITE_LEFT() {
        final Board board = new Board();
        final Position startPos = new Position(5, 6);
        final Position endPos = new Position(3,4);
        board.getBoard()[4][5].setPiece(new Piece(Piece.TYPE.SINGLE, Piece.COLOR.RED));

        final Move CuT = new Move(board.getBoard());
        final Position between = new Position(4, 5);

        assertEquals(between, CuT.validSimpleJump(startPos, endPos), "Simple Jump equals between");
    }


    @Test
    public void testValidSimpleJumpTooFarWHITE_LEFT() {
        final Board board = new Board();
        final Position startPos = new Position(5, 6);
        final Position endPos = new Position(2,3);
        board.getBoard()[4][5].setPiece(new Piece(Piece.TYPE.SINGLE, Piece.COLOR.RED));

        final Move CuT = new Move(board.getBoard());

        assertNull(CuT.validSimpleJump(startPos, endPos), "Simple Jump too large");
    }


    @Test
    public void testValidSimpleJumpSameColorWHITE_LEFT() {
        final Board board = new Board();
        final Position startPos = new Position(5, 6);
        final Position endPos = new Position(3,4);
        board.getBoard()[4][3].setPiece(new Piece(Piece.TYPE.SINGLE, Piece.COLOR.WHITE));

        final Move CuT = new Move(board.getBoard());

        assertNull(CuT.validSimpleJump(startPos, endPos), "Simple Jump equals between");
    }

    @Test
    public void testGetStartNull() {
        final Space[][] board = new Space[8][8];
        final Move CuT = new Move( board );

        assertNull( CuT.getStart(), "Start is null");
    }

    @Test
    public void testGetStartRED_LEFT() {
        final Board board = new Board();
        final Position startPos = new Position(2, 1);
        final Position endPos = new Position(3,2);

        final Move CuT = new Move(board.getBoard());

        CuT.validSimpleMove(startPos, endPos);

        assertEquals(startPos, CuT.getStart(), "Start is saved correctly");
    }

    @Test
    public void testGetStartRED_RIGHT() {
        final Board board = new Board();
        final Position startPos = new Position(2, 1);
        final Position endPos = new Position(3,2);

        final Move CuT = new Move(board.getBoard());

        CuT.validSimpleMove(startPos, endPos);

        assertEquals( startPos, CuT.getStart(), "Start is saved correctly");
    }

    @Test
    public void testGetStartWHITE_LEFT() {
        final Board board = new Board();
        final Position startPos = new Position(5, 6);
        final Position endPos = new Position(4,5);

        final Move CuT = new Move(board.getBoard());

        CuT.validSimpleMove(startPos, endPos);

        assertEquals(startPos, CuT.getStart(), "Start is saved correctly");
    }

    @Test
    public void testGetStartWHITE_RIGHT() {
        final Board board = new Board();
        final Position startPos = new Position(5, 2);
        final Position endPos = new Position(4,3);

        final Move CuT = new Move(board.getBoard());

        CuT.validSimpleMove(startPos, endPos);

        assertEquals( startPos, CuT.getStart(), "Start is saved correctly");
    }

    @Test
    public void testGetEndNull() {
        final Space[][] board = new Space[8][8];
        final Move CuT = new Move( board );

        assertNull(CuT.getStart(), "End is null");
    }

    @Test
    public void testGetEndRED_LEFT() {
        final Board board = new Board();
        final Position startPos = new Position(2, 1);
        final Position endPos = new Position(3,2);

        final Move CuT = new Move(board.getBoard());

        CuT.validSimpleMove(startPos, endPos);

        assertEquals(endPos, CuT.getEnd(), "Start is saved correctly");
    }

    @Test
    public void testGetEndRED_RIGHT() {
        final Board board = new Board();
        final Position startPos = new Position(2, 1);
        final Position endPos = new Position(3,2);

        final Move CuT = new Move(board.getBoard());

        CuT.validSimpleMove(startPos, endPos);

        assertEquals( endPos, CuT.getEnd(), "Start is saved correctly");
    }

    @Test
    public void testGetEndWHITE_LEFT() {
        final Board board = new Board();
        final Position startPos = new Position(5, 6);
        final Position endPos = new Position(4,5);

        final Move CuT = new Move(board.getBoard());

        CuT.validSimpleMove(startPos, endPos);

        assertEquals(endPos, CuT.getEnd(), "Start is saved correctly");
    }

    @Test
    public void testGetEndWHITE_RIGHT() {
        final Board board = new Board();
        final Position startPos = new Position(5, 2);
        final Position endPos = new Position(4,3);

        final Move CuT = new Move(board.getBoard());

        CuT.validSimpleMove(startPos, endPos);

        assertEquals( endPos, CuT.getEnd(), "Start is saved correctly");
    }
}