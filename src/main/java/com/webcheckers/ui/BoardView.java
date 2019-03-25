package com.webcheckers.ui;

import com.webcheckers.model.Board;
import com.webcheckers.ui.Row;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class BoardView implements Iterable<Row> {

    //constant for the max amount of rows in the board
    private final int MAX_ROWS = 8;
    private ArrayList<Row> rows = new ArrayList<>();

    public BoardView(Board board, boolean isRed){

        for(int rowNum = 0; rowNum < MAX_ROWS; rowNum++){
            rows.add(new Row(rowNum, board.getRow(rowNum)));
        }

        if(!isRed){
            for(int rowNum = MAX_ROWS; rowNum >= 0; rowNum--){
                rows.add(new Row(rowNum, board.getRow(rowNum)));
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
