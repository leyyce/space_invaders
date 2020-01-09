package com.code_connoisseure.space_invaders.enteties;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Animation;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.graphics.SpriteSheet;


public abstract class AnimatedBoxGameObject {
    protected CollisionBox collisionBox;
    protected Animation<Sprite> objectAnimation;
    protected int sheetFrameWidth;
    protected int sheetFrameHeight;
    protected float speed;

    public AnimatedBoxGameObject(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, boolean looping,
                                 float speed) {

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
    }

    public void update(float delta) {
        //preUpdate() must be called before any changes are made to the CollisionBox
        // collisionBox.preUpdate();
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
