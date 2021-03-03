package com.example.minigolfapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
//import android.view.View.OnScrollChangeListener;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecard);
        HorizontalScrollView scores = findViewById(R.id.hscrll2);
        View.OnScrollChangeListener scrollChange = new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldX, int oldY) {
                HorizontalScrollView holes = findViewById(R.id.hscrll1);
                holes.setScrollX(x);
            }
        };
        scores.setOnScrollChangeListener(scrollChange);
        init();
    }


    public void init() {
        TableLayout holes = (TableLayout) findViewById(R.id.hole_numbers);
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        int numPlayers = 25;
        int numHoles = 18;

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

        TableRow hrow = new TableRow(this);
        hrow.setBackgroundColor(Color.WHITE);
        List<TextView> holeList = new LinkedList<>();
        for(int i = 0; i < numHoles; i ++) {
            TextView holeNumber = new TextView(this);
            holeNumber.setWidth(100);
            //holeNumber.setHeight(100);
            holeNumber.setBackgroundColor(Color.WHITE);
            holeNumber.setGravity(Gravity.CENTER);
            holeNumber.setText(Integer.toString(i + 1));
            holeList.add(holeNumber);
        }
        for (TextView hole : holeList){
            hrow.addView(hole);
        }
        holes.addView(hrow);

        for (int i = 0; i < numPlayers; i++) {
            TableRow tbrow = new TableRow(this);
            tbrow.setGravity(Gravity.LEFT);
            List<EditText> columns = new LinkedList<>();

            for (int j = 0; j < numHoles; j++) {
                EditText holeScore = new EditText(this);
                holeScore.setWidth(100);
                holeScore.setGravity(Gravity.CENTER);
                holeScore.setInputType(InputType.TYPE_CLASS_NUMBER);
                columns.add(holeScore);
            }
            for (EditText score : columns) {
                tbrow.addView(score);
            }

            stk.addView(tbrow);
        }
    }
}