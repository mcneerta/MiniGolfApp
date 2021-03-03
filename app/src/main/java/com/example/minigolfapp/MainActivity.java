package com.example.minigolfapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecard);

        init();
    }

    public void init() {
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        /*TextView tv0 = new TextView(this);
        tv0.setText(" Sl.No ");
        tv0.setTextColor(Color.WHITE);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Product ");
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Unit Price ");
        tv2.setTextColor(Color.WHITE);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" Stock Remaining ");
        tv3.setTextColor(Color.WHITE);
        tbrow0.addView(tv3);
        stk.addView(tbrow0);*/
        int numPlayers = 25;
        int holes = 4;
        for (int i = 0; i < numPlayers; i++) {
            TableRow tbrow = new TableRow(this);
            tbrow.setGravity(Gravity.LEFT);
            List<EditText> columns = new LinkedList<EditText>();
            for (int j = 0; j < holes; j++) {
                EditText holeScore = new EditText(this);
                holeScore.setWidth(100);
                holeScore.setInputType(InputType.TYPE_CLASS_NUMBER);
                columns.add(holeScore);
            }
            for (EditText hole : columns) {
                tbrow.addView(hole);
            }
            stk.addView(tbrow);
        }
    }
}