package com.platformer.GameAssets.Map;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.platformer.Main;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class MapCollisions {
	
	PolygonShape shape = new PolygonShape();
	BodyDef bdef = new BodyDef();
	FixtureDef fdef = new FixtureDef();
	Body body;
	
	public void BlockBody(TiledMap tiledMap, World world) {
		MapObjects objects = tiledMap.getLayers().get(1).getObjects();
		for(MapObject object : objects) {
			Rectangle rectangle = ((RectangleMapObject)object).getRectangle();
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / Main.PPM, (rectangle.getY() + rectangle.getHeight() / 2) / Main.PPM);
			body = world.createBody(bdef);
			shape.setAsBox(rectangle.getWidth() / 2 / Main.PPM, rectangle.getHeight() / 2 / Main.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
		}
	}

}
