package com.code_connoisseure.space_invaders;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.code_connoisseure.space_invaders.enteties.AnimatedBoxGameObject;
import com.code_connoisseure.space_invaders.enteties.player_ships.PlayerShip;
import com.code_connoisseure.space_invaders.enteties.projectiles.Projectile;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import com.code_connoisseure.space_invaders.enteties.Alien;
import com.code_connoisseure.space_invaders.enteties.player_ships.DefaultShip;
import org.mini2Dx.core.graphics.Sprite;

public class SpaceInvadersGame extends BasicGame {
    public static final String GAME_IDENTIFIER = "com.code_connoisseure.space_invaders";

    private Sprite backGround;
    private PlayerShip ship;
    private ArrayList<ArrayList<Alien>> enemies;
    private ArrayList<Projectile> projectiles;
    private ArrayList<Projectile> enemyProjectiles;

    @Override
    public void initialise() {
        backGround = new Sprite(new Texture("backgrounds/background.png"));
        ship = new DefaultShip();
        enemies = generateAliens();
        projectiles = new ArrayList<Projectile>();
        enemyProjectiles = new ArrayList<Projectile>();
    }

    @Override
    public void update(float delta) {
        // Update ship
        ship.update(delta);
        // Update projectiles
        for (Projectile p : projectiles) {
            p.update(delta);
        }
        // Update enemy projectiles
        for (Projectile p : enemyProjectiles) {
            p.update(delta);
        }
        // Update enemies
        for (ArrayList<Alien> row: enemies) {
            for(Alien alien: row) {
                alien.update(delta);
            }
        }

        checkForAlienHits();
        clearOffScreenProjectiles();
        clearOffScreenAliens();
        reactToKeyPresses();
        enemyAttacks();
        // FOR TESTING ONLY: Make aliens respawn if all are cleared
        boolean allRowsEmpty = true;
        for (ArrayList<Alien> row : enemies) {
            if (row.size() != 0) {
                allRowsEmpty = false;
                break;
            }
        }
        if (allRowsEmpty)
            enemies = generateAliens();
        // ----------------------------------------------------------
    }

    @Override
    public void interpolate(float alpha) {
        // Interpolate ship
        ship.interpolate(alpha);
        // Interpolate projectiles
        for (Projectile p : projectiles) {
            p.interpolate(alpha);
        }
        // Interpolate enemy projectiles
        for (Projectile p : enemyProjectiles) {
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
        g.drawSprite(backGround);
        // Render ship
        ship.render(g);
        // Render projectiles
        for (Projectile p : projectiles) {
            p.render(g);
        }
        // Render enemy projectiles
        for (Projectile p : enemyProjectiles) {
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
            ship.move(AnimatedBoxGameObject.Directions.LEFT, null);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            ship.move(AnimatedBoxGameObject.Directions.RIGHT, null);
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            ship.fireProjectile(projectiles, 7);
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
        ArrayList<Projectile> remove = new ArrayList<Projectile>();
        for (Projectile p : projectiles) {
            if (!p.objectInBounds())
                remove.add(p);
        }
        projectiles.removeAll(remove);
    }

    private void clearOffScreenAliens() {
        ArrayList<Alien> remove;
        for (ArrayList<Alien> row : enemies) {
            remove = new ArrayList<Alien>();
            for (Alien a : row) {
                if (a.getY() >= Gdx.graphics.getHeight())
                    remove.add(a);
            }
            row.removeAll(remove);
        }
    }

    private void checkForAlienHits() {
        ArrayList<Alien> aliensToRemove;
        ArrayList<Projectile> projectilesToRemove = new ArrayList<Projectile>();
        for (Projectile p : projectiles) {
            for (ArrayList<Alien> row : enemies) {
                aliensToRemove = new ArrayList<Alien>();
                for (Alien a : row) {
                    if (a.contains(p.getCollisionBox())) {
                        projectilesToRemove.add(p);
                        aliensToRemove.add(a);
                    }
                }
                row.removeAll(aliensToRemove);
            }
        }
        projectiles.removeAll(projectilesToRemove);
    }

    private void enemyAttacks() {
        Random rand = new Random();
        for (ArrayList<Alien> row : enemies) {
            if (row.size() > 0) {
                int attackingEnemies = rand.nextInt(151);  // Bound is exclusive
                attackingEnemies = attackingEnemies < 3 ? Math.min(attackingEnemies, row.size()) : 0;
                for (int i = 0; i < attackingEnemies; i++) {
                    int enemyIndex = rand.nextInt(row.size());
                    row.get(enemyIndex).dropBomb(enemyProjectiles, 2);
                }
            }
        }
    }
}
