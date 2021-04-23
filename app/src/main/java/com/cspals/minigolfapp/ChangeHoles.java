package com.cspals.minigolfapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;

public class ChangeHoles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_holes);
        Scorecard.revisit = true;
        init();
    }

    public void init(){

    }

    public void changeHolesClick(View v){
        float density = this.getResources().getDisplayMetrics().density;
        int pixelsV = (int) (density * 42);
        int pixelsH = (int) (density * 46.667);
        int oldHoleNum = Scorecard.numHoles;

        EditText newHoleNumText = findViewById(R.id.newHoleNumEditText);
        Scorecard.numHoles = Integer.parseInt(newHoleNumText.getText().toString());
        int newHoleNum = Scorecard.numHoles;

        if(oldHoleNum < newHoleNum){
            //int holesAd
            for(int i = 0; i < MainActivity.numPlayers; i++) {
                for (int j = 0; j < newHoleNum - oldHoleNum; j++) {
                    EditText holeScore = new EditText(this);
                    holeScore.setWidth(pixelsH);
                    holeScore.setBackgroundResource(R.drawable.grid_border);

                    holeScore.setGravity(Gravity.CENTER);
                    holeScore.setInputType(InputType.TYPE_CLASS_NUMBER);
                    holeScore.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});

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

                    Scorecard.scoreList.add((oldHoleNum + j * (i+1)) + (newHoleNum-oldHoleNum * i), holeScore);
                }
            }
        }

        startActivity(new Intent(ChangeHoles.this, Scorecard.class));
    }

    public void setTotals(){
        int x = 0;
        int j = 0;
        for(int i = 0; i < MainActivity.numPlayers; i++){
            int total = 0;
            for(j = x; j < Scorecard.numHoles + x; j++){
                String currentScore = Scorecard.scoreList.get(j).getText().toString();
                if (!currentScore.isEmpty()) {
                    total += Integer.parseInt(currentScore);
                }
            }
            x = j;
            Scorecard.totalList.get(i).setText(Integer.toString(total));
        }
    }
}