package com.platformer.MenuAssets;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.platformer.Assets.AssetLoader;

public class MenuAssets {
	
	/**
	 * @author Navin
	 *	
	 *	@Menu Assets
	 *	It takes the assets from @AssetLoader and 
	 *	gets the acual path so it can be used for the other classes like
	 *	
	 *	@Button @SettingsState @Sliders @GraphicsSettings
	 */
	
	public static TextureAtlas BUTTONATLAS = AssetLoader.AM.get(AssetLoader.ButtonAtlas);
	public static Music MENUSOUND = AssetLoader.AM.get(AssetLoader.MenuAudio);
	public static Sound BUTTONSOUND = AssetLoader.AM.get(AssetLoader.ButtonClickedAudio);
	
	
}
