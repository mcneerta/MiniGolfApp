package com.example.minigolfapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.view.Gravity.CENTER;

public class Results extends AppCompatActivity {

    List<Integer> sortedScoresList = new ArrayList<>();
    List<String> sortedNamesList = new ArrayList<>();

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
            sortedScoresList.add(Integer.parseInt(Scorecard.totalList.get(i).getText().toString()));
        }

        Collections.sort(sortedScoresList);
//            int tempVal_I = Integer.parseInt(Scorecard.totalList.get(i).getText().toString());
//            for(int j = i + 1; j < Scorecard.totalList.size(); j++) {
//                if (tempVal_I < Integer.parseInt(Scorecard.totalList.get(j).getText().toString())) {
//
//                    Scorecard.totalList.set(i, Scorecard.totalList.get(j));
//                    Scorecard.totalList.set(j, Scorecard.totalList.get(i));
//                }
//            }
//        }

//        for (int i = 0; i < Scorecard.totalList.size(); i++) {
//            int tempValue = Integer.parseInt(Scorecard.totalList.get(i).getText().toString());
//            for (int j = i + 1; j < Scorecard.totalList.size(); j++) {
//               if (tempValue <= Integer.parseInt(Scorecard.totalList.get(j).getText().toString())) {
////                   sortedScoresList.set(i, Scorecard.totalList.get(i));
////                   System.out.println(sortedScoresList);
////                   sortedScoresList.get(i).setText(Scorecard.totalList.get(i).getText().toString());
//                   sortedScoresList.add(Integer.parseInt(Scorecard.totalList.get(i).getText().toString()));
//
//               }
//           }
//        }
//
//        for (int i = 0; i < Scorecard.nameStrings.size(); i++) {
//            for (int j = 0; j < Scorecard.nameStrings.size(); j++) {
//                if (sortedScoresList.get(i) == Integer.parseInt(Scorecard.totalList.get(j).getText().toString())) {
//                    sortedNamesList.add(Scorecard.nameStrings.get(j));
//                }
//            }
//        }

        for (int i = 0; i < MainActivity.numPlayers; i++) {
            TextView name = new TextView(this);
            name.setHeight(136);
            name.setText(Scorecard.nameStrings.get(i));
//            name.setText(sortedNamesList.get(i));
            name.setTextSize(18);
            name.setGravity(CENTER);
            left.addView(name);

            TextView playerTotal = new TextView(this);
            playerTotal.setHeight(136);

//            playerTotal.setText(Scorecard.totalList.get(i).getText().toString());
            playerTotal.setText(String.valueOf(sortedScoresList.get(i)));
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