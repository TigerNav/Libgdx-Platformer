/**
 * 
 */
package com.platformer.GameAssets.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.platformer.Main;
import com.platformer.GameAssets.GameState;

/**
 * @author Main
 *	Handles the map
 */
public class MapLoader {
	
	private OrthogonalTiledMapRenderer renderer;
	private MapCollisions mc;
	private Box2DDebugRenderer b2dr;
	
	
	public MapLoader(TiledMap map, World world) {
		renderer = new OrthogonalTiledMapRenderer(map, 1 / Main.PPM);
		mc = new MapCollisions(); 
		mc.BlockBody(map, world);
		b2dr = new Box2DDebugRenderer();
	}
	public void update(GameState gs) {
		renderer.setView(GameState.getGamecam());
		
	}
	public void render(World world, GameState gs) {
		renderer.render();
		b2dr.render(world, GameState.getGamecam().combined);
	}

}
