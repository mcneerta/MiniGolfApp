package com.cspals.minigolfapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

}
