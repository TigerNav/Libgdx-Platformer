/**
 * 
 */
package com.platformer.GameAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.platformer.Main;
import com.platformer.GameAssets.Entities.AddEntities;
import com.platformer.GameAssets.Map.MapLoader;
import com.platformer.GameAssets.Map.WorldContactListener;
import com.platformer.GameAssets.Objects.AddObjects;
import com.platformer.GameAssets.UI.Debug.DebugDraw;
import com.platformer.MenuAssets.MenuAssets;
import com.platformer.PauseAssets.PauseAssets;
import com.platformer.PauseAssets.PauseUI;

/**
 * 	@author Navin
 *	GameState class Holds the First game screen
 */

public class GameState implements Screen{
	

	
	private Stage stage;
	public Stage getStage() {return stage;}
	private static World world;
	private MapLoader ml;
	private SpriteBatch sb;
	private static OrthographicCamera gamecam;
	private Viewport vp;
	private AddEntities AddEntity;
	private AddObjects AddObject;
	public static World getWorld() {return world;}
	private DebugDraw debug;
	public static boolean pause;
	private PauseUI pauseUI;
	public static boolean visable;
	
	public GameState(Main main) {
		gamecam = new OrthographicCamera();
		vp = new StretchViewport(Main.V_WIDTH / Main.PPM, Main.V_HEIGHT / Main.PPM, gamecam);
		world = new World(new Vector2(0, -1), true);
		AddEntity = new AddEntities(world);
		AddObject = new AddObjects();
		ml = new MapLoader(GameAssets.LEVEL1, world);
		sb = new SpriteBatch();
		world.setContactListener(new WorldContactListener());	
		stage = new Stage();
		debug = new DebugDraw(stage);
		debug.update();
		pause = false;
		pauseUI = new PauseUI(stage,debug);
		
	}
	
	@Override
	public void show() {
		
	}
	
	public void State(float dt) {
		
	}

	public void update(float dt) {
		
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			visable = true;
			pause = true;
		}
			
		ml.update(this);
		
		AddEntity.update(dt);
		AddObject.update();
		
		gamecam.update();
		
		world.step(1/60f, 6, 2);
	
	}
	
	@Override
	public void render(float delta) {	
		Gdx.gl.glClearColor(0 ,0 ,0 ,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		
		ml.render(world, this);
		
		if(pause) {
			MenuAssets.MENUSOUND.pause();
			pauseUI.show(visable);
			if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
				pause = false;
				visable = false;
			}
		}
		else {
			MenuAssets.MENUSOUND.play();
			PauseAssets.SELECTIONCHANGE.stop();
			update(delta);
			pauseUI.hide();
		}
		
		debug.update();
	
		pauseUI.SelectionLogic();
		
		sb.setProjectionMatrix(gamecam.combined);
		sb.begin();
		
		AddObject.render(sb);
		AddEntity.render(sb);
		
		stage.draw();
		stage.act();
		
		sb.end();

	}

	@Override
	public void resize(int width, int height) {
		vp.update(width, height);
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
		
	}
	public static OrthographicCamera getGamecam() {return gamecam;}

}
