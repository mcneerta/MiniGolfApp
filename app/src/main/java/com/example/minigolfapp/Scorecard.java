package com.example.minigolfapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
//import android.view.View.OnScrollChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Testing new github last try
public class Scorecard extends AppCompatActivity {

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

        List<EditText> scoreList = new ArrayList<>();
        List<TextView> totalList = new ArrayList<>();

        int numPlayers = 2;
        int numHoles = 18;

        TableRow hRow = new TableRow(this);
        List<TextView> holeList = new LinkedList<>();
        for(int i = 0; i < numHoles; i++) {
            TextView holeNumber = new TextView(this);
            holeNumber.setWidth(100);
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

            nRow.addView(name);
            tableName.addView(nRow);

            // Total Score Column
            TableRow tRow = new TableRow(this);
            TextView total = new TextView(this);
            total.setHeight(124);
            total.setGravity(Gravity.CENTER);
            //String totalScore = 0;
            total.setText("0");
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
                        setTotals(numPlayers, numHoles, scoreList, totalList);
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

    public void setTotals(int numPlayers, int numHoles, List<EditText> scoreList, List<TextView> totalList){
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

    public void backClick(View v){
        startActivity(new Intent(Scorecard.this, MainActivity.class));
    }

    public void finishClick(View v){
        startActivity(new Intent(Scorecard.this, Scorecard.class));
    }
}