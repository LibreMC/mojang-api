package io.github.libremc.mojang;

import io.github.libremc.mojang.objects.GameProfile;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestJUnit {
    @Test
    public void listBlockedServers() {
        try {
            System.out.println("Blocked Servers");
            MojangAPI.getBlockedServers().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void resolveProfile() {
        try {
            System.out.println("User Resolver");
            GameProfile profile = MojangAPI.getGameProfile("Alerithe");
            System.out.println("UUID: " + profile.getId());
            System.out.println("Name: " + profile.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
