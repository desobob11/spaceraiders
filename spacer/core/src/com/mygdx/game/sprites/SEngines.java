package com.mygdx.game.sprites;

public enum SEngines {
    SENGINES_BASE_IDLE("Main Ship - Engines - Base Engine - Idle.png", 3),
    SENGINES_BASE_POWERING("Main Ship - Engines - Base Engine - Powering.png", 4);

    private final String path;
    private final int size;

    SEngines(String filename, int size) {
        String root = String.format("%s\\Main Ship - Engine Effects\\PNGs", System.getProperty("user.dir"));
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
