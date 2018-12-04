package com.atinem.starfishcollector.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.atinem.starfishcollector.StarfishCollector;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Starfish Collector";
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new StarfishCollector(), config);
	}
}
