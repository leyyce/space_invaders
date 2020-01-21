package com.code_connoisseure.space_invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class MouseInput implements InputProcessor {
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //PlayButtion
        if(screenX>= Gdx.graphics.getWidth()/2.0f-150 && screenX<= Gdx.graphics.getWidth()/2.0f+150 ){
            if(screenY>= 250 && screenY<=300){
                SpaceInvadersGame.State = SpaceInvadersGame.STATE.GAME;
            }
        }
        //QuitButton
        if(screenX>= Gdx.graphics.getWidth()/2.0f-150 && screenX<= Gdx.graphics.getWidth()/2.0f+150 ){
            if(screenY>= 450 && screenY<=500){
               System.exit(1);
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
