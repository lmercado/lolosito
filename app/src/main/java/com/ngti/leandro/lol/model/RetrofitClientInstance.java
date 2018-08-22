package com.ngti.leandro.lol.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static String BASE_URL;
    private static Retrofit retrofit;

    public static synchronized void resetInstance() {
        retrofit = null;
    }

    public static synchronized Retrofit getInstance(String server) {

        switch (server) {
            case "BR":
                BASE_URL = "https://br1.api.riotgames.com";
                break;
            case "EUNE":
                BASE_URL = "https://eun1.api.riotgames.com";
                break;
            case "EUW":
                BASE_URL = "https://euw1.api.riotgames.com";
                break;
            case "LAN":
                BASE_URL = "https://la1.api.riotgames.com";
                break;
            case "LAS":
                BASE_URL = "https://la2.api.riotgames.com";
                break;
            case "NA":
                BASE_URL = "https://na1.api.riotgames.com";
                break;
            case "OCE":
                BASE_URL = "https://oc1.api.riotgames.com";
                break;
            case "RU":
                BASE_URL = "https://ru.api.riotgames.com";
                break;
            case "TR":
                BASE_URL = "https://tr1.api.riotgames.com";
                break;
            case "JP":
                BASE_URL = "https://jp1.api.riotgames.com";
                break;
        }

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
