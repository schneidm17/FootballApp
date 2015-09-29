package edu.up.cs301.schneidm17.football;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Enumeration;


public class PlayMatch extends ActionBarActivity{

    private Spinner teamOneSpinner;
    private Spinner teamTwoSpinner;

    ArrayList<String> teamOneChoices;
    ArrayList<String> teamTwoChoices;

    public static Team matchTeamOne = MainActivity.allTeams.get("Team One F.C.");
    public static Team matchTeamTwo = MainActivity.allTeams.get("Team Two F.C.");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_match);

        teamOneSpinner = (Spinner) findViewById(R.id.teamOneSpinner);
        teamTwoSpinner = (Spinner) findViewById(R.id.teamTwoSpinner);
        teamOneChoices = new ArrayList<>();
        teamTwoChoices = new ArrayList<>();

        Enumeration<String> league = MainActivity.allTeams.keys();
        while (league.hasMoreElements()) {
            String currentTeam = league.nextElement();
            teamOneChoices.add(currentTeam);
            teamTwoChoices.add(currentTeam);
        }

        ArrayAdapter<String> teamOneAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, teamOneChoices);
        ArrayAdapter<String> teamTwoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, teamTwoChoices);
        teamOneSpinner.setAdapter(teamOneAdapter);
        teamTwoSpinner.setAdapter(teamTwoAdapter);


    }

}
