package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.levels.LBackgrounds;
import com.mygdx.game.sprites.SBullets;
import com.mygdx.game.sprites.SMainShip;

public class Game extends ApplicationAdapter {
	public static final float WIN_WIDTH = 1100f;
	public static final float WIN_HEIGHT = 800f;
	public OrthographicCamera cam;

	Background test;
	AssetManager manager = new AssetManager();
	SpriteBatch batch;
	Player player;
	float camera_zoom = 0.3f;

	Pixmap curs;




	@Override
	public void create () {
		curs = new Pixmap(Gdx.files.internal("misc\\crosshair.png"));
		Gdx.graphics.setCursor(Gdx.graphics.newCursor(curs, 0, 0));
		cam = new OrthographicCamera(WIN_WIDTH, WIN_HEIGHT);
		cam.position.set(WIN_WIDTH / 2, WIN_HEIGHT / 2, 0);
		cam.zoom -= camera_zoom;
		cam.update();
		batch = new SpriteBatch();
		player = new Player(manager);
	//	while (manager.update()) {System.out.println("Loading textures");}
		cache_backgrounds(manager);
		cache_bullets(manager);
		test = new Background(LBackgrounds.LBACKGROUNDS_LVL1_LAYER1.get(),
				LBackgrounds.LBACKGROUNDS_LVL1_LAYER2.get(),
				LBackgrounds.LBACKGROUNDS_LVL1_LAYER3.get(),
				LBackgrounds.LBACKGROUNDS_LVL1_LAYER4.get());

		//manager.load(String.format("%s\\backgrounds\\lvl1.png", System.getProperty("user.dir")), Texture.class);
	}

	@Override
	public void render () {
		if (manager.update()) {
			ScreenUtils.clear(0, 0, 0, 1);
			batch.setProjectionMatrix(cam.combined);
			batch.begin();
			//batch.draw((Texture) manager.get(String.format("%s\\backgrounds\\lvl1.png", System.getProperty("user.dir"))), 0, 0);
			test.draw_back(batch, player, manager);
			player.update(batch, manager, cam);
			test.draw_front(batch, player);
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



	private void cache_backgrounds(AssetManager manager) {
		String[] paths = new String[LBackgrounds.values().length];
		for (int i = 0; i < paths.length; ++i) {
			paths[i] = LBackgrounds.values()[i].get();
		}
		// this.atlas_idle = new TextureAtlas(Gdx.files.internal("animations\\ANIM_ENGINE_BASE_IDLE.atlas"));
		for (String s : paths) {
			manager.load(s, Texture.class);
		}
	}


	private void cache_bullets(AssetManager manager) {
		String[] paths = new String[SBullets.values().length];
		for (int i = 0; i < paths.length; ++i) {
			paths[i] = SBullets.values()[i].get();
		}
		// this.atlas_idle = new TextureAtlas(Gdx.files.internal("animations\\ANIM_ENGINE_BASE_IDLE.atlas"));
		for (String s : paths) {
			manager.load(s, Texture.class);
		}
	}



}



