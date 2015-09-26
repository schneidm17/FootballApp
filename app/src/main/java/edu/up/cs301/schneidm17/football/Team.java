package edu.up.cs301.schneidm17.football;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by schneidm17 on 9/25/2015.
 */
public class Team {
    private String teamName;
    private Hashtable<String, Player> TeamPlayers;
    private Bitmap teamPhoto;

    public Team(String name){
        TeamPlayers = new Hashtable<>();
        teamName = (name==null) ? "TeamName" : name;
    }

    public Team(String name, Player[] newPlayers){
        this(name);
        for(Player player : newPlayers){
            TeamPlayers.put(player.hash(), player);

        }
    }

    public boolean setTeamPhoto(Bitmap b) {
        if (b == null) {
            return false;
        } else {
            teamPhoto = b;
            return true;
        }
    }

    public boolean addPlayer(Player newPlayer){return (TeamPlayers.put(newPlayer.hash(), newPlayer)!=null);}
    public String getTeamName() {return teamName;}
    public Bitmap getTeamPhoto() {return teamPhoto;}
    public Hashtable<String, Player> getTeamPlayers() {return TeamPlayers;}
}
