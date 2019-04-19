package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board CuT;
    private Board CuTNoPieces;

    @BeforeEach
    void setUp() {
        CuTNoPieces = new Board(false);
        CuT = new Board( true);
    }

    @Test
    void getBoard() {
        assertNotNull(CuT.getBoard());
    }

    @Test
    void getRow() {
        for(int i = 0; i < 8; i++){
            assertNotNull(CuT.getRow(i));
        }

    }

    @Test
    void equals() {
        assertFalse( CuT.equals(CuTNoPieces) );
        assertTrue( CuT.equals(CuT) );
    }


    @Test
    void redPlayerFinishedGame(){
        Space[][] testSpaces = CuTNoPieces.getBoard();
        testSpaces[0][1] = new Space(1
                , new Piece(Piece.TYPE.SINGLE, Piece.COLOR.WHITE)
                , Space.COLOR.DARK);

        assertTrue( CuTNoPieces.finishedGame() );
    }

    @Test
    void whitePlayerFinishedGame(){
        Space[][] testSpaces = CuTNoPieces.getBoard();
        testSpaces[0][3] = new Space(1
                , new Piece(Piece.TYPE.SINGLE, Piece.COLOR.RED)
                , Space.COLOR.DARK);

        assertTrue( CuTNoPieces.finishedGame() );

    }

    @Test
    void redWinner() {
        Space[][] testSpaces = CuTNoPieces.getBoard();
        testSpaces[0][3] = new Space(1
                , new Piece(Piece.TYPE.SINGLE, Piece.COLOR.RED)
                , Space.COLOR.DARK);

        assertTrue( CuTNoPieces.finishedGame() );

        assertEquals(CuTNoPieces.winnerColor(), Player.PlayerColor.RED);

    }

    @Test
    void whiteWinner() {
        Space[][] testSpaces = CuTNoPieces.getBoard();
        testSpaces[0][1] = new Space(1
                , new Piece(Piece.TYPE.SINGLE, Piece.COLOR.WHITE)
                , Space.COLOR.DARK);

        assertTrue( CuTNoPieces.finishedGame() );

        assertEquals(CuTNoPieces.winnerColor(), Player.PlayerColor.WHITE);

    }

    @Test
    void noWinner() {
        Space[][] testSpaces = CuT.getBoard();
        assertFalse( CuT.finishedGame() );
        assertEquals( Player.PlayerColor.NONE, CuT.winnerColor() );

    }

    @Test
    void kingPieces() {
        Space[][] testSpaces = CuTNoPieces.getBoard();
        Piece pieceTest = new Piece(Piece.TYPE.SINGLE, Piece.COLOR.WHITE);
        testSpaces[0][1] = new Space(1
                , pieceTest
                , Space.COLOR.DARK);

        assertEquals(testSpaces[0][1].getPiece().getType(), Piece.TYPE.SINGLE);

        CuTNoPieces.kingPieces();

        assertEquals(testSpaces[0][1].getPiece().getType(), Piece.TYPE.KING);
    }

    @Test
    void testSetUpEndGame() {
        CuT.setUpEndGame();
    }

    @Test
    void testSetUpMultiJump() {
        CuT.setUpMultiJump();
    }

    @Test
    void testSetUpKingPiece() {
        CuT.setUpKingPiece();
    }

}