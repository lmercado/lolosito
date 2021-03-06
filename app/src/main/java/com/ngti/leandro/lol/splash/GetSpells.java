package com.ngti.leandro.lol.splash;

import android.os.AsyncTask;

import com.ngti.leandro.lol.model.RequestInterface;
import com.ngti.leandro.lol.model.ddragon.Spell;
import com.ngti.leandro.lol.model.ddragon.SpellsContainer;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

import static com.ngti.leandro.lol.splash.GetGameVersion.DDRAGON_SUMMONER_VERSION;

public class GetSpells extends AsyncTask {

    private static String BASE_URL = "http://ddragon.leagueoflegends.com";
    public static Map<Integer, Spell> allSpells = null;
    private SplashActivity splashActivity;
    private static Retrofit retrofit;

    public GetSpells(SplashActivity splashActivity) {
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

        Call<SpellsContainer> getSummonerSpells = service.getSummonerSpells(DDRAGON_SUMMONER_VERSION);

        try {
            Response<SpellsContainer> getSummonerSpellResponse = getSummonerSpells.execute();
            if (getSummonerSpellResponse.code() == 200) {
                SpellsContainer spells = getSummonerSpellResponse.body();
                allSpells = spells.getSpellById();
            } else {
                Timber.e(String.valueOf("Get summoner spells error: " + getSummonerSpellResponse.code()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allSpells;
    }
}
