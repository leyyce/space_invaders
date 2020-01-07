package com.code_connoisseure.space_invaders.desktop;

import org.mini2Dx.desktop.DesktopMini2DxConfig;

import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

import com.code_connoisseure.space_invaders.SpaceInvadersGame;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		DesktopMini2DxConfig config = new DesktopMini2DxConfig(SpaceInvadersGame.GAME_IDENTIFIER);
		config.vSyncEnabled = true;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		config.fullscreen = false;
		config.width = config.fullscreen ? screenSize.width : screenSize.width - 100;
		config.height = config.fullscreen ? screenSize.height : screenSize.height - 100;
		config.targetFPS = 60;
		new DesktopMini2DxGame(new SpaceInvadersGame(), config);
	}
}
