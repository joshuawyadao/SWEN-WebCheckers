package com.webcheckers.model;

import java.util.*;

public class Row implements Iterable {

    private int index; // { 0 to 7 }
    public final static int MAX_COLUMNS = 8;
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

    // delete later
    public Space[] getSpaces() { return this.spaces; }

    /**
     * Creates a java Iterator of the Spaces within a single row
     * @return Iterator<Space>
     */
    @Override
    public Iterator<Space> iterator() {

//        Iterator<Space> iterator = new Iterator<Space>() {
//
//            private int index = 0;
//
//            @Override
//            public boolean hasNext() {
//                return index < (MAX_COLUMNS - 1);
//            }
//
//            // FIX LATER!!!!
//            @Override
//            public Space next() {
//                if(hasNext()) {
//                    index++;
//                    return spaces[index];
//                } else {
//                    return spaces[0];
//                }
//            }
//        };

        //We can also make 'spaces' an List, instead of an array to
        //avoid this line before the return
        List<Space> spacesAsList = Arrays.asList(spaces);
        return spacesAsList.iterator();
    }

}
