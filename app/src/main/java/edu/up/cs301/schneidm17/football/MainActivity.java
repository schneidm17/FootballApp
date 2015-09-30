package edu.up.cs301.schneidm17.football;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Hashtable;


public class MainActivity extends ActionBarActivity {

    public static Hashtable<String, Player> allPlayers;
    public static Hashtable<String, Team> allTeams;
    private Bitmap defaultPhoto;
    private Bitmap teamOnePlayer;
    private Bitmap teamOneKeeper;
    private Bitmap teamTwoPlayer;
    private Bitmap teamTwoKeeper;
    private Bitmap teamThreePlayer;
    private Bitmap teamThreeKeeper;
    private Bitmap teamFourPlayer;
    private Bitmap teamFourKeeper;
    private String team1="Team One F.C.";
    private String team2="Team Two F.C.";
    private String team3="Team Three F.C.";
    private String team4="Team Four F.C.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allPlayers = new Hashtable<>();
        allTeams = new Hashtable<>();
        defaultPhoto = BitmapFactory.decodeResource(getResources(), R.drawable.team_none);
        teamOnePlayer = BitmapFactory.decodeResource(getResources(), R.drawable.team_1_kit);
        teamOneKeeper = BitmapFactory.decodeResource(getResources(), R.drawable.team_1_gkit);
        teamTwoPlayer = BitmapFactory.decodeResource(getResources(), R.drawable.team_2_kit);
        teamTwoKeeper = BitmapFactory.decodeResource(getResources(), R.drawable.team_2_gkit);
        teamThreePlayer = BitmapFactory.decodeResource(getResources(), R.drawable.team_3_kit);
        teamThreeKeeper = BitmapFactory.decodeResource(getResources(), R.drawable.team_3_gkit);
        teamFourPlayer = BitmapFactory.decodeResource(getResources(), R.drawable.team_4_kit);
        teamFourKeeper = BitmapFactory.decodeResource(getResources(), R.drawable.team_4_gkit);

        addDefaultPlayers();

        Button gotoPlayerViewButton = (Button)findViewById(R.id.gotoPlayerView);
        gotoPlayerViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PlayerSelect.class));
            }
        });

        Button gotoTeamViewButton = (Button)findViewById(R.id.gotoTeamView);
        gotoTeamViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TeamSelect.class));
            }
        });

        Button startMatchButton = (Button)findViewById(R.id.playMatchButton);
        startMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MatchTeamSelect.class));
            }
        });
    }

    private void addDefaultPlayers() {
        //Create four default teams
        addToHashtable(new Player("Mathew", "Rosemond", team1, 1, Player.position.FORWARD, 20, 11, 7, 20, 0, 2, 1, 0));
        addToHashtable(new Player("Dominic", "Piesse", team2, 1, Player.position.FORWARD, 28, 9, 3, 19, 0, 1, 0, 0));
        addToHashtable(new Player("Adam", "Parsons", team3, 1, Player.position.FORWARD, 27, 12, 5, 18, 0, 1, 0, 0));
        addToHashtable(new Player("Zak", "Martin", team4, 1, Player.position.FORWARD, 27, 8, 3, 28, 0, 4, 1, 0));
        addToHashtable(new Player("George", "Byrne", team1, 2, Player.position.FORWARD, 26, 9, 8, 18, 0, 2, 1, 0));
        addToHashtable(new Player("Lucas", "Webster", team2, 2, Player.position.FORWARD, 22, 14, 9, 21, 0, 6, 2, 0));
        addToHashtable(new Player("Toby", "Mackay", team3, 2, Player.position.FORWARD, 21, 12, 7, 20, 0, 3, 0, 0));
        addToHashtable(new Player("Mackenzie", "Akhtar", team4, 2, Player.position.FORWARD, 18, 7, 9, 12, 0, 1, 0, 0));
        addToHashtable(new Player("Joe", "Parkes", team1, 3, Player.position.FORWARD, 23, 4, 8, 16, 0, 2, 0, 1));
        addToHashtable(new Player("Charlie", "Hayward", team2, 3, Player.position.FORWARD, 28, 6, 7, 20, 0, 4, 1, 0));
        addToHashtable(new Player("Brogan", "Martin", team3, 3, Player.position.FORWARD, 23, 3, 6, 14, 0, 1, 0, 0));
        addToHashtable(new Player("Jeremy", "Hartog", team4, 3, Player.position.FORWARD, 19, 2, 4, 10, 0, 3, 0, 0));
        addToHashtable(new Player("Anthony", "Leathers", team1, 4, Player.position.MIDFIELDER, 28, 0, 6, 9, 0, 1, 0, 0));
        addToHashtable(new Player("Glenn", "Wallace", team2, 4, Player.position.MIDFIELDER, 19, 1, 4, 13, 0, 2, 0, 0));
        addToHashtable(new Player("Charles", "Carter", team3, 4, Player.position.MIDFIELDER, 21, 1, 3, 8, 0, 2, 2, 0));
        addToHashtable(new Player("Elliot", "Barr", team4, 4, Player.position.MIDFIELDER, 28, 2, 2, 8, 0, 1, 0, 0));
        addToHashtable(new Player("Jack", "Clark", team1, 5, Player.position.MIDFIELDER, 23, 0, 4, 9, 0, 1, 0, 0));
        addToHashtable(new Player("Daniel", "Bentley", team2, 5, Player.position.MIDFIELDER, 24, 1, 7, 6, 1, 0, 0, 0));
        addToHashtable(new Player("Jenson", "Taylor", team3, 5, Player.position.MIDFIELDER, 19, 2, 8, 4, 0, 2, 1, 0));
        addToHashtable(new Player("Leo", "Wall", team4, 5, Player.position.MIDFIELDER, 27, 2, 6, 7, 1, 0, 0, 0));
        addToHashtable(new Player("Nigel", "Hughes", team1, 6, Player.position.MIDFIELDER, 22, 0, 2, 8, 1, 3, 1, 0));
        addToHashtable(new Player("Xavier", "Davey", team2, 6, Player.position.MIDFIELDER, 20, 1, 5, 4, 0, 0, 0, 0));
        addToHashtable(new Player("Christopher", "Carter", team3, 6, Player.position.MIDFIELDER, 27, 0, 7, 2, 1, 1, 0, 0));
        addToHashtable(new Player("Lochlan", "Clark", team4, 6, Player.position.MIDFIELDER, 21, 1, 2, 2, 2, 0, 0, 0));
        addToHashtable(new Player("Mason", "Holden", team1, 7, Player.position.DEFENDER, 25, 0, 1, 1, 2, 1, 0, 0));
        addToHashtable(new Player("David", "Atkins", team2, 7, Player.position.DEFENDER, 20, 0, 0, 1, 3, 1, 0, 0));
        addToHashtable(new Player("Liam", "Howarth", team3, 7, Player.position.DEFENDER, 26, 0, 0, 0, 2, 3, 1, 0));
        addToHashtable(new Player("Spencer", "Palmer", team4, 7, Player.position.DEFENDER, 18, 0, 1, 1, 4, 0, 0, 0));
        addToHashtable(new Player("Declan", "Bryant", team1, 8, Player.position.DEFENDER, 22, 0, 0, 0, 6, 2, 1, 0));
        addToHashtable(new Player("Joshua", "Yelverton", team2, 8, Player.position.DEFENDER, 28, 0, 1, 0, 4, 0, 0, 0));
        addToHashtable(new Player("Quinn", "Ferguson", team3, 8, Player.position.DEFENDER, 19, 0, 0, 0, 3, 0, 0, 0));
        addToHashtable(new Player("Riley", "Moore", team4, 8, Player.position.DEFENDER, 26, 0, 0, 0, 12, 4, 3, 1));
        addToHashtable(new Player("Jamie", "Brady", team1, 9, Player.position.DEFENDER, 23, 0, 1, 0, 4, 0, 0, 0));
        addToHashtable(new Player("Timothy", "Billington", team2, 9, Player.position.DEFENDER, 25, 0, 0, 0, 10, 1, 0, 0));
        addToHashtable(new Player("Kian", "Bartlett", team3, 9, Player.position.DEFENDER, 28, 0, 0, 0, 7, 3, 1, 1));
        addToHashtable(new Player("Wiktor", "McMillan", team4, 9, Player.position.DEFENDER, 19, 0, 0, 0, 9, 1, 0, 0));
        addToHashtable(new Player("James", "Barnes", team1, 10, Player.position.DEFENDER, 22, 0, 0, 0, 11, 2, 1, 0));
        addToHashtable(new Player("Armaan", "McLean", team2, 10, Player.position.DEFENDER, 18, 0, 0, 0, 10, 0, 0, 0));
        addToHashtable(new Player("Alexander", "Webster", team3, 10, Player.position.DEFENDER, 20, 0, 0, 0, 15, 1, 0, 0));
        addToHashtable(new Player("Max", "Chamberlain", team4, 10, Player.position.DEFENDER, 25, 0, 0, 0, 12, 0, 0, 0));
        addToHashtable(new Player("William", "O'Malley", team1, 0, Player.position.GOALKEEPER, 25, 0, 0, 0, 37, 0, 0, 0));
        addToHashtable(new Player("Corey", "Short", team2, 0, Player.position.GOALKEEPER, 27, 0, 0, 0, 42, 0, 1, 0));
        addToHashtable(new Player("Bailey", "Stephenson", team3, 0, Player.position.GOALKEEPER, 24, 0, 0, 0, 41, 0, 0, 0));
        addToHashtable(new Player("Owen", "MacDonald", team4, 0, Player.position.GOALKEEPER, 23, 0, 0, 0, 36, 0, 0, 0));

        allTeams.get(team1).setTeamPhoto(BitmapFactory.decodeResource(getResources(), R.drawable.team_1_crest));
        allTeams.get(team2).setTeamPhoto(BitmapFactory.decodeResource(getResources(), R.drawable.team_2_crest));
        allTeams.get(team3).setTeamPhoto(BitmapFactory.decodeResource(getResources(),R.drawable.team_3_crest));
        allTeams.get(team4).setTeamPhoto(BitmapFactory.decodeResource(getResources(),R.drawable.team_4_crest));

        allTeams.get(team1).setMotto("The Golden Ones");
        allTeams.get(team2).setMotto("Better Together");
        allTeams.get(team4).setMotto("Strength in Numbers");

        allTeams.get(team1).setRecord(6, 8, 2);
        allTeams.get(team2).setRecord(10,6,0);
        allTeams.get(team3).setRecord(9,6,1);
        allTeams.get(team4).setRecord(7,8,1);
    }

    private void addToHashtable(Player myPlayer) {
        if(myPlayer.getTeamName().equals(team1) && myPlayer.getPositionEnum().equals(Player.position.GOALKEEPER))
            {myPlayer.setPlayerPhoto(teamOneKeeper);}
        else if (myPlayer.getTeamName().equals(team1))
            {myPlayer.setPlayerPhoto(teamOnePlayer);}
        else if(myPlayer.getTeamName().equals(team2) && myPlayer.getPositionEnum().equals(Player.position.GOALKEEPER))
            {myPlayer.setPlayerPhoto(teamTwoKeeper);}
        else if (myPlayer.getTeamName().equals(team2))
            {myPlayer.setPlayerPhoto(teamTwoPlayer);}
        else if(myPlayer.getTeamName().equals(team3) && myPlayer.getPositionEnum().equals(Player.position.GOALKEEPER))
            {myPlayer.setPlayerPhoto(teamThreeKeeper);}
        else if (myPlayer.getTeamName().equals(team3))
            {myPlayer.setPlayerPhoto(teamThreePlayer);}
        else if(myPlayer.getTeamName().equals(team4) && myPlayer.getPositionEnum().equals(Player.position.GOALKEEPER))
            {myPlayer.setPlayerPhoto(teamFourKeeper);}
        else if (myPlayer.getTeamName().equals(team4))
            {myPlayer.setPlayerPhoto(teamFourPlayer);}
        else
            {myPlayer.setPlayerPhoto(defaultPhoto);}

        allPlayers.put(myPlayer.hash(), myPlayer);
        if(allTeams.containsKey(myPlayer.getTeamName())) {
            allTeams.get(myPlayer.getTeamName()).addPlayer(myPlayer);
        } else {
            allTeams.put(myPlayer.getTeamName(),new Team(myPlayer.getTeamName()));
            allTeams.get(myPlayer.getTeamName()).addPlayer(myPlayer);
        }
    }
}
