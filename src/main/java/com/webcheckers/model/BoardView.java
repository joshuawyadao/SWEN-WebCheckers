package com.webcheckers.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class BoardView implements Iterable<Row> {

    private final int MAX_ROWS = 8;
    private Row[] rows = new Row[MAX_ROWS];

    /**
     * Initializes board
     */
    public BoardView() {
        Piece piece;
        Space[] spaces;
        Space.COLOR sColor;


        for(int index = 0; index < MAX_ROWS; index++ ) {
            spaces = new Space[Row.MAX_COLUMNS];
            rows[index] = new Row( index, spaces );

            for(int cellId = 0; cellId < Row.MAX_COLUMNS; cellId++ ) {

                if ( (index + cellId) % 2 == 0) {
                    sColor = Space.COLOR.LIGHT;
                    piece = null;
                } else {
                    sColor = Space.COLOR.DARK;

                    if( index < 3 ) {
                        piece = new Piece( Piece.TYPE.SINGLE, Piece.COLOR.WHITE );
                    } else if( index > 4 ) {
                        piece = new Piece( Piece.TYPE.SINGLE, Piece.COLOR.RED );
                    } else {
                        piece = null;
                    }
                }

                spaces[cellId] = new Space( cellId, piece, sColor );
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
                    if(spaces[cell].getPiece().getColor() == Piece.COLOR.RED)
                        output += ("[  " + spaces[cell].getPiece().getColor() + "  ]");
                    else
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

//        Iterator<Row> iterator = new Iterator<Row>() {
//
//            private int index = 0;
//
//            @Override
//            public boolean hasNext() {
//                return index < (MAX_ROWS - 1);
//            }
//
//            @Override
//            public Row next() {
//                if(hasNext()) {
//                    index++;
//                    return rows[index];
//                } else {
//                    return rows[0];
//                }
//            }
//        };

        List<Row> rowsAsList = Arrays.asList(rows);
        return rowsAsList.iterator();
    }
}
