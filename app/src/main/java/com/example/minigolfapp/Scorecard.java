package com.example.minigolfapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//import android.view.View.OnScrollChangeListener;

public class Scorecard extends AppCompatActivity {

    public static List<TextView> totalList = new ArrayList<>();
    public static List<EditText> scoreList = new ArrayList<>();
    public static List<TextView> nameList = new ArrayList<>();
    public static List<String> nameStrings = new ArrayList<>();
    public static String winnerName;

    int numPlayers = MainActivity.numPlayers;
    int numHoles = 18;
    boolean playersNamed = MainActivity.playersNamed;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecard);
        HorizontalScrollView scoresH = findViewById(R.id.ScrollViewScoresH);
        HorizontalScrollView holesH = findViewById(R.id.ScrollViewHoles);
        ScrollView scoresV = findViewById(R.id.ScrollViewScoresV);
        ScrollView namesV = findViewById(R.id.ScrollViewNames);
        ScrollView totalsV = findViewById(R.id.ScrollViewTotals);

        holesH.setHorizontalScrollBarEnabled(false);
        scoresH.setHorizontalScrollBarEnabled(false);
        scoresV.setVerticalScrollBarEnabled(false);
        namesV.setVerticalScrollBarEnabled(false);
        totalsV.setVerticalScrollBarEnabled(false);

        View.OnScrollChangeListener scrollChange2 = new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldX, int oldY) {
                scoresH.setScrollX(x);
            }
        };
        holesH.setOnScrollChangeListener(scrollChange2);

        View.OnScrollChangeListener scrollChange = new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldX, int oldY) {
                holesH.setScrollX(x);
            }
        };
        scoresH.setOnScrollChangeListener(scrollChange);

        View.OnScrollChangeListener scrollChange3 = new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldX, int oldY) {
                namesV.setScrollY(y);
                totalsV.setScrollY(y);
            }
        };
        scoresV.setOnScrollChangeListener(scrollChange3);

        View.OnScrollChangeListener scrollChange4 = new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldX, int oldY) {
                scoresV.setScrollY(y);
                totalsV.setScrollY(y);
            }
        };
        namesV.setOnScrollChangeListener(scrollChange4);

        View.OnScrollChangeListener scrollChange7 = new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldX, int oldY) {
                scoresV.setScrollY(y);
                namesV.setScrollY(y);
            }
        };
        totalsV.setOnScrollChangeListener(scrollChange4);

        holesH.setHorizontalFadingEdgeEnabled(true);
        scoresH.setHorizontalFadingEdgeEnabled(true);
        scoresV.setVerticalFadingEdgeEnabled(true);
        namesV.setVerticalFadingEdgeEnabled(true);
        totalsV.setVerticalFadingEdgeEnabled(true);

        init();
    }


    public void init() {
        TableLayout tableHoles = (TableLayout) findViewById(R.id.hole_numbers);
        TableLayout tableScore = (TableLayout) findViewById(R.id.table_score);
        TableLayout tableName = (TableLayout) findViewById(R.id.table_names);
        TableLayout tableTotals = (TableLayout) findViewById(R.id.table_totals);

        float density = this.getResources().getDisplayMetrics().density;
        int pixelsV = (int) (density * 42);
        int pixelsH = (int) (density * 46.667);

        TableRow hRow = new TableRow(this);
        List<TextView> holeList = new LinkedList<>();
        for(int i = 0; i < numHoles; i++) {
            TextView holeNumber = new TextView(this);
            holeNumber.setWidth(pixelsH);
            holeNumber.setTextSize(18);
            holeNumber.setGravity(Gravity.CENTER);
            holeNumber.setText(Integer.toString(i + 1));
            holeList.add(holeNumber);
        }
        for (TextView hole : holeList){
            hRow.addView(hole);
        }
        tableHoles.addView(hRow);

        for (int i = 0; i < numPlayers; i++) {
            TableRow nRow = new TableRow(this);
            TextView name = new TextView(this);
            String playerName;
            name.setHeight(pixelsV);
            name.setGravity(Gravity.CENTER);
            if (playersNamed) {
                playerName = nameStrings.get(i);
            } else {
                playerName = "Player " + (i+1);
            }
            name.setText(playerName);
            name.setTextSize(18);
            name.setWidth((int) (density * 75));
            nameList.add(name);

            nRow.addView(name);
            tableName.addView(nRow);

            // Total Score Column
            TableRow tRow = new TableRow(this);
            TextView total = new TextView(this);
            total.setHeight(pixelsV);
            total.setWidth((int) (density * 75));
            total.setGravity(Gravity.CENTER);
            //String totalScore = 0;
            total.setText("0");
            total.setTextSize(18);
            totalList.add(total);
            tRow.addView(total);
            tableTotals.addView(tRow);

            TableRow sRow = new TableRow(this);
            sRow.setGravity(Gravity.LEFT);
            List<EditText> columns = new LinkedList<>();
            for (int j = 0; j < numHoles; j++) {
                EditText holeScore = new EditText(this);
                holeScore.setWidth(pixelsH);
                holeScore.setBackgroundResource(R.drawable.grid_border);

                holeScore.setGravity(Gravity.CENTER);
                holeScore.setInputType(InputType.TYPE_CLASS_NUMBER);

                holeScore.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        setTotals();
                    }
                });

                columns.add(holeScore);
                scoreList.add(holeScore);
            }
            for (EditText score : columns) {
                sRow.addView(score);
            }

            tableScore.addView(sRow);
        }
        //Adds names to nameStrings if custom names were not added
        if (nameStrings.isEmpty()){
            fillNames();
        }
    }

    public void fillNames(){
        for (int i = 0; i < numPlayers; i++){
            nameStrings.add("Player " + Integer.toString(i + 1));
        }
    }

    public void setTotals(){
        int x = 0;
        int j = 0;
        for(int i = 0; i < numPlayers; i++){
            int total = 0;
            for(j = x; j < numHoles + x; j++){
                String currentScore = scoreList.get(j).getText().toString();
                if (!currentScore.isEmpty()) {
                    total += Integer.parseInt(currentScore);
                }
            }
            x = j;
            totalList.get(i).setText(Integer.toString(total));
        }
    }

    public void showFinishGameAlertDialogButtonClicked(View view) {

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK){
            case Configuration.UI_MODE_NIGHT_YES:
                builder.setTitle(Html.fromHtml("<font color='#ffffff'>Finish game?</font>"));
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                builder.setTitle(Html.fromHtml("<font color='#000001'>Finish game?</font>"));
                break;
        }
        // add the buttons
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<Integer> totalIntList = new ArrayList<>();
                totalIntList = getTotalIntList();

                int winnerValue = Collections.min(totalIntList);
                int winnerIndex = totalIntList.indexOf(winnerValue);
                winnerName = nameStrings.get(winnerIndex);
                // do something like...
                finishGameClick(view);

            }
        });
        builder.setNegativeButton("No", null);


        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public List<Integer> getTotalIntList() {
        List<Integer> totalIntList = new ArrayList<>();
        for (int i = 0; i < totalList.size(); i++) {
            totalIntList.add(
                    Integer.parseInt(totalList.get(i).getText().toString()));
        }
        return totalIntList;
    }

//    public void showWinnerAlertDialogButtonClicked(View view) {
//
//        List<Integer> totalIntList = new ArrayList<>();
//        totalIntList = getTotalIntList();
//
//        int winnerValue = Collections.min(totalIntList);
//        int winnerIndex = totalIntList.indexOf(winnerValue);
//        winnerName = nameList.get(winnerIndex).getText().toString();
//        // setup the alert builder
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("The winner is " + winnerName + "!");
//        // add the buttons
//        builder.setPositiveButton("Return to Menu", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                backClick(view);
//            }
//        });

//        // create and show the alert dialog
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }

    public void backClick(View v){
        startActivity(new Intent(Scorecard.this, MainActivity.class));
    }

    public void finishGameClick(View v){

//        showWinnerAlertDialogButtonClicked(v);
        startActivity(new Intent(Scorecard.this, Results.class));
    }

    public void finishClick(View v) {
        showFinishGameAlertDialogButtonClicked(v);
    }
}