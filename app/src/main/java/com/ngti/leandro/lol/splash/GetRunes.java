package com.ngti.leandro.lol.splash;

import android.os.AsyncTask;

import com.ngti.leandro.lol.model.RequestInterface;
import com.ngti.leandro.lol.model.ddragon.RunesContainer;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

import static com.ngti.leandro.lol.splash.GetGameVersion.DDRAGON_RUNE_VERSION;

public class GetRunes extends AsyncTask {

    private static String BASE_URL = "http://ddragon.leagueoflegends.com";
    public static List<RunesContainer> allRunes = null;
    private SplashActivity splashActivity;
    private static Retrofit retrofit;

    public GetRunes(SplashActivity splashActivity) {
        this.splashActivity = splashActivity;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        RequestInterface service = retrofit.create(RequestInterface.class);

        Call<List<RunesContainer>> getRunes = service.getRunes(DDRAGON_RUNE_VERSION);

        try {
            Response<List<RunesContainer>> getRunesResponse = getRunes.execute();
            if (getRunesResponse.code() == 200) {
                allRunes = getRunesResponse.body();
            } else {
                Timber.e(String.valueOf("Get runes error: " + getRunesResponse.code()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allRunes;
    }
}
