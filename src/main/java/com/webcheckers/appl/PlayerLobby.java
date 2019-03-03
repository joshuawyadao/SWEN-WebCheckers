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
            int alphanumericCount = 0;
            for(int i = 0; i < name.length(); i++){
                if(!(Character.isLetterOrDigit(name.charAt(i))) && !(name.charAt(i) == ' '))
                    return false;

                if(Character.isLetterOrDigit(name.charAt(i)))
                    alphanumericCount++;
            }

            if(alphanumericCount > 0)
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
