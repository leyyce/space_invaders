package com.code_connoisseure.space_invaders.enteties;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Animation;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.graphics.SpriteSheet;

import java.awt.*;

public class Ship {
    private CollisionBox collisionBox;
    private Animation<Sprite> shipAnimation = new Animation<Sprite>();

    public Ship() {
        int sheetFrameDimension = 108;

        Texture shipTextures = new Texture("ship.png");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        SpriteSheet sheet = new SpriteSheet(shipTextures, sheetFrameDimension, sheetFrameDimension);
        collisionBox = new CollisionBox((float) screenSize.width / 2 - sheetFrameDimension / 2f, screenSize.height - 20 - sheetFrameDimension, 108f, 108f);
        for (int i = 0; i < sheet.getTotalFrames(); i++) {
            shipAnimation.addFrame(sheet.getSprite(i), 0.2f);
        }
        shipAnimation.setLooping(true);
    }

    public void moveHor(float xStep) {
        collisionBox.setX(collisionBox.getX() + xStep);
    }

    public void moveVert(float yStep) {
        collisionBox.setY(collisionBox.getY() + yStep);
    }

    public void move(float xStep, float yStep) {
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
        g.drawSprite(shipAnimation.getCurrentFrame(), collisionBox.getRenderX(), collisionBox.getRenderY());
    }
}
