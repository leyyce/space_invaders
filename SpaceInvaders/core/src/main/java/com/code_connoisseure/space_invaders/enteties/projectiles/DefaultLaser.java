package com.code_connoisseure.space_invaders.enteties.projectiles;

import com.badlogic.gdx.graphics.Texture;

public class DefaultLaser extends Projectile{
    private static Texture spriteSheet = new Texture("projectiles/laser_projectile.png");
    private static int sheetFrameWidth = 5;
    private static int sheetFrameHeight = 14;
    private static float animationDuration = 0.125f;
    private static Directions moveDirection = Directions.UP;

    public DefaultLaser(float x, float y, float speed) {
        super(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, speed, moveDirection);
    }
}
