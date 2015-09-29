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
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.Externalizable;
import java.util.Enumeration;
import java.util.Hashtable;


public class TeamEdit extends ActionBarActivity implements View.OnClickListener{

    private static final int SELECT_PLAYER_TO_ADD_REQUEST = 369;

    private Hashtable<String, TableRow> playerTablePointers;
    private TableLayout myTable;

    private Team currentTeam;
    private String teamNamePrompt = "Team Name";
    private String teamMottoPrompt = "Team Motto (Optional)";

    private TextView teamNameNoEditField;
    private EditText teamNameField;
    private EditText teamMottoField;
    private EditText teamWinsField;
    private EditText teamLossesField;
    private EditText teamTiesField;
    private TextView numPlayers;

    private Button saveButton;
    private Button cancelButton;
    private Button addButton;
    private Button removeButton;
    private boolean waitingToRemovePlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_edit);

        teamNameField = (EditText)findViewById(R.id.teamNameField);
        teamNameNoEditField = (TextView)findViewById(R.id.teamNameNoEditField);
        teamMottoField = (EditText)findViewById(R.id.teamMottoField);
        teamWinsField = (EditText)findViewById(R.id.teamWinsField);
        teamLossesField = (EditText)findViewById(R.id.teamLossesField);
        teamTiesField = (EditText)findViewById(R.id.teamTiesField);
        numPlayers = (TextView)findViewById(R.id.numTeamPlayers);

        saveButton = (Button)findViewById(R.id.saveTeamStatsButton);
        cancelButton = (Button)findViewById(R.id.cancelTeamStatsButton);
        addButton = (Button)findViewById(R.id.addPlayerToTeamButton);
        removeButton = (Button)findViewById(R.id.removePlayerFromTeamButton);
        saveButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        addButton.setOnClickListener(this);
        removeButton.setOnClickListener(this);

        myTable = (TableLayout)findViewById(R.id.teamPlayerEditTableView);
        playerTablePointers = new Hashtable<>();
        waitingToRemovePlayer=false;

        Intent myIntent = getIntent();
        String currentTeamName = myIntent.getStringExtra(TeamSelect.TEAM_TO_BE_EDITED);
        try{
            currentTeam = (currentTeamName==null) ? null : MainActivity.allTeams.get(currentTeamName);
        } catch (Exception e) {
            currentTeam = null;
        }

        loadTeamData();
        importTeamPlayers();
    }

    private void loadTeamData(){
        if(currentTeam==null) {
            teamNameField.setVisibility(View.VISIBLE);
            teamNameNoEditField.setVisibility(View.GONE);
            teamNameField.setText(teamNamePrompt);
            teamMottoField.setText(teamMottoPrompt);

            findViewById(R.id.numTeamPlayersRow).setVisibility(View.GONE);
            addButton.setVisibility(View.GONE);
            removeButton.setVisibility(View.GONE);
            return;
        }

        teamNameField.setVisibility(View.GONE);
        teamNameNoEditField.setVisibility(View.VISIBLE);
        teamNameNoEditField.setText(currentTeam.getTeamName());
        teamMottoField.setText((currentTeam.hasMotto()) ? currentTeam.getTeamMotto() : teamMottoPrompt);
        teamWinsField.setText(""+currentTeam.getWins());
        teamLossesField.setText("" + currentTeam.getLosses());
        teamTiesField.setText("" + currentTeam.getTies());
        numPlayers.setText("" + currentTeam.getTeamPlayers().size());
    }

    private void importTeamPlayers() {
        if(currentTeam==null) {
            return;
        }

        Enumeration<Player> myPlayers = currentTeam.getTeamPlayers().elements();
        while(myPlayers.hasMoreElements()) {
            addPlayerToView(myPlayers.nextElement());
        }
    }

    private void addPlayerToView(final Player currentPlayer){
        String bufferString = "          ";

        final TableRow myRow = new TableRow(this);
        myTable.addView(myRow);
        myRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTeamMembers(currentPlayer);
            }
        });
        myRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                myRow.setBackgroundColor(0x40ffffff);
                return false;
            }
        });

        //store this pointer for use later, eg. if player is removed from the table
        playerTablePointers.put(currentPlayer.hash(), myRow);

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

    private void editTeamMembers(Player myPlayer) {
        if(waitingToRemovePlayer) {
            if(currentTeam.removePlayer(myPlayer)) {
                try {
                    TableRow rowRemoved = playerTablePointers.get(myPlayer.hash());
                    myTable.removeView(rowRemoved);
                } catch (Exception ignore) {}
            }
            numPlayers.setText("" + currentTeam.getTeamPlayers().size());
            waitingToRemovePlayer=false;
            addButton.setClickable(true);
            addButton.setTextColor(Color.BLACK);
        } else {
            Intent newIntent = new Intent(TeamEdit.this, PlayerStats.class);
            newIntent.putExtra(PlayerSelect.PLAYER_SELECTED, myPlayer.hash());
            newIntent.putExtra(PlayerSelect.ADD_PLAYER_BUTTONS_VISIBLE, false);
            startActivity(newIntent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==SELECT_PLAYER_TO_ADD_REQUEST && resultCode== Activity.RESULT_OK){
            String playerToAdd=data.getStringExtra(PlayerSelect.PLAYER_SELECTED);
            try {
                Player newPlayer = MainActivity.allPlayers.get(playerToAdd);
                if(currentTeam.addPlayer(newPlayer)) {
                    addPlayerToView(newPlayer);
                }
            } catch (Exception ignore) {}
            numPlayers.setText("" + currentTeam.getTeamPlayers().size());
        }
    }

    @Override
    public void onClick(View view) {
        if(view.equals(addButton)){
            Intent newIntent = new Intent(TeamEdit.this, PlayerSelect.class);
            newIntent.putExtra(PlayerSelect.ADD_PLAYER_BUTTONS_VISIBLE, true);
            newIntent.putExtra(PlayerSelect.TEAM_TO_HIDE, currentTeam.getTeamName());
            startActivityForResult(newIntent, SELECT_PLAYER_TO_ADD_REQUEST);
        }

        else if(view.equals(removeButton)){
            if(waitingToRemovePlayer) {
                waitingToRemovePlayer=false;
                addButton.setClickable(true);
                addButton.setTextColor(Color.BLACK);
            } else {
                waitingToRemovePlayer = true;
                addButton.setClickable(false);
                addButton.setTextColor(Color.GRAY);
            }
        }

        else if(view.equals(saveButton)){
            String teamName = this.teamNameField.getText().toString().trim();
            if(teamName.equals(teamNamePrompt) || teamName.equals("")) {
                teamName="New_Team";
            }

            //create a new team
            boolean createdNewTeam = false;
            if(currentTeam==null) {
                //if the name of the team is already taken
                if(MainActivity.allTeams.containsKey(teamName)) {
                    teamName = teamName + "_2";
                }
                currentTeam = new Team(teamName);
                MainActivity.allTeams.put(teamName, currentTeam);
                createdNewTeam=true;
            }

            String teamMotto = this.teamMottoField.getText().toString().trim();
            if(!teamMotto.equals(teamMottoPrompt) && !teamMotto.equals("")) {
                currentTeam.setMotto(teamMotto);
            }

            try {
                String wins = this.teamWinsField.getText().toString().trim();
                String losses = this.teamLossesField.getText().toString().trim();
                String ties = this.teamTiesField.getText().toString().trim();

                int numWins = (wins.equals("")) ? 0: Integer.parseInt(wins);
                int numLosses = (losses.equals("")) ? 0 : Integer.parseInt(losses);
                int numTies = (ties.equals("")) ? 0 : Integer.parseInt(ties);
                currentTeam.setRecord(numWins, numLosses, numTies);
            } catch (NumberFormatException e) {
                currentTeam.setRecord(0,0,0);
            }

            Intent data = new Intent();
            data.putExtra(TeamSelect.NEW_TEAM_NAME, currentTeam.getTeamName());
            data.putExtra(TeamSelect.CREATED_NEW_TEAM, createdNewTeam);
            setResult(Activity.RESULT_OK, data);
            finish();
        }
        else if(view.equals(cancelButton)){
            if(waitingToRemovePlayer){
                waitingToRemovePlayer=false;
                addButton.setClickable(true);
                addButton.setTextColor(Color.BLACK);
            } else {
                Intent data = new Intent();
                data.putExtra(TeamSelect.CREATED_NEW_TEAM, false);
                setResult(Activity.RESULT_CANCELED, data);
                finish();
            }
        }
    }
}
