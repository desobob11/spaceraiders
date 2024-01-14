package com.mygdx.game.sprites;

public enum SBullets {

    SBULLETS_BASE("Fx_01.png", 3);


    private final String path;
    private final int size;

    SBullets(String filename, int size) {
        String root = String.format("%s\\Effects", System.getProperty("user.dir"));
        this.path = String.format("%s\\%s",root, filename);
        this.size = size;
    }

    public String get() {
        return this.path;
    }

    public int size() {
        return this.size;
    }




}
