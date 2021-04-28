package com.cspals.minigolfapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

public class AddRemovePlayers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_remove_players);

        Scorecard.revisit = true;
        init();
    }

    public void init(){

        float density = this.getResources().getDisplayMetrics().density;
        int pixelsV = (int) (density * 42);
        int pixelsH = (int) (2 * density * 46.667);

        TableLayout addRemovePlayers = findViewById(R.id.addRemovePlayersTable);
        for (int i = 0; i < ScorecardSetup.numPlayers; i++) {
            TableRow player = new TableRow(this);
            Button remove = new Button(this);
            EditText playerName = new EditText(this);
            remove.setWidth(pixelsH);
            remove.setHeight(pixelsV);
            remove.setText("-");
            Drawable buttonDrawable = remove.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.main_blue));
            remove.setBackground(buttonDrawable);
            playerName.setWidth(pixelsH);
            playerName.setHeight(2*pixelsV);
            playerName.setGravity(Gravity.CENTER);
            playerName.setHint(Scorecard.nameStrings.get(i));
            player.addView(remove);
            player.addView(playerName);
            addRemovePlayers.addView(player);
        }

        TableRow addPlayer = new TableRow(this);
        Button add = new Button(this);
        add.setWidth(pixelsH);
        add.setHeight(pixelsV);
        add.setText("+");
        Drawable buttonDrawable = add.getBackground();
        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
        DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.main_blue));
        add.setBackground(buttonDrawable);
        EditText addPlayerName = new EditText(this);
        addPlayerName.setWidth(pixelsH);
        addPlayerName.setHeight(2*pixelsV);
        String addPlayerText = "Add player...";
        addPlayerName.setHint(addPlayerText);
        addPlayerName.setGravity(Gravity.CENTER);
        addPlayer.addView(add);
        addPlayer.addView(addPlayerName);
        addRemovePlayers.addView(addPlayer);
        String hello;

    }

    public void buttonClick(View v){
        startActivity(new Intent(AddRemovePlayers.this, Scorecard.class));
    }
}