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
    protected Vector2 direction;
    protected float angle;

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



    protected Vector2 get_direction() {
        Vector2 vec;
        if (this.direction == null) {
            vec = new Vector2(0, 0);
        }
        else {
            vec = new Vector2(direction.x, direction.y);
        }
        return vec;
    }

    protected float get_angle() {
        return this.angle;
    }

    protected Vector2 get_position() {
        Vector2 vec;
        if (this.sprite == null) {
            vec = new Vector2(0, 0);
        }
        else {
            vec = new Vector2(sprite.getX(), sprite.getY());
        }
        return vec;
    }

    protected Vector2 get_muzzle_center() {
        Vector2 vec;
        if (this.direction == null) {
            vec = new Vector2(0, 0);
        }
        else {
            // hacky 4 here
            //direction.nor();
            sprite.setOriginCenter();
            // first set to top center of sprite as if there was no rotation
            vec = new Vector2(sprite.getX() + sprite.getOriginX(), sprite.getY() + sprite.getOriginY());
            // then multiply by direction vector
            vec.add(this.sprite.getWidth() / 4 * this.direction.x, this.sprite.getHeight() / 4 * this.direction.y);
        }
        return vec;
    }

    protected abstract void load_resources(AssetManager manager);
}
