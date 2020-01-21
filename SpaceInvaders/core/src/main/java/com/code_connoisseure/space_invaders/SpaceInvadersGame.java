package com.code_connoisseure.space_invaders;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.code_connoisseure.space_invaders.enteties.AnimatedBoxGameObject;
import com.code_connoisseure.space_invaders.enteties.enemies.BasicEnemy;
import com.code_connoisseure.space_invaders.enteties.player_ships.PlayerShip;
import com.code_connoisseure.space_invaders.enteties.projectiles.Projectile;
import com.code_connoisseure.space_invaders.logic.LevelSettings;
import com.code_connoisseure.space_invaders.music.PlayList;
import com.code_connoisseure.space_invaders.ui.HealthBar;
import com.code_connoisseure.space_invaders.ui.Score;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import com.code_connoisseure.space_invaders.enteties.enemies.Alien;
import com.code_connoisseure.space_invaders.enteties.player_ships.DefaultShip;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.graphics.viewport.StretchViewport;

public class SpaceInvadersGame extends BasicGame {
    public static final String GAME_IDENTIFIER = "com.code_connoisseure.space_invaders";
    public static final int BASE_GAME_WIDTH = 1920;
    public static final int BASE_GAME_HEIGHT = 1080;
    public static int windowBaseWidthDifference;
    public static int windowBaseHeightDifference;

    private StretchViewport viewport;
    private LevelSettings levelSettings;
    private Score score;
    private Sprite backGround;
    private HealthBar healthBar;
    private PlayerShip ship;
    private ArrayList<ArrayList<BasicEnemy>> enemies;
    private ArrayList<Projectile> projectiles;
    private ArrayList<Projectile> enemyProjectiles;
    private PlayList playList;

    @Override
    public void initialise() {
        windowBaseHeightDifference = Gdx.graphics.getHeight() - SpaceInvadersGame.BASE_GAME_HEIGHT;
        windowBaseWidthDifference = Gdx.graphics.getHeight() - SpaceInvadersGame.BASE_GAME_HEIGHT;
        viewport = new StretchViewport(BASE_GAME_WIDTH, BASE_GAME_HEIGHT);
        levelSettings = new LevelSettings();
        score = new Score();
        backGround = createScaledSprite(new Texture(Gdx.files.internal("backgrounds/background_2_4k.jpg")));
        ship = new DefaultShip();
        healthBar = new HealthBar(ship, 0, 0);
        enemies = generateAliens();
        projectiles = new ArrayList<Projectile>();
        enemyProjectiles = new ArrayList<Projectile>();
        playList = new PlayList(Gdx.audio.newMusic(Gdx.files.internal("music/out_of_space.mp3")));
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
        if (allRowsEmpty) {
            levelSettings.increaseLevel();
            enemies = generateAliens();
        }
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
        viewport.apply(g);
        g.drawSprite(backGround);
        g.drawString(String.format("Stage: %d", levelSettings.getCurrentLevel()), 20, 150);
        g.drawString(String.format("Score: %d", score.getScore()), 20, 175);
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

    @Override
    public void resize(int width, int height) {
        windowBaseHeightDifference = Gdx.graphics.getHeight() - SpaceInvadersGame.BASE_GAME_HEIGHT;
        windowBaseWidthDifference = Gdx.graphics.getHeight() - SpaceInvadersGame.BASE_GAME_HEIGHT;
    }

    @Override
    public void dispose() {
        super.dispose();
        Gdx.app.exit();
    }

    private void reactToKeyPresses() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            if (Gdx.graphics.isFullscreen()) {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Gdx.graphics.setWindowedMode(screenSize.width - 160, screenSize.height - 90);
            }
            else {
                Gdx.app.exit();
                // No longer needed because of config.forceExit=true
                // System.exit(0);
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
        float availableSpaceX = BASE_GAME_WIDTH - 2 * alienWidth;
        float availableSpaceY = BASE_GAME_HEIGHT - 3 * alienHeight - shipHeight;
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
                    2 * rowIndex * alienHeight, levelSettings.getEnemyLives(), levelSettings.getEnemySpeed())
            );
        }
        return row;
    }

    private void clearOffScreenProjectiles() {
        ArrayList<Projectile> remove = new ArrayList<Projectile>();
        for (Projectile p : projectiles) {
            if (p.getY() + p.getHeight() < 0 || p.getY() > Gdx.graphics.getHeight() - windowBaseHeightDifference) {
                remove.add(p);
                score.decreaseScore(10);
            }
        }
        projectiles.removeAll(remove);
        remove = new ArrayList<Projectile>();
        for (Projectile p : enemyProjectiles) {
            if (p.getY() + p.getHeight() < 0 || p.getY() > Gdx.graphics.getHeight() - windowBaseHeightDifference)
                remove.add(p);
        }
        enemyProjectiles.removeAll(remove);
    }

    private void clearOffScreenAliens() {
        ArrayList<BasicEnemy> remove;
        for (ArrayList<BasicEnemy> row : enemies) {
            remove = new ArrayList<BasicEnemy>();
            for (BasicEnemy a : row) {
                if (a.getY() >= Gdx.graphics.getHeight() - windowBaseHeightDifference)
                    remove.add(a);
            }
            row.removeAll(remove);
        }
    }

    private void checkForEnemyHits() {
        ArrayList<BasicEnemy> enemiesToRemove;
        ArrayList<Projectile> projectilesToRemove = new ArrayList<Projectile>();
        for (Projectile p : projectiles) {
            for (ArrayList<BasicEnemy> row : enemies) {
                enemiesToRemove = new ArrayList<BasicEnemy>();
                for (BasicEnemy e : row) {
                    if (e.contains(p.getCollisionBox())) {
                        p.damageObject();
                        if (!p.alive()) projectilesToRemove.add(p);
                        e.damageObject();
                        if (!e.alive()) {
                            enemiesToRemove.add(e);
                            score.increaseScore(50);
                        }
                    }
                }
                row.removeAll(enemiesToRemove);
            }
        }
        projectiles.removeAll(projectilesToRemove);

        for (ArrayList<BasicEnemy> row : enemies) {
            enemiesToRemove = new ArrayList<BasicEnemy>();
            for (BasicEnemy e : row) {
                if (ship.intersects(e.getCollisionBox())) {
                    if (healthBar.shipAlive()) {
                        healthBar.damageShip();
                        score.decreaseScore(250);
                        e.damageObject();
                    }
                    if (!e.alive()) enemiesToRemove.add(e);
                }
            }
            row.removeAll(enemiesToRemove);
        }
    }

    private void checkForPlayerHits() {
        ArrayList<Projectile> projectilesToRemove = new ArrayList<Projectile>();
        for (Projectile p : enemyProjectiles) {
            if (ship.contains(p.getCollisionBox())) {
                healthBar.damageShip();
                if (healthBar.shipAlive()) score.decreaseScore(150);
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
                int attackingEnemies = rand.nextInt(levelSettings.getAttackProbability() + levelSettings.getAttackersPerRow() + 1);  // Bound is exclusive
                attackingEnemies = attackingEnemies <= levelSettings.getAttackersPerRow() ? Math.min(attackingEnemies, row.size()) : 0;
                for (int i = 0; i < attackingEnemies; i++) {
                    int enemyIndex = rand.nextInt(row.size());
                    row.get(enemyIndex).fireProjectile(enemyProjectiles, levelSettings.getProjectileSpeed());
                }
            }
        }
    }

    private static Sprite createScaledSprite(Texture texture) {
        float SCALE_RATIO = (float) texture.getWidth() / BASE_GAME_WIDTH;
        Sprite sprite = new Sprite(texture);
        sprite.getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        sprite.setSize(sprite.getWidth() / SCALE_RATIO,
                sprite.getHeight() / SCALE_RATIO);
        return sprite;
    }
}
