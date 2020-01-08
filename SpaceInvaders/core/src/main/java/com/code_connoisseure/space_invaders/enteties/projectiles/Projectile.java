package com.code_connoisseure.space_invaders.enteties.projectiles;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Animation;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.graphics.SpriteSheet;

public class Projectile {
    protected CollisionBox collisionBox;
    protected Animation<Sprite> projectileAnimation;
    protected float speed;
    protected int sheetFrameWidth;
    protected int sheetFrameHeight;

    public Projectile(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float speed) {
        SpriteSheet sheet = new SpriteSheet(spriteSheet, sheetFrameWidth, sheetFrameHeight);
        projectileAnimation = new Animation<Sprite>();
        this.sheetFrameWidth = sheetFrameWidth;
        this.sheetFrameHeight = sheetFrameHeight;
        this.collisionBox = new CollisionBox(x - sheetFrameWidth / 2f, y, sheetFrameWidth, sheetFrameHeight);
        for (int i = 0; i < sheet.getTotalFrames(); i++) {
            this.projectileAnimation.addFrame(sheet.getSprite(i), 0.1f);
        }
        projectileAnimation.setLooping(true);
        this.speed = speed;
    }

    public void update(float delta) {
        //preUpdate() must be called before any changes are made to the CollisionPoint
        collisionBox.preUpdate();

        collisionBox.setY(collisionBox.getY() - speed);

        projectileAnimation.update(delta);
    }

    public void interpolate(float alpha) {
        //This method uses the lerp (linear interpolate) method from LibGDX
        //to interpolate between the previous and current positions
        //and set the render coordinates correctly
        collisionBox.interpolate(null, alpha);
    }

    public void render(Graphics g) {
        //Use the point's render coordinates to draw the sprite
        g.drawSprite(projectileAnimation.getCurrentFrame(), collisionBox.getRenderX(), collisionBox.getRenderY());
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
