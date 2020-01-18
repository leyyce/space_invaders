package com.code_connoisseure.space_invaders.logic;

public class LevelSettings {
    private int currentLevel;
    private int enemyLives;
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
        enemyLives = currentLevel >= 15 ? currentLevel / 5 - 1 : 1;
        switch (currentLevel) {
            case 1:
                enemySpeed = 5;
                attackProbability = 0;
                projectileSpeed = 0;
                attackersPerRow = 0;
                break;
            case 2:
                enemySpeed = 6;
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
                enemyLives = 2;
                enemySpeed = 8;
                attackProbability = 180;
                projectileSpeed = 3;
                attackersPerRow = 1;
                break;
            case 6:
                enemyLives = 2;
                enemySpeed = 8;
                attackProbability = 180;
                projectileSpeed = 3;
                attackersPerRow = 2;
                break;
            case 7:
                enemySpeed = 8;
                attackProbability = 170;
                projectileSpeed = 5;
                attackersPerRow = 2;
            case 8:
                enemySpeed = 8;
                attackProbability = 170;
                projectileSpeed = 5;
                attackersPerRow = 3;
                break;
            case 9:
                enemySpeed = 8;
                attackProbability = 160;
                projectileSpeed = 7;
                attackersPerRow = 3;
                break;
            case 10:
                enemySpeed = 8;
                attackProbability = 150;
                projectileSpeed = 7;
                attackersPerRow = 3;
            default:
                enemySpeed = 9;
                attackProbability = 165;
                projectileSpeed = 7;
                attackersPerRow = 3;
        }
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public int getEnemyLives() {
        return enemyLives;
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
