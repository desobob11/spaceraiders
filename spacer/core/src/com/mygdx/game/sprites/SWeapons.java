package com.mygdx.game.sprites;

public enum SWeapons {

    SWEAPONS_AUTO("Main Ship - Weapons - Auto Cannon.png",7);


    private final String path;
    private final int size;

    SWeapons(String filename, int size) {
        String root = String.format("%s/Main Ship - Weapons/PNGs", System.getProperty("user.dir"));
        this.path = String.format("%s/%s",root, filename);
        this.size = size;
    }

    public String get() {
        return this.path;
    }

    public int size() {
        return this.size;
    }

}
