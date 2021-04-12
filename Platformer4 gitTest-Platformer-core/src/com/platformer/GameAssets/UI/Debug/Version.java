package com.platformer.GameAssets.UI.Debug;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Version {

	private Label VersionLabel;
	
	public Version(DebugDraw debug) {
		
		debug.DefineStyle();
		VersionLabel = new Label("Java Version : "+System.getProperty("java.version"), debug.style);
		debug.LabelStyle(VersionLabel);
		VersionLabel.setFontScale(2,2);
		
		debug.table.add(VersionLabel).padRight(420);
	}

}
