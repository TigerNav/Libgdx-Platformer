package com.platformer.GameAssets.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.platformer.Main;

public abstract class GameObjects extends Sprite{
	
	protected boolean toDestroy;
	protected boolean Destroy;
	protected Sprite sprite;
	protected TextureAtlas ta;
	protected TextureRegion tr;
	protected Animation<TextureRegion> Animation;
	protected Array<TextureRegion> frames;
	protected float stateTimer;
	protected BodyDef bdef;
	protected Body body;
	protected FixtureDef fdef;
	protected PolygonShape shape;
	protected Texture texture;
	protected float width, height, x, y;
	
	public static Rectangle rect;
	
	public GameObjects(float x, float y, int width, int height, TextureAtlas ta, String PackThing) {
		this.ta = ta;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		sprite = ta.createSprite(PackThing);
		
		frames = new Array<TextureRegion>();
		rect = new Rectangle();
		rect.width = (width / 2) / Main.PPM;
		rect.height = (height / 2) / Main.PPM;
	}
	
	public GameObjects(float x, float y, int width, int height, Texture texture) {
		this.texture = texture;
		sprite = new Sprite(texture);
		sprite.setPosition(x, y);
		sprite.setSize((width + 10) / Main.PPM, (height + 10) / Main.PPM);
		rect = new Rectangle();
		rect.width = (width / 2) / Main.PPM;
		rect.height = (height / 2) / Main.PPM;
		
		
	}
	
	/**
	 * @param TextureAmount add 1 to how many there acually are
	 */
	
	public void ItemAnimation(int TextureAmount, int StartX, int StartY, int PixelWidth, int PixelHeight) {
		tr = new TextureRegion(sprite.getTexture(), StartX, StartY, PixelWidth, PixelHeight);
		
		for(int i = 1; i < TextureAmount; i++) 
			frames.add(new TextureRegion(sprite.getTexture(),StartX, i * StartY, PixelWidth, PixelHeight));
		Animation = new Animation<TextureRegion>(0.8f , frames);
		stateTimer = 0;
	}

	public void update() {
		stateTimer += Gdx.graphics.getDeltaTime();
		sprite.setRegion(Animation.getKeyFrame(stateTimer, true));
		sprite.setSize((width + 10) / Main.PPM, (height + 10) / Main.PPM);
		rect.x = x;
		rect.y = y;
	}
	public void render(SpriteBatch sb) {
		sprite.draw(sb);
	}
}
