package edu.up.cs301.schneidm17.football;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;


public class MainActivity extends ActionBarActivity {

    public static Hashtable<String, Player> playerHashtable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerHashtable = new Hashtable<>();
        addDefaultPlayers();

        TextView foo = (TextView)findViewById(R.id.startButton);
        foo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PlayerSelect.class));
            }
        });
    }

    private void addDefaultPlayers() {
        addToHashtable(playerHashtable, new Player("Mathew", "Rosemond", "TeamOne FC", 1, Player.position.FORWARD, 20, 10, 7, 30, 0, 2, 1, 0));
        addToHashtable(playerHashtable, new Player("Dominic", "Piesse", "TeamTwo FC", 1, Player.position.FORWARD, 28, 9, 3, 19, 0, 1, 0, 0));
        addToHashtable(playerHashtable, new Player("Adam", "Parsons", "TeamThree FC", 1, Player.position.FORWARD, 27, 12, 5, 18, 0, 1, 0, 0));
        addToHashtable(playerHashtable, new Player("Zak", "Martin", "TeamFour FC", 1, Player.position.FORWARD, 27, 8, 3, 28, 0, 4, 1, 0));
        /*
        addToHashtable(playerHashtable, new Player("George", "Byrne", "TeamOne FC", 2, Player.position.FORWARD, 26, 9, 8, 18, 0, 2, 1, 0));
        addToHashtable(playerHashtable, new Player("Lucas", "Webster", "TeamTwo FC", 2, Player.position.FORWARD, 22, 14, 9, 31, 0, 6, 2, 0));
        addToHashtable(playerHashtable, new Player("Toby", "Mackay", "TeamThree FC", 2, Player.position.FORWARD, 21, 12, 7, 20, 0, 3, 0, 0));
        addToHashtable(playerHashtable, new Player("Mackenzie", "Akhtar", "TeamFour FC", 2, Player.position.FORWARD, 18, 5, 9, 12, 0, 1, 0, 0));
        addToHashtable(playerHashtable, new Player("Joe", "Parkes", "TeamOne FC", 3, Player.position.FORWARD, 23, 4, 8, 16, 0, 2, 0, 1));
        addToHashtable(playerHashtable, new Player("Charlie", "Hayward", "TeamTwo FC", 3, Player.position.FORWARD, 28, 6, 7, 20, 0, 4, 1, 0));
        addToHashtable(playerHashtable, new Player("Brogan", "Martin", "TeamThree FC", 3, Player.position.FORWARD, 23, 3, 6, 14, 0, 1, 0, 0));
        addToHashtable(playerHashtable, new Player("Jeremy", "Hartog", "TeamFour FC", 3, Player.position.FORWARD, 19, 2, 4, 10, 0, 3, 0, 0));
        addToHashtable(playerHashtable, new Player("Anthony", "Leathers", "TeamOne FC", 4, Player.position.FORWARD, 28, 0, 6, 9, 0, 1, 0, 0));
        addToHashtable(playerHashtable, new Player("Glenn", "Wallace", "TeamTwo FC", 4, Player.position.FORWARD, 19, 1, 4, 13, 0, 2, 0, 0));
        addToHashtable(playerHashtable, new Player("Charles", "Carter", "TeamThree FC", 4, Player.position.FORWARD, 21, 1, 3, 8, 0, 2, 2, 0));
        addToHashtable(playerHashtable, new Player("Elliot", "Barr", "TeamFour FC", 4, Player.position.FORWARD, 28, 0, 2, 8, 0, 1, 0, 0));
        addToHashtable(playerHashtable, new Player("Jack", "Clark", "TeamOne FC", 5, Player.position.MIDFIELDER, 23, 0, 4, 9, 0, 1, 0, 0));
        addToHashtable(playerHashtable, new Player("Daniel", "Bentley", "TeamTwo FC", 5, Player.position.MIDFIELDER, 24, 1, 7, 6, 1, 0, 0, 0));
        addToHashtable(playerHashtable, new Player("Jenson", "Taylor", "TeamThree FC", 5, Player.position.MIDFIELDER, 19, 2, 8, 4, 0, 2, 1, 0));
        addToHashtable(playerHashtable, new Player("Leo", "Wall", "TeamFour FC", 5, Player.position.MIDFIELDER, 27, 1, 6, 7, 1, 0, 0, 0));
        addToHashtable(playerHashtable, new Player("Dawid", "Hughes", "TeamOne FC", 6, Player.position.MIDFIELDER, 22, 0, 2, 8, 1, 3, 1, 0));
        addToHashtable(playerHashtable, new Player("Xavier", "Davey", "TeamTwo FC", 6, Player.position.MIDFIELDER, 20, 1, 5, 4, 0, 0, 0, 0));
        addToHashtable(playerHashtable, new Player("Christopher", "Carter", "TeamThree FC", 6, Player.position.MIDFIELDER, 27, 0, 7, 2, 1, 1, 0, 0));
        addToHashtable(playerHashtable, new Player("Lochlan", "Clark", "TeamFour FC", 6, Player.position.MIDFIELDER, 21, 1, 2, 2, 2, 0, 0, 0));
        addToHashtable(playerHashtable, new Player("Mason", "Holden", "TeamOne FC", 7, Player.position.DEFENDER, 25, 0, 1, 1, 2, 1, 0, 0));
        addToHashtable(playerHashtable, new Player("David", "Atkins", "TeamTwo FC", 7, Player.position.DEFENDER, 20, 0, 0, 1, 3, 1, 0, 0));
        addToHashtable(playerHashtable, new Player("Liam", "Howarth", "TeamThree FC", 7, Player.position.DEFENDER, 26, 0, 0, 0, 2, 3, 1, 0));
        addToHashtable(playerHashtable, new Player("Spencer", "Palmer", "TeamFour FC", 7, Player.position.DEFENDER, 18, 0, 1, 1, 4, 0, 0, 0));
        addToHashtable(playerHashtable, new Player("Declan", "Bryant", "TeamOne FC", 8, Player.position.DEFENDER, 22, 0, 0, 0, 6, 2, 1, 0));
        addToHashtable(playerHashtable, new Player("Joshua", "Yelverton", "TeamTwo FC", 8, Player.position.DEFENDER, 28, 0, 1, 0, 4, 0, 0, 0));
        addToHashtable(playerHashtable, new Player("Quinn", "Ferguson", "TeamThree FC", 8, Player.position.DEFENDER, 19, 0, 0, 0, 3, 0, 0, 0));
        addToHashtable(playerHashtable, new Player("Riley", "Moore", "TeamFour FC", 8, Player.position.DEFENDER, 26, 0, 0, 0, 12, 4, 3, 1));
        addToHashtable(playerHashtable, new Player("Jamie", "Brady", "TeamOne FC", 9, Player.position.DEFENDER, 23, 0, 1, 0, 4, 0, 0, 0));
        addToHashtable(playerHashtable, new Player("Timothy", "Billington", "TeamTwo FC", 9, Player.position.DEFENDER, 25, 0, 0, 0, 10, 1, 0, 0));
        addToHashtable(playerHashtable, new Player("Kian", "Bartlett", "TeamThree FC", 9, Player.position.DEFENDER, 28, 0, 0, 0, 7, 3, 1, 1));
        addToHashtable(playerHashtable, new Player("Wiktor", "McMillan", "TeamFour FC", 9, Player.position.DEFENDER, 19, 0, 0, 0, 9, 1, 0, 0));
        addToHashtable(playerHashtable, new Player("James", "Barnes", "TeamOne FC", 10, Player.position.DEFENDER, 22, 0, 0, 0, 11, 2, 1, 0));
        addToHashtable(playerHashtable, new Player("Armaan", "McLean", "TeamTwo FC", 10, Player.position.DEFENDER, 18, 0, 0, 0, 10, 0, 0, 0));
        addToHashtable(playerHashtable, new Player("Alexander", "Webster", "TeamThree FC", 10, Player.position.DEFENDER, 20, 0, 0, 0, 15, 1, 0, 0));
        addToHashtable(playerHashtable, new Player("Max", "Chamberlain", "TeamFour FC", 10, Player.position.DEFENDER, 25, 0, 0, 0, 12, 0, 0, 0));
        addToHashtable(playerHashtable, new Player("William", "O'Malley", "TeamOne FC", 0, Player.position.GOALKEEPER, 25, 0, 0, 0, 37, 0, 0, 0));
        addToHashtable(playerHashtable, new Player("Corey", "Short", "TeamTwo FC", 0, Player.position.GOALKEEPER, 27, 0, 0, 0, 42, 0, 1, 0));
        addToHashtable(playerHashtable, new Player("Bailey", "Stephenson", "TeamThree FC", 0, Player.position.GOALKEEPER, 24, 0, 0, 0, 31, 0, 0, 0));
        addToHashtable(playerHashtable, new Player("Owen", "MacDonald", "TeamFour FC", 0, Player.position.GOALKEEPER, 23, 0, 0, 0, 26, 0, 0, 0));
        */
    }

    private void addToHashtable(Hashtable<String, Player> myHash, Player myPlayer) {
        myHash.put(myPlayer.hash(), myPlayer);
    }

}
