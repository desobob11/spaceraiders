package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.sprites.SMainShip;

public class Player extends BaseEntity {
    private final float SPAWN_X = Game.WIN_WIDTH / 2;
    private final float SPAWN_Y = 100f;

    private final float MOVE_SPEED = 10f;

    public Player() {
        super();
        String[] paths = new String[SMainShip.values().length];
        for (int i = 0; i < paths.length; ++i) {
            paths[i] = SMainShip.values()[i].get();
        }
        base_load_textures(paths);
        this.sprite = new Sprite((Texture) manager.get(SMainShip.SMAINSHIP_FULL.get()));
        this.sprite.setPosition(SPAWN_X, SPAWN_Y);
        this.hbox = new Rectangle(SPAWN_X, SPAWN_Y, sprite.getWidth(), sprite.getHeight());
    }

    public void update(SpriteBatch batch) {
        draw(batch);
        move();
    }


    private void move() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            translate(0, MOVE_SPEED);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            translate(0, -MOVE_SPEED);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            translate(-MOVE_SPEED, 0);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            translate(MOVE_SPEED, 0);
        }
    }



}
