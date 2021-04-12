package com.platformer.MenuAssets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.platformer.Main;
import com.platformer.Assets.AssetLoader;
import com.platformer.GameAssets.GameState;
import com.platformer.MenuAssets.Settings.SettingsState;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

/**
 * @author Navin
 *	
 *	Hold ONLY the main menu Buttons and assets
 *	Holds the animations for the Buttons
 *	Holds actions for buttons
 */

public class Button {

	public static TextButtonStyle style;
	private TextureAtlas atlas = new TextureAtlas(AssetLoader.ButtonAtlas);
	private String[] MenuItems = {"Play","Options","Exit"};
	private int currentSelection = 0;
	private TextButton PlayButton, OptionsButton ,ExitButton;
	public Label label;
	private Skin skin;
	private TweenManager tweenManager;
	private Main main;
	public static Table ButtonTable;
	
	public Button(final Menu menu, final Main main) {
		this.main = main;
		skin = new Skin(atlas);
		ButtonTable = new Table(skin);
		ButtonStyle(menu);	
		PlayButton = new TextButton(MenuItems[0], style);
		OptionsButton = new TextButton(MenuItems[1], style);
		ExitButton = new TextButton(MenuItems[2], style);		
		Label PlayLabel = PlayButton.getLabel();
		Menu.stage.addActor(ButtonTable);
		ButtonTable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());	
		label = new Label("Game", new Label.LabelStyle(menu.font, Color.CHARTREUSE));
		label.setFontScale(4);
		PlayLabel.setFontScale(2);
		PlayButton.setColor(Color.CHARTREUSE);
		PlayButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				MenuAssets.BUTTONSOUND.play();
				ButtonTable.remove();
				main.setScreen(new GameState(main));
		}
			
		});
		
		OptionsButton.getLabel().setFontScale(2);
		OptionsButton.setColor(Color.CHARTREUSE);
		
		OptionsButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {			
						MenuAssets.BUTTONSOUND.play();		
						
						main.setScreen(new SettingsState(main));
				}});
		ExitButton.getLabel().setFontScale(2);
		ExitButton.setColor(Color.CHARTREUSE);
		ExitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {		
					try {MenuAssets.BUTTONSOUND.play();
						Thread.sleep(1000);
						Gdx.app.exit();} 
					catch (InterruptedException e){e.printStackTrace();}}});
		
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());
		
		Timeline.createSequence().beginSequence()
			.push(Tween.set(PlayButton, ActorAccessor.ALPHA).target(0))
			.push(Tween.set(OptionsButton, ActorAccessor.ALPHA).target(0))
			.push(Tween.set(ExitButton, ActorAccessor.ALPHA).target(0))
			.push(Tween.from(label, ActorAccessor.ALPHA, 0.5f).target(0))
			.push(Tween.to(PlayButton, ActorAccessor.ALPHA, 0.5f).target(1))
			.push(Tween.to(OptionsButton, ActorAccessor.ALPHA, 0.5f).target(1))
			.push(Tween.to(ExitButton, ActorAccessor.ALPHA, 0.5f).target(1))
			.end().start(tweenManager);
			
			
				
		Gdx.input.setInputProcessor(Menu.stage);
	}
	
	public void setColor(TextButton one, TextButton two, TextButton three) {
		one.setColor(Color.BLUE);
		two.setColor(Color.CHARTREUSE);
		three.setColor(Color.CHARTREUSE);
	}
	
	public void ButtonAdd() {
		ButtonTable.add(label).padBottom(25);
		ButtonTable.row();
		ButtonTable.add(PlayButton).padBottom(25);
		ButtonTable.row();
		ButtonTable.add(OptionsButton).padBottom(25);
		ButtonTable.row();
		ButtonTable.add(ExitButton).padBottom(25);
	}
	
	public void ButtonStyle(Menu menu) {
		Menu.skin = new Skin(MenuAssets.BUTTONATLAS);
		style = new TextButtonStyle();
		style.fontColor = Color.CHARTREUSE;
		style.up = Menu.skin.getDrawable("button-pressed");
		style.over = Menu.skin.getDrawable("button-over");
		style.down = Menu.skin.getDrawable("button");
		style.pressedOffsetX = 1;
		style.pressedOffsetY = -1;
		style.font = menu.font;
		
	}
	public void Update(float delta, Menu menu) {
		tweenManager.update(delta);
		
		if(Gdx.input.isKeyJustPressed(Keys.UP))
			currentSelection -= 1;
		if(Gdx.input.isKeyJustPressed(Keys.DOWN))
			currentSelection += 1;
		
		switch(currentSelection) {
		case -1:
			currentSelection = 2;
			break;
		case 0:
			setColor(PlayButton, OptionsButton, ExitButton);
			if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
				MenuAssets.BUTTONSOUND.play();
				ButtonTable.remove();
				main.setScreen(new GameState(main));
			}
			break;	
			
		case 1:
			setColor(OptionsButton, PlayButton, ExitButton);
				if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
					MenuAssets.BUTTONSOUND.play();		
					main.setScreen(new SettingsState(main));
				}
			break;
			
		case 2:
			setColor(ExitButton,OptionsButton, PlayButton);
			if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
				try {MenuAssets.BUTTONSOUND.play();
				Thread.sleep(1000);
				Gdx.app.exit();} 
				catch (InterruptedException e){e.printStackTrace();};
			}
			break;
		default:
			currentSelection = 0;
			break;
		}
		
	}

}
