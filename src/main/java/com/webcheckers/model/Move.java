package com.webcheckers.model;

import javafx.geometry.Pos;

import java.text.ParsePosition;

public class Move {

    private Position start;
    private Position end;

    public Move(){}

    /**
     * The starting position of the move
     * @return the position
     */
    public Position getStart() {
        return this.start;
    }

    /**
     * The ending position of the move
     * @return the position
     */
    public Position getEnd() {
        return this.end;
    }

    public void movePiece( Position startingPos, Position endingPos ) {
        if( validMove( startingPos, endingPos ) ) {

        }
    }

    private boolean validMove( Position startingPos, Position endingPos ) {
        return true;
    }


}
