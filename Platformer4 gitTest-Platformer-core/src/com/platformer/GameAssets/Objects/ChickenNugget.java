package com.platformer.GameAssets.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.platformer.GameAssets.Entities.Player;

public class ChickenNugget extends GameObjects{

	
	public ChickenNugget(float x, float y, int width, int height, Texture texture) {
		super(x, y, width, height, texture);
		
	}

	public void update() {
		rect.setPosition(sprite.getX(), sprite.getY());
		if(rect.overlaps(Player.rect)) {
			Player.MaxHeight = 2f;
			sprite.setColor(0, 0, 0, 0);
		}
	}
	
	public void render(SpriteBatch sb) {
		super.render(sb);
	}
}
