package edu.up.cs301.schneidm17.football;

import android.graphics.Bitmap;

import java.util.HashMap;

/**
 * Created by schneidm17 on 9/24/2015.
 */
public class Player {
    private String firstName;
    private String lastName;
    private String teamName;
    private int teamNumber;
    private int age;
    private position playerPosition;
    private int goals;
    private int assists;
    private int shots;
    private int saves;
    private int fouls;
    private int yCards;
    private int rCards;
    private Bitmap playerPhoto;

    public enum position {FORWARD, MIDFIELDER, DEFENDER, GOALKEEPER}

    public Player(String fname, String lname, position pos){


        this.firstName = (fname==null) ? "[FirstName]" : fname;
        this.lastName = (lname==null) ? "[LastName]" : lname;
        this.playerPosition = pos;
        this.teamName=null;
        this.teamNumber=0;
        this.age=0;
        this.goals=0;
        this.assists=0;
        this.shots=0;
        this.saves=0;
        this.fouls=0;
        this.yCards=0;
        this.rCards=0;
        this.playerPhoto=null;
    }

    public Player(String fname, String lname, String teamName, int teamNumber, position pos, int age,
                  int goals, int assists, int shots, int saves, int fouls, int yCards, int rCards){
        this(fname, lname, pos);
        this.teamName = (teamName==null) ? "[TeamName]" : teamName;
        this.teamNumber = (teamNumber<0) ? 0 : teamNumber;
        setAge(age);
        setGoals(goals);
        setAssists(assists);
        setShots(shots);
        setSaves(saves);
        setFouls(fouls);
        setYCards(yCards);
        setRCards(rCards);
    }


    /*
     * Set methods; will set as long as n is not negative
     * @returns true if the value was changed; otherwise returns false
     */
    public boolean setAge(int n) {if(n<=0) return false; age=n; return true;}
    public boolean setGoals(int n) {if(n<0) return false; goals=n; return true;}
    public boolean setAssists(int n) {if(n<0) return false; assists=n; return true;}
    public boolean setShots(int n) {if(n<0) return false; shots=n; return true;}
    public boolean setSaves(int n) {if(n<0) return false; saves=n; return true;}
    public boolean setFouls(int n) {if(n<0) return false; fouls=n; return true;}
    public boolean setYCards(int n) {if(n<0) return false; yCards=n; return true;}
    public boolean setRCards(int n) {if(n<0) return false; rCards=n; return true;}
    public boolean setPlayerPhoto(Bitmap b) {if(b==null) return false; playerPhoto=b; return true;}

    /*
     * Get methods @returns the value called
     */
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getTeamName() {return teamName;}
    public String getTeamNumber() {return "#"+teamNumber;}
    public int getAge() {return age;}
    public String getPosition() {
        switch(playerPosition) {
            case FORWARD: return "Forward";
            case MIDFIELDER: return "Midfielder";
            case DEFENDER: return "Defender";
            default: return "Goalkeeper";
        }
    }
    public int getGoals() {return goals;}
    public int getAssists() {return assists;}
    public int getShots() {return shots;}
    public int getSaves() {return saves;}
    public int getFouls() {return fouls;}
    public int getyCards() {return yCards;}
    public int getrCards() {return rCards;}
    public Bitmap getPlayerPhoto() {return playerPhoto;}

    //public static String hash(String fname, String lname) {return fname+"_#$_"+lname;}
    public String hash() {return firstName+"_#$_"+lastName;}

}
