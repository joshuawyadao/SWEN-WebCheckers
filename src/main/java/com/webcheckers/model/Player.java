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

    public Player(String name){
        this.name = name;
        playerColor = PlayerColor.NONE;
        this.playing = false;
    }

    public String getName() {
        return name;
    }

    public void joinGame(PlayerColor playerColor){
        this.playerColor = playerColor;
        this.playing = true;
    }

    public void leaveGame(){
        this.playerColor = PlayerColor.NONE;
        this.playing = false;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

//    public void inGame(boolean is) {
//        this.playing = is;
//    }

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

    @Override
    public String toString() {
        return name;
    }
}
