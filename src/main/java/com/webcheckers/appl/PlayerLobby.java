package com.webcheckers.appl;

import com.webcheckers.model.Player;
import spark.Session;

import java.util.HashMap;
import java.util.Set;

public class PlayerLobby {
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

    /**
     * Allows the player to sign-in to the PlayerLobby
     * @param player the player to be signed in
     * @param playerSession the session of the player
     */
    public void signInPlayer(Player player, Session playerSession){
        players.put(player, playerSession);
    }

    /**
     * Get a player's session through only entering their name
     * @param playerName the name of the player
     * @return true if the player is found, false if not in lobby
     */
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

}
