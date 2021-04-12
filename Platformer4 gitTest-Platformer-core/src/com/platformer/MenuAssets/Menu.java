package com.platformer.MenuAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.platformer.Main;
import com.platformer.Assets.AssetLoader;

/**
 * @author Navin
 *	
 *	The Main menu holder
 *	The main menu just holds 3 buttons which you can find in @Button
 */

public class Menu implements Screen{
	
	public static Skin skin;
	public BitmapFont font;
	public static Stage stage;
	private Button button;
	private SpriteBatch sb;
	private OrthographicCamera gamecam;
	
	
	public Menu(Main main) {
		gamecam = new OrthographicCamera();
		new StretchViewport(Main.V_WIDTH, Main.V_HEIGHT, gamecam);
		stage = new Stage();
		sb = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal(AssetLoader.Font), false);
		button = new Button(this, main);
		button.ButtonAdd();
		MenuAssets.MENUSOUND.play();
		MenuAssets.MENUSOUND.isLooping();
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0 ,0 ,0 ,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))
			Gdx.app.exit();
		
		stage.draw();
		stage.act();

		button.Update(delta, this);
		
		
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
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
