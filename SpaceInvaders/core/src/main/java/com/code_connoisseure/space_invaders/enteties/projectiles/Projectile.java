package com.code_connoisseure.space_invaders.enteties.projectiles;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.code_connoisseure.space_invaders.enteties.AnimatedBoxGameObject;

/**
 * This abstract class represents a basic projectile and contains all functionality necessary to move it upwards across
 * the screen.
 * @author Yannic Wehner
 */
public abstract class Projectile extends AnimatedBoxGameObject {
    /**
     * The direction the projectile will fly in.
     */
    Directions moveDirection;

    /**
     * The sound that will be played when the projectile is fired.
     */
    Sound fireSound;

    /**
     * @param spriteSheet A texture containing the frames of the projectile animation.
     * @param x The x-position of the upper left corner of the projectile in the Cartesian coordinate system.
     * @param y The y-position of the upper left corner of the projectile in the Cartesian coordinate system.
     * @param sheetFrameWidth The width of a single animation frame in the spriteSheet in pixels.
     * @param sheetFrameHeight The height of a single animation frame in the spriteSheet in pixels.
     * @param animationDuration The time in seconds a single animation cycle will take.
     * @param speed The amount of pixels the projectile moves with each call of the move method in a given direction.
     * @param moveDirection The direction the projectile will traverse in.
     */
    public Projectile(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, float speed, Directions moveDirection) {
        super(spriteSheet, x - sheetFrameWidth / 2f, y, sheetFrameWidth, sheetFrameHeight, animationDuration, true, speed);
        this.moveDirection = moveDirection;
        this.fireSound = null;
    }

    /**
     * @param spriteSheet A texture containing the frames of the projectile animation.
     * @param x The x-position of the upper left corner of the object in the Cartesian coordinate system.
     * @param y The y-position of the upper left corner of the projectile in the Cartesian coordinate system.
     * @param sheetFrameWidth The width of a single animation frame in the spriteSheet in pixels.
     * @param sheetFrameHeight The height of a single animation frame in the spriteSheet in pixels.
     * @param animationDuration The time in seconds a single animation cycle will take.
     * @param speed The amount of pixels the projectile moves with each call of the move method in a given direction.
     * @param moveDirection The direction the projectile will traverse in.
     * @param fireSound The sound that will be played when the projectile is fired.
     */
    public Projectile(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, float speed, Directions moveDirection, Sound fireSound) {
        super(spriteSheet, x - sheetFrameWidth / 2f, y, sheetFrameWidth, sheetFrameHeight, animationDuration, true, speed);
        this.moveDirection = moveDirection;
        this.fireSound = fireSound;
        playFireSound();
    }

    /**
     * Prepares the collision box for further changes, updates the object animation based on delta and moves the object.
     * @param delta Provided by mini2Dx.
     *              The time span between the current frame and the last frame in seconds.
     *              Might be smoothed over n frames.
     */
    @Override
    public void update(float delta) {
        //preUpdate() must be called before any changes are made to the CollisionBox
        collisionBox.preUpdate();

        move(null, moveDirection);

        objectAnimation.update(delta);
    }

    /**
     * Plays the fire sound
     * @return Returns whether or not the sound could be played.
     */
    public boolean playFireSound() {
        if (fireSound != null) {
            fireSound.play();
            return true;
        }
        return false;
    }

    /**
     * Checks if the planned move is legal.
     * @param xDirection The x-direction the object should move in.
     * @param yDirection The y-direction the object should move in.
     * @return Returns true because move will always be in bounds.
     */
    @Override
    // Allows projectiles to leave the screen because AnimatedBoxObject keeps objects in bounds by default
    protected boolean moveInBounds(Directions xDirection, Directions yDirection) {
        return true;
    }
}
