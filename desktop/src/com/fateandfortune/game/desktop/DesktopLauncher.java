package com.fateandfortune.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fateandfortune.game.FateAndFortune;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// window dimensions
		config.width = FateAndFortune.WIDTH;
		config.height = FateAndFortune.HEIGHT;
		config.title = FateAndFortune.TITLE;
		new LwjglApplication(new FateAndFortune(), config);
	}
}
