package edu.up.cs301.schneidm17.football;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Enumeration;


public class MatchTeamSelect extends ActionBarActivity {

    private int team1Selected;
    private int team2Selected;
    ArrayList<TableRow> rowsInTable1;
    ArrayList<TableRow> rowsInTable2;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_team_select);

        Button startButton = (Button) findViewById(R.id.beginMatchButton);
        TableLayout table1 = (TableLayout) findViewById(R.id.selectFirstTeamTable);
        TableLayout table2 = (TableLayout) findViewById(R.id.selectSecondTeamTable);
        rowsInTable1 = new ArrayList<>();
        rowsInTable2 = new ArrayList<>();
        team1Selected = -1;
        team2Selected = -1;
        int currentRow = -1;

        Enumeration<Team> myTeams = MainActivity.allTeams.elements();
        while (myTeams.hasMoreElements()) {
            Team currentTeam = myTeams.nextElement();
            currentRow++;

            final TableRow row1 = new TableRow(this);
            final TableRow row2 = new TableRow(this);
            table1.addView(row1);
            table2.addView(row2);
            rowsInTable1.add(row1);
            rowsInTable2.add(row2);
            row1.setPadding(50,20,50,20);
            row2.setPadding(50,20,50,20);
            ImageView logo1 = new ImageView(this);
            ImageView logo2 = new ImageView(this);
            try {
                Bitmap scaledTeamPhoto = Bitmap.createScaledBitmap(currentTeam.getTeamPhoto(), 150, 150, true);
                logo1.setImageBitmap(scaledTeamPhoto);
                logo2.setImageBitmap(scaledTeamPhoto);
            } catch (NullPointerException e) {
                logo1.setMinimumHeight(150);
                logo1.setMinimumWidth(150);
                logo1.setBackgroundColor(0x40ffffff);
                logo2.setMinimumHeight(150);
                logo2.setMinimumWidth(150);
                logo2.setBackgroundColor(0x40ffffff);
            }
            row1.addView(logo1);
            row2.addView(logo2);
            TextView name1 = new TextView(this);
            TextView name2 = new TextView(this);
            name1.setText(currentTeam.getTeamName());
            name1.setTextColor(Color.WHITE);
            name1.setTextSize(30);
            row1.addView(name1);
            name2.setText(currentTeam.getTeamName());
            name2.setTextColor(Color.WHITE);
            name2.setTextSize(30);
            row2.addView(name2);

            final int ROW_THIS=currentRow;
            row1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTeamSelected(1,ROW_THIS);
                }
            });
            row2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTeamSelected(2,ROW_THIS);
                }
            });
        }
    }

    private void setTeamSelected(int n, int currentRow){
        if(n==1) {
            try {
                TableRow oldRow = rowsInTable1.get(team1Selected);
                oldRow.setBackgroundColor(0x00000000);
            } catch (Exception ignore) {}
            TableRow newRow = rowsInTable1.get(currentRow);
            newRow.setBackgroundColor(0x40ffffff);
            team1Selected = currentRow;
        } else if (n==2) {
            try {
                TableRow oldRow = rowsInTable2.get(team2Selected);
                oldRow.setBackgroundColor(0x00000000);
            } catch (Exception ignore) {}
            TableRow newRow = rowsInTable2.get(currentRow);
            newRow.setBackgroundColor(0x40ffffff);
            team2Selected = currentRow;
        }
    }
}
