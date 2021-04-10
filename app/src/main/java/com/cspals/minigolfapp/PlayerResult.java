package com.cspals.minigolfapp;

public class PlayerResult implements Comparable<PlayerResult>{
    private String playerName = "";
    private Integer playerScore = 0;

    public PlayerResult(String playerName, int playerScore){
        this.playerName = playerName;
        this.playerScore = playerScore;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerScore(Integer playerScore) {
        this.playerScore = playerScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Integer getPlayerScore() {
        return playerScore;
    }

    @Override
    public int compareTo(PlayerResult playerResult) {
        return this.getPlayerScore().compareTo(playerResult.getPlayerScore());
    }
}
