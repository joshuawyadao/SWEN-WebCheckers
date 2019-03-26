package com.webcheckers.model;

public class Move {

    public Move(){}

    public void movePiece( Space src, Space dst ) {
        if( validMove( src, dst ) ) {
            dst.setPiece(src.getPiece());
            src.setPiece(null);
        }
    }

    private boolean validMove( Space src, Space dst ) {
        return true;
    }
}
