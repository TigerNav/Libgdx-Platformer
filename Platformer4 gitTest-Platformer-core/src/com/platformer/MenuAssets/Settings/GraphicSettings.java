package com.platformer.MenuAssets.Settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * @author Navin
 *	
 *	Settings for graphics and stuff
 *	like Fullscreen, VSync and FPS
 */

public class GraphicSettings {
	
	private Slider FPS;
	private Label fps;
	private CheckBox EnableVSync, EnableFullScreen;
	private CheckBox.CheckBoxStyle cs = new CheckBox.CheckBoxStyle();
	private BitmapFont font = new BitmapFont();
	
	public void EnableFullScreen() {
		
		EnableFullScreen = new CheckBox("FullScreen", cs);		
		EnableFullScreen.addListener(new ChangeListener() {		
			@Override
			public void changed(ChangeEvent event, Actor actor) {	
				if(EnableFullScreen.isChecked())
					Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
				if(!EnableFullScreen.isChecked()) 
					Gdx.graphics.setWindowedMode(1600, 800);
				
			}});
	}
	public void FullScreenDraw(Table table) {
		table.add(EnableFullScreen).padTop(10).padRight(40);
	}
	
	public void EnableVSync(Skin skin) {
		font.getData().setScale(2);
		cs.font = font;
		cs.fontColor = Color.CHARTREUSE;
		
		cs.checkboxOff = skin.getDrawable("checkbox");
		cs.checkboxOn = skin.getDrawable("checkbox-pressed");
		EnableVSync = new CheckBox("Enable VSync", cs);
		CheckBoxStyle cgs = EnableVSync.getStyle();
		EnableVSync.setColor(Color.CHARTREUSE);
		cgs.checkboxOff.setMinHeight(30);
		cgs.checkboxOff.setMinWidth(30);
		cgs.checkboxOn.setMinHeight(30);
		cgs.checkboxOn.setMinWidth(30);
		EnableVSync.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.graphics.setVSync(true);		
			}});
		}
	
	public void VSyncCBDraw(Table table) {
		table.setFillParent(true);
		table.add(EnableVSync).padTop(30);
	}
	
	public void FPSSettings(SliderStyle ss, LabelStyle label) {
		fps = new Label(""+Gdx.graphics.getFramesPerSecond(), label);
		FPS = new Slider(15, 250, 5, false, ss);
		FPS.setValue(FPS.getMaxValue() / 2);
		
		FPS.setColor(Color.CHARTREUSE);
		FPS.addListener(new EventListener() {		
			@Override
			public boolean handle(Event event) {
				Gdx.graphics.setForegroundFPS((int) FPS.getValue());
				return false;}});
	}
	
	public void FPSDraw(Table table, LabelStyle label) {
		table.add(new Label("FPS", label));
		table.add(FPS).padLeft(-10f);
		table.add(fps).padLeft(20f);
		
		
	}
	
	public void FPSUpdate() {
		fps.setText(""+FPS.getValue());
		if(FPS.getValue() == FPS.getMaxValue()) 
			fps.setText("Max");
		if(FPS.getValue() == FPS.getMinValue()) 
			fps.setText("Min");
	}	
}
