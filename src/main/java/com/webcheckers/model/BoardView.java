package com.webcheckers.model;

import java.util.Iterator;

public class BoardView implements Iterable {

    private Row[] rows;

    /**
     * Initializes board
     */
    public BoardView() {
        rows = new Row[8];
        int count = 1;
        Piece piece;
        Space[] spaces;
        Space.COLOR sColor;


        for( int index = 0; index <= 7; index++ ) {
            spaces = new Space[8];
            rows[index] = new Row( index, spaces );
            for( int cellId = 0; cellId <= 7; cellId++ ) {
                if ( count % 2 == 1 ) {
                    sColor = Space.COLOR.LIGHT;
                    piece = null;
                } else {
                    sColor = Space.COLOR.DARK;
//                    if( index < 2 ) {
                        piece = new Piece( Piece.TYPE.SINGLE, Piece.COLOR.RED );
//                    } else if( index > 5 ) {
//                        piece = new Piece( Piece.TYPE.SINGLE, Piece.COLOR.WHITE );
//                    } else {
//                        piece = null;
//                    }
                }
                spaces[cellId] = new Space( cellId, piece, sColor );
                if ( cellId != 7 ) {
                    count++;
                }
            }
        }
    }

    @Override
    public String toString() {
        String output = "";
        for (int index = 0; index <= 7; index++) {
            for (int cell = 0; cell <= 7; cell++) {
                Space[] spaces = rows[index].getSpaces();
                // output += ( "[ " + rows[index].getIndex() + ", " + spaces[cell].getCellIdx() + " ]" );
                if (spaces[cell].getPiece() != null) {
                    output += ("[ " + spaces[cell].getPiece().getColor() + " ]");
                } else {
                    output += ("[ empty ]");
                }
                //output += ( "[ " + spaces[cell].getColor() + " ]" );
            }
            output += "\n";

        }
        output += "\n";
        return output;
    }

    /**
     * Creates a java Iterator of the Rows on the checkers board
     * @return Iterator<Row>
     */
    @Override
    public Iterator<Row> iterator() {

        Iterator<Row> iterator = new Iterator<Row>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < 7;
            }

            @Override
            public Row next() {
                if(hasNext()) {
                    index++;
                    return rows[index];
                } else {
                    return rows[0];
                }
            }
        };

        return iterator;
    }
}
