package com.mygdx.game.levels;

public enum LBackgrounds {

    LBACKGROUNDS_LVL1_LAYER1("lvl1\\lvl1_stars.png"),
    LBACKGROUNDS_LVL1_LAYER2("lvl1\\lvl1_nebula.png"),
    LBACKGROUNDS_LVL1_LAYER3("lvl1\\lvl1_planets.png"),
    LBACKGROUNDS_LVL1_LAYER4("lvl1\\lvl1_dust.png");
    private final String path;

    LBackgrounds(String filename) {
        String root = String.format("%s\\backgrounds", System.getProperty("user.dir"));
        this.path = String.format("%s\\%s",root, filename);
    }

    public String get() {
        return this.path;
    }







}
