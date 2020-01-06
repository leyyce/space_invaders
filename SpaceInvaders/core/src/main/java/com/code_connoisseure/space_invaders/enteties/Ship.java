package com.code_connoisseure.space_invaders.enteties;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Animation;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.graphics.SpriteSheet;

public class Ship {
    private CollisionBox collisionBox;
    private Animation<Sprite> shipAnimation = new Animation<Sprite>();

    public Ship() {
        Texture shipTextures = new Texture("ship.png");
        SpriteSheet sheet = new SpriteSheet(shipTextures, 108, 108);
        collisionBox = new CollisionBox(15f, 15f, 108f, 108f);
        for (int i = 0; i < sheet.getTotalFrames(); i++) {
            shipAnimation.addFrame(sheet.getSprite(i), 0.2f);
        }
        shipAnimation.setLooping(true);
    }

    public void moveHor(int xStep) {
        collisionBox.setX(collisionBox.getRenderX() + xStep);
    }

    public void moveVert(int yStep) {
        collisionBox.setY(collisionBox.getRenderY() + yStep);
    }

    public void move(int xStep, int yStep) {
        this.moveHor(xStep);
        this.moveVert(yStep);
    }

    public void update(float delta) {
        //preUpdate() must be called before any changes are made to the CollisionPoint
        collisionBox.preUpdate();
        shipAnimation.update(delta);
    }

    public void interpolate(float alpha) {
        //This method uses the lerp (linear interpolate) method from LibGDX
        //to interpolate between the previous and current positions
        //and set the render coordinates correctly
        collisionBox.interpolate(null, alpha);
    }

    public void render(Graphics g) {
        //Use the point's render coordinates to draw the sprite
        g.drawSprite(shipAnimation.getCurrentFrame(), collisionBox.getX(), collisionBox.getY());
    }
}
