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

    public static Team matchTeamOne = MainActivity.allTeams.get("Team One F.C.");
    public static Team matchTeamTwo = MainActivity.allTeams.get("Team Two F.C.");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_match);

    }

}
