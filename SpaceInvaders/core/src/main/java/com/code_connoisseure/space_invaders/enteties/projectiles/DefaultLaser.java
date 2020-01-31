package com.code_connoisseure.space_invaders.enteties.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * This class represents a simple laser projectile that can be fired by the player and will travel to the
 * upper side of the screen.
 * @author Yannic Wehner
 */
public class DefaultLaser extends Projectile {
    /**
     * The sprite sheet for the DefaultLaser
     */
    private static Texture spriteSheet = new Texture(Gdx.files.internal("projectiles/laser_projectile.png"));

    /**
     * The sound that will be played when the laser is fired.
     */
    private static Sound fireSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser_sound.wav"));

    /**
     * The width of a single animation frame on the sprite sheet.
     */
    private static int sheetFrameWidth = 5;

    /**
     * The height of a single animation frame on the sprite sheet.
     */
    private static int sheetFrameHeight = 14;

    /**
     * The duration of a whole animation cycle.
     */
    private static float animationDuration = 0.125f;

    /**
     * The direction the laser will travel in.
     */
    private static Directions moveDirection = Directions.UP;

    /**
     * @param x The x-position of the upper left corner of the laser in the Cartesian coordinate system.
     * @param y The y-position of the upper left corner of the laser in the Cartesian coordinate system.
     * @param speed The amount of pixels the laser moves with each call of the move method in a given direction.
     */
    public DefaultLaser(float x, float y, float speed) {
        super(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, speed, moveDirection, fireSound);
    }

    /**
     * @return Returns the height of a single animation frame on the sprite sheet in pixels
     */
    public static int getSheetFrameHeight_() {
        return sheetFrameHeight;
    }
}
