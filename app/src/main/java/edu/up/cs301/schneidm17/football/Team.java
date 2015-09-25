package edu.up.cs301.schneidm17.football;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by schneidm17 on 9/25/2015.
 */
public class Team {
    private String teamName;
    private HashMap<String, Player> TeamPlayers;
    private Bitmap teamPhoto;

    public Team(String name){
        TeamPlayers = new HashMap<>();
        teamName = (name==null) ? "TeamName" : name;
    }

    public Team(String name, Player[] newPlayers){
        this(name);
        for(Player player : newPlayers){
            TeamPlayers.put(player.hash(), player);

        }
    }

    public boolean addPlayer(Player newPlayer){
        return (TeamPlayers.put(newPlayer.hash(), newPlayer)!=null);
    }

    public String getTeamName(){
        return teamName;
    }

}
