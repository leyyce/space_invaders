package com.code_connoisseure.space_invaders.enteties;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.graphics.TextureRegion;

public class Ship extends Sprite {
    private final Texture shipTexture = new Texture("test_ship.png");
    private TextureRegion shipTextureRegion;

    public Ship() {
        super();
        this.shipTextureRegion = new TextureRegion(shipTexture);
        this.setRegion(shipTextureRegion);
        this.setBounds(0, 0, shipTextureRegion.getRegionWidth(), shipTextureRegion.getRegionHeight());
        this.setPosition(0, 0);
    }

    public Ship(int x, int y) {
        super();
        this.shipTextureRegion = new TextureRegion(shipTexture);
        this.setRegion(shipTextureRegion);
        this.setBounds(x, y, shipTextureRegion.getRegionWidth(), shipTextureRegion.getRegionHeight());
        this.setPosition(x, y);
    }

    public void moveHor(int xStep) {
        this.setX(this.getX() + xStep);
    }

    public void moveVert(int yStep) {
        this.setY(this.getY() + yStep);
    }

    public void move(int xStep, int yStep) {
        this.moveHor(xStep);
        this.moveVert(yStep);
    }
}
