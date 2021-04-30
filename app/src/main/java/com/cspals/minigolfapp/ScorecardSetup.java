package com.cspals.minigolfapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ScorecardSetup extends AppCompatActivity {
    public static int numHoles;
    public static int numPlayers;
    public static boolean playersNamed;
    EditText editPlayers;
    EditText editHoles;
    CheckBox nameCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecard_setup);
        editPlayers = findViewById(R.id.playersEdit);
        editHoles = findViewById(R.id.holesEdit);
        nameCheck = findViewById(R.id.checkBox);

        editPlayers.setFilters(new InputFilter[] { new InputFilter.LengthFilter(2) });
        editHoles.setFilters(new InputFilter[] { new InputFilter.LengthFilter(2) });
    }

    public boolean validateInput(){
        String holesString = editHoles.getText().toString();
        String playerString = editPlayers.getText().toString();
        int holesTemp = 0;
        int playerTemp = 0;

        if(!holesString.isEmpty()) {
            holesTemp = Integer.parseInt(holesString);
        }

        if(!playerString.isEmpty()) {
            playerTemp = Integer.parseInt(playerString);
        }

        if(playerTemp < 1) {
            Context context = getApplicationContext();
            CharSequence text = "You need at least one player!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            editPlayers.getText().clear();
            return false;
        }

        else if(playerTemp > 10){
            Context context = getApplicationContext();
            CharSequence text = "No more than 10 players!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            editPlayers.getText().clear();
            return false;
        }

        if(holesTemp < 1) {
            Context context = getApplicationContext();
            CharSequence text = "You need at least one hole!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            editHoles.getText().clear();
            return false;
        }

        else if(holesTemp > 18){
            Context context = getApplicationContext();
            CharSequence text = "No more than 18 holes!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            editHoles.getText().clear();
            return false;
        }

        return true;
    }

    public void continueClick(View v){
        if(validateInput()){
            numHoles = Integer.parseInt(editHoles.getText().toString());
            numPlayers = Integer.parseInt(editPlayers.getText().toString());
            playersNamed = nameCheck.isChecked();
            System.out.println("Number of Holes: " + numHoles);
            System.out.println("Number of Players: " + numPlayers);
            System.out.println("Name Players: " + playersNamed);

            if(playersNamed) {
                startActivity(new Intent(ScorecardSetup.this, Name.class));
            }
            else{
                startActivity(new Intent(ScorecardSetup.this, Scorecard.class));
            }
        }
    }
}