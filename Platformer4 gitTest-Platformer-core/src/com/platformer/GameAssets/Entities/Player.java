package com.platformer.GameAssets.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.platformer.Main;
import com.platformer.GameAssets.GameAssets;
import com.platformer.GameAssets.GameState;

public class Player extends GameEntity{
	
	public Vector2 Velocity;
	public static float MaxSpeed = 1.5f;
	public static float MaxHeight = 1.5f;
	
	public Player(World world, float x, float y, int width, int height, TextureAtlas ta, String PackThings) {
		super(world, x, y, width, height, ta, PackThings);
		WalkAnimation(9, 0, 40);
		JumpAnimation(2, 69, 362);
		IdleAnimaion(5, 65, 40);
		RunningAnimation(8, 23, 40);
	}

	
	@Override
	public void update() {
		super.update();
		
		GameState.getGamecam().position.x = body.getPosition().x;
		GameState.getGamecam().position.y = body.getPosition().y;
		
		Velocity = new Vector2(0, 0);
		
		rect.x = body.getPosition().x - 10 / Main.PPM;
		rect.y = body.getPosition().y - 20 / Main.PPM;
		
		if(Gdx.input.isKeyJustPressed(Keys.W)) {
			body.applyLinearImpulse(Velocity.x, Velocity.y += MaxHeight, 0, 0, true);	
			GameAssets.PLAYERJUMPSOUND.play();
		}
		if(Gdx.input.isKeyPressed(Keys.D) && body.getLinearVelocity().x <= MaxSpeed)
			body.applyLinearImpulse(Velocity.x += 0.03, 0, 0, 0, true);
		if(Gdx.input.isKeyPressed(Keys.A) && body.getLinearVelocity().x >= -MaxSpeed) 
			body.applyLinearImpulse(Velocity.x -= 0.03, 0, 0, 0, true);
	}
	
	
	@Override
	public void render(SpriteBatch sb) {
		super.render(sb);
	}
}

