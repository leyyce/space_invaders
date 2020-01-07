package com.code_connoisseure.space_invaders;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.code_connoisseure.space_invaders.enteties.projectiles.DefaultLaser;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import com.code_connoisseure.space_invaders.enteties.Alien;
import com.code_connoisseure.space_invaders.enteties.Ship;
import org.mini2Dx.core.graphics.Sprite;

public class SpaceInvadersGame extends BasicGame {
    public static final String GAME_IDENTIFIER = "com.code_connoisseure.space_invaders";

    private Sprite backdrop;
    private Ship ship;
    private ArrayList<ArrayList<Alien>> enemies;
    private ArrayList<DefaultLaser> projectiles;

    @Override
    public void initialise() {
        backdrop = new Sprite(new Texture("backdrop.png"));
        ship = new Ship();
        enemies = generateAliens();
        projectiles = new ArrayList<DefaultLaser>();
    }

    @Override
    public void update(float delta) {
        checkForHits();
        clearOffScreenProjectiles();
        reactToKeyPresses();
        // Update ship
        ship.update(delta);
        // Update projectiles
        for (DefaultLaser p : projectiles) {
            p.update(delta);
        }
        // Update enemies
        for (ArrayList<Alien> row: enemies) {
            for(Alien alien: row) {
                alien.update(delta);
            }
        }
    }

    @Override
    public void interpolate(float alpha) {
        // Interpolate ship
        ship.interpolate(alpha);
        // Interpolate projectiles
        for (DefaultLaser p : projectiles) {
            p.interpolate(alpha);
        }
        // Interpolate enemies
        for (ArrayList<Alien> row : enemies) {
            for(Alien alien: row) {
                alien.interpolate(alpha);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawSprite(backdrop);
        // Render ship
        ship.render(g);
        // Render projectiles
        for (DefaultLaser p : projectiles) {
            p.render(g);
        }
        // Render enemies
        for (ArrayList<Alien> row : enemies) {
            for(Alien alien: row){
                alien.render(g);
            }
        }
    }

    private void reactToKeyPresses() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            Gdx.graphics.setWindowedMode(640, 480);
	    /* REMOVED BECAUSE YOU'RE ONLY SUPPOSED TO MOVE LEFT AND RIGHT
	    if (Gdx.input.isKeyPressed(Input.Keys.UP))
	        ship.moveVert(-5);
	    if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
	        ship.moveVert(5);
	     */
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            ship.moveHor(-5);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            ship.moveHor(5);
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            ship.shoot(projectiles);
    }

    private ArrayList<ArrayList<Alien>> generateAliens() {
        // Spacing calc
        float alienSpacing = 15;
        Alien alien = new Alien(0,0);
        float alienWidth = alien.getAlienTexture().getWidth();
        float alienHeight = alien.getAlienTexture().getHeight();
        float shipHeight = ship.getShipTexture().getWidth();
        float availableSpaceX = Gdx.graphics.getWidth() - 2 * alienWidth;
        float availableSpaceY = Gdx.graphics.getHeight() - 3 * shipHeight;
        int numberAlienX = (int) Math.floor((availableSpaceX - 2 * alienSpacing) / alienWidth);
        int numberAlienY = (int) Math.floor(availableSpaceY / alienHeight);

        ArrayList<ArrayList<Alien>> aliens = new ArrayList<ArrayList<Alien>>();
        for (int rowIndex = 0; rowIndex < numberAlienY; rowIndex++) {
            aliens.add(generateAlienRow(rowIndex, numberAlienX, alienSpacing));
        }
        return aliens;
    }

    private ArrayList<Alien> generateAlienRow(int rowIndex, int aliensPerRow, float alienSpacing) {
        Alien alien = new Alien(0,0);
        float alienWidth = alien.getAlienTexture().getWidth();
        float alienHeight = alien.getAlienTexture().getHeight();

        ArrayList<Alien> row = new ArrayList<Alien>();
        for (int i = 0; i < aliensPerRow; i++) {
            row.add(new Alien(alienSpacing * i + alienWidth * i,
                    2 * rowIndex * alienHeight)
            );
        }
        return row;
    }

    private void clearOffScreenProjectiles() {
        ArrayList<DefaultLaser> remove = new ArrayList<DefaultLaser>();
        for (DefaultLaser p : projectiles) {
            if (p.getY() + p.getHeight() < 0)
                remove.add(p);
        }
        projectiles.removeAll(remove);
    }

    private void checkForHits() {
        ArrayList<Alien> aliensToRemove = new ArrayList<Alien>();
        ArrayList<DefaultLaser> projectilesToRemove = new ArrayList<DefaultLaser>();
        for (DefaultLaser p : projectiles) {
            for (ArrayList<Alien> row : enemies) {
                for (Alien a : row) {
                    if (a.contains(p.getCollisionBox())) {
                        projectilesToRemove.add(p);
                        aliensToRemove.add(a);
                    }
                }
                enemies.get(enemies.indexOf(row)).removeAll(aliensToRemove);
            }
        }
        projectiles.removeAll(projectilesToRemove);
    }
}
