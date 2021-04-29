package com.cspals.minigolfapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SavedGames extends AppCompatActivity {

    public GameSave[] saves;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate (Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_savedgames);
        saves = MainActivity.games;

        init();
    }

    private void init() {
        Button[] buttons = {findViewById(R.id.SavedGame1), findViewById(R.id.SavedGame2), findViewById(R.id.SavedGame3), findViewById(R.id.SavedGame4), findViewById(R.id.SavedGame5)};
        for(int i = 0; i < saves.length; i ++){
            if(saves[i] != null && saves[i].gameDate != null) {
                String gameDate = saves[i].gameDate.month + "/" + saves[i].gameDate.monthDay + "/" + saves[i].gameDate.year;
                buttons[i].setText(gameDate);
            } else {
                buttons[i].setText("New Game");
            }
        }
    }

    public void Game1 (View v){
        ResumeGame(0);
    }
    public void Game2 (View v){
        ResumeGame(1);
    }
    public void Game3 (View v){
        ResumeGame(2);
    }
    public void Game4 (View v){
        ResumeGame(3);
    }
    public void Game5 (View v){
        ResumeGame(4);
    }

    public void ResumeGame(int game){
        if(saves[game] != null){
            Scorecard.nameStrings = new ArrayList<>(saves[game].nameStrings);
            Scorecard.scoreList = new EditText[saves[game].scoreList.length][];
            for(int i = 0; i < saves[game].scoreList.length; i++){
                Scorecard.scoreList[i] = saves[game].scoreList[i].clone();
            }
            Scorecard.totalList = saves[game].totalList;
            Scorecard.playersNamed = saves[game].playersNamed;
            Scorecard.numPlayers = saves[game].players;
            Scorecard.numHoles = saves[game].holes;
            Scorecard.revisit = true;
            startActivity(new Intent(SavedGames.this, Scorecard.class));
        }
    }


}
