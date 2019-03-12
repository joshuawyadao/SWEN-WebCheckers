package com.webcheckers.appl;

import com.webcheckers.model.Player;
import spark.Session;

import java.util.HashMap;
import java.util.Set;

public class PlayerLobby {
    private HashMap<Player, Session> players;

    public PlayerLobby(){
        players = new HashMap<>();
    }

    public boolean isValidPlayer(Player player){
        if(!players.containsKey(player)){
            int alphanumericCount = 0;
            String playerName = player.getName();

            for(int i = 0; i < playerName.length(); i++){
                if(!(Character.isLetterOrDigit(playerName.charAt(i))) && !(playerName.charAt(i) == ' '))
                    return false;

                if(Character.isLetterOrDigit(playerName.charAt(i)))
                    alphanumericCount++;
            }

            if(alphanumericCount > 0)
                return true;
        }

        return false;
    }

    public void signInPlayer(Player player, Session playerSession){
        players.put(player, playerSession);
    }

    public Session getPlayerSessionByName(String playerName){
        return players.get(new Player(playerName));
    }

    public Set<Player> getPlayers(){
        return players.keySet();
    }

    public int getNumOfPlayers(){
        return players.size();
    }

}
