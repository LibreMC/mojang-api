package io.github.libremc.mojang;

import io.github.libremc.mojang.objects.GameProfile;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MojangAPI {
    /**
     * The base url for *MOST* of the Mojang API, exclusions include things such as blocked servers and server status'.
     */
    public static final String API_BASE = "https://api.mojang.com";

    private static Retrofit retrofit;
    private static MojangService service;
    private static OkHttpClient httpClient;

    /**
     * Retrieve a list of Mojang's blocked servers (hashed in md5)
     * @return
     * @throws IOException
     */
    public static List<String> getBlockedServers() throws IOException {
        Request request = new Request.Builder()
                .url("https://sessionserver.mojang.com/blockedservers")
                .build();

        Response response = httpClient.newCall(request).execute();
        ResponseBody body = response.body();
        if (body != null) {
            return Arrays.stream(body.string().split("(\n)|(\r\n)")).collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    /**
     * Resolve <code>username</code> to a Mojang GameProfile.
     * @param username
     * @return
     * @throws IOException
     */
    public static GameProfile getGameProfile(String username) throws IOException {
        return service.getProfile(username).execute().body();
    }

    static {
        httpClient = new OkHttpClient.Builder().build(); // Init OkHttpClient
        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build(); // Init Retrofit
        service = retrofit.create(MojangService.class); // Init MojangService
    }
}
