package com.platformer.PauseAssets.PauseOptions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.platformer.GameAssets.GameState;
import com.platformer.GameAssets.UI.Debug.DebugDraw;
import com.platformer.PauseAssets.PauseAssets;
import com.platformer.PauseAssets.PauseUI;

public class Options {

	private Table table;
	private Label Options;
	private Label DebugLabel, OnandOff, Volume, VolumePercent,Back;
	private Stage stage;
	private DebugDraw draw;
	private String[] MenuItems = {"DebugOverlay","Volume","Back"};
	public int currentSelection = 0;
	private boolean ShowDebug;
	
	public Options(DebugDraw draw,Stage stage, PauseUI pauseUi) {
		this.stage = stage;
		this.draw = draw;
		table = new Table();
		table.setBounds(0, 0, stage.getWidth(), stage.getHeight());
		table.setBackground(draw.skin.newDrawable(draw.skin.getDrawable("BackDebug"), 0, 0, 0, 0.4f));
		Options = new Label("Options", draw.style);
		Back = new Label(MenuItems[2], draw.style);
		table.add(Options).padTop(200).padBottom(50);
		table.row();
		Options.setFontScale(3);
		DebugOverlay(draw);
		Back.setFontScale(2);
		table.add(OnandOff);
		OnandOff.setFontScale(1.7f);
		table.row();
		Volume(draw);
		table.row();
		table.add(Back).padTop(50);
		
		
	}
	
	public void DebugOverlay(DebugDraw draw) {
		DebugLabel = new Label(MenuItems[0],draw.style);
		OnandOff = new Label("", draw.style);
		DebugLabel.setFontScale(2);
		table.add(DebugLabel);
		table.row();
	}
	
	public void DebugOverlayLogic() {		
		draw.table.setVisible(ShowDebug);	
		if(draw.table.isVisible())
			OnandOff.setText("ON");
		else
			OnandOff.setText("OFF");
	}
	
	public void Volume(DebugDraw draw) {
		Volume = new Label(MenuItems[1], draw.style);
		VolumePercent = new Label("%", draw.style);
		Volume.setFontScale(2);
		VolumePercent.setScale(1.7f);
		table.add(Volume).padTop(50);
		table.row();
		table.add(VolumePercent);
	}
	public void VolumeLogic() {
		
	}
	
	public void SelectionLogic() {
		
		
		if(Gdx.input.isKeyJustPressed(Keys.UP))
			currentSelection--;
		if(Gdx.input.isKeyJustPressed(Keys.DOWN))
			currentSelection++;
		
		switch(currentSelection){
			case -1:
				currentSelection = 1;
				break;
			case 0:
				DebugLabel.setColor(Color.YELLOW);
				Volume.setColor(Color.WHITE);
				Back.setColor(Color.WHITE);
				DebugOverlayLogic();
				
				if(Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
					ShowDebug = true;
					PauseAssets.SELECTIONCHANGE.play();
				}
				
				if(Gdx.input.isKeyJustPressed(Keys.LEFT)) {
					ShowDebug = false;
					PauseAssets.SELECTIONCHANGE.play();
				}		
				break;
			case 1:
				DebugLabel.setColor(Color.WHITE);
				Volume.setColor(Color.YELLOW);
				Back.setColor(Color.WHITE);
				
				break;
			case 2:
				DebugLabel.setColor(Color.WHITE);
				Back.setColor(Color.YELLOW);
				if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
					table.setVisible(false);
					GameState.visable = true;
				}
				break;
			case 3:
				currentSelection = 0;
				break;
		}
		
		
	}
	
	
	public void add() {
		table.top();
		stage.addActor(table);
	}
	public void show() {
		table.setVisible(true);
	}
	public void hide() {
		table.setVisible(false);
	}
	
}
