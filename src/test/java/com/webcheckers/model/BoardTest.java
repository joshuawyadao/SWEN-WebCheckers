package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-tier")
class BoardTest {

    private final int BOARD_SIDE = 8;
    private final int RED_SIDE = 3;
    private final int WHITE_SIDE = 4;

    private Board CuT;
    private Board CuTRed;
    private Board CuTWhite;

    @BeforeEach
    void setup() {
        this.CuT = new Board();
        this.CuTRed = new Board(false, true);
        this.CuTWhite = new Board( false, false );
    }

    @Test
    void getBoard() {
        assertNotNull(CuT.getBoard());
    }

    @Test
    void getRow() {
        assertNotNull(CuT.getRow(0));
    }

    @Test
    void validateMove() {

        Position redMoveSrc = new Position(2,1);
        Position redMoveDst = new Position( 3, 0);

        Position redJumpSrc = new Position(2, 3);
        Position redJumpDst = new Position( 4, 5);

        Position whiteMoveSrc = new Position(5, 0);
        Position whiteMoveDst = new Position( 4, 1);

        Position whiteJumpSrc = new Position(7, 0);
        Position whiteJumpDst = new Position( 5, 1);

        Piece redSingle = new Piece(Piece.TYPE.SINGLE, Piece.COLOR.RED);
        Piece whiteSingle = new Piece(Piece.TYPE.SINGLE, Piece.COLOR.WHITE);

        Piece redKing = new Piece(Piece.TYPE.KING, Piece.COLOR.RED);
        Piece whiteKing = new Piece(Piece.TYPE.KING, Piece.COLOR.WHITE);

        // Null Piece
        assertFalse(CuT.validateMove( redMoveSrc, redMoveDst, 0, null, CuT));


        // Red Invalid Move w/ -1
        assertFalse(CuT.validateMove( redJumpSrc, redJumpDst, -1, redSingle, CuT));

        // Red Invalid Jump w/ -2
        assertFalse(CuT.validateMove( redMoveSrc, redMoveDst, -2, redSingle, CuT));

        // Red Valid Move w/ -1
        assertTrue(CuTRed.validateMove( redMoveSrc, redMoveDst, -1, redSingle, CuTRed));

        // Red Valid Jump w/ -2
        assertTrue(CuTRed.validateMove( redJumpSrc, redJumpDst, -1, redSingle, CuTRed));


        // White Invalid Move w/ 1
        assertFalse(CuT.validateMove( whiteJumpSrc, whiteJumpDst, 1, whiteSingle, CuT));

        // White Invalid Jump w/ 2
        assertFalse(CuT.validateMove( whiteMoveSrc, whiteMoveDst, 2, whiteSingle, CuT));

        // White Valid Move w/ 1
        assertTrue(CuT.validateMove( whiteMoveSrc, whiteMoveDst, 1, whiteSingle, CuT));

        // White Valid Jump w/ 2
        assertTrue(CuT.validateMove( whiteJumpSrc, whiteJumpDst, 2, whiteSingle, CuT));

    }

    @Test
    void movePiece() {
    }

    @Test
    void finishedGame() {
        //assertTrue( CuTRed.finishedGame() );
        //assertTrue( CuTWhite.finishedGame() );
    }

    @Test
    void winnerColor() {
        //assertEquals(Player.PlayerColor.RED, CuTWhite.winnerColor());
        //assertEquals(Player.PlayerColor.WHITE, CuTRed.winnerColor());
    }

}