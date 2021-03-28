package com.example.minigolfapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        TableLayout playerNameList = (TableLayout) findViewById(R.id.playerNamesList);
        TableLayout playerScoreList = (TableLayout) findViewById(R.id.playerScoresList);
        String winnerMessage = "The winner is " + Scorecard.winnerName + "!";
        winner.setText(winnerMessage);

        for (int i = 0; i < MainActivity.numPlayers; i++) {
            TableRow nRow = new TableRow(this);
            TextView name = new TextView(this);
            name.setHeight(136);
            name.setText(Scorecard.nameStrings.get(i));
            name.setTextSize(18);
//            name.setGravity(CENTER);
            nRow.addView(name);
            playerNameList.addView(nRow);

            TableRow sRow = new TableRow(this);
            TextView playerTotal = new TextView(this);
            playerTotal.setHeight(136);
            playerTotal.setText(Scorecard.totalList.get(i).getText().toString());
            playerTotal.setTextSize(18);
//            playerTotal.setGravity(CENTER_VERTICAL);
            sRow.addView(playerTotal);
            playerScoreList.addView(sRow);
        }
    }

    public void returnClick(View v) {
        Scorecard.scoreList.clear();
        Scorecard.totalList.clear();
        Scorecard.nameStrings.clear();
        startActivity(new Intent(Results.this, MainActivity.class));
    }
}