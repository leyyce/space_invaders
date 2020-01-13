package com.code_connoisseure.space_invaders.enteties.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class Alien extends BasicEnemy {
    private static Texture spriteSheet = new Texture("enemies/alien.png");
    private static Sound destructionSound = Gdx.audio.newSound(new FileHandle("sounds/alien_destruction.wav"));
    private static int sheetFrameWidth = 60;
    private static int sheetFrameHeight = 60;
    private static float animationDuration = 0.2f;
    private static float speed = 5;

    public Alien(float x, float y) {
        super(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, speed, destructionSound);
    }
}
