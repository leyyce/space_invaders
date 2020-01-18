package com.code_connoisseure.space_invaders.desktop;

import org.mini2Dx.desktop.DesktopMini2DxConfig;

import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

import com.code_connoisseure.space_invaders.SpaceInvadersGame;

import java.awt.Toolkit;
import java.awt.Dimension;

public class DesktopLauncher {
	public static void main (String[] arg) {
		DesktopMini2DxConfig config = new DesktopMini2DxConfig(SpaceInvadersGame.GAME_IDENTIFIER);
		config.title = "[Alpha] SpaceInvaders";
		config.vSyncEnabled = true;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		config.fullscreen = false;
		config.width = config.fullscreen ? screenSize.width : screenSize.width - 160;
		config.height = config.fullscreen ? screenSize.height : screenSize.height - 90;
		config.targetFPS = 60;
		// Needed to make the game close and clean up correctly
		config.forceExit = false;
		new DesktopMini2DxGame(new SpaceInvadersGame(), config);
	}
}
