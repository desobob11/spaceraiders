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

public class Background {
    private String l1_s, l2_s, l3_s, l4_s;
    private Vector2 player_dir;
    private Sprite sp1, sp2, sp3, sp4;

    float off1 = 0.1f;
    float off2 = 0.4f;
    float off3 = 0.8f;
    float off4 = 1f;
    boolean is_instantiated;


    public Background(String l1, String l2, String l3, String l4) {
       // manager.load(l1, Texture.class);
       // manager.load(l2, Texture.class);
       // manager.load(l3, Texture.class);
       // manager.load(l4, Texture.class);

        this.l1_s = l1;
        this.l2_s = l2;
        this.l3_s = l3;
        this.l4_s = l4;
    }

    public void instantiate(AssetManager manager, Player player) {
        this.player_dir = player.get_direction();
        sp1 = new Sprite((Texture) manager.get(l1_s));
        sp2 = new Sprite((Texture) manager.get(l2_s));
        sp3 = new Sprite((Texture) manager.get(l3_s));
        sp4 = new Sprite((Texture) manager.get(l4_s));

        set_sprites();

    }


    public void draw_back(SpriteBatch batch, Player player, AssetManager manager) {
        if (!is_instantiated) {
            instantiate(manager, player);
            this.is_instantiated = true;
        }
        //batch.draw((Texture) manager.get(l1_s), 0, 0);
       // batch.draw((Texture) manager.get(l2_s), 0, 0);
        //batch.draw((Texture) manager.get(l3_s), 0, 0);
        //this.player_dir = player.get_direction();
        Vector2 dir = player.get_direction();
        if (player.is_moving()) {
            sp1.translate(-(off1 * dir.x), -(off1 * dir.y));
            sp2.translate(-(off2 * dir.x), -(off2 * dir.y));
            sp3.translate(-(off3 * dir.x), -(off3 * dir.y));
        }



        sp1.draw(batch);
        sp2.draw(batch);
        sp3.draw(batch);



    }

    public void draw_front(SpriteBatch batch, Player player) {
        //batch.draw((Texture) manager.get(l4_s), 0, 0);
        Vector2 dir = player.get_direction();

        if (player.is_moving()) {
            sp4.translate(-(off4 * dir.x), -(off4 * dir.y));
        }
        sp4.draw(batch);
    }


    public void set_player_dir(Vector2 vec) {
        this.player_dir = vec;
    }



    private void set_sprites() {
        sp1.setPosition(0, 0);
        sp2.setPosition(0, 0);
        sp3.setPosition(0, 0);
        sp4.setPosition(0, 0);
    }








}
