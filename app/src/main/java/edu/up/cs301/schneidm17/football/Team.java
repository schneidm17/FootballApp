package edu.up.cs301.schneidm17.football;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by schneidm17 on 9/25/2015.
 */
public class Team {
    private Hashtable<String, Player> TeamPlayers;
    private String teamName;
    private String teamMotto;
    private Bitmap teamPhoto;
    private int wins;
    private int losses;
    private int ties;
    private int totalGoals;
    private int totalShots;
    private int totalSaves;
    private int totalFouls;
    private int totalYCards;
    private int totalRCards;

    public Team(String name){
        this.TeamPlayers = new Hashtable<>();
        this.teamName = (name==null) ? "TeamName" : name;
        this.teamMotto = null;
        this.teamPhoto = null;
        this.wins = 0;
        this.losses = 0;
        this.ties = 0;
        this.totalGoals = 0;
        this.totalShots = 0;
        this.totalSaves = 0;
        this.totalFouls = 0;
        this.totalYCards = 0;
        this.totalRCards = 0;
    }

    public Team(String name, Player[] newPlayers){
        this(name);
        for(Player player : newPlayers){
            TeamPlayers.put(player.hash(), player);
        }
    }

    public boolean addPlayer(Player newPlayer) {
        if (newPlayer==null || TeamPlayers.contains(newPlayer))
            return false;
        if(TeamPlayers.put(newPlayer.hash(), newPlayer)!=null)
            return false;
        totalGoals+=newPlayer.getGoals();
        totalShots+=newPlayer.getShots();
        totalSaves+=newPlayer.getSaves();
        totalFouls+=newPlayer.getFouls();
        totalYCards+=newPlayer.getyCards();
        totalRCards+=newPlayer.getrCards();
        return true;
    }

    public boolean removePlayer(Player newPlayer) {
        if (newPlayer==null || !TeamPlayers.containsKey(newPlayer.hash()))
            return false;
        TeamPlayers.remove(newPlayer.hash());
        totalGoals-=newPlayer.getGoals();
        totalShots-=newPlayer.getShots();
        totalSaves-=newPlayer.getSaves();
        totalFouls-=newPlayer.getFouls();
        totalYCards-=newPlayer.getyCards();
        totalRCards-=newPlayer.getrCards();
        return true;
    }


    public boolean setTeamPhoto(Bitmap b) {if (b == null) return false; teamPhoto = b; return true;}
    public boolean setRecord(int w, int l, int t) {if(w<0 || l<0 || t<0)
        return false;wins=w; losses=l; ties=t; return true;}
    public boolean setMotto(String motto) {if(motto==null) return false; teamMotto=motto; return true;}
    public boolean hasMotto() {return (teamMotto!=null);}

    public String getTeamName() {return teamName;}
    public Bitmap getTeamPhoto() {return teamPhoto;}
    public String getTeamMotto() {return teamMotto;}
    public String getRecord() {if(wins+losses+ties==0) return null;
        else if(ties==0) return wins+"-"+losses; return wins+"-"+losses+"-"+ties;}
    public int getWins() {return wins;}
    public int getLosses() {return losses;}
    public int getTies() {return ties;}
    public int getTotalGoals() {return totalGoals;}
    public int getTotalShots() {return totalShots;}
    public int getTotalSaves() {return totalSaves;}
    public int getTotalFouls() {return totalFouls;}
    public int getTotalYCards() {return totalYCards;}
    public int getTotalRCards() {return totalRCards;}
    public Hashtable<String, Player> getTeamPlayers() {return TeamPlayers;}
}
