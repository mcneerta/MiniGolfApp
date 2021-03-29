package com.example.minigolfapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.CENTER_HORIZONTAL;
import static android.view.Gravity.CENTER_VERTICAL;

public class Results extends AppCompatActivity {

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

        for (int i = 0; i < MainActivity.numPlayers; i++) {
            TextView name = new TextView(this);
            name.setHeight(136);
            name.setText(Scorecard.nameStrings.get(i));
            name.setTextSize(18);
            name.setGravity(CENTER);
            left.addView(name);

            TextView playerTotal = new TextView(this);
            playerTotal.setHeight(136);
            playerTotal.setText(Scorecard.totalList.get(i).getText().toString());
            playerTotal.setTextSize(18);
            playerTotal.setGravity(CENTER);
            right.addView(playerTotal);

        }
    }

    public void returnClick(View v) {
        Scorecard.scoreList.clear();
        Scorecard.totalList.clear();
        Scorecard.nameStrings.clear();
        startActivity(new Intent(Results.this, MainActivity.class));
    }
}