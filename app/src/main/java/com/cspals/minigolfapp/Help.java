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
//        helpText.setText("1. Select each box to input a score\n\n2. The column indicates the hole number, while the row indicates the players. The total is displayed to the right\n\n3. To change the number of players, change the number of holes, add handicap, or add par, click on the gear icon and click on 'Change Scorecard'\n\n4. The 5 most recent games will be saved\n\n5. To return to the scorecard, click the back button\n\n6. The number of holes cannot exceed 18");
    }

}