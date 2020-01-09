package com.code_connoisseure.space_invaders.enteties.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.code_connoisseure.space_invaders.enteties.AnimatedBoxGameObject;

public abstract class Projectile extends AnimatedBoxGameObject {
    public Projectile(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, float speed) {
        super(spriteSheet, x - sheetFrameWidth / 2f, y, sheetFrameWidth, sheetFrameHeight, animationDuration, true, speed);
    }

    @Override
    public void update(float delta) {
        //preUpdate() must be called before any changes are made to the CollisionBox
        collisionBox.preUpdate();

        collisionBox.setY(collisionBox.getY() - speed);

        objectAnimation.update(delta);
    }
}
