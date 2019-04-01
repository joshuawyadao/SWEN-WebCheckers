package com.webcheckers.model;

public class Player {

    public enum PlayerColor{
        RED,
        WHITE,
        NONE
    }

    private String name;
    private PlayerColor playerColor;
    private boolean playing;

    /**
     * Constructor of player
     * @param name the name of the player
     */
    public Player(String name){
        this.name = name;
        playerColor = PlayerColor.NONE;
        this.playing = false;
    }

    /**
     * Returns the name of this player
     * @return the name of this player
     */
    public String getName() {
        return name;
    }

    /**
     * Methods called whenever a player joins a game,
     *      it takes the player's color in as a parameter to
     *      distinguish which player is joining the game
     * @param playerColor the color of the player that is joining the game
     */
    public void joinGame(PlayerColor playerColor){
        if( playerColor != PlayerColor.NONE ) {
            this.playerColor = playerColor;
            this.playing = true;
        }
    }

    //method called when a game is over (either resigned
    //or ended naturally)

    /**
     *
     */
    public void leaveGame(){
        if ( this.isPlaying() ) {
            this.playerColor = PlayerColor.NONE;
            this.playing = false;
        }
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public boolean isPlaying() { return this.playing; }

    public Piece.COLOR playerColorToPieceColor() {
        if( this.playerColor == PlayerColor.RED ) {
            return Piece.COLOR.RED;
        } else if( this.playerColor == PlayerColor.WHITE ) {
            return Piece.COLOR.WHITE;
        } else {
            return null;
        }
    }

    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Player)) return false;

        final Player that = (Player) obj;

        return this.name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
