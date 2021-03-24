package com.example.minigolfapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;


public class Name extends AppCompatActivity {

    public static List<String> nameStrings = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        ScrollView namesInput = findViewById(R.id.ScrollViewNamesInput);

        namesInput.setVerticalScrollBarEnabled(false);

        View.OnScrollChangeListener scrollChange = new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldX, int oldY) {
                namesInput.setScrollX(x);
            }
        };
        namesInput.setOnScrollChangeListener(scrollChange);

        namesInput.setVerticalFadingEdgeEnabled(true);
    }
}