package edu.up.cs301.schneidm17.football;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Enumeration;
import java.util.HashMap;


public class TeamStats extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_stats);
        importTeamStats(TeamSelect.teamSelected);
    }

    private void importTeamStats(Team myTeam) {
        if(myTeam==null) {
            return;
        }

        TextView teamName = (TextView)findViewById(R.id.teamName);
        teamName.setText(myTeam.getTeamName());
        ImageView teamPhoto = (ImageView)findViewById(R.id.teamLogoView);
        teamPhoto.setImageBitmap(myTeam.getTeamPhoto());


        TableLayout myTable = (TableLayout)findViewById(R.id.teamPlayerTableView);
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
        PlayerSelect.playerSelected = myPlayer;
        startActivity(new Intent(TeamStats.this, PlayerStats.class));
    }
}
