package com.platformer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.platformer.Assets.AssetLoader; 
import com.platformer.MenuAssets.Menu;

/**
 * @author Navin
 * 
 *	Main class holds the 
 *	Virtual width and height
 *	loads all assets and updates
 */

public class Main extends Game {
	
	public static int V_WIDTH = 800, V_HEIGHT = 400;
	public static float PPM = 100;
	public static SpriteBatch batch;
	
	@Override
	public void create () {
		
		AssetLoader.load();
		
		while(!AssetLoader.AM.update()) {
			System.out.println(AssetLoader.AM.getProgress() * 100 + "%");
		}
		
		setScreen(new Menu(this));
	}
	
	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}
