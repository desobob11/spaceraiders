package com.mygdx.game.sprites;

public enum SEngines {
    SENGINES_BASE_IDLE("Main Ship - Engines - Base Engine - Idle.png");

    private final String path;

    SEngines(String filename) {
        String root = String.format("%s\\Main Ship - Engine Effects\\PNGs", System.getProperty("user.dir"));
        this.path = String.format("%s\\%s",root, filename);
    }

    public String get() {
        return this.path;
    }

}
