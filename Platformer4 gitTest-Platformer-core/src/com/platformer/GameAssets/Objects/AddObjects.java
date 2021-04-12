package com.platformer.GameAssets.Objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.platformer.GameAssets.GameAssets;

public class AddObjects {

	private Shrooms shroom;
	private DecorMushroom DM;
	private ChickenNugget cn;
	
	public AddObjects() {
		shroom = new Shrooms(20, 18.24f, 15, 14, GameAssets.MUSHROOMS, "sprites");
		DM = new DecorMushroom(22, 18.24f, 70, 60, GameAssets.MUSHROOMS, "mushroom");
		cn = new ChickenNugget(19, 18.24f, 12, 10, GameAssets.CHICKENNUGGET);
	}
	
	public void update() {
		shroom.update();
		DM.update();
		cn.update();
	}
	
	public void render(SpriteBatch sb) {
		shroom.render(sb);
		DM.render(sb);
		cn.render(sb);
	}

}
