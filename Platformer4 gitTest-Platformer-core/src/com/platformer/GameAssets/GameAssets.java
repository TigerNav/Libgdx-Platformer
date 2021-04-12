/**
 * 
 */
package com.platformer.GameAssets;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.platformer.Assets.AssetLoader;

/**
 * @author Main
 * 
 * @Game Assets
 * It takes the assets from @AssetLoader and 
 * gets the acual path so it can be used for the other classes like
 */

public class GameAssets {
	public static TiledMap LEVEL1 = AssetLoader.AM.get(AssetLoader.Level1);
	public static TextureAtlas PLAYERTEXTURES = AssetLoader.AM.get(AssetLoader.PlayerAsset);
	public static Sound PLAYERJUMPSOUND = AssetLoader.AM.get(AssetLoader.PlayerJump);
	public static TextureAtlas MUSHROOMS = AssetLoader.AM.get(AssetLoader.Mushroom);
	public static Texture CHICKENNUGGET = AssetLoader.AM.get(AssetLoader.ChickenNugget);
	public static TextureAtlas LABELBACK = AssetLoader.AM.get(AssetLoader.LabelBack);
}
