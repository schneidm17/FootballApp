package edu.up.cs301.schneidm17.football;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Externalizable;


public class TeamEdit extends ActionBarActivity implements View.OnClickListener{

    private Team currentTeam;
    private String teamNamePrompt = "Team Name";
    private String teamMottoPrompt = "Team Motto (Optional)";

    private TextView teamNameNoEditField;
    private EditText teamNameField;
    private EditText teamMottoField;
    private EditText teamWinsField;
    private EditText teamLossesField;
    private EditText teamTiesField;

    private Button saveButton;
    private Button cancelButton;

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

        saveButton = (Button)findViewById(R.id.saveTeamStatsButton);
        cancelButton = (Button)findViewById(R.id.cancelTeamStatsButton);
        saveButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        Intent myIntent = getIntent();
        String currentTeamName = myIntent.getStringExtra(TeamSelect.TEAM_TO_BE_EDITED);
        try{
            currentTeam = (currentTeamName==null) ? null : MainActivity.allTeams.get(currentTeamName);
        } catch (Exception e) {
            currentTeam = null;
        }

        loadTeamData();
    }

    private void loadTeamData(){
        if(currentTeam==null) {
            teamNameField.setVisibility(View.VISIBLE);
            teamNameNoEditField.setVisibility(View.GONE);
            teamNameField.setText(teamNamePrompt);
            teamMottoField.setText(teamMottoPrompt);
            return;
        }

        teamNameField.setVisibility(View.GONE);
        teamNameNoEditField.setVisibility(View.VISIBLE);
        teamNameNoEditField.setText(currentTeam.getTeamName());
        teamMottoField.setText((currentTeam.hasMotto()) ? currentTeam.getTeamMotto() : teamMottoPrompt);
        teamWinsField.setText(""+currentTeam.getWins());
        teamLossesField.setText("" + currentTeam.getLosses());
        teamTiesField.setText(""+currentTeam.getTies());
    }

    @Override
    public void onClick(View view) {
        if(view.equals(saveButton)){
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
            Intent data = new Intent();
            data.putExtra(TeamSelect.CREATED_NEW_TEAM, false);
            setResult(Activity.RESULT_CANCELED, data);
            finish();
        }
    }
}
