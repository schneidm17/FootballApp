package edu.up.cs301.schneidm17.football;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by schneidm17 on 9/29/2015.
 */
public class FootballField extends SurfaceView{

    private double scale;
    private int Xcent;
    private int Ycent;
    private Team teamOne;
    private Team teamTwo;
    private Paint lines;
    private Paint pitch;

    private ArrayList<Player> teamOneForwards;
    private ArrayList<Player> teamOneMidfielders;
    private ArrayList<Player> teamOneDefenders;
    private ArrayList<Player> teamOneGoalkeepers;
    private ArrayList<Player> teamTwoForwards;
    private ArrayList<Player> teamTwoMidfielders;
    private ArrayList<Player> teamTwoDefenders;
    private ArrayList<Player> teamTwoGoalkeepers;

    public FootballField(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        teamOneForwards = new ArrayList<>();
        teamOneMidfielders = new ArrayList<>();
        teamOneDefenders = new ArrayList<>();
        teamOneGoalkeepers = new ArrayList<>();
        teamTwoForwards = new ArrayList<>();
        teamTwoMidfielders = new ArrayList<>();
        teamTwoDefenders = new ArrayList<>();
        teamTwoGoalkeepers = new ArrayList<>();

        teamOne = MatchTeamSelect.matchTeamOne;
        teamTwo = MatchTeamSelect.matchTeamTwo;

        lines = new Paint();
        lines.setColor(Color.WHITE);
        lines.setStyle(Paint.Style.STROKE);
        lines.setStrokeWidth(4);

        pitch = new Paint();
        pitch.setColor(0xff006000);
        pitch.setStyle(Paint.Style.FILL);

        if(teamOne != null) {
            Enumeration<Player> teamOnePlayers = teamOne.getTeamPlayers().elements();
            while (teamOnePlayers.hasMoreElements()) {
                Player currentPlayer = teamOnePlayers.nextElement();
                switch (currentPlayer.getPositionEnum()) {
                    case FORWARD:
                        teamOneForwards.add(currentPlayer);
                        break;
                    case MIDFIELDER:
                        teamOneMidfielders.add(currentPlayer);
                        break;
                    case DEFENDER:
                        teamOneDefenders.add(currentPlayer);
                        break;
                    case GOALKEEPER:
                        teamOneGoalkeepers.add(currentPlayer);
                }
            }
        }

        if(teamTwo != null) {
            Enumeration<Player> teamTwoPlayers = teamTwo.getTeamPlayers().elements();
            while (teamTwoPlayers.hasMoreElements()) {
                Player currentPlayer = teamTwoPlayers.nextElement();
                switch (currentPlayer.getPositionEnum()) {
                    case FORWARD:
                        teamTwoForwards.add(currentPlayer);
                        break;
                    case MIDFIELDER:
                        teamTwoMidfielders.add(currentPlayer);
                        break;
                    case DEFENDER:
                        teamTwoDefenders.add(currentPlayer);
                        break;
                    case GOALKEEPER:
                        teamTwoGoalkeepers.add(currentPlayer);
                }
            }
        }
    }

    private float Xpos(double x){
        return (float)(Xcent + x*scale/90.0);
    }
    private float Ypos(double y){
        return (float)(Ycent + y*scale/90.0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onDraw(Canvas canvas) {
        int Xscale = (canvas.getWidth() - 100) / 2;
        int Yscale = (canvas.getHeight() - 100) / 2;
        scale = Math.min(Xscale, Yscale * 4 / 3);
        Xcent = canvas.getWidth() / 2;
        Ycent = canvas.getHeight() / 2;

        canvas.drawRect(Xpos(-90) - 10, Ypos(-67.5) - 10, Xpos(90) + 10, Ypos(67.5) + 10, pitch);
        canvas.drawRect(Xpos(-90), Ypos(-67.5), Xpos(0), Ypos(67.5), lines);
        canvas.drawRect(Xpos(0), Ypos(-67.5), Xpos(90), Ypos(67.5), lines);
        canvas.drawRect(Xpos(-90), Ypos(-45), Xpos(-54), Ypos(45), lines);
        canvas.drawRect(Xpos(54), Ypos(-45), Xpos(90), Ypos(45), lines);
        canvas.drawRect(Xpos(-90), Ypos(-21), Xpos(-78), Ypos(21), lines);
        canvas.drawRect(Xpos(78), Ypos(-21), Xpos(90), Ypos(21), lines);
        canvas.drawOval(Xpos(-15), Ypos(-15), Xpos(15), Ypos(15), lines);
        canvas.drawArc(Xpos(-78), Ypos(-15), Xpos(-48), Ypos(15),
                (float) Math.toDegrees(Math.acos(0.6)), -2 * (float) Math.toDegrees(Math.acos(0.6)), false, lines);
        canvas.drawArc(Xpos(48), Ypos(-15), Xpos(78), Ypos(15),
                180 + (float) Math.toDegrees(Math.acos(0.6)), -2 * (float) Math.toDegrees(Math.acos(0.6)), false, lines);
        canvas.drawArc(Xpos(-92), Ypos(-69.5), Xpos(-88), Ypos(-65.5), 0, 90, false, lines);
        canvas.drawArc(Xpos(-92), Ypos(65.5), Xpos(-88), Ypos(69.5), 0, -90, false, lines);
        canvas.drawArc(Xpos(88), Ypos(-69.5), Xpos(92), Ypos(-65.5), 180, -90, false, lines);
        canvas.drawArc(Xpos(88), Ypos(65.5), Xpos(92), Ypos(69.5), 180, 90, false, lines);

        //////Draw Team One players
        if(teamOne != null) {
            for (int x = 0; x < teamOneGoalkeepers.size(); x++) {
                Player myPlayer = teamOneGoalkeepers.get(x);
                Bitmap scaledPlayerPhoto = Bitmap.createScaledBitmap(myPlayer.getPlayerPhoto(), (int)(scale / 6), (int)(scale / 6), true);
                canvas.drawBitmap(scaledPlayerPhoto, Xpos(-78 - 7.5), Ypos(-7.5 + 9 * (teamOneGoalkeepers.size() - 1) - 18 * x), null);
            }
            for (int x = 0; x < teamOneDefenders.size(); x++) {
                Player myPlayer = teamOneDefenders.get(x);
                Bitmap scaledPlayerPhoto = Bitmap.createScaledBitmap(myPlayer.getPlayerPhoto(), (int)(scale / 6), (int)(scale / 6), true);
                canvas.drawBitmap(scaledPlayerPhoto, Xpos(-54 - 7.5), Ypos(-7.5 + 9 * (teamOneDefenders.size() - 1) - 18 * x), null);
            }
            for (int x = 0; x < teamOneMidfielders.size(); x++) {
                Player myPlayer = teamOneMidfielders.get(x);
                Bitmap scaledPlayerPhoto = Bitmap.createScaledBitmap(myPlayer.getPlayerPhoto(), (int)(scale / 6), (int)(scale / 6), true);
                canvas.drawBitmap(scaledPlayerPhoto, Xpos(-36 - 7.5), Ypos(-7.5 + 9 * (teamOneMidfielders.size() - 1) - 18 * x), null);
            }
            for (int x = 0; x < teamOneForwards.size(); x++) {
                Player myPlayer = teamOneForwards.get(x);
                Bitmap scaledPlayerPhoto = Bitmap.createScaledBitmap(myPlayer.getPlayerPhoto(), (int)(scale / 6), (int)(scale / 6), true);
                canvas.drawBitmap(scaledPlayerPhoto, Xpos(-18 - 7.5), Ypos(-7.5 + 9 * (teamOneForwards.size() - 1) - 18 * x), null);
            }
        }

        if(teamTwo!=null) {
            //////Draw Team Two players
            for (int x = 0; x < teamTwoForwards.size(); x++) {
                Player myPlayer = teamTwoForwards.get(x);
                Bitmap scaledPlayerPhoto = Bitmap.createScaledBitmap(myPlayer.getPlayerPhoto(), (int)(scale / 6), (int)(scale / 6), true);
                canvas.drawBitmap(scaledPlayerPhoto, Xpos(18 - 7.5), Ypos(-7.5 + 9 * (teamTwoForwards.size() - 1) - 18 * x), null);
            }
            for (int x = 0; x < teamTwoMidfielders.size(); x++) {
                Player myPlayer = teamTwoMidfielders.get(x);
                Bitmap scaledPlayerPhoto = Bitmap.createScaledBitmap(myPlayer.getPlayerPhoto(), (int)(scale / 6), (int)(scale / 6), true);
                canvas.drawBitmap(scaledPlayerPhoto, Xpos(36 - 7.5), Ypos(-7.5 + 9 * (teamTwoMidfielders.size() - 1) - 18 * x), null);
            }
            for (int x = 0; x < teamTwoDefenders.size(); x++) {
                Player myPlayer = teamTwoDefenders.get(x);
                Bitmap scaledPlayerPhoto = Bitmap.createScaledBitmap(myPlayer.getPlayerPhoto(), (int)(scale / 6), (int)(scale / 6), true);
                canvas.drawBitmap(scaledPlayerPhoto, Xpos(54 - 7.5), Ypos(-7.5 + 9 * (teamTwoDefenders.size() - 1) - 18 * x), null);
            }
            for (int x = 0; x < teamTwoGoalkeepers.size(); x++) {
                Player myPlayer = teamTwoGoalkeepers.get(x);
                Bitmap scaledPlayerPhoto = Bitmap.createScaledBitmap(myPlayer.getPlayerPhoto(), (int)(scale / 6), (int)(scale / 6), true);
                canvas.drawBitmap(scaledPlayerPhoto, Xpos(78 - 7.5), Ypos(-7.5 + 9 * (teamTwoGoalkeepers.size() - 1) - 18 * x), null);
            }
        }
    }
}