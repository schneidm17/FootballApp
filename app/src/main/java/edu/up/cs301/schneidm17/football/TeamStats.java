package edu.up.cs301.schneidm17.football;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class TeamStats extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_stats);
        importTeamStats(TeamSelect.teamSelected);
    }

    private void importTeamStats(Team myTeam) {
        TextView teamName = (TextView)findViewById(R.id.teamName);
        teamName.setText(myTeam.getTeamName());

    }
}
