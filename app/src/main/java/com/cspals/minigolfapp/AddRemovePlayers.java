package com.cspals.minigolfapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddRemovePlayers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_remove_players);

        Scorecard.revisit = true;
        init();
    }

    public void init(){

    }

    public void buttonClick(View v){
        startActivity(new Intent(AddRemovePlayers.this, Scorecard.class));
    }
}