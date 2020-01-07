package com.code_connoisseure.space_invaders.enteties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Shape;
import org.mini2Dx.core.graphics.Animation;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.graphics.SpriteSheet;

public class Alien {
    private CollisionBox collisionBox;
    private Animation<Sprite> alienAnimation = new Animation<Sprite>();
    private boolean moveRight = true;

    public Alien(float x, float y) {
        Texture alienTextures = new Texture("alien.png");
        SpriteSheet sheet = new SpriteSheet(alienTextures, 60, 60);
        collisionBox = new CollisionBox(x, y, alienTextures.getWidth(), alienTextures.getHeight());
        for (int i = 0; i < sheet.getTotalFrames(); i++) {
            alienAnimation.addFrame(sheet.getSprite(i), 0.2f);
        }
        alienAnimation.setLooping(true);
    }

    public void update(float delta) {
        //preUpdate() must be called before any changes are made to the CollisionPoint
        collisionBox.preUpdate();
        move(5);
        alienAnimation.update(delta);
    }

    public void interpolate(float alpha) {
        //This method uses the lerp (linear interpolate) method from LibGDX
        //to interpolate between the previous and current positions
        //and set the render coordinates correctly
        collisionBox.interpolate(null, alpha);
    }

    public void render(Graphics g) {
        //Use the point's render coordinates to draw the sprite
        g.drawSprite(alienAnimation.getCurrentFrame(), collisionBox.getRenderX(), collisionBox.getRenderY());
    }

    public Texture getAlienTexture() {
        return alienAnimation.getCurrentFrame().getTexture();
    }

    public boolean contains(Shape shape) {
        return collisionBox.contains(shape);
    }

    private void moveHor(float xStep) {
        collisionBox.setX(collisionBox.getX() + xStep);
    }

    private void moveVert(float yStep) {
        collisionBox.setY(collisionBox.getY() + yStep);
    }

    private void move(float xStep) {
        if (checkEdgeCollision(this.moveRight ? collisionBox.getX() + xStep + getAlienTexture().getWidth() / 2f: collisionBox.getX() - xStep)) {
            this.moveRight = !moveRight;
            moveVert(getAlienTexture().getHeight());
        }
        moveHor(this.moveRight ? xStep : - xStep);
    }

    private boolean checkEdgeCollision(float position) {
        return !(position < Gdx.graphics.getWidth() && position > 0);
    }
}
