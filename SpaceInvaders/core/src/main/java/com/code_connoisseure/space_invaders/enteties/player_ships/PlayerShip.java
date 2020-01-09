package com.code_connoisseure.space_invaders.enteties.player_ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.code_connoisseure.space_invaders.enteties.AnimatedBoxGameObject;
import com.code_connoisseure.space_invaders.enteties.projectiles.DefaultLaser;
import com.code_connoisseure.space_invaders.enteties.projectiles.Projectile;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayList;

public abstract class PlayerShip extends AnimatedBoxGameObject {

    public enum Directions {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    public PlayerShip(Texture spriteSheet, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, float speed) {
        super(spriteSheet, _getCenterX(sheetFrameWidth), _getCenterY(sheetFrameHeight), sheetFrameWidth, sheetFrameHeight, animationDuration, true, speed);
    }

    public PlayerShip(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, float speed) {
        super(spriteSheet, x + sheetFrameWidth / 2f, y + sheetFrameHeight / 2f, sheetFrameWidth, sheetFrameHeight, animationDuration, true, speed);
    }

    @Override
    public void update(float delta) {
        //preUpdate() must be called before any changes are made to the CollisionPoint
        collisionBox.preUpdate();
        // Recenter ship if it gets out of bounds
        if (!shipInBounds()) {
            collisionBox.set(_getCenterX(sheetFrameWidth), _getCenterY(sheetFrameHeight));
        }
        objectAnimation.update(delta);
    }

    @Override
    public void interpolate(float alpha) {
        //This method uses the lerp (linear interpolate) method from LibGDX
        //to interpolate between the previous and current positions
        //and set the render coordinates correctly
        collisionBox.interpolate(null, alpha);
    }

    @Override
    public void render(Graphics g) {
        //Use the point's render coordinates to draw the sprite
        g.drawSprite(objectAnimation.getCurrentFrame(), collisionBox.getRenderX(), collisionBox.getRenderY());
    }

    public boolean move(Directions direction) {
        if (direction == Directions.LEFT || direction == Directions.RIGHT) {
            return moveHor(direction);
        }
        else {
            moveVert(direction);
            return true;
        }
    }

    public void fire(ArrayList<Projectile> projectiles, float speed) {
        projectiles.add(new DefaultLaser(collisionBox.getCenterX(), collisionBox.getY(), speed));
    }

    // TODO Find usage and replace with functions from AnimatedBoxObject
    public Texture getShipTexture() {
        return objectAnimation.getCurrentFrame().getTexture();
    }

    protected boolean moveHor(Directions direction) {
        float move = (direction == Directions.RIGHT ? speed : -speed);
        if (moveInBounds(move)) {
            collisionBox.setX(collisionBox.getX() + move);
            return true;
        }
        return false;
    }

    protected void moveVert(Directions direction) {
        collisionBox.setY(collisionBox.getY() + (direction == Directions.DOWN ? speed : -speed));
    }

    protected boolean moveInBounds(float xStep) {
        float x = collisionBox.getX();
        return x + xStep > 0 && x + xStep + collisionBox.getWidth() < Gdx.graphics.getWidth();
    }

    private static float _getCenterX(int sheetFrameWidth) {
        return Gdx.graphics.getWidth() / 2f - sheetFrameWidth / 2f;
    }

    private static float _getCenterY(int sheetFrameHeight) {
        return Gdx.graphics.getHeight() - 20 - sheetFrameHeight;
    }

    private boolean shipInBounds() {
        return moveInBounds(0) && collisionBox.getY() == _getCenterY(sheetFrameHeight);
    }
}
