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


public abstract class AnimatedBoxGameObject {
    protected CollisionBox collisionBox;
    protected Animation<Sprite> objectAnimation;
    protected Sound destructionSound;
    protected int sheetFrameWidth;
    protected int sheetFrameHeight;
    protected float speed;

    public enum Directions {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    public AnimatedBoxGameObject(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, boolean looping,
                                 float speed) {
        this(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, looping, speed, null);
    }

    public AnimatedBoxGameObject(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, boolean looping,
                                 float speed, Sound destructionSound) {

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
    }

    public void update(float delta) {
        //preUpdate() must be called before any changes are made to the CollisionBox
        collisionBox.preUpdate();
        objectAnimation.update(delta);
    }

    public void interpolate(float alpha) {
        //This method uses the lerp (linear interpolate) method from LibGDX
        //to interpolate between the previous and current positions
        //and set the render coordinates correctly
        collisionBox.interpolate(null, alpha);
    }

    public void render(Graphics g) {
        //Use the point's render coordinates to draw the sprite
        g.drawSprite(objectAnimation.getCurrentFrame(), collisionBox.getRenderX(), collisionBox.getRenderY());
    }

    public void fireProjectile(ArrayList<Projectile> projectiles, float speed) {
        projectiles.add(new DefaultLaser(collisionBox.getCenterX(), collisionBox.getY() - DefaultLaser.getSheetFrameHeight_(), speed));
    }

    public boolean move(Directions xDirection, Directions yDirection) {
        return moveHor(xDirection) && moveVert(yDirection);
    }

    public boolean contains(Shape shape) {
        return collisionBox.contains(shape);
    }

    protected boolean moveHor(Directions direction) {
        if (direction == null) return true;
        if (moveInBounds(direction, null)) {
            collisionBox.setX(collisionBox.getX() + (direction == Directions.RIGHT ? speed : -speed));
            return true;
        }
        return false;
    }

    protected boolean moveVert(Directions direction) {
        if (direction == null) return true;
        if (moveInBounds(null, direction)) {
            collisionBox.setY(collisionBox.getY() + (direction == Directions.DOWN ? speed : -speed));
            return true;
        }
        return false;
    }

    public boolean objectInBounds() {
        return moveInBounds(null, null);
    }

    public boolean destruct() {
        if (destructionSound != null) {
            destructionSound.play();
            return true;
        }
        return false;
    }

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

    public Texture getCurrentTextureFrame() {
        return objectAnimation.getCurrentFrame().getTexture();
    }

    public float getX() {
        return collisionBox.getX();
    }

    public float getY() {
        return collisionBox.getY();
    }

    public float getHeight() {
        return collisionBox.getHeight();
    }

    public float getWidth() {
        return collisionBox.getWidth();
    }

    public CollisionBox getCollisionBox() {
        return collisionBox;
    }

    public int getSheetFrameWidth() {
        return sheetFrameWidth;
    }

    public int getSheetFrameHeight() {
        return sheetFrameHeight;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
