package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.SMainShip;

public abstract class BaseEntity {
    protected Rectangle hbox;
    protected Sprite sprite;
    protected Vector2 rotation;

    protected float x, y;

    protected void draw(SpriteBatch batch) {
        this.sprite.draw(batch);
    }

    protected void base_load_textures(AssetManager manager, String[] paths) {
        for (String s : paths) {
            manager.load(s, Texture.class);
        }
    }



    protected void translate(float x, float y) {
        this.sprite.translateX(x);
        this.sprite.translateY(y);
        this.hbox.setPosition(sprite.getX(), sprite.getY());
    }





    protected abstract void load_resources(AssetManager manager);
}
