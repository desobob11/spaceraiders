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
import org.w3c.dom.Text;

public class Player extends BaseEntity {
    private final float SPAWN_X = Game.WIN_WIDTH / 2;
    private final float SPAWN_Y = Game.WIN_HEIGHT / 2;
    private Vector2 direction;

    private final int IDLE_NUM = 3;


    private final float MOVE_SPEED = 5f;
    private boolean is_instantiated = false;
    private float velocity;

    private double move_clock;
    private Texture engine_texture;
    private TextureRegion[] engine_frames;
    private float anim_time;
    private float angle;

    Animation<TextureRegion> engine_animation;
    //TextureAtlas atlas_idle;


    public Player(AssetManager manager) {
        load_resources(manager);
    }

    public void update(SpriteBatch batch, AssetManager manager, OrthographicCamera cam) {
        if (!is_instantiated) {
            instantiate(manager);
        }
        move(cam);
        follow_cursor(cam);
        engine_draw(batch);
        draw(batch);
    }

    private void follow_cursor(OrthographicCamera cam) {
        Vector3 cursor = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        Vector3 ppos = new Vector3(this.sprite.getX() + sprite.getOriginX(), this.sprite.getY() + sprite.getOriginY(), 0);
        this.angle = (float) Math.atan2(cursor.x - ppos.x, cursor.y - ppos.y);

        Vector3 sub = cursor.sub(ppos).nor();
        this.angle = (float) Math.toDegrees(this.angle);
        this.direction = new Vector2(sub.x, sub.y);
        this.sprite.setRotation(-this.angle);
    }

    public void instantiate(AssetManager manager) {
        this.sprite = new Sprite((Texture) manager.get(SMainShip.SMAINSHIP_FULL.get()));
        this.sprite.setPosition(SPAWN_X, SPAWN_Y);
        this.sprite.setOriginCenter();
        this.hbox = new Rectangle(SPAWN_X, SPAWN_Y, sprite.getWidth(), sprite.getHeight());
        this.is_instantiated = true;
        this.rotation = new Vector2(0, 0);

        // base engine ile will be start animation for engine
        this.engine_texture = manager.get(SEngines.SENGINES_BASE_IDLE.get());
        cache_engine(engine_texture);

        // start animation time at 0
        this.anim_time = 0;

    }



    // hardcoded bounds in this function
    private void move(OrthographicCamera cam) {

        // if player is pressing a movement key
        //if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.S)) {
        this.velocity = 1f;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (cam.position.y + (cam.viewportHeight) <= 3000) {
                //  translate(0, MOVE_SPEED);
                translate(this.direction.x * MOVE_SPEED, this.direction.y * MOVE_SPEED);
            }
        }
            /*
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                if (cam.position.y + (cam.viewportHeight) >= 1400) {
                    System.out.println(cam.position.y + (cam.viewportHeight));
                    //  translate(0, -MOVE_SPEED);
                    translate(-this.direction.x * MOVE_SPEED, -this.direction.y * MOVE_SPEED);
                }
            }
            */

      //  }

        // otherwise move until acceleration is drained
        else {
                // need to set up velocity decay here

            }
            // check if one second has passed
        cam.position.set(sprite.getX() + sprite.getOriginX(), sprite.getY() + sprite.getOriginY(), 0);
    }

    private void cache_engine(Texture sheet) {
        TextureRegion[][] temp = TextureRegion.split(sheet, sheet.getWidth() / IDLE_NUM,
                sheet.getHeight());

        this.engine_frames = new TextureRegion[IDLE_NUM];
        int index = 0;
        for (int i = 0; i < 1; ++i) {
            for (int j = 0; j < IDLE_NUM; ++j) {
                this.engine_frames[index] = temp[i][j];
                ++index;
            }
        }
        this.engine_animation = new Animation<TextureRegion>(0.05f, this.engine_frames);
    }

    private void engine_draw(SpriteBatch batch) {
        anim_time += Gdx.graphics.getDeltaTime();
        TextureRegion frame = this.engine_animation.getKeyFrame(anim_time, true);
        Sprite frame_sprite = new Sprite(frame);
        frame_sprite.setOriginCenter();
        frame_sprite.setPosition(sprite.getX(), sprite.getY());
        frame_sprite.setRotation(sprite.getRotation());
        frame_sprite.draw(batch);
    }


    public boolean isInstantiated() {
        return this.is_instantiated;
    }


    /*
        ==========================================================================================
                        RESOURCE LOADING
        ==========================================================================================
     */




    @Override
    protected void load_resources(AssetManager manager) {
        // load ship textures
        String[] paths = new String[SMainShip.values().length];
        for (int i = 0; i < paths.length; ++i) {
            paths[i] = SMainShip.values()[i].get();
        }
        // this.atlas_idle = new TextureAtlas(Gdx.files.internal("animations\\ANIM_ENGINE_BASE_IDLE.atlas"));
        base_load_textures(manager, paths);

        // load engine animation textures
        paths = new String[SEngines.values().length];
        for (int i = 0; i < paths.length; ++i) {
            paths[i] = SEngines.values()[i].get();
        }
        // this.atlas_idle = new TextureAtlas(Gdx.files.internal("animations\\ANIM_ENGINE_BASE_IDLE.atlas"));
        base_load_textures(manager, paths);
    }






}
