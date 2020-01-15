package com.code_connoisseure.space_invaders.enteties.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Bomb extends Projectile {
    private static Texture spriteSheet = new Texture(Gdx.files.internal("projectiles/bomb.png"));
    private static Sound fireSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bomb_spawn.wav"));
    private static int sheetFrameWidth = 18;
    private static int sheetFrameHeight = 36;
    private static float animationDuration = 0.125f;
    private static Directions moveDirection = Directions.DOWN;

    public Bomb(float x, float y, float speed) {
        super(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, speed, moveDirection, fireSound);
    }
}
