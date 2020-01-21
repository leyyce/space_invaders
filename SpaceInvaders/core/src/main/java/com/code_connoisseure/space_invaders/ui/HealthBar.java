package com.code_connoisseure.space_invaders.ui;

import com.code_connoisseure.space_invaders.enteties.player_ships.DefaultShip;
import com.code_connoisseure.space_invaders.enteties.player_ships.PlayerShip;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayList;

public class HealthBar {
    private PlayerShip trackedShip;
    private float x;
    private float y;
    private int spacing = 20;
    private ArrayList<PlayerShip> healthAsShips = new ArrayList<PlayerShip>();

    public HealthBar(PlayerShip shipToTrack, float x, float y) {
        this.trackedShip = shipToTrack;
        this.x = x;
        this.y = y;
        for (int i = 0; i < shipToTrack.getLives(); i++) {
            if (shipToTrack.getClass() == DefaultShip.class) {
                healthAsShips.add(new DefaultShip(this.x + i * (trackedShip.getSheetFrameWidth() + spacing), this.y) {
                    // Removed re-centering of ship if it gets out of bounds.
                    @Override
                    public void update(float delta) {
                        //preUpdate() must be called before any changes are made to the CollisionPoint
                        collisionBox.preUpdate();
                        objectAnimation.update(delta);
                    }
                });
            }
        }
    }

    public boolean damageShip() {
        return damageShip(1);
    }

    public boolean damageShip(int damage) {
        if (healthAsShips.size() >= damage) {
            trackedShip.damageObject(damage);
            healthAsShips.remove(healthAsShips.size() - 1);
            return true;
        }
        return false;
    }

    public int getLives() {
        return trackedShip.getLives();
    }

    public boolean shipAlive() {
        return trackedShip.alive();
    }

    public void update(float delta) {
        for (PlayerShip s : healthAsShips)
            s.update(delta);
    }

    public void interpolate(float alpha) {
        for (PlayerShip s : healthAsShips)
            s.interpolate(alpha);
    }

    public void render(Graphics g) {
        for (PlayerShip s : healthAsShips)
            s.render(g);
    }
}
