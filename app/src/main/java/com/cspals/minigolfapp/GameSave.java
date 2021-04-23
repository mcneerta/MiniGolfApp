package com.cspals.minigolfapp;

import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GameSave {
    public static List<TextView> totalList;
    public static List<EditText> scoreList;
    public static List<TextView> nameList;
    public static List<String> nameStrings;
    int players;

    public void GameSave(List<TextView> totalList, List<EditText> scoreList, List<TextView> nameList, List<String> nameStrings, int players){
        this.totalList = totalList;
        this.scoreList = scoreList;
        this.nameList = nameList;
        this.nameStrings = nameStrings;
        this.players = players;
    }
}
