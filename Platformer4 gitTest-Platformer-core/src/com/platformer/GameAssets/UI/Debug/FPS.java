package com.platformer.GameAssets.UI.Debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
public class FPS {


	private int FPSCounter;
	private Label FPSLabel;
	
	
	
	public FPS(Stage stage, DebugDraw draw) {
		
		draw.DefineStyle();
		
		FPSLabel = new Label("FPS : "+FPSCounter, draw.style);
		FPSLabel.setFontScale(2, 2);
		draw.LabelStyle(FPSLabel);
		System.out.println("bruh");
		draw.table.add(FPSLabel).padRight(550);
	}
	
	public void update() {
		FPSCounter = Gdx.graphics.getFramesPerSecond();
		FPSLabel.setText("FPS : "+FPSCounter);
		
	}

}
