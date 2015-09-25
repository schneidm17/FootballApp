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
        Enumeration<Player> myPlayers = MainActivity.playerHashtable.elements();

        while(myPlayers.hasMoreElements())
        {
            Player currentPlayer=myPlayers.nextElement();
            final Player FINAL_PLAYER = currentPlayer;
            String bufferString = "          ";

            TableRow myRow = new TableRow(this);
            myTable.addView(myRow);
            myRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoPlayerStats(FINAL_PLAYER);
                }
            });
            TextView playerName = new TextView(this);
            playerName.setText(FINAL_PLAYER.getFirstName() + " " + FINAL_PLAYER.getLastName() + bufferString);
            playerName.setTextColor(Color.WHITE);
            playerName.setTextSize(20);
            myRow.addView(playerName);

            TextView playerPosition = new TextView(this);
            playerPosition.setText(FINAL_PLAYER.getPosition() + bufferString);
            playerPosition.setTextColor(Color.WHITE);
            playerPosition.setTextSize(15);
            myRow.addView(playerPosition);

            TextView playerTeamInfo = new TextView(this);
            playerTeamInfo.setText(FINAL_PLAYER.getTeamNumber() + ", " + FINAL_PLAYER.getTeamName());
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