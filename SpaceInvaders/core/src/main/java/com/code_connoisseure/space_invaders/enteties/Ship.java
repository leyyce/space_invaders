package com.code_connoisseure.space_invaders.enteties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.code_connoisseure.space_invaders.enteties.projectiles.DefaultLaser;
import com.code_connoisseure.space_invaders.enteties.projectiles.Projectile;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Animation;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.graphics.SpriteSheet;

import java.util.ArrayList;


public class Ship {
    private CollisionBox collisionBox;
    private Animation<Sprite> shipAnimation = new Animation<Sprite>();
    private int sheetFrameWidth = 99;
    private int sheetFrameHeight = 102;

    public Ship() {
        Texture shipTextures = new Texture("ship.png");
        SpriteSheet sheet = new SpriteSheet(shipTextures, sheetFrameWidth, sheetFrameHeight);
        collisionBox = new CollisionBox(getCenterPosition()[0], getCenterPosition()[1], sheetFrameWidth, sheetFrameHeight);
        for (int i = 0; i < sheet.getTotalFrames(); i++) {
            shipAnimation.addFrame(sheet.getSprite(i), 0.2f);
        }
        shipAnimation.setLooping(true);
    }

    public boolean moveHor(float xStep) {
        if (moveInBounds(xStep)) {
            collisionBox.setX(collisionBox.getX() + xStep);
            return true;
        }
        return false;
    }

    public void moveVert(float yStep) {
        collisionBox.setY(collisionBox.getY() + yStep);
    }

    public boolean move(float xStep, float yStep) {
        if (this.moveHor(xStep)) {
            this.moveVert(yStep);
            return true;
        }
        return false;
    }

    public void shoot(ArrayList<Projectile> projectiles, float speed) {
        projectiles.add(new DefaultLaser(collisionBox.getCenterX(), collisionBox.getY(), speed));
    }

    public void update(float delta) {
        //preUpdate() must be called before any changes are made to the CollisionPoint
        collisionBox.preUpdate();
        // Recenter ship if it gets out of bounds
        if (!shipInBounds()) {
            collisionBox.set(getCenterPosition()[0], getCenterPosition()[1]);
        }
        shipAnimation.update(delta);
    }

    public void interpolate(float alpha) {
        //This method uses the lerp (linear interpolate) method from LibGDX
        //to interpolate between the previous and current positions
        //and set the render coordinates correctly
        collisionBox.interpolate(null, alpha);
    }

    public void render(Graphics g) {
        //Use the point's render coordinates to draw the sprite
        g.drawSprite(shipAnimation.getCurrentFrame(), collisionBox.getRenderX(), collisionBox.getRenderY());
    }

    private boolean moveInBounds(float xStep) {
        float y = collisionBox.getX();
        return y + xStep > 0 && y + xStep + collisionBox.getWidth() < Gdx.graphics.getWidth();
    }

    private float[] getCenterPosition() {
        return new float[]{Gdx.graphics.getWidth() / 2f - sheetFrameWidth / 2f, Gdx.graphics.getHeight() - 20 - sheetFrameHeight};
    }

    public Texture getShipTexture() {
        return shipAnimation.getCurrentFrame().getTexture();
    }

    private boolean shipInBounds() {
        return moveInBounds(0) && collisionBox.getY() == getCenterPosition()[1];
    }
}
