package com.code_connoisseure.space_invaders.enteties.projectiles;

import com.badlogic.gdx.graphics.Texture;

public class DefaultLaser extends Projectile{
    private static int sheetFrameWidth = 5;
    private static int sheetFrameHeight = 14;

    public DefaultLaser(float x, float y, float speed) {
        super(new Texture("laser_projectile.png"), x, y, sheetFrameWidth, sheetFrameHeight, speed);
    }
}
