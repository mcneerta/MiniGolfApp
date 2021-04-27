package com.cspals.minigolfapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChangeScorecard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changescorecard);

    }

    public void addPlayerClick(View v){
        startActivity(new Intent(ChangeScorecard.this, AddRemovePlayers.class));
    }

    public void changeHolesClick(View v){
        startActivity(new Intent(ChangeScorecard.this, ChangeHoles.class));
    }


    public void addParClick(View v){

    }

    public void addHandicapClick(View v){
        if(Scorecard.handicap){
            Scorecard.handicap = false;
            Context context = getApplicationContext();
            CharSequence text = "Handicap column removed";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else{
            Scorecard.handicap = true;
            addHandicapEditText();
            Context context = getApplicationContext();
            CharSequence text = "Handicap column added";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        setTotals();
        Scorecard.revisit = true;
        startActivity(new Intent(ChangeScorecard.this, Scorecard.class));
    }

    private void addHandicapEditText() {
        for(int i = 0; i < ScorecardSetup.numPlayers; i++) {
            float density = this.getResources().getDisplayMetrics().density;
            int pixelsV = (int) (density * 42);
            int pixelsH = (int) (density * 46.667);

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

            Scorecard.scoreList[i][18] = holeScore;
        }
    }

    public void setTotals(){

        for(int i = 0; i < ScorecardSetup.numPlayers; i++){
            int total = 0;
            for(int j = 0; j < Scorecard.numHoles; j++){
                String currentScore = Scorecard.scoreList[i][j].getText().toString();
                if (!currentScore.isEmpty()) {
                    total += Integer.parseInt(currentScore);
                }
            }
            if(Scorecard.handicap) {
                String handicapScore = Scorecard.scoreList[i][18].getText().toString();
                if (!handicapScore.isEmpty()) {
                    total += Integer.parseInt(handicapScore);
                }
            }
            Scorecard.totalList.get(i).setText(Integer.toString(total));
        }
    }


}
