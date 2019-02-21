package com.webcheckers.model;

import java.util.*;
import java.util.function.Consumer;

public class Row implements Iterable {

    private int row; // { 0 to 7 }

    /**
     * Index of this row within the board
     * @return int from 0 to 7
     */
    public int getIndex() {
        return 0;
    }

    /**
     * Creates a java Iterator of the Spaces within a single row
     * @return Iterator<Space>
     */
    @Override
    public Iterator<Space> iterator() {
        return null;
    }

}
