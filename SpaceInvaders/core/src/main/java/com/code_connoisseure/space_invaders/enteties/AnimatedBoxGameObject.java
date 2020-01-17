package com.code_connoisseure.space_invaders.enteties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.code_connoisseure.space_invaders.enteties.projectiles.DefaultLaser;
import com.code_connoisseure.space_invaders.enteties.projectiles.Projectile;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Shape;
import org.mini2Dx.core.graphics.Animation;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.graphics.SpriteSheet;

import java.util.ArrayList;

/**
 * This abstract class represents a basic box shaped, animated on-screen object that can move and fire projectiles and is
 * bound-checked to stay inside the visible screen area.
 *
 * @author Yannic Wehner
 */
public abstract class AnimatedBoxGameObject {
    /**
     * The collisionBox provides the object it's shape and boundary.
     */
    protected CollisionBox collisionBox;

    /**
     * Contains the different frames of the object animation.
     */
    protected Animation<Sprite> objectAnimation;

    /**
     * If provided this sound is played when the object is destroyed (lives drop to zero).
     */
    protected Sound destructionSound;

    /**
     * If provided this sound is played when the object is damaged.
     */
    protected Sound damageSound;

    protected int sheetFrameWidth;
    protected int sheetFrameHeight;

    /**
     * The amount of pixels the object moves with each call of the move method in a given direction.
     */
    protected float speed;

    /**
     * Represents the health of the object. The object won't be able to move or shoot if it drops to zero by default.
     */
    protected int lives;


    /**
     * This enum represents the four directions the object will be able to move in.
     */
    public enum Directions {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    /**
     * @param spriteSheet A texture containing the frames of the object animation.
     * @param x The x-position of the upper left corner of the object in the Cartesian coordinate system.
     * @param y The y-position of the upper left corner of the object in the Cartesian coordinate system.
     * @param sheetFrameWidth The width of a single animation frame in the spriteSheet in pixels.
     * @param sheetFrameHeight The height of a single animation frame in the spriteSheet in pixels.
     * @param animationDuration The time in seconds a single animation cycle will take.
     * @param looping Should the animation be looping?
     */
    public AnimatedBoxGameObject(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, boolean looping) {
        this(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, looping, 1, 0, null, null);
    }

    /**
     * @param spriteSheet A texture containing the frames of the object animation.
     * @param x The x-position of the upper left corner of the object in the Cartesian coordinate system.
     * @param y The y-position of the upper left corner of the object in the Cartesian coordinate system.
     * @param sheetFrameWidth The width of a single animation frame in the spriteSheet in pixels.
     * @param sheetFrameHeight The height of a single animation frame in the spriteSheet in pixels.
     * @param animationDuration The time in seconds a single animation cycle will take.
     * @param looping Should the animation be looping?
     * @param lives The amount of hp the object should have.
     */
    public AnimatedBoxGameObject(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, boolean looping,
                                 int lives) {
        this(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, looping, lives, 0, null, null);
    }

    /**
     * @param spriteSheet A texture containing the frames of the object animation.
     * @param x The x-position of the upper left corner of the object in the Cartesian coordinate system.
     * @param y The y-position of the upper left corner of the object in the Cartesian coordinate system.
     * @param sheetFrameWidth The width of a single animation frame in the spriteSheet in pixels.
     * @param sheetFrameHeight The height of a single animation frame in the spriteSheet in pixels.
     * @param animationDuration The time in seconds a single animation cycle will take.
     * @param looping Should the animation be looping?
     * @param destructionSound The sound that plays when the objects life reaches zero
     */
    public AnimatedBoxGameObject(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, boolean looping,
                                 Sound destructionSound) {
        this(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, looping, 1, 0, destructionSound, null);
    }

    /**
     * @param spriteSheet A texture containing the frames of the object animation.
     * @param x The x-position of the upper left corner of the object in the Cartesian coordinate system.
     * @param y The y-position of the upper left corner of the object in the Cartesian coordinate system.
     * @param sheetFrameWidth The width of a single animation frame in the spriteSheet in pixels.
     * @param sheetFrameHeight The height of a single animation frame in the spriteSheet in pixels.
     * @param animationDuration The time in seconds a single animation cycle will take.
     * @param looping Should the animation be looping?
     * @param lives The amount of hp the object should have.
     * @param destructionSound The sound that plays when the objects life reaches zero
     */
    public AnimatedBoxGameObject(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, boolean looping,
                                 int lives, Sound destructionSound) {
        this(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, looping, lives, 0, destructionSound, null);
    }

    /**
     * @param spriteSheet A texture containing the frames of the object animation.
     * @param x The x-position of the upper left corner of the object in the Cartesian coordinate system.
     * @param y The y-position of the upper left corner of the object in the Cartesian coordinate system.
     * @param sheetFrameWidth The width of a single animation frame in the spriteSheet in pixels.
     * @param sheetFrameHeight The height of a single animation frame in the spriteSheet in pixels.
     * @param animationDuration The time in seconds a single animation cycle will take.
     * @param looping Should the animation be looping?
     * @param speed The amount of pixels the object moves with each call of the move method in a given direction.
     * @param destructionSound The sound that plays when the objects life reaches zero
     */
    public AnimatedBoxGameObject(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, boolean looping,
                                 float speed, Sound destructionSound) {
        this(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, looping, 1, speed, destructionSound, null);
    }

    /**
     * @param spriteSheet A texture containing the frames of the object animation.
     * @param x The x-position of the upper left corner of the object in the Cartesian coordinate system.
     * @param y The y-position of the upper left corner of the object in the Cartesian coordinate system.
     * @param sheetFrameWidth The width of a single animation frame in the spriteSheet in pixels.
     * @param sheetFrameHeight The height of a single animation frame in the spriteSheet in pixels.
     * @param animationDuration The time in seconds a single animation cycle will take.
     * @param looping Should the animation be looping?
     * @param speed The amount of pixels the object moves with each call of the move method in a given direction.
     */
    public AnimatedBoxGameObject(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, boolean looping,
                                 float speed) {
        this(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, looping, 1, speed, null, null);
    }

    /**
     * @param spriteSheet A texture containing the frames of the object animation.
     * @param x The x-position of the upper left corner of the object in the Cartesian coordinate system.
     * @param y The y-position of the upper left corner of the object in the Cartesian coordinate system.
     * @param sheetFrameWidth The width of a single animation frame in the spriteSheet in pixels.
     * @param sheetFrameHeight The height of a single animation frame in the spriteSheet in pixels.
     * @param animationDuration The time in seconds a single animation cycle will take.
     * @param looping Should the animation be looping?
     * @param lives The amount of hp the object should have.
     * @param speed The amount of pixels the object moves with each call of the move method in a given direction.
     */
    public AnimatedBoxGameObject(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, boolean looping,
                                 int lives, float speed) {
        this(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, looping, lives, speed, null, null);
    }

    /**
     * @param spriteSheet A texture containing the frames of the object animation.
     * @param x The x-position of the upper left corner of the object in the Cartesian coordinate system.
     * @param y The y-position of the upper left corner of the object in the Cartesian coordinate system.
     * @param sheetFrameWidth The width of a single animation frame in the spriteSheet in pixels.
     * @param sheetFrameHeight The height of a single animation frame in the spriteSheet in pixels.
     * @param animationDuration The time in seconds a single animation cycle will take.
     * @param looping Should the animation be looping?
     * @param lives The amount of hp the object should have.
     * @param speed The amount of pixels the object moves with each call of the move method in a given direction.
     * @param destructionSound The sound that plays when the objects life reaches zero
     */
    public AnimatedBoxGameObject(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, boolean looping,
                                 int lives, float speed, Sound destructionSound) {
        this(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, looping, lives, speed, destructionSound, null);
    }

    /**
     * @param spriteSheet A texture containing the frames of the object animation.
     * @param x The x-position of the upper left corner of the object in the Cartesian coordinate system.
     * @param y The y-position of the upper left corner of the object in the Cartesian coordinate system.
     * @param sheetFrameWidth The width of a single animation frame in the spriteSheet in pixels.
     * @param sheetFrameHeight The height of a single animation frame in the spriteSheet in pixels.
     * @param animationDuration The time in seconds a single animation cycle will take.
     * @param looping Should the animation be looping?
     * @param lives The amount of hp the object should have.
     * @param speed The amount of pixels the object moves with each call of the move method in a given direction.
     * @param destructionSound The sound that plays when the objects life reaches zero.
     * @param damageSound The sound that plays when the objects is damaged.
     */
    public AnimatedBoxGameObject(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, boolean looping,
                                 int lives, float speed, Sound destructionSound, Sound damageSound) {

        collisionBox = new CollisionBox(x, y, sheetFrameWidth, sheetFrameHeight);
        SpriteSheet sheet = new SpriteSheet(spriteSheet, sheetFrameWidth, sheetFrameHeight);
        objectAnimation = new Animation<Sprite>();
        this.sheetFrameWidth = sheetFrameWidth;
        this.sheetFrameHeight = sheetFrameHeight;
        for (int i = 0; i < sheet.getTotalFrames(); i++) {
            this.objectAnimation.addFrame(sheet.getSprite(i), animationDuration);
        }
        objectAnimation.setLooping(looping);
        this.speed = speed;
        this.destructionSound = destructionSound;
        this.damageSound = damageSound;
        this.lives = lives;
    }

    /**
     * Prepares the collision box for further changes and updates the object animation based on delta.
     * @param delta Provided by mini2Dx.
     *              The time span between the current frame and the last frame in seconds.
     *              Might be smoothed over n frames.
     */
    public void update(float delta) {
        //preUpdate() must be called before any changes are made to the CollisionBox
        collisionBox.preUpdate();
        objectAnimation.update(delta);
    }

    /**
     * Calculates the render coordinates of the object.
     * @param alpha Provided by mini2Dx.
     *              Will be between 0.0 and 1.0, representing how much of an update to simulate, i.e. 0.5 means it is halfway through an update.
     */
    public void interpolate(float alpha) {
        //This method uses the lerp (linear interpolate) method from LibGDX
        //to interpolate between the previous and current positions
        //and set the render coordinates correctly
        collisionBox.interpolate(null, alpha);
    }

    /**
     * Renders the object on the screen.
     * @param g Provided by mini2Dx.
     *          Common interface to graphics rendering functionality.
     */
    public void render(Graphics g) {
        //Use the point's render coordinates to draw the sprite
        g.drawSprite(objectAnimation.getCurrentFrame(), collisionBox.getRenderX(), collisionBox.getRenderY());
    }

    /**
     *
     * @param projectiles Projectile container to which the new projectile will be added.
     * @param speed The amount of pixels the projectile moves with each call of the move method.
     * @return Returns whether or not the projectile was successfully added to the projectiles container.
     */
    public boolean fireProjectile(ArrayList<Projectile> projectiles, float speed) {
        if (!alive()) {
            return false;
        }
        projectiles.add(new DefaultLaser(collisionBox.getCenterX(), collisionBox.getY() - DefaultLaser.getSheetFrameHeight_(), speed));
        return true;
    }

    /**
     * Moves the object in the given direction if the move is legal as defined by moveInBounds.
     * @param xDirection The x-direction the object should move in.
     * @param yDirection The y-direction the object should move in.
     * @return Returns whether or not the move could be executed successfully.
     */
    public boolean move(Directions xDirection, Directions yDirection) {
        if (!alive()) {
            return false;
        }
        return moveHor(xDirection) && moveVert(yDirection);
    }

    /**
     * Checks if a given shape is contained in collisionBox.
     * @param shape The shape that should be checked.
     * @return Returns whether or not the shape is contained in collisionBox.
     */
    public boolean contains(Shape shape) {
        return collisionBox.contains(shape);
    }

    /**
     * Moves the object in the given x-direction if the move is legal as defined by moveInBounds.
     * @param direction The x-direction the object should move in.
     * @return Returns whether or not the the move was successful.
     */
    protected boolean moveHor(Directions direction) {
        if (direction == null) return true;
        if (moveInBounds(direction, null)) {
            collisionBox.setX(collisionBox.getX() + (direction == Directions.RIGHT ? speed : -speed));
            return true;
        }
        return false;
    }

    /**
     * Moves the object in the given y-direction if the move is legal as defined by moveInBounds.
     * @param direction The y-direction the object should move in.
     * @return Returns whether or not the the move was successful.
     */
    protected boolean moveVert(Directions direction) {
        if (direction == null) return true;
        if (moveInBounds(null, direction)) {
            collisionBox.setY(collisionBox.getY() + (direction == Directions.DOWN ? speed : -speed));
            return true;
        }
        return false;
    }

    /**
     * Checks if the object currently is inside it's bounds as defined by moveInBounds.
     * @return Returns whether or not the object is in bounds.
     */
    public boolean objectInBounds() {
        return moveInBounds(null, null);
    }

    /**
     * Damages the object for one hp.
     * @return Returns whether or not the object could be damaged.
     */
    public boolean damageObject() {
        return damageObject(1);
    }

    /**
     * Damages the object for a given amount of hp.
     * @param damage The amount of damage the object should take.
     * @return Returns whether or not the object could be damaged.
     */
    public boolean damageObject(int damage) {
        if (alive()) {
            lives -= Math.min(damage, lives);
            if (!alive()) playDestructionSound();
            else playDamageSound();
            return true;
        }
        return false;
    }

    /**
     * @return Returns whether or not the object is alive (lives greater than 0).
     */
    public boolean alive() {
        return lives > 0;
    }

    /**
     * Plays the destruction sound.
     * @return Returns whether or not the sound could be played.
     */
    protected boolean playDestructionSound() {
        if (destructionSound != null) {
            destructionSound.play();
            return true;
        }
        return false;
    }

    /**
     * Plays the damage sound.
     * @return Returns whether or not the sound could be played.
     */
    protected boolean playDamageSound() {
        if (damageSound != null) {
            damageSound.play();
            return true;
        }
        return false;
    }

    /**
     * Checks if the planned move is legal.
     * @param xDirection The x-direction the object should move in.
     * @param yDirection The y-direction the object should move in.
     * @return Returns whether or not the move is legal.
     */
    protected boolean moveInBounds(Directions xDirection, Directions yDirection) {
        float x = collisionBox.getX();
        float y = collisionBox.getY();

        boolean inBoundsX =  (
                xDirection == null ? x + collisionBox.getWidth() <= Gdx.graphics.getWidth() && x >= 0 : (
                        xDirection == Directions.RIGHT ? x + collisionBox.getWidth() + speed <= Gdx.graphics.getWidth() : x - speed >= 0
                )
        );
        boolean inBoundsY =  (
                yDirection == null ? y + collisionBox.getHeight() <= Gdx.graphics.getHeight() && y >= 0 : (
                        yDirection == Directions.DOWN ? y + collisionBox.getHeight() <= Gdx.graphics.getHeight() + speed : x - speed >= 0
                )
        );

        return inBoundsX && inBoundsY;
    }

    // Setter and Getters

    /**
     * @return Returns the current frame of the object animation
     */
    public Texture getCurrentTextureFrame() {
        return objectAnimation.getCurrentFrame().getTexture();
    }

    /**
     * @return Returns the x-position of the upper left corner of the object.
     */
    public float getX() {
        return collisionBox.getX();
    }

    /**
     * @return Returns the y-position of the upper left corner of the object.
     */
    public float getY() {
        return collisionBox.getY();
    }

    /**
     * @return Returns the height of the object.
     */
    public float getHeight() {
        return collisionBox.getHeight();
    }

    /**
     * @return Returns the width of the object.
     */
    public float getWidth() {
        return collisionBox.getWidth();
    }

    /**
     * @return Returns the collision box.
     */
    public CollisionBox getCollisionBox() {
        return collisionBox;
    }

    /**
     * @return Returns the amount of lives left.
     */
    public int getLives() {
        return lives;
    }

    public int getSheetFrameWidth() {
        return sheetFrameWidth;
    }

    public int getSheetFrameHeight() {
        return sheetFrameHeight;
    }

    /**
     * @return Returns the speed of the object.
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Sets the speed of the object.
     * @param speed The new speed of the object.
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
