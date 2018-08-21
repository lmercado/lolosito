package com.ngti.leandro.lol.splash;

import android.os.AsyncTask;

import com.ngti.leandro.lol.model.RequestInterface;
import com.ngti.leandro.lol.model.ddragon.VersionsContainer;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetGameVersion extends AsyncTask<Integer, Void, Integer> {

    private SplashActivity splashActivity;
    private static Retrofit retrofit;

    private static String BASE_URL = "https://ddragon.leagueoflegends.com";
    public static String DDRAGON_RUNE_VERSION;
    public static String DDRAGON_ITEM_VERSION;
    public static String DDRAGON_MASTERY_VERSION;
    public static String DDRAGON_SUMMONER_VERSION;
    public static String DDRAGON_CHAMPION_VERSION;
    public static String DDRAGON_PROFILE_ICON_VERSION;
    public static String DDRAGON_MAP_VERSION;
    public static String DDRAGON_LANGUAGE_VERSION;


    public GetGameVersion(SplashActivity splashActivity) {
        this.splashActivity = splashActivity;
    }

    @Override
    protected Integer doInBackground(Integer... params) {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        RequestInterface service = retrofit.create(RequestInterface.class);

        Call<VersionsContainer> getApiVersions = service.getDdragonApiVersions();

        Response<VersionsContainer> responseApiVersions = null;
        try {
            responseApiVersions = getApiVersions.execute();
            DDRAGON_ITEM_VERSION = responseApiVersions.body().allVersions().getItem();
            DDRAGON_RUNE_VERSION = responseApiVersions.body().allVersions().getRune();
            DDRAGON_MASTERY_VERSION = responseApiVersions.body().allVersions().getMastery();
            DDRAGON_SUMMONER_VERSION = responseApiVersions.body().allVersions().getSummoner();
            DDRAGON_CHAMPION_VERSION = responseApiVersions.body().allVersions().getChampion();
            DDRAGON_PROFILE_ICON_VERSION = responseApiVersions.body().allVersions().getProfileicon();
            DDRAGON_MAP_VERSION = responseApiVersions.body().allVersions().getMap();
            DDRAGON_LANGUAGE_VERSION = responseApiVersions.body().allVersions().getLanguage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseApiVersions.code();
    }

    @Override
    protected void onPostExecute(Integer apiResponseCode) {
        splashActivity.apiVersionsLoaded(apiResponseCode);
    }
}
