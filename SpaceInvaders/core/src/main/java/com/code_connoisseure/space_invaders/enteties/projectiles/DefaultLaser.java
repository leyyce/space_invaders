package com.code_connoisseure.space_invaders.enteties.projectiles;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Animation;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.graphics.SpriteSheet;

public class DefaultLaser {

    private CollisionBox collisionBox;
    private Animation<Sprite> laserAnimation = new Animation<Sprite>();
    private float speed = 5;
    private static int sheetFrameWidth = 5;
    private static int getSheetFrameHeight = 14;

    public DefaultLaser(float x, float y) {
        Texture shipTextures = new Texture("laser_projectile.png");
        SpriteSheet sheet = new SpriteSheet(shipTextures, sheetFrameWidth, getSheetFrameHeight);
        collisionBox = new CollisionBox(x, y, sheetFrameWidth, getSheetFrameHeight);
        for (int i = 0; i < sheet.getTotalFrames(); i++) {
            laserAnimation.addFrame(sheet.getSprite(i), 0.1f);
        }
        laserAnimation.setLooping(true);
    }

    public void update(float delta) {
        //preUpdate() must be called before any changes are made to the CollisionPoint
        collisionBox.preUpdate();

        collisionBox.setY(collisionBox.getY() - speed);

        laserAnimation.update(delta);
    }

    public void interpolate(float alpha) {
        //This method uses the lerp (linear interpolate) method from LibGDX
        //to interpolate between the previous and current positions
        //and set the render coordinates correctly
        collisionBox.interpolate(null, alpha);
    }

    public void render(Graphics g) {
        //Use the point's render coordinates to draw the sprite
        g.drawSprite(laserAnimation.getCurrentFrame(), collisionBox.getRenderX(), collisionBox.getRenderY());
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

    public static int getSheetFrameWidth() {
        return sheetFrameWidth;
    }
}
