package com.platformer.GameAssets.Objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;
import com.platformer.GameAssets.Entities.Player;

public class Shrooms extends GameObjects{

	World world;
	
	public Shrooms(float x, float y, int width, int height, TextureAtlas ta, String PackThing) {
		super(x, y, width, height, ta, PackThing);
			
		ItemAnimation(3, 80, 15, 15, 15);		
	}

	@Override
	public void update() {
		super.update();
		
		sprite.setPosition(x, y);
		if(rect.overlaps(Player.rect)) {
			Player.MaxSpeed = 2f;
			System.out.println("bruh");
			sprite.setColor(1.0f, 1.0f, 1.0f, 0);
			rect.setPosition(1000,  1000);
		}}

	@Override
	public void render(SpriteBatch sb) {
		super.render(sb);
		//pe.draw(sb);
	}
}
