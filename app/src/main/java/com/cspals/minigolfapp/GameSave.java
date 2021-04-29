package com.cspals.minigolfapp;

import android.content.Context;
import android.text.format.Time;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class GameSave {
    public static ArrayList<TextView> totalList;
    public static EditText[][] scoreList;
    //public static List<TextView> nameList;
    public static ArrayList<String> nameStrings;
    public boolean playersNamed;
    public int players;
    public int holes;
    Time gameDate = new Time();

    public GameSave(ArrayList<TextView> totalList, EditText[][] scoreList, /*List<TextView> nameList,*/ ArrayList<String> nameStrings, boolean playersNamed, int players, int holes){
        this.totalList = new ArrayList<>(totalList);
        //this.nameList = nameList;
        this.nameStrings = new ArrayList<>(nameStrings);
        this.playersNamed = playersNamed;
        this.players = players;
        this.holes = holes;
        gameDate.setToNow();
        this.scoreList = new EditText[scoreList.length][];
        for(int i = 0; i < scoreList.length; i++){
            this.scoreList[i] = scoreList[i].clone();
        }
    }
}
