package edu.up.cs301.schneidm17.football;

import android.app.Activity;
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

    public static final String CREATED_NEW_TEAM = "CREATED_NEW_TEAM";
    public static final String NEW_TEAM_NAME = "NEW_TEAM_NAME";
    public static final String TEAM_TO_BE_EDITED = "TEAM_TO_BE_EDITED";

    private static final int CREATE_NEW_TEAM_REQUEST = 456;
    public static Team teamSelected;

    private LinearLayout teamTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_select);

        createTeamTable();
        teamSelected = null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==CREATE_NEW_TEAM_REQUEST && resultCode== Activity.RESULT_OK){
            if (data.getBooleanExtra(CREATED_NEW_TEAM, true))
            {
                String newTeamName = data.getStringExtra(NEW_TEAM_NAME);
                Team newTeam;
                try {
                    newTeam = MainActivity.allTeams.get(newTeamName);
                    if (newTeam == null)
                        return;
                } catch (Exception e) {
                    return;
                }
                //if nothing went wrong, add the new team to the table
                addTeamToView(newTeam);
            }
        }
    }

    private void createTeamTable() {
        teamTable = (LinearLayout) findViewById(R.id.TeamViewLayout);
        Enumeration<Team> myTeams = MainActivity.allTeams.elements();

        while (myTeams.hasMoreElements()) {
            addTeamToView(myTeams.nextElement());
        }
    }

    private void gotoTeamStats(Team myTeam) {
        teamSelected = myTeam;
        startActivity(new Intent(TeamSelect.this, TeamStats.class));
    }

    public void createNewTeam(View view) {
        Intent newIntent = new Intent(TeamSelect.this, TeamEdit.class);
        startActivityForResult(newIntent, CREATE_NEW_TEAM_REQUEST);
    }

    private void addTeamToView(final Team myTeam){
        if(myTeam==null)
            return;

        LinearLayout teamEntry = new LinearLayout(this);
        teamEntry.setOrientation(LinearLayout.VERTICAL);
        teamEntry.setPadding(10, 10, 10, 10);
        teamTable.addView(teamEntry);

        ImageView teamImageView = new ImageView(this);
        try {
            Bitmap scaledTeamPhoto = Bitmap.createScaledBitmap(myTeam.getTeamPhoto(), 400, 400, true);
            teamImageView.setImageBitmap(scaledTeamPhoto);
        } catch (NullPointerException e) {
            teamImageView.setMinimumHeight(400);
            teamImageView.setMinimumWidth(400);
            teamImageView.setBackgroundColor(0x40ffffff);
        }
        teamEntry.addView(teamImageView);

        TextView teamNameView = new TextView(this);
        teamNameView.setText(myTeam.getTeamName());
        teamNameView.setTextColor(Color.WHITE);
        teamNameView.setTextSize(20);
        teamEntry.addView(teamNameView);

        teamEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoTeamStats(myTeam);
            }
        });
    }
}