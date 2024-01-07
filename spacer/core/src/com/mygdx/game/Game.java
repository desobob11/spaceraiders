package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.sprites.SMainShip;

public class Game extends ApplicationAdapter {
	public static final float WIN_WIDTH = 1100f;
	public static final float WIN_HEIGHT = 800f;
	public OrthographicCamera cam;


	AssetManager manager = new AssetManager();
	SpriteBatch batch;
	Player player;
	float camera_zoom = 0.3f;


	@Override
	public void create () {
		cam = new OrthographicCamera(WIN_WIDTH, WIN_HEIGHT);
		cam.position.set(WIN_WIDTH / 2, WIN_HEIGHT / 2, 0);
		cam.zoom -= camera_zoom;
		cam.update();
		batch = new SpriteBatch();
		player = new Player(manager);
		manager.load(String.format("%s\\backgrounds\\lvl1.png", System.getProperty("user.dir")), Texture.class);
	}

	@Override
	public void render () {
		if (manager.update()) {
			ScreenUtils.clear(0, 0, 0, 1);
			batch.setProjectionMatrix(cam.combined);
			batch.begin();
			batch.draw((Texture) manager.get(String.format("%s\\backgrounds\\lvl1.png", System.getProperty("user.dir"))), 0, 0);
			player.update(batch, manager, cam);
			cam.update();
			batch.end();
		}

		float progress = manager.getProgress();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		manager.dispose();
	}
}
