package edu.up.cs301.schneidm17.football;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class TeamEdit extends ActionBarActivity implements View.OnClickListener{

    private Team currentTeam;
    private String teamNamePrompt = "Team Name";
    private String teamMottoPrompt = "Team Motto (Optional)";

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
        teamMottoField = (EditText)findViewById(R.id.teamMottoField);
        teamWinsField = (EditText)findViewById(R.id.teamWinsField);
        teamLossesField = (EditText)findViewById(R.id.teamLossesField);
        teamTiesField = (EditText)findViewById(R.id.teamTiesField);

        saveButton = (Button)findViewById(R.id.saveTeamStatsButton);
        cancelButton = (Button)findViewById(R.id.cancelTeamStatsButton);
        saveButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        currentTeam = TeamSelect.teamSelected;
        loadTeamData();
    }

    private void loadTeamData(){
        if(currentTeam==null) {
            teamNameField.setText(teamNamePrompt);
            teamMottoField.setText(teamMottoPrompt);
            return;
        }

        teamNameField.setText(currentTeam.getTeamName());
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
            if(currentTeam==null) {
                //if the name of the team is already taken
                if(MainActivity.allTeams.containsKey(teamName)) {
                    teamName = teamName + "_2";
                }
                currentTeam = new Team(teamName);
                MainActivity.allTeams.put(teamName, currentTeam);
            }

            String teamMotto = this.teamMottoField.getText().toString().trim();
            if(!teamMotto.equals(teamMottoPrompt) && !teamMotto.equals("")) {
                currentTeam.setMotto(teamMotto);
            }

            try {
                String wins = this.teamWinsField.getText().toString().trim();
                String losses = this.teamLossesField.getText().toString().trim();
                String ties = this.teamTiesField.getText().toString().trim();
                int numWins = Integer.parseInt(wins);
                int numLosses = Integer.parseInt(losses);
                int numTies = Integer.parseInt(ties);
                currentTeam.setRecord(numWins, numLosses, numTies);
            } catch (NumberFormatException e) {
                currentTeam.setRecord(0,0,0);
            }

            finish();
        }
        else if(view.equals(cancelButton)){
            finish();
        }
    }
}
