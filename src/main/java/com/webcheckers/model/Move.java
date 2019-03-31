package com.webcheckers.model;

public class Move {

    private Space[][] checkerBoard;
    private Position start;
    private Position end;

    public Move( Space[][] board ) {
        this.checkerBoard = board;
        this.start = null;
        this.end = null;
    }

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

    private boolean checkSpace( Position pos ) {
        return this.checkerBoard[pos.getRow()][pos.getCell()].isValid();
    }

    private boolean differentPieces( Position firstPos, Position secondPos ) {
        Piece firstPiece = this.checkerBoard[firstPos.getRow()][firstPos.getCell()].getPiece();
        Piece secondPiece = this.checkerBoard[secondPos.getRow()][firstPos.getCell()].getPiece();

        System.out.println("firstPiece: " + firstPiece);
        System.out.println("secondPiece: " + secondPiece);

        return firstPiece.getColor() != secondPiece.getColor();
    }

    public boolean validSimpleMove( Position startingPos, Position endingPos ) {
        int diffRow = Math.abs( startingPos.getRow() - endingPos.getRow() );
        int diffCell = Math.abs( startingPos.getCell() - endingPos.getCell() );

        if ( ( diffRow == 1 && diffCell == 1 ) && !startingPos.equals( endingPos ) &&
                !checkSpace( startingPos ) && checkSpace( endingPos ) ) {
            this.start = startingPos;
            this.end = endingPos;

            return true;
        } else {
            return false;
        }
    }

    public Position validSimpleJump( Position startingPos, Position endingPos ) {
        int diffRow = Math.abs( startingPos.getRow() - endingPos.getRow() );
        int diffCell = Math.abs( startingPos.getCell() - endingPos.getCell() );
        Position between = startingPos.between( endingPos );

        System.out.println("start vals: ");
        System.out.println("\trow: " + startingPos.getRow());
        System.out.println("\tcell: " + startingPos.getCell());
        System.out.println("between vals: ");
        System.out.println("\trow: " + between.getRow());
        System.out.println("\tcell: " + between.getCell());
        System.out.println("end vals: ");
        System.out.println("\trow: " + endingPos.getRow());
        System.out.println("\tcell: " + endingPos.getCell());

//        System.out.println("between pos: " + between);
//        System.out.println("checkSpace: " + checkSpace(between));
        System.out.println("between space: " + this.checkerBoard[between.getRow()][between.getCell()]);
        System.out.println("between piece: " + this.checkerBoard[between.getRow()][between.getCell()].getPiece());

        if ( ( diffRow == 2 && diffCell == 2 ) && !startingPos.equals( endingPos ) &&
                !checkSpace( startingPos ) && !checkSpace( between ) && checkSpace( endingPos ) ) {
            Piece betweenPiece = this.checkerBoard[between.getRow()][between.getCell()].getPiece();
            Piece startingPiece = this.checkerBoard[startingPos.getRow()][startingPos.getCell()].getPiece();
            if( betweenPiece.getColor() != startingPiece.getColor() ) {
                this.start = startingPos;
                this.end = endingPos;


                return between;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


}
