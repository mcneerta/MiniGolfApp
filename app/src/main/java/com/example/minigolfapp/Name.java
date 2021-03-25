package com.example.minigolfapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Name extends AppCompatActivity {

    public static List<EditText> nameEdits = new ArrayList<>();
    public static List<String> nameStrings = new ArrayList<>();
    int playerNum = 4;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        ScrollView namesInput = findViewById(R.id.ScrollViewNamesInput);

        namesInput.setVerticalScrollBarEnabled(false);

        View.OnScrollChangeListener scrollChange = new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldX, int oldY) {
                namesInput.setScrollX(x);
            }
        };
        namesInput.setOnScrollChangeListener(scrollChange);

        namesInput.setVerticalFadingEdgeEnabled(true);
        init();
    }

    public void init(){
        TableLayout namesTable = (TableLayout) findViewById(R.id.namesTable);
        float density = this.getResources().getDisplayMetrics().density;
        int pixelsH = (int) (density * 46.667);
        int pixelsV = (int) (density * 45.334);

        for(int i = 0; i < playerNum; i++){
            TableRow namesRow = new TableRow(this);
            TextView nameNumber = new TextView(this);
            nameNumber.setHeight(pixelsV);
            nameNumber.setGravity(Gravity.CENTER);
            nameNumber.setText(Integer.toString(i + 1) + ". ");
            nameNumber.setTextSize(18);
            namesRow.addView(nameNumber);

            EditText nameEdit = new EditText(this);
            nameEdit.setWidth(pixelsH * 3);
            nameEdit.setTextSize(18);
            nameEdit.setGravity(Gravity.CENTER);
            nameEdit.setText("Player " + Integer.toString(i + 1));
            nameEdits.add(nameEdit);
            namesRow.addView(nameEdit);

            namesTable.addView(namesRow);
        }

    }

    public void startClick(View v){
        for (EditText names : nameEdits){
            nameStrings.add(names.getText().toString());
        }
        startActivity(new Intent(Name.this, Scorecard.class));
    }
}