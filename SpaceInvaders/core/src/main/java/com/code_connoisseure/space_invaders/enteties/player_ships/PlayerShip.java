package com.code_connoisseure.space_invaders.enteties.player_ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.code_connoisseure.space_invaders.enteties.AnimatedBoxGameObject;
import com.code_connoisseure.space_invaders.enteties.projectiles.DefaultLaser;
import com.code_connoisseure.space_invaders.enteties.projectiles.Projectile;

import java.util.ArrayList;

public abstract class PlayerShip extends AnimatedBoxGameObject {

    private int ammunition;


    public PlayerShip(Texture spriteSheet, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, float speed, int ammunition) {
        super(spriteSheet, _getCenterX(sheetFrameWidth), _getCenterY(sheetFrameHeight), sheetFrameWidth, sheetFrameHeight, animationDuration, true, speed);
        this.ammunition = ammunition;
    }

    public PlayerShip(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, float speed, int ammunition){
        super(spriteSheet, x + sheetFrameWidth / 2f, y + sheetFrameHeight / 2f, sheetFrameWidth, sheetFrameHeight, animationDuration, true, speed);
        this.ammunition = ammunition;
    }

    @Override
    public void update(float delta) {
        //preUpdate() must be called before any changes are made to the CollisionPoint
        collisionBox.preUpdate();
        // Recenter ship if it gets out of bounds
        if (!objectInBounds()) {
            collisionBox.set(_getCenterX(sheetFrameWidth), _getCenterY(sheetFrameHeight));
        }
        objectAnimation.update(delta);
    }

    @Override
    public boolean objectInBounds() {
        return super.objectInBounds() && collisionBox.getY() == _getCenterY(sheetFrameHeight);
    }

    @Override
    protected boolean moveInBounds(Directions xDirection, Directions yDirection) {
        return super.moveInBounds(xDirection, yDirection) && yDirection == null;
    }

    // TODO Find usage and replace with functions from AnimatedBoxObject
    public Texture getShipTexture() {
        return objectAnimation.getCurrentFrame().getTexture();
    }

    @Override
    protected boolean moveHor(Directions direction) {
        if (moveInBounds(direction, null)) {
            return super.moveHor(direction);
        }
        return false;
    }
    @Override
    public void fireProjectile(ArrayList<Projectile> projectiles, float speed) {
        if(ammunition > 0){
            super.fireProjectile(projectiles, speed);
            ammunition--;
        }
    }

    public int getAmmunition(){
        return ammunition;
    }

    private static float _getCenterX(int sheetFrameWidth) {
        return Gdx.graphics.getWidth() / 2f - sheetFrameWidth / 2f;
    }

    private static float _getCenterY(int sheetFrameHeight) {
        return Gdx.graphics.getHeight() - 20 - sheetFrameHeight;
    }
}
