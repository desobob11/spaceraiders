package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.sprites.SEngines;
import com.mygdx.game.sprites.SMainShip;
import com.mygdx.game.sprites.SWeapons;



public class Bullet {
   private Vector2 direction;
    private  Sprite sprite;
    private  Rectangle hbox;
    private Animation<TextureRegion> anim;
    private final float SPEED = 10f;
    private TextureRegion[] frames;
    private  float anim_time;
    private  float rotation;
    private Vector2 spawn;
    private double life_time;
    private final double TTL  = 5f;



    public Bullet(String s, int size, AssetManager manager, BaseEntity ent) {
        Texture text = manager.get(s);
        this.direction = ent.get_direction();
        this.rotation = -ent.get_angle();
        this.spawn = ent.get_muzzle_center();
        this.life_time = 0;
       // this.spawn = ent.get_position();

      //  this.sprite = new Sprite();
       // this.sprite.setPosition(spawn.x, spawn.y);
        cache_animation(text, size);
        this.sprite = new Sprite(anim.getKeyFrame(0f, true));

    }

    private void cache_animation(Texture sheet, int size) {
        TextureRegion[][] temp = TextureRegion.split(sheet, sheet.getWidth() / size,
                sheet.getHeight());

        this.frames = new TextureRegion[size];
        int index = 0;
        for (int i = 0; i < 1; ++i) {
            for (int j = 0; j < size; ++j) {
                this.frames[index] = temp[i][j];
                ++index;
            }
        }
        this.anim = new Animation<>(0.08f, this.frames);
        this.anim_time = 0f;
    }

    public void update(SpriteBatch batch) {
        this.life_time += Gdx.graphics.getDeltaTime();
        draw(batch);
    }

    private void draw(SpriteBatch batch) {
        anim_time += Gdx.graphics.getDeltaTime();
        this.sprite = new Sprite(anim.getKeyFrame(anim_time, true));
        this.sprite.setPosition(spawn.x, spawn.y);
        this.sprite.setOrigin(0, this.sprite.getHeight() / 2);
        this.sprite.setRotation(this.rotation);
       this.sprite.rotate(90);
        // draw at first position, then translate for next draw
        this.sprite.draw(batch);
        this.spawn.add(SPEED * direction.x, SPEED * direction.y);

    }


    public float get_x() {
        return this.sprite.getX();
    }

    public float get_y() {
        return this.sprite.getY();
    }


    public boolean past_lifetime() {
        return this.life_time > TTL;
    }



}
