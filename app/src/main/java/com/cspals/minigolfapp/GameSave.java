package com.cspals.minigolfapp;

import android.text.format.Time;


import java.util.ArrayList;

public class GameSave {
    public static ArrayList<String> totalList;
    public static String[][] scoreList;
    public static ArrayList<String> nameStrings;
    public boolean playersNamed;
    public int players;
    public int holes;
    Time gameDate = new Time();

    public GameSave(ArrayList<String> totalList, String[][] scoreList, ArrayList<String> nameStrings, boolean playersNamed, int players, int holes){
        this.totalList = Scorecard.deepCopyStrList(totalList);
        this.nameStrings = Scorecard.deepCopyStrList(nameStrings);
        this.playersNamed = playersNamed;
        this.players = players;
        this.holes = holes;
        gameDate.setToNow();
        this.scoreList = Scorecard.deepCopyStr2DArr(scoreList);
    }

}
