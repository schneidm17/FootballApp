package edu.up.cs301.schneidm17.football;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Enumeration;


public class PlayMatch extends ActionBarActivity{

    public static Team matchTeamOne;
    public static Team matchTeamTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_match);

        SurfaceView field = (SurfaceView) findViewById(R.id.footballFieldSurfaceView);
        Intent myIntent = getIntent();
        try {
            matchTeamOne = MainActivity.allTeams.get(myIntent.getStringExtra(MatchTeamSelect.MATCH_TEAM_1));
        } catch (Exception e) {
            matchTeamOne = null;
        }

        try {
            matchTeamTwo = MainActivity.allTeams.get(myIntent.getStringExtra(MatchTeamSelect.MATCH_TEAM_2));
        } catch (Exception e) {
            matchTeamTwo = null;
        }
    }
}
