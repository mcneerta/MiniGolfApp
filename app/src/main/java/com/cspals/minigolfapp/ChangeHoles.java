package com.cspals.minigolfapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        EditText newHoleNum = findViewById(R.id.newHoleNumEditText);
        Scorecard.numHoles = Integer.parseInt(newHoleNum.getText().toString());
        startActivity(new Intent(ChangeHoles.this, Scorecard.class));
    }
}