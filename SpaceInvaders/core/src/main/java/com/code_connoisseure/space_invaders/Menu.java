package com.code_connoisseure.space_invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import org.mini2Dx.core.graphics.Graphics;


public class Menu {


    public void render (Graphics g){
        g.drawRect(Gdx.graphics.getWidth()/2.0f-150, 150, 300, 50);
        g.drawString("Play", Gdx.graphics.getWidth()/2.0f-50, 170);
        g.drawRect(Gdx.graphics.getWidth()/2.0f-150, 250, 300, 50);
        g.drawString("High Score", Gdx.graphics.getWidth()/2.0f-50, 270);
        g.drawRect(Gdx.graphics.getWidth()/2.0f-150, 350, 300, 50);
        g.drawString("Quit", Gdx.graphics.getWidth()/(2.0f)-50, 370);


     //   g.setFont(GameFont);
        g.setColor(Color.WHITE);
        g.drawString("SPACE INVADERS", 50, 50);

    }
}
