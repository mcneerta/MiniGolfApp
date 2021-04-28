package com.cspals.minigolfapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class ChangePar extends AppCompatActivity {
    public static boolean usingPar = false;
    CheckBox parBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_par);
        parBox = findViewById(R.id.parCheckbox);
        parBox.setChecked(usingPar);
    }

    public void continueClick(View V){
        usingPar = parBox.isChecked();
        startActivity(new Intent(ChangePar.this, Scorecard.class));
    }
}