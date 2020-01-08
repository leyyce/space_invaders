package com.code_connoisseure.space_invaders.enteties.projectiles;

import com.badlogic.gdx.graphics.Texture;

public class Bomb extends Projectile{
    private static int sheetFrameWidth = 18;
    private static int sheetFrameHeight = 36;

    public Bomb(float x, float y, float speed) {
        super(new Texture("bomb.png"), x, y, sheetFrameWidth, sheetFrameHeight, 0.125f, -speed);
    }
}
