package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Player;
/*
 *  This class holds all the information for a CheckersGame (meaning all the variables
 *  needed to render 'game.ftl') and contains the methods that are needed for the game
 *  to function from start to end
 */
public class CheckersGame {
    //Enumeration for the View Mode
    public enum ViewMode{
        PLAY,
        SPECTATE
    }

    private Player redPlayer;
    private Player whitePlayer;
    private ViewMode viewMode;
    private BoardView checkerBoard;

    public CheckersGame(Player redPlayer, Player whitePlayer, ViewMode viewMode, PlayerLobby playerLobby){
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.viewMode = viewMode;
        this.checkerBoard = new BoardView();
    }

    //Accessors
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

    //Joins both players to the checkers game
    public void initializeGame(){
        redPlayer.joinGame(Player.PlayerColor.RED);
        whitePlayer.joinGame(Player.PlayerColor.WHITE);
    }

}
