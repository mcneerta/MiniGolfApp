package com.example.minigolfapp;

public class GameSave {
    int numPlayers;
    int numHoles;
    String[] playerNames;
    int[][] playerScores;

    public void GameSave(int numPlayers, int numHoles, String[] playerNames, int[][] playerScores){
        this.numPlayers = numPlayers;
        this.numHoles = numHoles;
        this.playerNames = playerNames;
        this.playerScores = playerScores;
    }
}
