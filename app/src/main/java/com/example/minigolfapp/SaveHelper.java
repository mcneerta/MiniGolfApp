package com.example.minigolfapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class SaveHelper {

    private static final String LIST_KEY = "mini_golf";

    public static void save(Context context, GameSave[] games){
        Gson gson = new Gson();
        String json = gson.toJson(games);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(LIST_KEY, json);
        editor.apply();
    }

    public static GameSave[] load(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String json = pref.getString(LIST_KEY, "");

        Gson gson = new Gson();
        Type type = new TypeToken<GameSave[]>(){}.getType();
        GameSave[] games = gson.fromJson(json, type);
        return games;
    }
}
