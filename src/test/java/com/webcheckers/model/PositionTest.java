package com.webcheckers.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    public void testPositionNullity() {
        final Position CuT = new Position(0, 0);

        assertNotNull( CuT, "Position is not null");
    }

    @Test
    public void testGetRow() {
        final Position CuT = new Position(1, 2);

        assertEquals(1, CuT.getRow(), "Row is set correctly");
    }

    @Test
    public void testGetCell() {
        final Position CuT = new Position(1, 2 );

        assertEquals(2, CuT.getCell(), "Cell is set correctly");
    }

    @Test
    public void testCompareLess() {
        final Position CuT = new Position(1,2 );
        final Position more = new Position( 2, 2 );

        assertEquals(1, CuT.compare(more), "CuT is less than more");
    }

    @Test
    public void testCompareMore() {
        final Position CuT = new Position(1, 2 );
        final Position less = new Position(0, 2 );

        assertEquals(-1, CuT.compare(less), "CuT is more than less");
    }

    @Test
    public void testDifferenceMore() {
        final Position CuT = new Position(3, 2 ) ;
        final Position more = new Position(1, 2 );

        assertEquals( 2, CuT.difference(more), "CuT is less than more");
    }

    @Test
    public void testDifferenceLess() {
        final Position CuT = new Position(0, 2 ) ;
        final Position less = new Position(2, 2 );

        assertEquals( -2, CuT.difference(less), "CuT is more than less");
    }

    @Test
    public void testBetweenHigh() {
        final Position CuT = new Position(2, 2 );
        final Position other = new Position(4, 4 );
        final Position between = new Position(3, 3 );

        assertEquals(between, CuT.between(other), "between is between CuT and other");
    }

    @Test
    public void testBetweenLow() {
        final Position CuT = new Position(2, 2 );
        final Position other = new Position(0, 0 );
        final Position between = new Position(1, 1 );

        assertEquals(between, CuT.between(other), "between is between CuT and other");
    }

    @Test
    public void testEqualsSameObject() {
        final Position CuT = new Position(1, 1 );

        assertTrue( CuT.equals(CuT), "CuT is the same object as CuT");
    }

    @Test
    public void testEqualsDifferentObject() {
        final Position CuT = new Position(1, 1);
        final int other = 1;

        assertFalse( CuT.equals(other), "CuT is not the same object");
    }

    @Test
    public void testEqualsSimilarObject() {
        final Position CuT = new Position(1, 1 );
        final Position same = new Position(1, 1 );

        assertTrue( CuT.equals(same), "CuT is equivalent as same");
    }
}