package com.webcheckers.appl;

import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Session;
import spark.utils.StringUtils;

import java.util.HashMap;
import java.util.Set;

public class PlayerLobby {
    private final static Message INVALID_NAME_MSG = Message.error("ERROR: Name MUST contain at least one " +
            "alphanumeric character, and can optionally contain spaces.");

    private final static Message LOGIN_SUCCESSFUL = Message.info("Login Successful!");

    private HashMap<Player, Session> players;

    public PlayerLobby(){
        players = new HashMap<>();
    }

    public Message signInPlayer(Player player, Session playerSession){
        int alphanumericCount = 0;
        if(!players.containsKey(player)) {
            String playerName = player.getName();

            for (int i = 0; i < playerName.length(); i++) {
                if (!(Character.isLetterOrDigit(playerName.charAt(i))) && !(playerName.charAt(i) == ' '))
                    return INVALID_NAME_MSG;

                if (Character.isLetterOrDigit(playerName.charAt(i)))
                    alphanumericCount++;
            }

            if (alphanumericCount > 0) {
                players.put(player, playerSession);
                return LOGIN_SUCCESSFUL;
            }else{
                return INVALID_NAME_MSG;
            }
        }

        return Message.error("ERROR: Sorry, '" + player.getName() + "' is already taken by another user.");
    }

    //Get a player's session through only entering their name
    public Session getPlayerSessionByName(String playerName){
        for(Player player: players.keySet()){
            if((player.getName().equals(playerName))){
                return players.get(player);
            }
        }

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
