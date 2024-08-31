package com.mygdx.game.sprites;
// Assets root: C:\Users\desmo\spaceraiders\spacer\assets

import com.badlogic.gdx.graphics.Texture;

public enum SMainShip {


    SMAINSHIP_DAMAGED("Main Ship - Base - Damaged.png"),
    SMAINSHIP_FULL("Main Ship - Base - Full health.png"),
    SMAINSHIP_SLIGHT("Main Ship - Base - Slight damage.png"),
    SMAINSHIP_VERY("Main Ship - Base - Very damaged.png");

    private final String path;

    SMainShip(String filename) {
        String root = String.format("%s/Main Ship - Bases/PNGs", System.getProperty("user.dir"));
        this.path = String.format("%s/%s",root, filename);
    }

    public String get() {
        return this.path;
    }


}
