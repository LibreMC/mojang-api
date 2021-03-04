package io.github.libremc.mojang.objects;

public class GameProfile {
    private String id;
    private String name;

    public GameProfile(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
