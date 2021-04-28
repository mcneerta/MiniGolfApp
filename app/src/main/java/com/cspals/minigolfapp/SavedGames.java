package com.cspals.minigolfapp;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class SavedGames extends AppCompatActivity {

    public Button[] buttons = {findViewById(R.id.SavedGame1), findViewById(R.id.SavedGame2), findViewById(R.id.SavedGame3), findViewById(R.id.SavedGame4), findViewById(R.id.SavedGame5)};
    public GameSave[] saves = MainActivity.games;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate (Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_savedgames);

        init();
    }

    private void init() {

    }
}
