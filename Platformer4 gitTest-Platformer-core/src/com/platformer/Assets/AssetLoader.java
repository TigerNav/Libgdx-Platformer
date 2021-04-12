package com.platformer.Assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader.ParticleEffectParameter;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * @author Navin
 *	This class loads all the assets to be used for @MenuAssets @GameAssets and @PauseAssets
 */

public class AssetLoader {
		
	public static AssetManager AM = new AssetManager(new InternalFileHandleResolver());
	public static ParticleEffectParameter pep = new ParticleEffectParameter();
	public static final String ButtonAtlas = "MenuAssets/neon/skin/neon-ui.atlas";
	public static final String WindowJson = "MenuAssets/neon/skin/neon-ui.json";
	public static final String Font = "MenuAssets/neon/skin/font-over-export.fnt";
	public static final String ButtonClickedAudio = "MenuAssets/MenuSfx/ButtonPick.wav";
	public static final String MenuAudio = "MenuAssets/MenuSfx/Menu.ogg";
	public static final String Level1 = "GameAssets/Map/untitled.tmx";
	public static final String PlayerAsset = "GameAssets/Platformer assets pack/Player/PlayerAssets.pack";
	public static final String PlayerJump = "GameAssets/Platformer assets pack/Player/JumpSound.wav";
	public static final String Mushroom = "GameAssets/Platformer assets pack/Objects/mushrooms.pack";
	public static final String ChickenNugget = "GameAssets/Platformer assets pack/Objects/chickenNugget.png";
	public static final String LabelBack = "MenuAssets/DebugBack.pack";
	public static final String SelectionChange = "PauseAssets/SelectionChange.wav";
	
	
	public static void load() {
		AM.load(ButtonAtlas, TextureAtlas.class);
		AM.load(ButtonClickedAudio, Sound.class);
		AM.load(MenuAudio, Music.class);
		AM.load(PlayerAsset, TextureAtlas.class);
		AM.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		AM.load(Level1, TiledMap.class);
		AM.load(PlayerJump, Sound.class);
		AM.load(Mushroom, TextureAtlas.class);
		AM.load(ChickenNugget, Texture.class);
		AM.load(LabelBack, TextureAtlas.class);
		AM.load(SelectionChange, Sound.class);
	}
	public static void dispose() {
		AM.dispose();
	}
	
}
