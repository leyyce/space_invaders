package com.code_connoisseure.space_invaders.enteties;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.graphics.TextureRegion;

public class Alien extends Sprite {
    private final Texture alienTexture = new Texture("test_alien.png");
    private TextureRegion alienTextureRegion;

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
}
