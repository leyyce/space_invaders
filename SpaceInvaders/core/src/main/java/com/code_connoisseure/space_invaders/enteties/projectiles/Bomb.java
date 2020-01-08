package com.code_connoisseure.space_invaders.enteties.projectiles;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

public class Bomb {

    private CollisionBox collisionBox;
    Sprite bombSprite;
    private float speed = 8;
    private static int sheetFrameWidth = 9;
    private static int sheetFrameHeight = 18;

    public Bomb(float x, float y) {
        bombSprite = new Sprite(new Texture("bomb.png"));
        collisionBox = new CollisionBox(x, y, sheetFrameWidth, sheetFrameHeight);
    }

    public void update(float delta) {
        //preUpdate() must be called before any changes are made to the CollisionPoint
        collisionBox.preUpdate();

        collisionBox.setY(collisionBox.getY() + speed);
    }

    public void interpolate(float alpha) {
        //This method uses the lerp (linear interpolate) method from LibGDX
        //to interpolate between the previous and current positions
        //and set the render coordinates correctly
        collisionBox.interpolate(null, alpha);
    }

    public void render(Graphics g) {
        //Use the point's render coordinates to draw the sprite
        g.drawSprite(bombSprite, collisionBox.getRenderX(), collisionBox.getRenderY());
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
