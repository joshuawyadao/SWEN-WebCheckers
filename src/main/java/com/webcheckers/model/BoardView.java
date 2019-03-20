package com.webcheckers.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class BoardView implements Iterable<Row> {

    //constant for the max amount of rows in the board
    private final int MAX_ROWS = 8;
    private Row[] rows = new Row[MAX_ROWS];

    /**
     * Initializes board
     */
    public BoardView() {
        Piece piece;
        Space[] spaces;
        Space.COLOR sColor;

        //changed some of the logic for this for loop to incorporate
        //the board's column and row max constants, as determining if a
        //a square is LIGHT OR DARK without the use another variable and
        //if- statement
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

        //We can also make 'rows' an List, instead of an array to
        //avoid this line before the return
        List<Row> rowsAsList = Arrays.asList(rows);
        return rowsAsList.iterator();
    }
}
