package com.code_connoisseure.space_invaders.enteties.player_ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.code_connoisseure.space_invaders.enteties.AnimatedBoxGameObject;

public abstract class PlayerShip extends AnimatedBoxGameObject {

    private static Sound defaultDamageSound = Gdx.audio.newSound(Gdx.files.internal("sounds/player_damage.wav"));
    private static Sound defaultDestructionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/player_destruction.wav"));

    public PlayerShip(Texture spriteSheet, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, int lives, float speed) {
        this(spriteSheet, _getCenterX(sheetFrameWidth), _getCenterY(sheetFrameHeight), sheetFrameWidth, sheetFrameHeight, animationDuration, lives, speed);
    }

    public PlayerShip(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, int lives, float speed) {
        this(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, lives, speed, defaultDestructionSound, defaultDamageSound);
    }

    public PlayerShip(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, int lives, float speed, Sound destructionSound) {
        this(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, lives, speed, destructionSound, defaultDamageSound);
    }

    public PlayerShip(Texture spriteSheet, float x, float y, int sheetFrameWidth, int sheetFrameHeight, float animationDuration, int lives, float speed, Sound destructionSound, Sound damageSound) {
        super(spriteSheet, x, y, sheetFrameWidth, sheetFrameHeight, animationDuration, true, lives, speed, destructionSound, damageSound);
    }

    @Override
    public void update(float delta) {
        //preUpdate() must be called before any changes are made to the CollisionPoint
        collisionBox.preUpdate();
        // Recenter ship if it gets out of bounds
        if (!objectInBounds()) {
            collisionBox.set(_getCenterX(sheetFrameWidth), _getCenterY(sheetFrameHeight));
        }
        objectAnimation.update(delta);
    }

    @Override
    public boolean objectInBounds() {
        return super.objectInBounds() && collisionBox.getY() == _getCenterY(sheetFrameHeight);
    }

    @Override
    protected boolean moveInBounds(Directions xDirection, Directions yDirection) {
        return super.moveInBounds(xDirection, yDirection) && yDirection == null;
    }

    @Override
    protected boolean moveHor(Directions direction) {
        if (moveInBounds(direction, null)) {
            return super.moveHor(direction);
        }
        return false;
    }

    private static float _getCenterX(int sheetFrameWidth) {
        return Gdx.graphics.getWidth() / 2f - sheetFrameWidth / 2f;
    }

    private static float _getCenterY(int sheetFrameHeight) {
        return Gdx.graphics.getHeight() - 20 - sheetFrameHeight;
    }
}
