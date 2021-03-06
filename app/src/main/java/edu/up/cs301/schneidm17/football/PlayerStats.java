package edu.up.cs301.schneidm17.football;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class PlayerStats extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats);

        Intent myIntent = getIntent();
        String currentPlayerHash = myIntent.getStringExtra(PlayerSelect.PLAYER_SELECTED);
        if(MainActivity.allPlayers.containsKey(currentPlayerHash)) {
            importPlayerStats(MainActivity.allPlayers.get(currentPlayerHash));
        }
    }

    private void importPlayerStats(Player myPlayer) {
        if(myPlayer==null) {
            return;
        }

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
        ImageView playerPhoto = (ImageView) findViewById(R.id.playerPhoto);
        ImageView TeamLogoPhoto = (ImageView) findViewById(R.id.playerTeamLogoView);

        playerFirstName.setText(myPlayer.getFirstName());
        playerLastName.setText(myPlayer.getLastName());
        playerPosition.setText(myPlayer.getPosition());
        playerTeamName.setText(myPlayer.getTeamName());
        playerTeamNumber.setText(myPlayer.getTeamNumber());
        if(myPlayer.getAge()>0) {
            playerAge.setText("" + myPlayer.getAge());
        } else {
            playerAge.setText("Unknown");
        }
        playerGoals.setText(""+myPlayer.getGoals());
        playerAssists.setText(""+myPlayer.getAssists());
        playerShots.setText(""+myPlayer.getShots());
        playerSaves.setText(""+myPlayer.getSaves());
        playerFouls.setText(""+myPlayer.getFouls());
        playerYCards.setText(""+myPlayer.getyCards());
        playerRCards.setText("" + myPlayer.getrCards());
        playerPhoto.setImageBitmap(myPlayer.getPlayerPhoto());

        try {
            Bitmap scaledTeamPhoto = Bitmap.createScaledBitmap(
            MainActivity.allTeams.get(myPlayer.getTeamName()).getTeamPhoto(),100,100,true);
            TeamLogoPhoto.setImageBitmap(scaledTeamPhoto);
        } catch (NullPointerException e) {
            TeamLogoPhoto.setMinimumHeight(100);
            TeamLogoPhoto.setMinimumWidth(100);
            TeamLogoPhoto.setBackgroundColor(0x40ffffff);
        }
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
