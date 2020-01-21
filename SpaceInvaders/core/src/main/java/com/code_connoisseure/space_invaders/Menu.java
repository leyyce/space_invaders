package com.code_connoisseure.space_invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import org.mini2Dx.core.graphics.Graphics;


public class Menu {

    public void render (Graphics g){
        g.setColor(Color.GRAY);
        g.drawRect(Gdx.graphics.getWidth()/2.0f-150, 250, 300, 50);
        g.fillRect(Gdx.graphics.getWidth()/2.0f-150, 250, 300, 50);

        g.setColor(Color.WHITE);
        g.drawString("Play", Gdx.graphics.getWidth()/2.0f-50, 270, 0, 15);

        g.setColor(Color.GRAY);
        g.drawRect(Gdx.graphics.getWidth()/2.0f-150, 350, 300, 50);
        g.fillRect(Gdx.graphics.getWidth()/2.0f-150, 350, 300, 50);

        g.setColor(Color.WHITE);
        g.drawString("High Score", Gdx.graphics.getWidth()/2.0f-50, 370,0,15);

        g.setColor(Color.GRAY);
        g.drawRect(Gdx.graphics.getWidth()/2.0f-150, 450, 300, 50);
        g.fillRect(Gdx.graphics.getWidth()/2.0f-150, 450, 300, 50);

        g.setColor(Color.WHITE);
        g.drawString("Quit", Gdx.graphics.getWidth()/(2.0f)-50, 470,0,15);


        g.setColor(Color.WHITE);
        g.drawString("SPACE INVADERS", Gdx.graphics.getWidth()/2.0f-70, 125);


    }
}
