package com.cspals.minigolfapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        TextView helpText = findViewById(R.id.textHelp);
        helpText.setText("1. Please select each box to input a score\n\n2. The column indicates the hole number, while the row indicates the players. The total is displayed to the right.\n\nTo include par, click on the gear icon and click on 'Change Scorecard'");
    }

    public void returnClick(View v) {
        startActivity(new Intent(Help.this, Scorecard.class));
    }
}