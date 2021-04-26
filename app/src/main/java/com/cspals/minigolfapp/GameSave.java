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
    Time gameDate = new Time();

    public GameSave(List<TextView> totalList, EditText[][] scoreList, /*List<TextView> nameList,*/ List<String> nameStrings, int players, Context context){
        this.totalList = totalList;
        this.scoreList = scoreList;
        //this.nameList = nameList;
        this.nameStrings = nameStrings;
        this.players = players;
        gameDate.setToNow();
//        CharSequence text = Integer.toString(gameDate.monthDay);
//        int duration = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();
    }
}
