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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Enumeration;


public class PlayMatch extends ActionBarActivity{

    private Team matchTeamOne;
    private Team matchTeamTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_match);

        Intent myIntent = getIntent();
        try {
            matchTeamOne = MainActivity.allTeams.get(myIntent.getStringExtra(MatchTeamSelect.MATCH_TEAM_1));
            matchTeamTwo = MainActivity.allTeams.get(myIntent.getStringExtra(MatchTeamSelect.MATCH_TEAM_2));
        } catch (Exception e) {
            matchTeamOne = MatchTeamSelect.matchTeamOne;
            matchTeamTwo = MatchTeamSelect.matchTeamTwo;
        }

        if(matchTeamOne == null || matchTeamTwo == null) {
            return;
        }

        int teamOneScore = 0;
        int teamTwoScore = 0;

        //use team stats to determine the score
        if(matchTeamTwo.getNumPlyers()!=0) {
            teamOneScore = (int) Math.max(0.0, 1.0 * matchTeamOne.getTotalGoals() / matchTeamTwo.getNumPlyers()
                    + (matchTeamOne.getTotalShots() - matchTeamTwo.getTotalSaves()) / 16.0);
        }
        if(matchTeamOne.getNumPlyers()!=0) {
            teamTwoScore = (int) Math.max(0.0, 1.0 * matchTeamTwo.getTotalGoals() / matchTeamOne.getNumPlyers()
                    + (matchTeamTwo.getTotalShots() - matchTeamOne.getTotalSaves()) / 16.0);
        }

        TextView title = (TextView) findViewById(R.id.playMatchTitle);
        title.setText(matchTeamOne.getTeamName() + " vs. " + matchTeamTwo.getTeamName());
        TextView score = (TextView) findViewById(R.id.matchFinalScore);
        score.setText("Final score:          " + matchTeamOne.getTeamName()+"  "+teamOneScore+"          "+
            matchTeamTwo.getTeamName()+"  "+teamTwoScore);
        TextView winner = (TextView) findViewById(R.id.matchWinner);
        if(teamOneScore>teamTwoScore) {
            winner.setText(matchTeamOne.getTeamName()+" wins the game!");
        } else if (teamOneScore<teamTwoScore) {
            winner.setText(matchTeamTwo.getTeamName() + " wins the game!");
        } else {
            winner.setText("Match ends in a tie.");
        }
    }
}
