package com.code_connoisseure.space_invaders;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.code_connoisseure.space_invaders.enteties.AnimatedBoxGameObject;
import com.code_connoisseure.space_invaders.enteties.enemies.BasicEnemy;
import com.code_connoisseure.space_invaders.enteties.player_ships.PlayerShip;
import com.code_connoisseure.space_invaders.enteties.projectiles.Projectile;
import com.code_connoisseure.space_invaders.music.PlayList;
import com.code_connoisseure.space_invaders.ui.HealthBar;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import com.code_connoisseure.space_invaders.enteties.enemies.Alien;
import com.code_connoisseure.space_invaders.enteties.player_ships.DefaultShip;
import org.mini2Dx.core.graphics.Sprite;

public class SpaceInvadersGame extends BasicGame {
    public static final String GAME_IDENTIFIER = "com.code_connoisseure.space_invaders";

    private Sprite backGround;
    private HealthBar healthBar;
    private PlayerShip ship;
    private ArrayList<ArrayList<BasicEnemy>> enemies;
    private ArrayList<Projectile> projectiles;
    private ArrayList<Projectile> enemyProjectiles;
    private PlayList playList;

    @Override
    public void initialise() {
        backGround = new Sprite(new Texture("backgrounds/background.png"));
        ship = new DefaultShip();
        healthBar = new HealthBar(ship, 0, 0);
        enemies = generateAliens();
        projectiles = new ArrayList<Projectile>();
        enemyProjectiles = new ArrayList<Projectile>();
        playList = new PlayList(Gdx.audio.newMusic(new FileHandle("music/outer_space.mp3")));
        playList.shufflePlay();

    }

    @Override
    public void update(float delta) {
        healthBar.update(delta);
        // Update playlist
        playList.update(delta);
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
        for (ArrayList<BasicEnemy> row: enemies) {
            for(BasicEnemy alien: row) {
                alien.update(delta);
            }
        }

        checkForEnemyHits();
        checkForPlayerHits();
        clearOffScreenProjectiles();
        clearOffScreenAliens();
        reactToKeyPresses();
        enemyAttacks();
        // FOR TESTING ONLY: Make aliens respawn if all are cleared
        boolean allRowsEmpty = true;
        for (ArrayList<BasicEnemy> row : enemies) {
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
        healthBar.interpolate(alpha);
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
        for (ArrayList<BasicEnemy> row : enemies) {
            for(BasicEnemy alien: row) {
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
        for (ArrayList<BasicEnemy> row : enemies) {
            for(BasicEnemy alien: row){
                alien.render(g);
            }
        }
        // Render Health Bar
        healthBar.render(g);
    }

    private void reactToKeyPresses() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            if (Gdx.graphics.isFullscreen())
                Gdx.graphics.setWindowedMode(800, 600);

            else {
                Gdx.app.exit();
                System.exit(0);
            }
        }
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

    private ArrayList<ArrayList<BasicEnemy>> generateAliens() {
        // Spacing calc
        Alien alien = new Alien(0,0);
        float alienSpacing = 25;
        float alienWidth = alien.getWidth();
        float alienHeight = alien.getHeight();
        float shipHeight = ship.getHeight();
        float availableSpaceX = Gdx.graphics.getWidth() - 2 * alienWidth;
        float availableSpaceY = Gdx.graphics.getHeight() - 3 * alienHeight - shipHeight;
        int numberAlienX = (int) Math.floor(availableSpaceX / (2 * alienWidth));
        int numberAlienY = (int) Math.floor(availableSpaceY / (2 * alienHeight));

        ArrayList<ArrayList<BasicEnemy>> aliens = new ArrayList<ArrayList<BasicEnemy>>();
        for (int rowIndex = 0; rowIndex < numberAlienY; rowIndex++) {
            aliens.add(generateAlienRow(rowIndex, numberAlienX, alienSpacing));
        }
        return aliens;
    }

    private ArrayList<BasicEnemy> generateAlienRow(int rowIndex, int aliensPerRow, float alienSpacing) {
        Alien alien = new Alien(0,0);
        float alienWidth = alien.getWidth();
        float alienHeight = alien.getHeight();

        ArrayList<BasicEnemy> row = new ArrayList<BasicEnemy>();
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
            if (p.getY() + p.getHeight() < 0 || p.getY() > Gdx.graphics.getHeight())
                remove.add(p);
        }
        projectiles.removeAll(remove);
        remove = new ArrayList<Projectile>();
        for (Projectile p : enemyProjectiles) {
            if (p.getY() + p.getHeight() < 0 || p.getY() > Gdx.graphics.getHeight())
                remove.add(p);
        }
        enemyProjectiles.removeAll(remove);
    }

    private void clearOffScreenAliens() {
        ArrayList<BasicEnemy> remove;
        for (ArrayList<BasicEnemy> row : enemies) {
            remove = new ArrayList<BasicEnemy>();
            for (BasicEnemy a : row) {
                if (a.getY() >= Gdx.graphics.getHeight())
                    remove.add(a);
            }
            row.removeAll(remove);
        }
    }

    private void checkForEnemyHits() {
        ArrayList<BasicEnemy> aliensToRemove;
        ArrayList<Projectile> projectilesToRemove = new ArrayList<Projectile>();
        for (Projectile p : projectiles) {
            for (ArrayList<BasicEnemy> row : enemies) {
                aliensToRemove = new ArrayList<BasicEnemy>();
                for (BasicEnemy e : row) {
                    if (e.contains(p.getCollisionBox())) {
                        p.damageObject();
                        if (!p.alive()) projectilesToRemove.add(p);
                        // TODO Find good sounding explosion
                        e.damageObject();
                        if (!e.alive()) aliensToRemove.add(e);
                    }
                }
                row.removeAll(aliensToRemove);
            }
        }
        projectiles.removeAll(projectilesToRemove);
    }

    private void checkForPlayerHits() {
        ArrayList<Projectile> projectilesToRemove = new ArrayList<Projectile>();
        for (Projectile p : enemyProjectiles) {
            if (ship.contains(p.getCollisionBox())) {
                healthBar.damageShip();
                // ship = new DefaultShip();
                p.damageObject();
                if (!p.alive()) projectilesToRemove.add(p);
            }
        }
        enemyProjectiles.removeAll(projectilesToRemove);
    }

    private void enemyAttacks() {
        Random rand = new Random();
        for (ArrayList<BasicEnemy> row : enemies) {
            if (row.size() > 0) {
                int attackingEnemies = rand.nextInt(151);  // Bound is exclusive
                attackingEnemies = attackingEnemies < 3 ? Math.min(attackingEnemies, row.size()) : 0;
                for (int i = 0; i < attackingEnemies; i++) {
                    int enemyIndex = rand.nextInt(row.size());
                    row.get(enemyIndex).fireProjectile(enemyProjectiles, 2);
                }
            }
        }
    }
}
