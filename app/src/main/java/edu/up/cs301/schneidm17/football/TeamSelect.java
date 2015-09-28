package edu.up.cs301.schneidm17.football;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Enumeration;


public class TeamSelect extends ActionBarActivity {

    public static Team teamSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_select);

        createTeamTable();
        teamSelected=null;
    }

    private void createTeamTable() {
        LinearLayout teamTable = (LinearLayout)findViewById(R.id.TeamViewLayout);
        Enumeration<Team> myTeams = MainActivity.allTeams.elements();

        while(myTeams.hasMoreElements())
        {
            final Team currentTeam=myTeams.nextElement();
            LinearLayout teamEntry = new LinearLayout(this);
            teamEntry.setOrientation(LinearLayout.VERTICAL);
            teamEntry.setPadding(10, 10, 10, 10);
            teamTable.addView(teamEntry);

            ImageView teamImageView = new ImageView(this);
            try {
                Bitmap scaledTeamPhoto = Bitmap.createScaledBitmap(currentTeam.getTeamPhoto(), 400, 400, true);
                teamImageView.setImageBitmap(scaledTeamPhoto);
            } catch (NullPointerException e) {
                teamImageView.setMinimumHeight(400);
                teamImageView.setMinimumWidth(400);
                teamImageView.setBackgroundColor(0x40ffffff);
            }
            teamEntry.addView(teamImageView);

            TextView teamNameView = new TextView(this);
            teamNameView.setText(currentTeam.getTeamName());
            teamNameView.setTextColor(Color.WHITE);
            teamNameView.setTextSize(20);
            teamEntry.addView(teamNameView);

            teamEntry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoTeamStats(currentTeam);
                }
            });
        }
    }

    private void gotoTeamStats(Team myTeam) {
        teamSelected = myTeam;
        startActivity(new Intent(TeamSelect.this, TeamStats.class));
    }

    public void createNewTeam(View view){
        teamSelected = null;
        startActivity(new Intent(TeamSelect.this, TeamEdit.class));
    }
}
