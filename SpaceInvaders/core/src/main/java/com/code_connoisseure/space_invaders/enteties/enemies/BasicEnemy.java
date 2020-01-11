package com.code_connoisseure.space_invaders.enteties.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.code_connoisseure.space_invaders.enteties.AnimatedBoxGameObject;
import com.code_connoisseure.space_invaders.enteties.projectiles.Bomb;
import com.code_connoisseure.space_invaders.enteties.projectiles.Projectile;

import java.util.ArrayList;

public class BasicEnemy extends AnimatedBoxGameObject {
    private boolean moveRight = true;

    public BasicEnemy(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, float speed) {
        super(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, true, speed);
    }

    public BasicEnemy(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, float speed, Sound destructionSound) {
        super(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, true, speed, destructionSound);
    }

    @Override
    public void update(float delta) {
        //preUpdate() must be called before any changes are made to the CollisionPoint
        collisionBox.preUpdate();
        if (moveInBounds(moveRight ? Directions.RIGHT : Directions.LEFT, null))
            move(moveRight ? Directions.RIGHT : Directions.LEFT, null);
        else {
            moveRight = !moveRight;
            move(null, Directions.DOWN);
        }
        objectAnimation.update(delta);
    }

    @Override
    protected boolean moveVert(Directions yDirection) {
        if (yDirection == Directions.DOWN) {
            collisionBox.setY(collisionBox.getY() + collisionBox.getHeight());
            return true;
        }
        return false;
    }

    @Override
    protected boolean moveInBounds(Directions xDirection, Directions yDirection) {
        float x = collisionBox.getX();
        return (
                xDirection == null ? x + collisionBox.getWidth() <= Gdx.graphics.getWidth() && x >= 0 : (
                        xDirection == Directions.RIGHT ? x + collisionBox.getWidth() + speed <= Gdx.graphics.getWidth() : x - speed >= 0
                )
        );
    }

    @Override
    public void fireProjectile(ArrayList<Projectile> projectiles, float speed) {
        projectiles.add(new Bomb(collisionBox.getCenterX(), collisionBox.getY(), speed));
    }
}
