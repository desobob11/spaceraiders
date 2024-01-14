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
import com.mygdx.game.sprites.SBullets;
import com.mygdx.game.sprites.SEngines;
import com.mygdx.game.sprites.SMainShip;
import com.mygdx.game.sprites.SWeapons;

import java.util.LinkedList;
import java.util.Queue;


public class Player extends BaseEntity {
    private final float SPAWN_X = Game.WIN_WIDTH / 2;
    private final float SPAWN_Y = Game.WIN_HEIGHT / 2;

    private int anim_size;


    private final float MOVE_SPEED = 5f;
    private boolean is_instantiated = false;
    private float velocity;

    private double move_clock;
    private Texture engine_texture, weapon_texture;
    private TextureRegion[] engine_frames;
    private float engine_anim_time, weapon_anim_time;
    private boolean is_moving;

    Animation<TextureRegion> engine_animation;
    Animation<TextureRegion> weapon_animation;
    LinkedList<Bullet> bullets;
    //TextureAtlas atlas_idle;


    public Player(AssetManager manager) {
        load_resources(manager);
    }

    public void update(SpriteBatch batch, AssetManager manager, OrthographicCamera cam) {
        if (!is_instantiated) {
            instantiate(manager);
        }
        move(cam, manager);
        follow_cursor(cam);
        engine_draw(batch);
        weapon_draw(batch);
       // clean_bullets(cam);
        shoot(manager);
        draw_bullets(batch);
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

        this.bullets = new LinkedList<>();


        // base engine ile will be start animation for engine
        this.engine_texture = manager.get(SEngines.SENGINES_BASE_IDLE.get());
        this.weapon_texture = manager.get(SWeapons.SWEAPONS_AUTO.get());
        cache_weapon(weapon_texture, SWeapons.SWEAPONS_AUTO.size());
        cache_engine(engine_texture, SEngines.SENGINES_BASE_IDLE.size());
        is_moving = false;

    }

    private void shoot(AssetManager manager) {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            bullets.add(new Bullet(SBullets.SBULLETS_BASE.get(), SBullets.SBULLETS_BASE.size(), manager, this));
        }
    }

    private void clean_bullets(OrthographicCamera cam) {
        if (bullets.size() > 0) {
            int i = bullets.size() - 1;
            int count = 0;
            boolean lim_found = false;
            while (i >= 0 || !lim_found) {
                Bullet current = bullets.get(i);
                if (current.get_x() > cam.position.x + cam.viewportWidth || current.get_x() < cam.position.x - cam.viewportWidth) {
                    ++count;
                    --i;
                } else if (current.get_y() > cam.position.y + cam.viewportHeight || current.get_y() < cam.position.y - cam.viewportHeight) {
                    ++count;
                    --i;
                } else {
                    lim_found = true;
                }
            }
            i = 0;
            while (i < count) {
                bullets.remove(0);
                ++i;
            }
        }
    }

    private void draw_bullets(SpriteBatch batch) {
        for (Bullet b : bullets) {
            b.update(batch);
        }
        System.out.println(bullets.size());
    }


    private void move(OrthographicCamera cam, AssetManager manager) {

        this.velocity = 1f;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (!is_moving) {
                cache_engine(manager.get(SEngines.SENGINES_BASE_POWERING.get()), SEngines.SENGINES_BASE_POWERING.size());
                is_moving = true;
            }
            translate(this.direction.x * MOVE_SPEED, this.direction.y * MOVE_SPEED);
        }
        else {
            if (is_moving) {
                cache_engine(manager.get(SEngines.SENGINES_BASE_IDLE.get()), SEngines.SENGINES_BASE_IDLE.size());
                is_moving = false;
            }
        }
        cam.position.set(sprite.getX() + sprite.getOriginX(), sprite.getY() + sprite.getOriginY(), 0);
    }




    private void weapon_draw(SpriteBatch batch) {
        TextureRegion frame;
        Sprite frame_sprite;

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            weapon_anim_time += Gdx.graphics.getDeltaTime();
            frame = this.weapon_animation.getKeyFrame(weapon_anim_time, true);
        }
        else {
            frame = this.weapon_animation.getKeyFrame(0f, true);
        }
        frame_sprite = new Sprite(frame);
        frame_sprite.setOriginCenter();
        frame_sprite.setPosition(sprite.getX(), sprite.getY());
        frame_sprite.setRotation(sprite.getRotation());
        frame_sprite.draw(batch);
    }


    private void engine_draw(SpriteBatch batch) {
        engine_anim_time += Gdx.graphics.getDeltaTime();
        TextureRegion frame = this.engine_animation.getKeyFrame(engine_anim_time, true);
        Sprite frame_sprite = new Sprite(frame);
        frame_sprite.setOriginCenter();
        frame_sprite.setPosition(sprite.getX(), sprite.getY());
        frame_sprite.setRotation(sprite.getRotation());
        frame_sprite.draw(batch);
    }




    public boolean isInstantiated() {
        return this.is_instantiated;
    }


    public boolean is_moving() {
        return Gdx.input.isKeyPressed(Input.Keys.W);
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


        // load weapon animation textures
        paths = new String[SWeapons.values().length];
        for (int i = 0; i < paths.length; ++i) {
            paths[i] = SWeapons.values()[i].get();
        }
        // this.atlas_idle = new TextureAtlas(Gdx.files.internal("animations\\ANIM_ENGINE_BASE_IDLE.atlas"));
        base_load_textures(manager, paths);
    }


    private void cache_engine(Texture sheet, int size) {
        TextureRegion[][] temp = TextureRegion.split(sheet, sheet.getWidth() / size,
                sheet.getHeight());

        this.engine_frames = new TextureRegion[size];
        int index = 0;
        for (int i = 0; i < 1; ++i) {
            for (int j = 0; j < size; ++j) {
                this.engine_frames[index] = temp[i][j];
                ++index;
            }
        }
        this.engine_animation = new Animation<TextureRegion>(0.08f, this.engine_frames);
        this.engine_anim_time = 0;
    }

    private void cache_weapon(Texture sheet, int size) {
        TextureRegion[][] temp = TextureRegion.split(sheet, sheet.getWidth() / size,
                sheet.getHeight());

        this.engine_frames = new TextureRegion[size];
        int index = 0;
        for (int i = 0; i < 1; ++i) {
            for (int j = 0; j < size; ++j) {
                this.engine_frames[index] = temp[i][j];
                ++index;
            }
        }
        this.weapon_animation = new Animation<TextureRegion>(0.03f, this.engine_frames);
        this.weapon_anim_time = 0;
    }






}
