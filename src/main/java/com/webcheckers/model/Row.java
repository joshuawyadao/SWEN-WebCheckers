package com.webcheckers.model;

import java.util.*;

public class Row implements Iterable {

    private int index; // { 0 to 7 }

    private Space[] spaces;

    /**
     * General construtor for a row
     * @param index the index of this row on the board
     * @param spaces the amount of spaces on this row
     */
    public Row( int index, Space[] spaces) {
        this.index = index;
        this.spaces = spaces;
    }

    /**
     * Index of this row within the board
     * @return int from 0 to 7
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Creates a java Iterator of the Spaces within a single row
     * @return Iterator<Space>
     */
    @Override
    public Iterator<Space> iterator() {

        Iterator<Space> iterator = new Iterator<Space>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < 64 && spaces[index] != null;
            }

            @Override
            public Space next() {
                index++;
                return spaces[index];
            }
        };

        return iterator;
    }

}
