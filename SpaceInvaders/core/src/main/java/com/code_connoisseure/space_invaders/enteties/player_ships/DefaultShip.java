package com.code_connoisseure.space_invaders.enteties.player_ships;

import com.badlogic.gdx.graphics.Texture;

public class DefaultShip extends PlayerShip{
    private static String spriteSheetPath = "ships/ship.png";
    private static int sheetFrameWidth = 99;
    private static int sheetFrameHeight = 102;

    public DefaultShip() {
        super(new Texture(spriteSheetPath), sheetFrameWidth, sheetFrameHeight, 0.2f, 5);
    }

    public DefaultShip(float x, float y) {
        super(new Texture(spriteSheetPath), x, y, sheetFrameWidth, sheetFrameHeight, 0.2f, 5);
    }
}
