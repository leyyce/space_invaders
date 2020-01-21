package com.code_connoisseure.space_invaders.enteties.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * This class represents a simple bomb that can be fired by an enemy and travels to the
 * lower side of the screen.
 * @author Yannic Wehner
 */
public class Bomb extends Projectile {
    /**
     * The sprite sheet for the Bomb
     */
    private static Texture spriteSheet = new Texture(Gdx.files.internal("projectiles/bomb.png"));

    /**
     * The sound that will be played when the bomb is released.
     */
    private static Sound fireSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bomb_spawn.wav"));

    /**
     * The width of a single animation frame on the sprite sheet.
     */
    private static int sheetFrameWidth = 18;

    /**
     * The height of a single animation frame on the sprite sheet.
     */
    private static int sheetFrameHeight = 36;

    /**
     * The duration of a whole animation cycle.
     */
    private static float animationDuration = 0.125f;

    /**
     * The direction the bomb will travel in.
     */
    private static Directions moveDirection = Directions.DOWN;

    /**
     * @param x The x-position of the upper left corner of the laser in the Cartesian coordinate system.
     * @param y The y-position of the upper left corner of the laser in the Cartesian coordinate system.
     * @param speed The amount of pixels the laser moves with each call of the move method in a given direction.
     */
    public Bomb(float x, float y, float speed) {
        super(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, speed, moveDirection, fireSound);
    }
}
