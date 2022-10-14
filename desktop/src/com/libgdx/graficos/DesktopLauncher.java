package com.libgdx.graficos;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.libgdx.graficos.Graphics;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);  //definição de frame-rate
		config.setTitle("Gráficos");  //definição de título da janela
		config.setWindowedMode(500,500); //definição de dimensões da janela
		config.setResizable(false);   //definição de dimensionamento estático da janela
		new Lwjgl3Application(new Graphics(), config);
	}
}
