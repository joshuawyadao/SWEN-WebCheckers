package com.webcheckers.appl;

import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Session;

import java.util.HashMap;
import java.util.Set;

public class PlayerLobby {
    private final static Message INVALID_NAME_MSG = Message.error("ERROR: Name MUST contain at least one " +
            "alphanumeric character, and can optionally contain spaces.");

    private final static Message LOGIN_SUCCESSFUL_MSG = Message.info("Login Successful!");


    private HashMap<Player, Session> players;

    /**
     * PlayerLobby constructor
     */
    public PlayerLobby(){
        players = new HashMap<>();
    }

    /**
     * Determines if the name of such player is a valid name
     * @param player the player to be checked
     * @return true if the player's name is valid, false otherwise
     */
    public Message signInPlayer(Player player, Session playerSession){
        int alphanumericCount = 0;

        if(!players.containsKey(player)){
            String playerName = player.getName();

            for(int i = 0; i < playerName.length(); i++){
                if(!(Character.isLetterOrDigit(playerName.charAt(i))) && !(playerName.charAt(i) == ' '))
                    return INVALID_NAME_MSG;

                if(Character.isLetterOrDigit(playerName.charAt(i)))
                    alphanumericCount++;
            }

            if(alphanumericCount > 0){
                players.put(player, playerSession);
                return LOGIN_SUCCESSFUL_MSG;
            }else{
                return INVALID_NAME_MSG;
            }
        }

        return Message.error("ERROR: Sorry, '" + player.getName() + "' is already taken by another user.");
    }

    /**
     * Get a player's session through only entering their name
     * @param playerName the name of the player
     * @return true if the player is found, false if not in lobby
     */
    public Session getPlayerSessionByName(String playerName){
        for(Player player: players.keySet()){
            if((player.getName().equals(playerName))){
                return players.get(player);
            }
        }

        return null;
    }

    /**
     * Gets a player from the PlayerLobby
     * @param player the player to be grabbed
     * @return the player grabbed from PlayerLobby
     */
    public Session getPlayerSession(Player player){
        return players.get(player);
    }

    /**
     * Gets all the players from the PlayerLobby
     * @return a set of all players
     */
    public Set<Player> getPlayers(){
        return players.keySet();
    }

    /**
     * Return the amount of players currently signed-in
     * @return the amount of players currently signed-in
     */
    public int getNumOfPlayers(){
        return players.size();
    }

    /**
     * Removes the player from the players HashMap
     * @param player the player wanting to sign out
     */
    public void signOut(Player player){
        players.remove(player);
    }

}
