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


    public String getName() {
        return name;
    }

    //methods called whenever a player joins a game,
    //it takes the player's color in as a parameter to
    //distinguish which player is joining the game
    public void joinGame(PlayerColor playerColor){
        if( playerColor != PlayerColor.NONE ) {
            this.playerColor = playerColor;
            this.playing = true;
        }
    }

    //method called when a game is over (either resigned
    //or ended naturally)
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
