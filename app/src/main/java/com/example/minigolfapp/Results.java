package com.example.minigolfapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.CENTER_HORIZONTAL;
import static android.view.Gravity.CENTER_VERTICAL;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        init();
    }

    public void init(){
        TextView winner = (TextView) findViewById(R.id.winner);
        LinearLayout left = findViewById(R.id.GridLayout1);
        LinearLayout right = findViewById(R.id.GridLayout2);

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