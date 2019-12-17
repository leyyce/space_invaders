package com.code_connoisseure.space_invaders;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import com.code_connoisseure.space_invaders.enteties.Alien;
import com.code_connoisseure.space_invaders.enteties.Ship;

public class SpaceInvadersGame extends BasicGame {
	public static final String GAME_IDENTIFIER = "com.code_connoisseure.space_invaders";

    private Ship ship;
    private ArrayList<Alien> enemies = new ArrayList<Alien>();
	
	@Override
    public void initialise() {
        ship = new Ship();
        for (int x = 30; x < 630; x += 100) {
            enemies.add(new Alien(x, 30));
        }
    }
    
    @Override
    public void update(float delta) {
	    reactToKeyPresses(delta);
    }
    
    @Override
    public void interpolate(float alpha) {
    
    }
    
    @Override
    public void render(Graphics g) {
        SpriteBatch s = new SpriteBatch();
        s.begin();
        g.drawSprite(ship);
        for (Alien a : enemies) {
            g.drawSprite(a);
        }
        s.end();
    }

    private void reactToKeyPresses(float delta) {
	    if (Gdx.input.isKeyPressed(Input.Keys.UP))
	        ship.moveVert(-5);
	    else if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
	        ship.moveVert(5);
	    if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
	        ship.moveHor(-5);
	    else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
	        ship.moveHor(5);
    }
}
