package com.code_connoisseure.space_invaders.enteties.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Alien extends BasicEnemy {
    private static Texture spriteSheet = new Texture(Gdx.files.internal("enemies/alien.png"));
    // TODO Find good sounding destruction sound.
    private static Sound destructionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/alien_destruction.wav"));
    private static int sheetFrameWidth = 60;
    private static int sheetFrameHeight = 60;
    private static float animationDuration = 0.2f;

    public Alien(float x, float y) {
        this(x, y, 1, 5);
    }

    public Alien(float x, float y, int lives) {
        this(x, y, lives, 5);
    }

    public Alien(float x, float y, float speed) {
        this(x, y, 1, speed);
    }

    public Alien(float x, float y, int lives, float speed) {
        super(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, lives, speed, destructionSound);
    }

}
