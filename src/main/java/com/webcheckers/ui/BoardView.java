package com.webcheckers.ui;

import com.webcheckers.model.*;

import java.util.*;

public class BoardView implements Iterable<Row> {

    //constant for the max amount of rows in the board
    private final int MAX_ROWS = 8;
    private ArrayList<Row> rows = new ArrayList<>();

    public BoardView(Board board, boolean isRed){
        if(!isRed) {
            for( int rowNum = 0; rowNum < MAX_ROWS; rowNum++ ) {
                rows.add(new Row(rowNum, board.getRow(rowNum)));
            }
        } else {
            for( int rowNum = MAX_ROWS - 1; rowNum >= 0; rowNum-- ) {
                Space[] tempRow = board.getRow(rowNum);
                Space[] setRows = new Space[MAX_ROWS];
                for( int index = 0; index < MAX_ROWS; index++ ) {
                    setRows[index] = tempRow[MAX_ROWS - 1 - index];
                }
                rows.add(new Row(rowNum, setRows));
            }
        }
    }

    /**
     * Creates a java Iterator of the Rows on the checkers board
     * @return Iterator<Row>
     */
    @Override
    public Iterator<Row> iterator() {
        return rows.listIterator();
    }
}
