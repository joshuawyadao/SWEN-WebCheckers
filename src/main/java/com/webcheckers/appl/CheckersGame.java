package com.webcheckers.appl;

import com.webcheckers.model.BoardView;
import com.webcheckers.model.Player;

public class CheckersGame {
    public enum ViewMode{
        PLAY,
        SPECTATE
    }

    private Player redPlayer;
    private Player whitePlayer;
    private ViewMode viewMode;
    private BoardView checkerBoard;

    public CheckersGame(Player redPlayer, Player whitePlayer, ViewMode viewMode){
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.viewMode = viewMode;
        this.checkerBoard = new BoardView();
    }

    public Player getRedPlayer() {
        return redPlayer;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public ViewMode getViewMode() {
        return viewMode;
    }

    public BoardView getCheckerBoard(){
        return checkerBoard;
    }

    public void intializeGame(){
        redPlayer.joinGame(Player.PlayerColor.RED);
        whitePlayer.joinGame(Player.PlayerColor.WHITE);
    }

}
