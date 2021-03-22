package com.example.minigolfapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
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

//Testing new github last try
public class Scorecard extends AppCompatActivity {

    List<TextView> totalList = new ArrayList<>();
    List<EditText> scoreList = new ArrayList<>();
    List<TextView> nameList = new ArrayList<>();
    public static String winnerName;

    int numPlayers = 2;
    int numHoles = 18;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecard);
        HorizontalScrollView scoresH = findViewById(R.id.hscrll2);
        HorizontalScrollView holesH = findViewById(R.id.hscrll1);
        ScrollView scoresV = findViewById(R.id.scrollView1);
        ScrollView namesV = findViewById(R.id.scrollViewNames);

        holesH.setHorizontalScrollBarEnabled(false);
        scoresH.setHorizontalScrollBarEnabled(false);
        scoresV.setVerticalScrollBarEnabled(false);
        namesV.setVerticalScrollBarEnabled(false);

        View.OnScrollChangeListener scrollChange = new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldX, int oldY) {
                holesH.setScrollX(x);
            }
        };
        scoresH.setOnScrollChangeListener(scrollChange);

        View.OnScrollChangeListener scrollChange2 = new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldX, int oldY) {
                scoresH.setScrollX(x);
            }
        };
        holesH.setOnScrollChangeListener(scrollChange2);

        View.OnScrollChangeListener scrollChange3 = new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldX, int oldY) {
                namesV.setScrollY(y);
            }
        };
        scoresV.setOnScrollChangeListener(scrollChange3);

        View.OnScrollChangeListener scrollChange4 = new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldX, int oldY) {
                scoresV.setScrollY(y);
            }
        };
        namesV.setOnScrollChangeListener(scrollChange4);

        holesH.setHorizontalFadingEdgeEnabled(true);
        scoresH.setHorizontalFadingEdgeEnabled(true);
//        scoresV.setVerticalFadingEdgeEnabled(true);
//        namesV.setVerticalFadingEdgeEnabled(true);

        init();
    }


    public void init() {
        TableLayout tableHoles = (TableLayout) findViewById(R.id.hole_numbers);
        TableLayout tableScore = (TableLayout) findViewById(R.id.table_score);
        TableLayout tableName = (TableLayout) findViewById(R.id.table_names);
        TableLayout tableTotals = (TableLayout) findViewById(R.id.table_totals);

        TableRow hRow = new TableRow(this);
        List<TextView> holeList = new LinkedList<>();
        for(int i = 0; i < numHoles; i++) {
            TextView holeNumber = new TextView(this);
            holeNumber.setWidth(100);
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
            name.setHeight(124);
            name.setGravity(Gravity.CENTER);

            String playerName = "Player " + Integer.toString(i + 1);
            name.setText(playerName);
            name.setTextSize(18);
            nameList.add(name);

            nRow.addView(name);
            tableName.addView(nRow);

            // Total Score Column
            TableRow tRow = new TableRow(this);
            TextView total = new TextView(this);
            total.setHeight(124);
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
                holeScore.setWidth(100);

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
        builder.setTitle("Finish Game?");
        // add the buttons
        builder.setPositiveButton("No", null);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<Integer> totalIntList = new ArrayList<>();
                totalIntList = getTotalIntList();

                int winnerValue = Collections.min(totalIntList);
                int winnerIndex = totalIntList.indexOf(winnerValue);
                winnerName = nameList.get(winnerIndex).getText().toString();
                // do something like...
                finishGameClick(view);

            }
        });

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