package com.platformer.MenuAssets;

import com.badlogic.gdx.scenes.scene2d.Actor;
import aurelienribon.tweenengine.TweenAccessor;

public class ActorAccessor implements TweenAccessor<Actor> {

	public static final int ALPHA = 0;
	
	@Override
	public int getValues(Actor target, int tweenType, float[] returnValue) {
		switch(tweenType) {
		case ALPHA:
			returnValue[0] = target.getColor().a;
			return 1;
		default:
			assert false;
			return -1;
		}
	}

	@Override
	public void setValues(Actor target, int tweenType, float[] newValue) {
		switch(tweenType) {
		case ALPHA:
			target.setColor(target.getColor().r, target.getColor().g, target.getColor().b, newValue[0]);
			break;
			default:
				assert false;
		}
		
	}

}
