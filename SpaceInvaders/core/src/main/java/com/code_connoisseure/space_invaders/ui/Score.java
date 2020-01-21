package com.code_connoisseure.space_invaders.ui;

public class Score {
    private int score;

    public Score() {
        this(0);
    }

    public Score(int score) {
        this.score = score;
    }

    public void increaseScore(int points) {
        score += points;
    }

    public void decreaseScore(int points) {
        score -= points;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
