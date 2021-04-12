/**
 * 
 */
package com.platformer.GameAssets.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.platformer.Main;

/**
 * @author Navin
 * This Class is used for all Entity object like players and enemies
 *
 */
public abstract class GameEntity {
	
	protected boolean isLeft, isRight;
	protected float stateTime;
	protected enum State{FALLING, JUMPING, WALKING, RUNNING, ATTACKING, STANDING};
	protected State currentState;
	protected State preState;
	protected float stateTimer;
	protected Animation<TextureRegion> RunAnimation, JumpAnimation, IdleAnimation, RunningAnimation;
	protected Sprite sprite;
	protected Vector2 poision;
	protected Body body;
	protected BodyDef bdef;
	protected FixtureDef fdef;
	protected PolygonShape shape;
	protected TextureAtlas ta;
	protected Array<TextureRegion> frames = new Array<TextureRegion>();
	protected TextureRegion tr;
	public static Rectangle rect;
	private int width, Height;
	
/**
 * @param world
 * @param x
 * @param y
 * @param width
 * @param height
 * @param ta the TextureAtlas most likely located in assetManager (GameAssets)
 * @param PackThings kinda unreasoable but just put it anyways
 */
	
	public GameEntity(World world,float x, float y, int width, int height, TextureAtlas ta, String PackThings) {
		
		this.width = width;
		this.Height = height;
		this.ta = ta;
		
		sprite = ta.createSprite(PackThings);
		sprite.setPosition(x, y);
		
		currentState = State.STANDING;
		preState = State.STANDING;
		stateTimer = 0;
		isRight = true;
		
		sprite.setSize(width / Main.PPM, Height / Main.PPM);
		bdef = new BodyDef();
		bdef.position.set(new Vector2(x / Main.PPM, y / Main.PPM));
		bdef.type = BodyDef.BodyType.DynamicBody;	
		fdef = new FixtureDef();
		fdef.friction = 2;
		shape = new PolygonShape();
		shape.setAsBox((width / 2) / Main.PPM , (height / 2)/ Main.PPM);
		fdef.shape = shape;
		body = world.createBody(bdef);
		body.createFixture(fdef);
		rect = new Rectangle();
		rect.width = (width / 2) / Main.PPM;
		rect.height = (Height / 2) / Main.PPM;
	}
	
	/**
	 * @param AmountTextures amount of textures
	 * @param PixelX on the sprite sheet of where the textures start X
	 * @param PixelY on the sprite sheet of where the textures start Y
	 * 
	 * @return the animations
	 */
	
	
	public void WalkAnimation(int AmountTextures,int PixelX, int PixelY) {	
		// walk animation
		for(int i = 1; i < AmountTextures; i++) 
			frames.add(new TextureRegion(sprite.getTexture(), PixelX,i * PixelY,width, Height));
		RunAnimation = new Animation<TextureRegion>(0.1f, frames);
		frames.clear();
	}	
	
	public void JumpAnimation(int AmountTextures, int PixelX, int PixelY) {
		// jump animation
		for(int i = 1; i < AmountTextures; i++) 
			frames.add(new TextureRegion(sprite.getTexture(), PixelX,i *  PixelY,width, Height));
		JumpAnimation = new Animation<TextureRegion>(0.1f, frames);
		frames.clear();
	}
	public void IdleAnimaion(int AmountTextures, int PixelX, int PixelY) {
		// idle animation
		for(int i = 1; i < 5; i++) 
			frames.add(new TextureRegion(sprite.getTexture(), PixelX, PixelY * i, width, Height));
		IdleAnimation = new Animation<TextureRegion>(0.3f, frames);
		frames.clear();
	}
	public void RunningAnimation(int AmountTextures, int PixelX, int PixelY) {
		// Running animation
		for(int i = 1; i <  AmountTextures; i++) 
			frames.add(new TextureRegion(sprite.getTexture(),PixelX, i * PixelY , width, Height));
		RunningAnimation = new Animation<TextureRegion>(0.1f, frames);
		frames.clear();
	}
	
	public TextureRegion getFrame() {
		currentState = getState();
		
		TextureRegion region;
		switch(currentState) {
		case WALKING:
			region = RunAnimation.getKeyFrame(stateTimer, true);
			break;
		case RUNNING:
			region = RunningAnimation.getKeyFrame(stateTimer, true);
			break;
		case JUMPING:
			region = JumpAnimation.getKeyFrame(stateTimer, true);
			break;
		case STANDING:
		default:
			region = IdleAnimation.getKeyFrame(stateTimer, true);
			break;
		}
		
		if((body.getLinearVelocity().x < 0 || !isRight) && !region.isFlipX()) {
			region.flip(true, false);
			isRight = false;
		}
		else if((body.getLinearVelocity().x > 0 || isRight) && region.isFlipX()) {
			region.flip(true, false);
			isRight = true;
		}
		
		stateTimer = currentState == preState ? stateTimer + Gdx.graphics.getDeltaTime() : 0;
		preState = currentState;
		return region;
	}
	
	public State getState() {
		if(body.getLinearVelocity().y > 0)
			return State.JUMPING;
		else if(body.getLinearVelocity().y < 0)
			return State.JUMPING;
		else if(body.getLinearVelocity().x > 1.2 || body.getLinearVelocity().x < -1.2) 
			return State.RUNNING;
		else if(body.getLinearVelocity().x != 0)
			return State.WALKING;
		else
			return State.STANDING;
	}

	public void update() {
		sprite.setRegion(getFrame());
		sprite.setPosition(body.getPosition().x - 10 / Main.PPM, body.getPosition().y - 20 / Main.PPM);
	}
	public void render(SpriteBatch sb) {
		sprite.draw(sb);
	}
		

}
