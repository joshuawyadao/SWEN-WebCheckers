package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.HashMap;

public class PlayerLobby {
    private HashMap<String, Player> players;

    public PlayerLobby(){
        players = new HashMap<>();
    }

    public boolean isValidPlayer(String name){
        if(!players.containsKey(name)){
            for(int i = 0; i < name.length(); i++){
                if(!(Character.isLetterOrDigit(name.charAt(i))) && !(name.charAt(i) == ' '))
                    return false;
            }

            return true;
        }

        return false;
    }

    public void signInPlayer(String name){
        players.put(name, new Player(name));
    }

    public Player getPlayer(String name){
        return players.get(name);
    }

}
