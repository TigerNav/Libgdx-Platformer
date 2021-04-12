package com.platformer.MenuAssets.Settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.platformer.Main;
import com.platformer.Assets.AssetLoader;
import com.platformer.MenuAssets.Button;
import com.platformer.MenuAssets.Menu;
import com.platformer.MenuAssets.MenuAssets;

/**
 * @author Navin
 * 
 * Holds The exit button and combines all the settings
 */

public class SettingsState implements Screen{
	
	private TextureAtlas atlas = new TextureAtlas(AssetLoader.ButtonAtlas);
	private Stage stage;
	public Skin skin;
	private TextButton Back;
	private Sliders vs;
	private OrthographicCamera gamecam;
	private SpriteBatch sb;
	public static Table table;
	
	public SettingsState(Main main) {
	
		sb = new SpriteBatch();
		gamecam = new OrthographicCamera();
		new StretchViewport(Main.V_WIDTH, Main.V_HEIGHT, gamecam);
		stage = new Stage();
		table = new Table();
		skin = new Skin(atlas);
		vs = new Sliders(skin);
		Button(main);
		
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public void Button(final Main main) {
		Back = new TextButton("Back", Button.style);
		Back.getLabel().setFontScale(2);
		Back.setColor(Color.CHARTREUSE);	
		Back.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				MenuAssets.BUTTONSOUND.play();
				
				main.setScreen(new Menu(main));
				}});
		
		table.left();
		table.bottom();
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0 ,0 ,0 ,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		table.setFillParent(true);
		table.add(Back);
		
		vs.update();
		stage.addActor(table);
		stage.addActor(vs.table);
		stage.draw();
		stage.act();
		
		sb.setProjectionMatrix(gamecam.combined);
		sb.begin();
		
		sb.end();		
		gamecam.update();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
