package com.example.minigolfapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static boolean playersNamed = false;
    public static int numPlayers = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showEnterPlayersDialog(View v) {

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("How many Players?");
        EditText players = new EditText(MainActivity.this);
        players.setInputType(InputType.TYPE_CLASS_NUMBER);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        players.setLayoutParams(lp);
        builder.setView(players);
        // add the buttons
        builder.setPositiveButton("Cancel", null);
        builder.setNegativeButton("Enter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(players.getText().length() > 0) {
                    numPlayers = Integer.parseInt(players.getText().toString());
                }
                showNamePlayersDialog(v);
            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showNamePlayersDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Name the players?");

        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                playersNamed = false;
                startActivity(new Intent(MainActivity.this, Scorecard.class));
            }
        });
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MainActivity.this, Name.class));
            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void startClick(View v){
        showEnterPlayersDialog(v);
        //startActivity(new Intent(MainActivity.this, Scorecard.class));
    }
}