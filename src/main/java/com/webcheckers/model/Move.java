package com.webcheckers.model;

public class Move {

    public Move(){}

    public void movePiece( Space src, Space dst ) {
        dst.setPiece( src.getPiece() );
        src.setPiece( null );
    }
}
