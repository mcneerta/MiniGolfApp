package com.cspals.minigolfapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.CENTER_HORIZONTAL;

public class Results extends AppCompatActivity {

    List<PlayerResult> sortedResultsList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        ScrollView scores = findViewById(R.id.ScrollViewScores);
        ScrollView names = findViewById(R.id.ScrollViewPlayers);

        scores.setVerticalScrollBarEnabled(false);
        names.setVerticalScrollBarEnabled(false);

        View.OnScrollChangeListener scrollChange2 = new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldX, int oldY) {
                scores.setScrollY(y);
            }
        };
        names.setOnScrollChangeListener(scrollChange2);

        View.OnScrollChangeListener scrollChange = new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldX, int oldY) {
                names.setScrollY(y);
            }
        };
        scores.setOnScrollChangeListener(scrollChange);

        init();
    }

    public void init(){
        TextView winner = (TextView) findViewById(R.id.winner);
        LinearLayout left = findViewById(R.id.LinearLayout1);
        LinearLayout right = findViewById(R.id.LinearLayout2);

        String winnerMessage = "The winner is " + Scorecard.winnerName + "!";
        winner.setText(winnerMessage);

        for (int i = 0; i < Scorecard.totalList.size(); i++) {
            String playerName = Scorecard.nameStrings.get(i);
            Integer playerScore = Integer.parseInt(Scorecard.totalList.get(i).getText().toString());
            sortedResultsList.add(new PlayerResult(playerName, playerScore));
        }
        Collections.sort(sortedResultsList);

        for (int i = 0; i < MainActivity.numPlayers; i++) {
            TextView name = new TextView(this);
            name.setHeight(136);
            String input = (i + 1) + ".   " + sortedResultsList.get(i).getPlayerName();
            name.setText(input);
            name.setTextSize(18);
            name.setGravity(Gravity.START);
            left.addView(name);

            TextView playerTotal = new TextView(this);
            playerTotal.setHeight(136);

            playerTotal.setText(String.valueOf(sortedResultsList.get(i).getPlayerScore()));
            playerTotal.setTextSize(18);
            playerTotal.setGravity(CENTER_HORIZONTAL);
            right.addView(playerTotal);

        }
    }

    public void returnClick(View v) {
        Scorecard.scoreList = new EditText[10][19];
        Scorecard.handicap = false;
        Scorecard.totalList.clear();
        Scorecard.nameStrings.clear();
        startActivity(new Intent(Results.this, MainActivity.class));
    }
}