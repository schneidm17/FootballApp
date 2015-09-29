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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Enumeration;
import java.util.HashMap;


public class TeamStats extends ActionBarActivity implements View.OnClickListener{

    private static final int EDIT_TEAM_STATS_REQUEST = 786;
    private Team myTeam;
    Button editButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_stats);
        myTeam = TeamSelect.teamSelected;

        importTeamStats();
        importTeamPlayers();
    }

    private void importTeamStats() {
        if (myTeam == null) {
            return;
        }

        editButton = (Button) findViewById(R.id.teamStatsEditButton);
        cancelButton = (Button) findViewById(R.id.teamStatsCanceoButton);
        editButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        TextView teamName = (TextView) findViewById(R.id.teamName);
        TextView teamMotto = (TextView) findViewById(R.id.teamMotto);
        ImageView teamPhoto = (ImageView) findViewById(R.id.teamLogoView);
        TextView numPlayers = (TextView) findViewById(R.id.numPlayers);
        TextView record = (TextView) findViewById(R.id.teamRecord);
        TableRow recordRow = (TableRow) findViewById(R.id.teamRecordRow);
        TextView goals = (TextView) findViewById(R.id.teamGoals);
        TextView shots = (TextView) findViewById(R.id.teamShots);
        TextView saves = (TextView) findViewById(R.id.teamSaves);
        TextView fouls = (TextView) findViewById(R.id.teamFouls);
        TextView yCards = (TextView) findViewById(R.id.teamYCards);
        TextView rCards = (TextView) findViewById(R.id.teamRCards);

        teamName.setText(myTeam.getTeamName());
        if (myTeam.hasMotto()) {
            teamMotto.setVisibility(View.VISIBLE);
            teamMotto.setText("\""+myTeam.getTeamMotto()+"\"");
        } else {
            teamMotto.setVisibility(View.GONE);
        }
        if (myTeam.getRecord() == null) {
            recordRow.setVisibility(View.GONE);
        } else {
            recordRow.setVisibility(View.VISIBLE);
            record.setText(myTeam.getRecord());
        }
        numPlayers.setText("" + myTeam.getTeamPlayers().size());
        goals.setText("" + myTeam.getTotalGoals());
        shots.setText("" + myTeam.getTotalShots());
        saves.setText("" + myTeam.getTotalSaves());
        fouls.setText("" + myTeam.getTotalFouls());
        yCards.setText("" + myTeam.getTotalYCards());
        rCards.setText("" + myTeam.getTotalRCards());

        try {
            Bitmap scaledTeamPhoto = Bitmap.createScaledBitmap(myTeam.getTeamPhoto(), 500, 500, true);
            teamPhoto.setImageBitmap(scaledTeamPhoto);
        } catch (NullPointerException e) {
            teamPhoto.setMinimumHeight(500);
            teamPhoto.setMinimumWidth(500);
            teamPhoto.setBackgroundColor(0x40ffffff);
        }
    }

    private void importTeamPlayers() {
        if (myTeam == null) {
            return;
        }

        TableLayout myTable = (TableLayout)findViewById(R.id.teamPlayerTableView);
        myTable.removeAllViews();
        Enumeration<Player> myPlayers = myTeam.getTeamPlayers().elements();

        while(myPlayers.hasMoreElements())
        {
            final Player currentPlayer=myPlayers.nextElement();
            String bufferString = "          ";

            TableRow myRow = new TableRow(this);
            myTable.addView(myRow);
            myRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoPlayerStats(currentPlayer);
                }
            });
            TextView playerName = new TextView(this);
            playerName.setText(currentPlayer.getFirstName() + " " + currentPlayer.getLastName() + bufferString);
            playerName.setTextColor(Color.WHITE);
            playerName.setTextSize(20);
            myRow.addView(playerName);

            TextView playerPosition = new TextView(this);
            playerPosition.setText(currentPlayer.getPosition() + bufferString);
            playerPosition.setTextColor(Color.WHITE);
            playerPosition.setTextSize(15);
            myRow.addView(playerPosition);

            TextView playerTeamInfo = new TextView(this);
            playerTeamInfo.setText(currentPlayer.getTeamNumber() + ", " + currentPlayer.getTeamName());
            playerTeamInfo.setTextColor(Color.WHITE);
            playerTeamInfo.setTextSize(15);
            myRow.addView(playerTeamInfo);
        }
    }

    private void gotoPlayerStats(Player myPlayer) {

        Intent newIntent = new Intent(TeamStats.this, PlayerStats.class);
        newIntent.putExtra(PlayerSelect.PLAYER_SELECTED, myPlayer.hash());
        newIntent.putExtra(PlayerSelect.ADD_PLAYER_BUTTONS_VISIBLE, false);
        startActivity(newIntent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==EDIT_TEAM_STATS_REQUEST && resultCode== Activity.RESULT_OK){
            importTeamStats();
            importTeamPlayers();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.equals(editButton)) {
            Intent newIntent = new Intent(TeamStats.this, TeamEdit.class);
            newIntent.putExtra(TeamSelect.TEAM_TO_BE_EDITED, myTeam.getTeamName());
            startActivityForResult(newIntent, EDIT_TEAM_STATS_REQUEST);
        } else if (v.equals(cancelButton)) {
            finish();
        }
    }
}
