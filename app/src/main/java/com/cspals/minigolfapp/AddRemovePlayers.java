package com.cspals.minigolfapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddRemovePlayers extends AppCompatActivity {

    private ArrayList<String> pageNameStrings;
    private String[][] pageScoreList;
    private ArrayList<String> pageTotalList;
    private int pageNumPlayers = ScorecardSetup.numPlayers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_remove_players);
        ScrollView scrollView = findViewById(R.id.addRemoveScroll);
        scrollView.setVerticalScrollBarEnabled(false);

        pageNameStrings = Scorecard.deepCopyStrList(Scorecard.nameStrings);
        pageScoreList = Scorecard.deepCopyStr2DArr(Scorecard.scoreList);
        pageTotalList = Scorecard.deepCopyStrList(Scorecard.totalList);
        init();
    }

    public void init() {
        int white = getResources().getColor(android.R.color.white);
        float density = this.getResources().getDisplayMetrics().density;
        int pixelsV = (int) (density * 42);
        int pixelsH = (int) (density * 40);

        TableLayout addRemovePlayers = findViewById(R.id.addRemovePlayersTable);
        addRemovePlayers.removeAllViews();
        int nightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        int buttonColor = 0;
        switch (nightMode){
            case Configuration.UI_MODE_NIGHT_YES:
                buttonColor = getResources().getColor(R.color.main_purple);
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                buttonColor = getResources().getColor(R.color.main_blue);
                break;
        }

        for (int i = 0; i < pageNumPlayers; i++) {
            TableRow player = new TableRow(this);
            Button remove = new Button(this);
            EditText playerName = new EditText(this);

            remove.setHeight(pixelsV);
            remove.setText("-");
            remove.setTextSize(18);
            Drawable buttonDrawable = remove.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            DrawableCompat.setTint(buttonDrawable, buttonColor);
            remove.setBackground(buttonDrawable);
            remove.setOnClickListener(this::removeButtonClick);

            playerName.setWidth(4 * pixelsH);
            playerName.setHeight(pixelsV);
            playerName.setGravity(Gravity.CENTER);
            playerName.setHint(pageNameStrings.get(i));
            playerName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    TableRow tRow = (TableRow) playerName.getParent();
                    TableLayout tLayout = (TableLayout) tRow.getParent();
                    int index = tLayout.indexOfChild(tRow);
                    pageNameStrings.set(index, playerName.getText().toString());
                }
            });
            ColorStateList x = ColorStateList.valueOf(white);
            ViewCompat.setBackgroundTintList(playerName, x);
            playerName.setTextColor(white);
            playerName.setHintTextColor(white);

            player.addView(remove);
            player.addView(playerName);

            addRemovePlayers.addView(player);
        }

        if (pageNumPlayers < 10){
            TableRow addPlayer = new TableRow(this);
            Button add = new Button(this);
            add.setHeight(pixelsV);
            add.setText("+");
            add.setTextSize(18);
            Drawable buttonDrawable = add.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            DrawableCompat.setTint(buttonDrawable, buttonColor);
            add.setBackground(buttonDrawable);
            add.setOnClickListener(this::addButtonClick);

            EditText addPlayerName = new EditText(this);
            addPlayerName.setWidth(pixelsH);
            addPlayerName.setHeight(pixelsV);
            String addPlayerText = "Add player...";
            addPlayerName.setHint(addPlayerText);
            addPlayerName.setGravity(Gravity.CENTER);
            ColorStateList x = ColorStateList.valueOf(white);
            ViewCompat.setBackgroundTintList(addPlayerName, x);
            addPlayerName.setTextColor(white);
            addPlayerName.setHintTextColor(white);

            addPlayer.addView(add);
            addPlayer.addView(addPlayerName);
            addRemovePlayers.addView(addPlayer);
        }

    }

    public void removeButtonClick(View v){

        if(pageNameStrings.size() > 1) {

            TableRow tRow = (TableRow) v.getParent();
            TableLayout tLayout = (TableLayout) tRow.getParent();
            int indexToRemove = tLayout.indexOfChild(tRow);

            EditText nameText = (EditText) tRow.getChildAt(1);
            String playerName = nameText.getText().toString();
            if(!(pageNameStrings.get(indexToRemove).equals(playerName))) {
                playerName = nameText.getHint().toString();
            }

            pageNameStrings.remove(indexToRemove);
            pageTotalList.remove(indexToRemove);

            for (int i = indexToRemove; i < pageScoreList.length - 1; i++) {
                pageScoreList[i] = pageScoreList[i + 1];
            }
            pageScoreList[pageScoreList.length - 1] = new String[pageScoreList[0].length];

            pageNumPlayers--;

            init();
        }
        else{
            Context context = getApplicationContext();
            CharSequence text = "There is a minimum of 1 player";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    public void addButtonClick(View v){
        TableRow tRow = (TableRow) v.getParent();
        TableLayout tLayout = (TableLayout) tRow.getParent();
        int indexToAdd = tLayout.indexOfChild(tRow);

        EditText addNameText = (EditText) tRow.getChildAt(1);
        String addNameString = addNameText.getText().toString();

        if(!(addNameString.equals(""))){
            pageNameStrings.add(addNameString);
            pageTotalList.add("0");
            pageNumPlayers++;
            init();
        }
        else{
            addNameString = "Player " + (pageNameStrings.size() + 1);
            pageNameStrings.add(addNameString);
            pageTotalList.add("0");
            pageNumPlayers++;
            init();
        }
    }

    public void buttonClick(View v){
        ScorecardSetup.numPlayers = pageNumPlayers;
        Scorecard.nameStrings = pageNameStrings;
        Scorecard.scoreList = pageScoreList;
        Scorecard.totalList = pageTotalList;
        Scorecard.revisit = true;
        startActivity(new Intent(AddRemovePlayers.this, Scorecard.class));
    }
}