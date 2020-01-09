package com.code_connoisseure.space_invaders.enteties.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.code_connoisseure.space_invaders.enteties.AnimatedBoxGameObject;

public abstract class Projectile extends AnimatedBoxGameObject {
    Directions moveDirection;

    public Projectile(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, float speed, Directions moveDirection) {
        super(spriteSheet, x - sheetFrameWidth / 2f, y, sheetFrameWidth, sheetFrameHeight, animationDuration, true, speed);
        this.moveDirection = moveDirection;
    }

    @Override
    public void update(float delta) {
        //preUpdate() must be called before any changes are made to the CollisionBox
        collisionBox.preUpdate();

        move(null, moveDirection);

        objectAnimation.update(delta);
    }

    @Override
    // Allows projectiles to leave the screen because AnimatedBoxObject keeps objects in bounds by default
    protected boolean moveInBounds(Directions xDirection, Directions yDirection) {
        return true;
    }
}
