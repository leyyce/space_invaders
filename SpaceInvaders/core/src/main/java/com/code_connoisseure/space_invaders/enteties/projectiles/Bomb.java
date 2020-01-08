package com.code_connoisseure.space_invaders.enteties.projectiles;

import com.badlogic.gdx.graphics.Texture;

public class Bomb extends Projectile{
    private static int sheetFrameWidth = 9;
    private static int sheetFrameHeight = 18;

    public Bomb(float x, float y, float speed) {
        super(new Texture("bomb.png"), x, y, sheetFrameWidth, sheetFrameHeight, speed);
    }

    @Override
    public void update(float delta) {
        //preUpdate() must be called before any changes are made to the CollisionPoint
        collisionBox.preUpdate();
        collisionBox.setY(collisionBox.getY() + speed);
    }
}
