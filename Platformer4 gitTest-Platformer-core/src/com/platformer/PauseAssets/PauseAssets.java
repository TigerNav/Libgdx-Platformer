package com.platformer.PauseAssets;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.platformer.Assets.AssetLoader;

public class PauseAssets {
	public static TextureAtlas WINDOWBACK = AssetLoader.AM.get(AssetLoader.ButtonAtlas);
	public static Sound SELECTIONCHANGE = AssetLoader.AM.get(AssetLoader.SelectionChange);
	
}
