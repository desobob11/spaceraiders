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



}
