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
    Vector2 direction;
    Sprite sprite;
    Rectangle hbox;
    Animation<TextureRegion> anim;
    private final float SPEED = 10f;
    TextureRegion[] frames;
    float anim_time;
    float rotation;
    Vector2 spawn;



    public Bullet(String s, int size, AssetManager manager, BaseEntity ent) {
        Texture text = manager.get(s);
        this.direction = ent.get_direction();
        this.rotation = -ent.get_angle();
        this.spawn = ent.get_muzzle_center();
       // this.spawn = ent.get_position();

      //  this.sprite = new Sprite();
       // this.sprite.setPosition(spawn.x, spawn.y);
        cache_animation(text, size);

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
        draw(batch);
    }

    private void draw(SpriteBatch batch) {
        TextureRegion frame;
        anim_time += Gdx.graphics.getDeltaTime();
        frame = this.anim.getKeyFrame(anim_time, true);

        //this.sprite.setTexture(frame.getTexture());
        this.sprite = new Sprite(frame.getTexture());
        this.sprite.setPosition(spawn.x, spawn.y);
        //this.sprite.setOriginCenter();
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






}
