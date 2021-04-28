package com.cspals.minigolfapp;

import android.content.Context;
import android.text.format.Time;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

public class GameSave {
    public static List<TextView> totalList;
    public static EditText[][] scoreList;
    //public static List<TextView> nameList;
    public static List<String> nameStrings;
    int players;
    int holes;
    Time gameDate = new Time();

    public GameSave(List<TextView> totalList, EditText[][] scoreList, /*List<TextView> nameList,*/ List<String> nameStrings, int players, int holes, Context context){
        this.totalList = totalList;
        this.scoreList = scoreList;
        //this.nameList = nameList;
        this.nameStrings = nameStrings;
        this.players = players;
        this.holes = holes;
        gameDate.setToNow();
    }
}
