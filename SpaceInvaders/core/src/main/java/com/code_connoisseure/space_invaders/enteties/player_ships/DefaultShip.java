package com.code_connoisseure.space_invaders.enteties.player_ships;

import com.badlogic.gdx.graphics.Texture;

public class DefaultShip extends PlayerShip{
    private static Texture spriteSheet = new Texture("ships/ship.png");
    private static int sheetFrameWidth = 99;
    private static int sheetFrameHeight = 102;

    public DefaultShip() {
        super(spriteSheet, sheetFrameWidth, sheetFrameHeight, 0.2f, 5);
    }

    public DefaultShip(float x, float y) {
        super(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, 0.2f, 5);
    }
}
