package edu.up.cs301.schneidm17.football;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Enumeration;
import java.util.Hashtable;


public class PlayerSelect extends ActionBarActivity {

    public static Player playerSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_select);

        createPlayerTable();
        playerSelected=null;
    }

    private void createPlayerTable() {
        TableLayout myTable = (TableLayout)findViewById(R.id.playerTableView);
        Enumeration<Player> myPlayers = MainActivity.allPlayers.elements();

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
        playerSelected = myPlayer;
        startActivity(new Intent(PlayerSelect.this, PlayerStats.class));
    }
}