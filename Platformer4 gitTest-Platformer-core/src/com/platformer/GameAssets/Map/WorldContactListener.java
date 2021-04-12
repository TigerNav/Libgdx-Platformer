package com.platformer.GameAssets.Map;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.platformer.GameAssets.Objects.Shrooms;

public class WorldContactListener implements ContactListener{

	@Override
	public void beginContact(Contact contact) {
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();
		
		System.out.println("begin contact");
		
		if(fixA.getUserData() == "hitbox" || fixB.getUserData() == "hitbox") {
			Fixture hitbox = fixA.getUserData() == "hitbox" ? fixA : fixB;
			Fixture object = hitbox == fixA ? fixB : fixA;
			
			
			if(object.getUserData() != null && Shrooms.class.isAssignableFrom(object.getUserData().getClass())) {
				
			}
			
		}
		
	}

	@Override
	public void endContact(Contact contact) {
		System.out.println("end contact");
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		
	}

}
