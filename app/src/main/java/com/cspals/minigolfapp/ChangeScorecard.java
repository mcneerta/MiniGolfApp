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
        //startActivity(new Intent(ChangeScorecard.this, ChangePar.class));
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
            Context context = getApplicationContext();
            CharSequence text = "Handicap column added";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        Scorecard.revisit = true;
        startActivity(new Intent(ChangeScorecard.this, Scorecard.class));
    }


}
