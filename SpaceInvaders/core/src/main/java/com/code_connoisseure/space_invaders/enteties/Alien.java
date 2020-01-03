package com.code_connoisseure.space_invaders.enteties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.graphics.TextureRegion;

public class Alien extends Sprite {
    private final Texture alienTexture = new Texture("test_alien.png");
    private TextureRegion alienTextureRegion;
    private boolean moveRight = true;

    public Alien() {
        super();
        this.alienTextureRegion = new TextureRegion(alienTexture);
        this.setRegion(alienTextureRegion);
        this.setBounds(0, 0, alienTextureRegion.getRegionWidth(), alienTextureRegion.getRegionHeight());
        this.setPosition(0, 0);
    }

    public Alien(int x, int y) {
        super();
        this.alienTextureRegion = new TextureRegion(alienTexture);
        this.setRegion(alienTextureRegion);
        this.setBounds(x, y, alienTextureRegion.getRegionWidth(), alienTextureRegion.getRegionHeight());
        this.setPosition(x, y);
    }

    public void move(int xStep) {
        if (checkEdgeCollision(this.moveRight ? this.getX() + xStep : this.getX() - xStep)) {
            this.moveRight = !moveRight;
            this.setY(this.getY() + alienTextureRegion.getRegionHeight() + 10);
        }
        this.setX(this.moveRight ? this.getX() + xStep : this.getX() - xStep);
    }

    private boolean checkEdgeCollision(float position) {
        return !(position < Gdx.graphics.getWidth() && position > 0);
    }
}
