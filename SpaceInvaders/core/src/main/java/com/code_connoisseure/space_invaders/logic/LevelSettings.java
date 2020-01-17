package com.code_connoisseure.space_invaders.logic;

public class LevelSettings {
    private int currentLevel;
    private float enemySpeed;
    private float projectileSpeed;
    private int attackProbability;
    private int attackersPerRow;

    public LevelSettings() {
        this(1);
    }

    public LevelSettings(int level) {
        this.currentLevel = level;
        updateSettings();
    }

    public void increaseLevel() {
        currentLevel++;
        updateSettings();
    }

    private void updateSettings() {
        switch (currentLevel) {
            case 1:
                enemySpeed = 3;
                attackProbability = 0;
                projectileSpeed = 0;
                attackersPerRow = 0;
                break;
            case 2:
                enemySpeed = 5;
                attackProbability = 0;
                projectileSpeed = 0;
                attackersPerRow = 0;
                break;
            case 3:
                enemySpeed = 7;
                attackProbability = 0;
                projectileSpeed = 0;
                attackersPerRow = 0;
                break;
            case 4:
                enemySpeed = 7;
                attackProbability = 200;
                projectileSpeed = 5;
                attackersPerRow = 1;
                break;
            case 5:
                enemySpeed = 7;
                attackProbability = 180;
                projectileSpeed = 3;
                attackersPerRow = 1;
                break;
            case 6:
                enemySpeed = 7;
                attackProbability = 180;
                projectileSpeed = 3;
                attackersPerRow = 2;
                break;
            case 7:
                enemySpeed = 7;
                attackProbability = 170;
                projectileSpeed = 5;
                attackersPerRow = 2;
            case 8:
                enemySpeed = 7;
                attackProbability = 170;
                projectileSpeed = 5;
                attackersPerRow = 3;
                break;
            case 9:
                enemySpeed = 7;
                attackProbability = 150;
                projectileSpeed = 7;
                attackersPerRow = 3;
                break;
            default:
                enemySpeed = 7;
                attackProbability = 130;
                projectileSpeed = 8;
                attackersPerRow = 3;
        }
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public float getEnemySpeed() {
        return enemySpeed;
    }

    public float getProjectileSpeed() {
        return projectileSpeed;
    }

    public int getAttackProbability() {
        return attackProbability;
    }

    public int getAttackersPerRow() {
        return attackersPerRow;
    }
}
