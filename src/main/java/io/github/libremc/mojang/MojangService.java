package io.github.libremc.mojang;

import io.github.libremc.mojang.objects.GameProfile;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MojangService {
    @GET("/users/profiles/minecraft/{username}")
    Call<GameProfile> getProfile(@Path("username") String username);
}
