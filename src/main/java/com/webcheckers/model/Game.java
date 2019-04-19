package com.webcheckers.model;

import com.webcheckers.appl.GameCenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Game {
    private Player redPlayer, whitePlayer, activePlayer, resignedPlayer;
    private Board checkerBoard;
    private Stack<Board> previousMoves;
    private boolean validTurn;
    private ArrayList<Board> previousTurns;
    private String gameId;
    private HashMap<Player, Board> spectators;

    /**
     * Creates a new game instance
     * @param redPlayer the red player
     * @param whitePlayer the white player
     */
    public Game(Player redPlayer, Player whitePlayer, String gameId){
        this.redPlayer = redPlayer;
        this.activePlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.checkerBoard = setUpPreferences();
        this.previousMoves = new Stack<>();
        this.gameId = gameId;
        previousMoves.push(checkerBoard);
        this.resignedPlayer = null;
        this.validTurn = false;
        this.previousTurns = new ArrayList<>();
        previousTurns.add(checkerBoard);
        this.spectators = new HashMap<>();
    }

    /**
     * Sets up certain preferences if anyone has a key in the their name
     * @return the initial turn
     */
    public Board setUpPreferences() {
        Board setUp = new Board(false);
        String keyName = getKeyNames();

        switch (keyName) {
            case "END GAME":
                setUp.setUpEndGame();
                break;
            case "MULTI JUMP":
                setUp.setUpMultiJump();
                break;
            case "KING PIECE":
                setUp.setUpKingPiece();
                break;
            default:
                setUp = new Board();
                break;
        }
        return setUp;
    }

    //    Accessors

    /**
     * Returns the red player
     * @return the red player
     */
    public Player getRedPlayer() {
        return this.redPlayer;
    }

    /**
     * Returns the white player
     * @return the white player
     */
    public Player getWhitePlayer() {
        return this.whitePlayer;
    }

    /**
     * Gets the initial layout of the board before any player moves on their turn
     * @return the initial layout of the board
     */
    public Board getCheckerBoard(){
        return this.checkerBoard;
    }

    /**
     * Gets the current active player
     * @return the active player
     */
    public Player getActivePlayer() {
        return activePlayer;
    }

    /**
     * Gets the game Id of this game
     * @return the game Id
     */
    public String getGameId(){
        return gameId;
    }

    /**
     * Checks if a player is in this game
     * @param player the player to be examined
     * @return true if the player is in this game
     */
    public boolean isInGame(Player player) {
        return ((this.whitePlayer.equals(player)) || (this.redPlayer.equals(player)));
    }

    /**
     * Return the move recent turn
     * @return the board object of the most recent turn
     */
    public Board getRecentTurn() { return this.previousMoves.peek(); }

    /**
     * Gets the current player's color
     * @param currentPlayer the current player
     * @return the color of the current player
     */
    public Player.PlayerColor getPlayerColor( Player currentPlayer ){
        return currentPlayer.getPlayerColor();
    }

    /**
     * Gets the previous turns from this game
     * @return an array list of the previous turns
     */
    public ArrayList<Board> getPreviousTurns() {
        return this.previousTurns;
    }

    /**
     * Starts a game
     */
    public void initializeGame() {
        this.redPlayer.joinGame( Player.PlayerColor.RED );
        this.whitePlayer.joinGame( Player.PlayerColor.WHITE );
    }

    /**
     * Makes a move
     * @param move the move to be made
     * @return true if the move was sucessful
     */
    public boolean makeMove( Move move ) {
        Board turn = new Board();
        //CHANGED: 'getRecentedTurn()' to 'this.checkerboard' so that multiple single moves
        //are not longer allowed
        turn.copyBoard( this.previousMoves.peek() );
        Piece selectPiece = turn.getBoard()[move.getStartRow()][move.getStartCell()].getPiece();
        Move.TYPE_OF_MOVE moveType = move.typeOfMove(selectPiece);

        if( moveType == Move.TYPE_OF_MOVE.ERROR ) {
            return false;
        }


        switch ( moveType ) {
            case SIMPLE:
                if( move.validSimpleMove( turn ) && ( this.previousMoves.size() == 1 ) ) {
                    movePiece( move, turn, selectPiece );
                    this.previousMoves.push(turn);
                    this.validTurn = validateTurn();
                    return true;
                }
                break;
            case JUMP:
                Position between = move.validSimpleJump( turn );
                if( between != null ) {
                    movePiece( move, turn, selectPiece );
                    turn.getBoard()[between.getRow()][between.getCell()].setPiece( null );
                    this.previousMoves.push(turn);

                    Piece endPiece = previousMoves.peek().getBoard()[move.getEndRow()][move.getEndCell()].getPiece();
                    this.validTurn = !(canJump(move.getEnd(), previousMoves.peek(), getNeighbors(endPiece, move.getEndRow(), move.getEndCell())));
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;

    }


    /**
     * Moves a piece
     * @param move the move object
     * @param turn the board to have the piece moved
     * @param piece the piece to be moved
     */
    public void movePiece( Move move, Board turn, Piece piece ) {
        turn.getBoard()[move.getEndRow()][move.getEndCell()].setPiece( new Piece( piece.getType(), piece.getColor() ) );
        turn.getBoard()[move.getStartRow()][move.getStartCell()].setPiece( null );
    }

    /**
     * Checks if the piece could have jumped
     * @param startPos the start position
     * @param turn the board turn instance
     * @param neighbors all the neighboring spaces
     * @return true if the piece can jump
     */
    public boolean canJump( Position startPos, Board turn, Position[] neighbors ) {
        for( Position position : neighbors ) {
            if( position.inBounds() ) {
                Move move = new Move( startPos, position );
                Position between = move.validSimpleJump( turn );
                if( between != null ) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Gets all the valid positions that neighbor the space
     * @param piece the piece to check
     * @param row the row of the piece
     * @param col the col of the piece
     * @return an array of all the neighbors
     */
    public Position[] getNeighbors(Piece piece, int row, int col){
        Piece.TYPE pieceType = piece.getType();
        Piece.COLOR pieceColor = piece.getColor();

        if (pieceType == Piece.TYPE.SINGLE ) {
            if (pieceColor == Piece.COLOR.RED) {
                return new Position[]{new Position(row + 2, col - 2),
                        new Position(row + 2, col + 2)};
            }
            if (pieceColor == Piece.COLOR.WHITE) {
                return new Position[]{new Position(row - 2, col - 2),
                        new Position(row - 2, col + 2)};
            }
        }else if( pieceType == Piece.TYPE.KING ) {
            return new Position[]{new Position(row + 2, col - 2),
                    new Position(row + 2, col + 2),
                    new Position(row - 2, col - 2),
                    new Position(row - 2, col + 2)};
        }

        return null;
    }

    /**
     * Validates every turn by checking if any piece could be captured and was not
     * @return true if the move was valid
     */
    public boolean validateTurn() {
        Space[][] board = this.checkerBoard.getBoard();
        for( int row = 0; row < 8; row++ ) {
            for( int col = 0; col < 8; col++ ) {
                Space selectedSpace = board[row][col];

                if( selectedSpace.validSpaceWithPiece( this.activePlayer ) ) {

                    Piece selectedPiece = selectedSpace.getPiece();
                    Position[] neighbors = getNeighbors(selectedPiece, row, col);
                    Position startPos = new Position( row, col );

                    if( canJump( startPos, checkerBoard, neighbors ) ){
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Submits a turn to be checked and resets the previous moves stack
     * @return if the turn was a valid turn
     */
    public boolean submitTurn() {
        if(this.validTurn) {
            this.checkerBoard = this.previousMoves.pop();
            this.checkerBoard.kingPieces();
            this.previousTurns.add(checkerBoard);
            this.previousMoves = new Stack<>();
            this.previousMoves.push(checkerBoard);
            this.validTurn = false;

            if (this.activePlayer.equals(this.redPlayer)) {
                this.activePlayer = this.whitePlayer;
            } else {
                this.activePlayer = this.redPlayer;
            }

            return true;
        }else{
            return false;
        }
    }

    /**
     * Backups a single move
     * @return if the turn was successfully backed-up
     */
    public boolean backup(){
        Board previousMove = this.previousMoves.pop();
        this.validTurn = validateTurn();

        return previousMove != null;
    }

    /**
     * Updates the spectator's view
     * @param spectator the spectator to be examined
     * @return true if the view was successfully updated
     */
    public boolean updateSpectator(Player spectator){
        this.spectators.put(spectator, this.checkerBoard);

        return true;
    }

    /**
     * Gets the board that the spectator is currently viewing
     * @param spectator the spectator to be examined
     * @return the board that the spectator is viewing
     */
    public Board getSpectatorBoard(Player spectator){
        return this.spectators.get(spectator);
    }

    /**
     * Checks to see if the spectator's view was updated
     * @param spectator the spectator to be examined
     * @return true if their view was updated
     */
    public boolean isSpectatorUpdated(Player spectator){
        Board spectatorBoard = this.spectators.get(spectator);

        return spectatorBoard.equals(this.checkerBoard);
    }

    /**
     * Removes spectators from the list of spectators
     * @param spectator the spectator to be removed
     * @return true if the spectator was successfully removed
     */
    public boolean removeSpectator(Player spectator){
        this.spectators.remove(spectator);

        return true;
    }

    /**
     * Returns the player who won the game
     * @return the player who won
     */
    public Player completedGame() {
        if( this.checkerBoard.finishedGame() ) {
            Player.PlayerColor winnerColor = this.checkerBoard.winnerColor();
            if( winnerColor == Player.PlayerColor.RED ) {
                return this.redPlayer;
            } else {
                return this.whitePlayer;
            }
        }

        return null;
    }

    /**
     * Resigns a player
     * @param resignedPlayer the player to resign
     */
    public void playerResigned(Player resignedPlayer){
        this.resignedPlayer = resignedPlayer;
    }

    /**
     * Checks to see if there is a resigned player
     * @return if a player is resigned
     */
    public boolean isResigned(){
        return this.resignedPlayer != null;
    }

    /**
     * Returns the player that resigned
     * @return the resigned player
     */
    public Player getResignedPlayer(){
        return this.resignedPlayer;
    }

    /**
     * Checks if both players are currently in a game
     * @return true if both players are in a game
     */
    public boolean arePlayersInGame(){
        return this.redPlayer.isPlaying() && this.whitePlayer.isPlaying();
    }

    /**
     * Gets the game result message
     * @param currentUser the current user to display and compare
     * @return the game result message
     */
    public String getGameResult(Player currentUser){
        String gameResult;

        if(this.isResigned()){
            gameResult = this.resignedPlayer.getName() + " has resigned.";
        }else{
            Player winner = this.completedGame();
            Player loser;

            if(winner.equals(this.getRedPlayer())){
                loser = this.getWhitePlayer();
            }else{
                loser = this.getRedPlayer();
            }

            if(winner.equals(currentUser)){
                gameResult = "You have captured all of " + loser.getName()
                        + "'s pieces. Congratulations, you win!";
            }else if(loser.equals(currentUser)){
                gameResult = winner.getName() + " has captured all of your pieces. You lose.";
            }else{
                gameResult = winner.getName() + " has captured all of " + loser.getName() + "'s pieces "
                        + "and has won the game! Thank you for watching!";
            }
        }

        return gameResult;
    }

    /**
     * Gets the number of current spectators
     * @return the number of current spectator
     */
    public int getSpectatorNum(){
        return this.spectators.size();
    }

    /**
     * Gets the key names if any are present
     * @return "END GAME": if we want the end game scenario
     *         "MULTI JUMP": if we want the multi jump scenario
     *         "KING PIECE": if we want the king piece scenario
     *         "NONE": if there is no name that makes the key
     */
    private String getKeyNames() {
        String redName = this.redPlayer.getName();
        String whiteName = this.whitePlayer.getName();

        if( redOrWhitePlayerNameEquals( redName, whiteName, "END GAME" ) ) {
            return "END GAME";
        } else if( redOrWhitePlayerNameEquals( redName, whiteName, "MULTI JUMP" ) ) {
            return "MULTI JUMP";
        } else if( redOrWhitePlayerNameEquals( redName, whiteName, "KING PIECE" ) ) {
            return "KING PIECE";
        } else {
            return "NONE";
        }
    }

    /**
     * Checks to see if the red or white player's name equals a special key
     * @param redName the name of the Red Player
     * @param whiteName the name of the White Player
     * @param key the key to be compared with
     * @return true if at least one of their names matches
     */
    private boolean redOrWhitePlayerNameEquals(String redName, String whiteName, String key) {
        return redName.equals(key) || whiteName.equals(key);
    }

}
