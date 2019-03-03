package com.webcheckers.model;

public class Player {
    public enum PlayerColor{
        RED,
        WHITE,
        NOT_IN_GAME
    }

    private String name;

    private PlayerColor playerColor;

    public Player(String name){
        this.name = name;
        playerColor = PlayerColor.NOT_IN_GAME;
    }

    public String getName() {
        return name;
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

        return this.name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
