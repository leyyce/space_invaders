package com.code_connoisseure.space_invaders;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
        for (int x = 30; x < 1366; x += 100) {
            enemies.add(new Alien(x, 30));
        }
        for (int x = 30; x < 1366; x += 100) {
            enemies.add(new Alien(x, 30 + 20 + enemies.get(0).getRegionHeight() * 2));
        }
    }
    
    @Override
    public void update(float delta) {
	    reactToKeyPresses();
        ship.update(delta);
        for (Alien alien: enemies) {
            alien.move(5);
        }
    }
    
    @Override
    public void interpolate(float alpha) {
	    ship.interpolate(alpha);
    }
    
    @Override
    public void render(Graphics g) {
        SpriteBatch s = new SpriteBatch();
        s.begin();
        ship.render(g);
        for (Alien a : enemies) {
            g.drawSprite(a);
        }
        s.end();
    }

    private void reactToKeyPresses() {
	    if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
	        Gdx.graphics.setWindowedMode(640, 480);
	    if (Gdx.input.isKeyPressed(Input.Keys.UP))
	        ship.moveVert(-5);
	    if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
	        ship.moveVert(5);
	    if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
	        ship.moveHor(-5);
	    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
	        ship.moveHor(5);
    }
}
