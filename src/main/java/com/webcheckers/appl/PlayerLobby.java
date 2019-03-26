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

    //Get a player's session through only entering their name
    public Session getPlayerSessionByName(String playerName){
        boolean foundPlayer = false;
        Player target = null;

        for(Player player: players.keySet()){
            if((player.getName().equals(playerName)) && !foundPlayer){
                target = player;
                foundPlayer = true;
            }
        }

        if(foundPlayer)
            return players.get(target);
        else
            return null;
    }

    public Session getPlayerSession(Player player){
        return players.get(player);
    }

    public Set<Player> getPlayers(){
        return players.keySet();
    }

    public int getNumOfPlayers(){
        return players.size();
    }

}
