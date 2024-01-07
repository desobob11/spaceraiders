package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.sprites.SMainShip;

public class Game extends ApplicationAdapter {
	public static final float WIN_WIDTH = 1100f;
	public static final float WIN_HEIGHT = 800f;


	SpriteBatch batch;
	Player player;


	@Override
	public void create () {
		batch = new SpriteBatch();
		player = new Player();
	}

	@Override
	public void render () {
		if (player.base_asset_update()) {

		}

		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		player.update(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		player.dispose();
	}
}
