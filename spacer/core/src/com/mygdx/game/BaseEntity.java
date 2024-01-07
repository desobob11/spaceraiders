package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.sprites.SMainShip;

public class BaseEntity {
    protected Rectangle hbox;
    protected Sprite sprite;
    protected AssetManager manager;

    protected float x, y;

    public BaseEntity() {
        this.manager = new AssetManager();
    }

    protected void draw(SpriteBatch batch) {
        this.sprite.draw(batch);
    }

    protected void base_load_textures(String[] paths) {
        for (String s : paths) {
            this.manager.load(s, Texture.class);
        }
    }

    protected boolean base_asset_update() {
        return this.manager.update();
    }

    protected void dispose() {
        this.manager.dispose();
    }


    protected void translate(float x, float y) {
        this.sprite.translateX(x);
        this.sprite.translateY(y);
        this.hbox.setPosition(sprite.getX(), sprite.getY());
    }



}
