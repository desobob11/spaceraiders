package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.utils.TextureProvider;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.sprites.SMainShip;

public class Player extends BaseEntity {
    private final float SPAWN_X = Game.WIN_WIDTH / 2;
    private final float SPAWN_Y = 100f;

    private final float MOVE_SPEED = 10f;
    private boolean is_instantiated = false;

    public Player(AssetManager manager) {
        String[] paths = new String[SMainShip.values().length];
        for (int i = 0; i < paths.length; ++i) {
            paths[i] = SMainShip.values()[i].get();
        }
        base_load_textures(manager, paths);
    }

    public void update(SpriteBatch batch, AssetManager manager) {
        if (!is_instantiated) {
            instantiate(manager);
        }
        move();
        draw(batch);
    }

    public void instantiate(AssetManager manager) {
        this.sprite = new Sprite((Texture) manager.get(SMainShip.SMAINSHIP_FULL.get()));
        this.sprite.setPosition(SPAWN_X, SPAWN_Y);
        this.hbox = new Rectangle(SPAWN_X, SPAWN_Y, sprite.getWidth(), sprite.getHeight());
        this.is_instantiated = true;
    }

    private void move() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            translate(0, MOVE_SPEED);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            translate(0, -MOVE_SPEED);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            translate(-MOVE_SPEED, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            translate(MOVE_SPEED, 0);
        }
    }

    public boolean isInstantiated() {
        return this.is_instantiated;
    }




}
