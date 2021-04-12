package com.platformer.GameAssets.UI.Debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.platformer.GameAssets.GameAssets;
import com.platformer.GameAssets.GameState;

public class DebugDraw {

	public Table table;
	private FPS fps;
	private UsedMemory UM;
	public LabelStyle style;
	public Skin skin;
	
	public DebugDraw(Stage stage) {
		skin = new Skin(GameAssets.LABELBACK);
		style = new LabelStyle();
		table = new Table();
		table.top();
		table.setFillParent(true);
		new Version(this);
		table.row();
		UM = new UsedMemory(this);
		table.row();
		fps = new FPS(stage, this);	
		table.row();
		table.left();
		table.setBounds(GameState.getGamecam().position.x, GameState.getGamecam().position.y , Gdx.graphics.getWidth() - 20 , Gdx.graphics.getHeight() - 20);	
		stage.addActor(table);
	}
	
	public void DefineStyle() {
		Drawable bruh = skin.newDrawable(skin.getDrawable("BackDebug"), 0, 0, 0, 0.4f);
		style.background = bruh;
		style.font = new BitmapFont();
		style.fontColor = Color.WHITE;
	}
	
	public void LabelStyle(Label label) {
		style.background.setMinWidth(label.getFontScaleX());
		style.background.setMinHeight(label.getFontScaleY());	
	}
	
	public void update() {
		fps.update();
		UM.update();
	}

}
