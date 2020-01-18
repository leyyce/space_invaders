package com.code_connoisseure.space_invaders.enteties.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.code_connoisseure.space_invaders.enteties.AnimatedBoxGameObject;
import com.code_connoisseure.space_invaders.enteties.projectiles.Bomb;
import com.code_connoisseure.space_invaders.enteties.projectiles.Projectile;

import java.util.ArrayList;

public abstract class BasicEnemy extends AnimatedBoxGameObject {
    private boolean moveRight = true;

    public BasicEnemy(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, float speed) {
        this(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, 1, speed);
    }

    public BasicEnemy(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, int lives, float speed) {
        this(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, lives, speed, null);
    }

    public BasicEnemy(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, int lives, float speed, Sound destructionSound) {
        this(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, lives, speed, destructionSound, null);
    }

    public BasicEnemy(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, int lives, float speed, Sound destructionSound, Sound damageSound) {
        super(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration,true,  lives, speed, destructionSound, damageSound);
    }

    @Override
    public void update(float delta) {
        //preUpdate() must be called before any changes are made to the CollisionPoint
        collisionBox.preUpdate();
        if (moveInBounds(moveRight ? Directions.RIGHT : Directions.LEFT, null))
            move(moveRight ? Directions.RIGHT : Directions.LEFT, null);
        else {
            move(null, Directions.DOWN);
            moveRight = !moveRight;
        }
        objectAnimation.update(delta);
    }

    @Override
    protected boolean moveVert(Directions yDirection) {
        if (yDirection == Directions.DOWN) {
            collisionBox.forceTo(moveRight ? Gdx.graphics.getWidth() : 0 - collisionBox.getWidth(),collisionBox.getY() + collisionBox.getHeight());
            return true;
        }
        return false;
    }

    @Override
    protected boolean moveInBounds(Directions xDirection, Directions yDirection) {
        float x = collisionBox.getX();
        return (
                xDirection == null ? x <= Gdx.graphics.getWidth() && x + collisionBox.getWidth() >= 0 : (
                        xDirection == Directions.RIGHT ? x + speed <= Gdx.graphics.getWidth() : x  + collisionBox.getWidth() - speed >= 0
                )
        );
    }

    @Override
    public boolean fireProjectile(ArrayList<Projectile> projectiles, float speed) {
        if (!alive()) return false;
        projectiles.add(new Bomb(collisionBox.getCenterX(), collisionBox.getY(), speed));
        return true;
    }
}
