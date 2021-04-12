package com.platformer.GameAssets.Entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.platformer.GameAssets.GameAssets;
import com.platformer.GameAssets.Objects.Shrooms;

public class AddEntities {

	private Player player;
	private Shrooms shroom;
	
	public AddEntities(World world) {
		player = new Player(world, 400, 2000, 23, 37, GameAssets.PLAYERTEXTURES, "Player_jump");
		shroom = new Shrooms(20, 18.24f, 14, 15, GameAssets.MUSHROOMS, "sprites");
	}
	
	public void update(float dt) {
		player.update();
		shroom.update();
		
	}
	
	public void render(SpriteBatch sb) {
		player.render(sb);
		shroom.render(sb);
	}
}
