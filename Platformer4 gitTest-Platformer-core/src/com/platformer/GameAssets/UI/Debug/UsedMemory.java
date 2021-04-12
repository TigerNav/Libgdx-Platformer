package com.platformer.GameAssets.UI.Debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class UsedMemory {
	
	private float MemoryHeap;
	private Label MemoryLabel;
	
	public UsedMemory(DebugDraw draw) {	
		draw.DefineStyle();
		MemoryLabel = new Label("Memory : "+MemoryHeap, draw.style);
		MemoryLabel.setFontScale(2,2);
		draw.LabelStyle(MemoryLabel);
		draw.table.add(MemoryLabel).padRight(400);
		
	}
	
	public void update() {
		MemoryHeap = Gdx.app.getNativeHeap() / 1000000;
		MemoryLabel.setText("Memory Used : "+MemoryHeap+"%");
	}

}
