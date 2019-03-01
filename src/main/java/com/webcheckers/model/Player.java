package com.webcheckers.model;

public class Player {
    public enum PlayerColor{
        RED,
        WHITE,
        NOT_IN_GAME
    }

    private String username;

    private PlayerColor playerColor;

    public Player(String username){
        this.username = username;
        playerColor = PlayerColor.NOT_IN_GAME;
    }

    public String getUsername() {
        return username;
    }

    public void setPlayerColor(boolean isPlayerOne) {
        if(isPlayerOne)
            playerColor = PlayerColor.RED;
        else
            playerColor = PlayerColor.WHITE;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Player)) return false;

        final Player that = (Player) obj;

        return this.username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
