package com.platformer.MenuAssets.Settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.platformer.MenuAssets.MenuAssets;

/**
 * @author Navin
 * 
 * Holds Slider Textures and 
 * The Volume Slider
 */

public class Sliders {

	private Slider VolumeSlider;
	public Table table;
	private Label MV;
	private Label mvInt;
	private BitmapFont font = new BitmapFont();
	public LabelStyle label = new Label.LabelStyle(font, Color.WHITE);
	private GraphicSettings gs;
	
	
	long id = 2;
	Slider.SliderStyle ss;
	
	public Sliders(Skin skin) {
		label.fontColor = Color.CHARTREUSE;
		MenuAssets.MENUSOUND.setLooping(true);
		table = new Table();
		gs = new GraphicSettings();
		ss = new Slider.SliderStyle();
		ss.knob = skin.getDrawable("slider-knob");	
		ss.knobDown = skin.getDrawable("slider-knob-pressed");
		ss.background = skin.getDrawable("slider");
		MV = new Label("Master Volume", label);
		
		VolumeSlider = new Slider(0 , 1.0f, 0.1f,false, ss);
		mvInt = new Label(""+MenuAssets.MENUSOUND.getVolume(), label);
		VolumeSlider.setColor(Color.CHARTREUSE);
		
		VolumeSlider.setValue(0.5f);
		SliderStyle vs = VolumeSlider.getStyle();
		vs.background.setMinHeight(10);
		vs.knobDown.setMinHeight(20);
		vs.knobDown.setMinWidth(20);
		vs.knob.setMinHeight(20);
		vs.knob.setMinWidth(20);
		
		gs.FPSSettings(ss, label);
		gs.EnableVSync(skin);
		gs.EnableFullScreen();
		label.font.getData().setScale(2);
		
		VolumeSlider.addListener(new EventListener() {	
			@Override
			public boolean handle(Event event) {
				MenuAssets.MENUSOUND.setVolume(VolumeSlider.getValue());
				return false;
			}});
		
		table.setFillParent(true);
		table.top();
		table.setFillParent(true);
		table.add(MV).padRight(10);
		table.add(VolumeSlider).padRight(10);
		table.add(mvInt).padLeft(5);
		table.row();
		gs.FPSDraw(table, label);
		table.row();
		gs.VSyncCBDraw(table);
		table.row();
		gs.FullScreenDraw(table);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());	
	}
	public void update() {
		mvInt.setText(""+MenuAssets.MENUSOUND.getVolume() * 10);
		if(MenuAssets.MENUSOUND.getVolume() == 0.90000004)
			MenuAssets.MENUSOUND.setVolume(0.9f);
		
		if(MenuAssets.MENUSOUND.getVolume() == VolumeSlider.getMaxValue()) 
			mvInt.setText("Max");
			
		if(MenuAssets.MENUSOUND.getVolume() == VolumeSlider.getMinValue())
			mvInt.setText("Mute");
		
		gs.FPSUpdate();
		
		
	}
}
