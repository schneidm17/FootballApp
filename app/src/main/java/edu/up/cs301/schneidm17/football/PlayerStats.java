package edu.up.cs301.schneidm17.football;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class PlayerStats extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats);
        importPlayerStats(PlayerSelect.playerSelected);
    }

    private void importPlayerStats(Player myPlayer) {
        TextView playerFirstName = (TextView) findViewById(R.id.playerFirstName);
        TextView playerLastName = (TextView) findViewById(R.id.playerLastName);
        TextView playerPosition = (TextView) findViewById(R.id.playerPosition);
        TextView playerTeamName = (TextView) findViewById(R.id.playerTeamName);
        TextView playerTeamNumber = (TextView) findViewById(R.id.playerTeamNumber);
        TextView playerAge = (TextView) findViewById(R.id.playerAge);
        TextView playerGoals = (TextView) findViewById(R.id.playerGoals);
        TextView playerAssists = (TextView) findViewById(R.id.playerAssists);
        TextView playerShots = (TextView) findViewById(R.id.playerShots);
        TextView playerSaves = (TextView) findViewById(R.id.playerSaves);
        TextView playerFouls = (TextView) findViewById(R.id.playerFouls);
        TextView playerYCards = (TextView) findViewById(R.id.playerYCards);
        TextView playerRCards = (TextView) findViewById(R.id.playerRCards);

        playerFirstName.setText(myPlayer.getFirstName());
        playerLastName.setText(myPlayer.getLastName());
        playerPosition.setText(myPlayer.getPosition());
        playerTeamName.setText(myPlayer.getTeamName());
        playerTeamNumber.setText(myPlayer.getTeamNumber());
        playerAge.setText(""+myPlayer.getAge());
        playerGoals.setText(""+myPlayer.getGoals());
        playerAssists.setText(""+myPlayer.getAssists());
        playerShots.setText(""+myPlayer.getShots());
        playerSaves.setText(""+myPlayer.getSaves());
        playerFouls.setText(""+myPlayer.getFouls());
        playerYCards.setText(""+myPlayer.getyCards());
        playerRCards.setText(""+myPlayer.getrCards());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
