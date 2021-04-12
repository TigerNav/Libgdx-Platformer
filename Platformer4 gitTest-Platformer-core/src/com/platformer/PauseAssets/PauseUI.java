package com.platformer.PauseAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.platformer.GameAssets.GameState;
import com.platformer.GameAssets.UI.Debug.DebugDraw;
import com.platformer.PauseAssets.PauseOptions.*;

public class PauseUI {
	
	public Table table;
	private Label Pause, Resume, Retry, Options,SaveQuit;
	private String[] PauseItems = {"Resume", "Retry","Options" ,"Save and Quit"};
	private int CurrentSelection = 0;
	private Options options;

	
	public PauseUI(Stage stage, DebugDraw draw) {
		
		table = new Table();
		table.background(draw.skin.newDrawable(draw.skin.getDrawable("BackDebug"), 0, 0, 0, 0.4f));
		table.setBounds(0, 0, stage.getWidth(), stage.getHeight());
		draw.DefineStyle();
		Pause = new Label("Paused", draw.style);
		Resume = new Label(PauseItems[0], draw.style);
		Retry = new Label(PauseItems[1], draw.style);
		Options = new Label(PauseItems[2], draw.style);
		SaveQuit = new Label(PauseItems[3], draw.style);
		options = new Options(draw, stage, this);
		draw.LabelStyle(Pause);
		Pause.setFontScale(3);	
		Resume.setFontScale(2);
		Retry.setFontScale(2);
		Options.setFontScale(2);
		SaveQuit.setFontScale(2);
		
		int bruh = 50;
		table.top();
		table.add(Pause).padTop(200).padBottom(bruh);
		table.row();
		table.add(Resume).padBottom(bruh);
		table.row();
		table.add(Retry).padBottom(bruh);
		table.row();
		table.add(Options).padBottom(bruh);
		table.row();
		table.add(SaveQuit);	
		options.add();
		stage.addActor(table);
	}
	
	public void setColor(Label one, Label two, Label three) {
		one.setColor(Color.YELLOW);
		two.setColor(Color.WHITE);
		three.setColor(Color.WHITE);
	}
	
	public void remove() {
		Pause.remove();
		Resume.remove();
		Retry.remove();
		Options.remove();
		SaveQuit.remove();
	}
	
	public void SelectionLogic() {

		options.SelectionLogic();
		
		if(Gdx.input.isKeyJustPressed(Keys.UP))
			CurrentSelection--;
		if(Gdx.input.isKeyJustPressed(Keys.DOWN))
			CurrentSelection++;
		
		switch(CurrentSelection) {
		case -1:
			CurrentSelection = 3;
			break;
		case 0:
			setColor(Resume, Retry, SaveQuit);
			if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
				GameState.pause = false;
			PauseAssets.SELECTIONCHANGE.play();
		}
			break;
		case 1:
			setColor(Retry, Resume, Options);
			if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
				PauseAssets.SELECTIONCHANGE.play();
			}
			break;
		case 2:	
			setColor(Options, SaveQuit, Retry);
			if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {	
				PauseAssets.SELECTIONCHANGE.play();
				options.show();
				GameState.visable = false;
			}
			break;
		case 3:			
			setColor(SaveQuit, Resume, Options);
			if(Gdx.input.isKeyJustPressed(Keys.ENTER))
				PauseAssets.SELECTIONCHANGE.play();
			break;
		default:
			CurrentSelection = 0;
			break;
		}	
	}
	
	public void show(boolean bruh) {
		table.setVisible(bruh);
	}
	
	public void hide() {
		table.setVisible(false);
		options.hide();
	}	
}
