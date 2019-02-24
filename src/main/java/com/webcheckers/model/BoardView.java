package com.webcheckers.model;

import java.util.Iterator;

public class BoardView implements Iterable {

    private Row[] rows = new Row[8];

    public BoardView( Row[] rows ) {
        this.rows = rows;
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
                return index < 8 && rows[index] != null;
            }

            @Override
            public Row next() {
                index++;
                return rows[index];
            }
        };

        return iterator;
    }
}
