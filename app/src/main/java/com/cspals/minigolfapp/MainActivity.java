package com.cspals.minigolfapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.view.View;
import android.widget.*;
import android.text.format.Time;


public class MainActivity extends AppCompatActivity {

    public static boolean playersNamed = false;
    public static int numPlayers = 1;
//  public static final String FILE_NAME = "games.txt";
    public static GameSave[] games;
    public static int gameIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        games = SaveHelper.load(this);
        if(games == null) {
            games = new GameSave[5];
        }

        getGameIndex();
//        TextView temp = findViewById(R.id.TempTestText);
//        temp.setText(Integer.toString(gameIndex));
    }

    private void getGameIndex (){
        Time compare = new Time();
        compare.setToNow();

        for(int i = 0; i < games.length; i++){

            if(games[i] == null){
                gameIndex = i;
                break;
            }

            GameSave current = games[i];

            if(current.gameDate == null){
                gameIndex = i;
                break;
            }

            if(current.gameDate.year < compare.year){
                gameIndex = i;
                compare = current.gameDate;
            }
            else if(current.gameDate.year == compare.year && current.gameDate.month < compare.month){
                gameIndex = i;
                compare = current.gameDate;
            }
            else if(current.gameDate.month == compare.month && current.gameDate.monthDay < compare.monthDay){
                gameIndex = i;
                compare = current.gameDate;
            }
            else if(current.gameDate.monthDay == compare.monthDay && current.gameDate.hour < compare.hour){
                gameIndex = i;
                compare = current.gameDate;
            }
            else if(current.gameDate.hour == compare.hour && current.gameDate.minute < compare.minute){
                gameIndex = i;
                compare = current.gameDate;
            }
            else if(current.gameDate.minute == compare.minute && current.gameDate.second < compare.second){
                gameIndex = i;
                compare = current.gameDate;
            }
        }
    }

    public void showEnterPlayersDialog(View v) {

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK){
            case Configuration.UI_MODE_NIGHT_YES:
                builder.setTitle(Html.fromHtml("<font color='#ffffff'>How many players?</font>"));
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                builder.setTitle(Html.fromHtml("<font color='#000001'>How many players?</font>"));
                break;
        }
        EditText players = new EditText(MainActivity.this);
        players.setInputType(InputType.TYPE_CLASS_NUMBER);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        players.setLayoutParams(lp);
        builder.setView(players);
        // add the buttons
        builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String playersStr = players.getText().toString();

                if(playersStr.length() > 0 && playersStr.length() < 3) {
                    if(Integer.parseInt(playersStr) > 0 && Integer.parseInt(playersStr) <= 10) {
                        numPlayers = Integer.parseInt(players.getText().toString());
                        showNamePlayersDialog(v);
                    }
                    else if(Integer.parseInt(playersStr) > 10){
                        Context context = getApplicationContext();
                        CharSequence text = "No more than 10 players!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                    else if(Integer.parseInt(playersStr) <= 0){
                        Context context = getApplicationContext();
                        CharSequence text = "You need at least one player!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                } else {
                    Context context = getApplicationContext();
                    CharSequence text;
                    if(playersStr.length() < 0){
                        text = "You need at least one player!";
                    }
                    else{
                        text = "No more than 10 players!";
                    }
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
        builder.setNegativeButton("Cancel", null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showNamePlayersDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK){
            case Configuration.UI_MODE_NIGHT_YES:
                builder.setTitle(Html.fromHtml("<font color='#ffffff'>Name players?</font>"));
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                builder.setTitle(Html.fromHtml("<font color='#000001'>Name players?</font>"));
                break;
        }

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MainActivity.this, Name.class));
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                playersNamed = false;
                startActivity(new Intent(MainActivity.this, Scorecard.class));
            }
        });


        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void startClick(View v){
        Scorecard.scoreList.clear();
        Scorecard.totalList.clear();
        Scorecard.nameStrings.clear();
        showEnterPlayersDialog(v);
        //startActivity(new Intent(MainActivity.this, Scorecard.class));
    }

    public void savedClick(View v){
        startActivity(new Intent(MainActivity.this, SavedGames.class));
    }

}