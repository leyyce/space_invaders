package com.code_connoisseure.space_invaders.enteties.player_ships;

import com.badlogic.gdx.graphics.Texture;

public class DefaultShip extends PlayerShip {
    private static Texture spriteSheet = new Texture("ships/ship.png");
    private static int sheetFrameWidth = 99;
    private static int sheetFrameHeight = 102;
    private static float animationDuration = 0.2f;
    private static float speed = 5;
    private static int ammunition = 250;

    public DefaultShip() {
        super(spriteSheet, sheetFrameWidth, sheetFrameHeight, animationDuration, speed, ammunition);
    }

    public DefaultShip(float x, float y) {
        super(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, speed, ammunition );
    }
}
