package com.example.minigolfapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
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
    int playerNum = MainActivity.numPlayers;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        ScrollView namesInput = findViewById(R.id.ScrollViewNamesInput);

        MainActivity.playersNamed = true;
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
        nameEdits.clear();
        TableLayout namesTable = (TableLayout) findViewById(R.id.namesTable);
        float density = this.getResources().getDisplayMetrics().density;
        int pixelsH = (int) (density * 46.667);
        int pixelsV = (int) (density * 45.334);
        int white = getResources().getColor(android.R.color.white);

        for(int i = 0; i < playerNum; i++){
            TableRow namesRow = new TableRow(this);
            TextView nameNumber = new TextView(this);
            nameNumber.setHeight(pixelsV);
            nameNumber.setGravity(Gravity.CENTER);
            nameNumber.setText(Integer.toString(i + 1) + ". ");
            nameNumber.setTextSize(18);
            namesRow.addView(nameNumber);

            EditText nameEdit = new EditText(this);
            ColorStateList x = ColorStateList.valueOf(white);
            ViewCompat.setBackgroundTintList(nameEdit, x);
            nameEdit.setTextColor(white);
            nameEdit.setWidth(pixelsH * 3);
            nameEdit.setTextSize(18);
            nameEdit.setGravity(Gravity.CENTER);
            nameEdit.setHint("Player " + Integer.toString(i + 1));
            nameEdit.setHintTextColor(white);
            nameEdit.setInputType(InputType.TYPE_CLASS_TEXT);
            nameEdits.add(nameEdit);
            namesRow.addView(nameEdit);

            namesTable.addView(namesRow);
        }

    }

    public void startClick(View v){
        Scorecard.nameStrings.clear();
        for (int i = 0; i < nameEdits.size(); i++){
            if (!nameEdits.get(i).getText().toString().isEmpty()) {
                Scorecard.nameStrings.add(nameEdits.get(i).getText().toString());
            }
            else{
                Scorecard.nameStrings.add("Player " + Integer.toString(i + 1));
            }
        }
        startActivity(new Intent(Name.this, Scorecard.class));
    }
}