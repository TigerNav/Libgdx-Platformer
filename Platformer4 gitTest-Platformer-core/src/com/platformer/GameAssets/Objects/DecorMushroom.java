package com.platformer.GameAssets.Objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class DecorMushroom extends GameObjects{

	public DecorMushroom(float x, float y, int width, int height, TextureAtlas ta, String PackThing) {
		super(x, y, width, height, ta, PackThing);
		
		ItemAnimation(6, 0, 40, 35, 40);
	}
	
	
	@Override
	public void update() {
		super.update();
		sprite.setPosition(x, y);
	}
	public void render(SpriteBatch sb) {
		super.render(sb);
	}
}
