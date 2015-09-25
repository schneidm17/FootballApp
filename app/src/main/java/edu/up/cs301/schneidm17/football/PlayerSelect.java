package edu.up.cs301.schneidm17.football;

import android.content.Intent;
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

            TableRow myRow = new TableRow(this);
            myTable.addView(myRow);
            Button playerNameButton = new Button(this);
            playerNameButton.setText(currentPlayer.getFirstName() + " " + currentPlayer.getLastName());
            myRow.addView(playerNameButton);

            final Player FINAL_PLAYER = currentPlayer;
            playerNameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoPlayerStats(FINAL_PLAYER);
                }
            });

            TextView playerInfoView = new TextView(this);
            playerInfoView.setText(currentPlayer.getPosition()+", "+currentPlayer.getTeamNumber()+" "+currentPlayer.getTeamName());
            myRow.addView(playerInfoView);
        }
    }

    private void gotoPlayerStats(Player myPlayer) {
        playerSelected = myPlayer;
        startActivity(new Intent(PlayerSelect.this, PlayerStats.class));
    }
}