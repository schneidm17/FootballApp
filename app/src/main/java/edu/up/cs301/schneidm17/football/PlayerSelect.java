package edu.up.cs301.schneidm17.football;

import android.app.Activity;
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

    //public static Player playerSelected;
    public static final String PLAYER_SELECTED = "PLAYER_SELECTED";
    public static final String TEAM_TO_HIDE = "TEAM_TO_HIDE";
    public static final String ADD_PLAYER_BUTTONS_VISIBLE = "ADD_PLAYER_BUTTONS_VISIBLE";
    private boolean addButtonsView;
    private Team hiddenTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_select);

        Intent myIntent = getIntent();
        addButtonsView = myIntent.getBooleanExtra(ADD_PLAYER_BUTTONS_VISIBLE, false);
        String teamToHide = myIntent.getStringExtra(TEAM_TO_HIDE);
        try {
            hiddenTeam = (teamToHide == null) ? null : MainActivity.allTeams.get(teamToHide);
        } catch (Exception e) {
            hiddenTeam = null;
        }

        TextView title = (TextView) findViewById(R.id.selectPlayerHeader);
        TextView subtitle = (TextView) findViewById(R.id.selectPlayerSubtitle);
        if(addButtonsView) {
            title.setText("Add a Player");
            subtitle.setText("Click on a player to add them to your team");
        }

        createPlayerTable();
        //playerSelected=null;
    }

    private void createPlayerTable() {
        TableLayout myTable = (TableLayout)findViewById(R.id.playerTableView);
        Enumeration<Player> myPlayers = MainActivity.allPlayers.elements();

        while(myPlayers.hasMoreElements())
        {
            final Player currentPlayer=myPlayers.nextElement();
            if(hiddenTeam!=null && hiddenTeam.getTeamPlayers().containsValue(currentPlayer)) {
                continue;
            }

            String bufferString = "          ";
            final TableRow myRow = new TableRow(this);
            myTable.addView(myRow);
            if(!addButtonsView) {
                myRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gotoPlayerStats(currentPlayer);
                    }
                });
            } else {
                myRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent data = new Intent();
                        data.putExtra(PLAYER_SELECTED, currentPlayer.hash());
                        setResult(Activity.RESULT_OK, data);
                        finish();
                    }
                });
                myRow.setOnLongClickListener(new View.OnLongClickListener(){
                    @Override
                    public boolean onLongClick(View v) {
                        myRow.setBackgroundColor(0x40ffffff);
                        return false;
                    }
                });
            }

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
        Intent newIntent = new Intent(PlayerSelect.this, PlayerStats.class);
        newIntent.putExtra(PLAYER_SELECTED, myPlayer.hash());
        startActivity(newIntent);
    }
}