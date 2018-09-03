package com.ngti.leandro.lol.fullmatchinfo;

import android.os.AsyncTask;

import com.ngti.leandro.lol.model.RequestInterface;
import com.ngti.leandro.lol.model.RetrofitClientInstance;
import com.ngti.leandro.lol.model.champions.Champion;
import com.ngti.leandro.lol.model.champions.ChampionsContainer;
import com.ngti.leandro.lol.model.match.MatchContainer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

import static com.ngti.leandro.lol.splash.GetGameVersion.DDRAGON_CHAMPION_VERSION;

public class LoadMatchAndChampionsFullMatchInfo extends AsyncTask<String, Integer, FullMatchInfoAndChampions> {

    private FullMatchInfoActivity fullMatchInfoActivity;
    private int loadChampionsResponseCode;
    private int loadMatchByIdResponseCode;

    private static Retrofit retrofit;
    private static String BASE_URL = "https://ddragon.leagueoflegends.com";

    LoadMatchAndChampionsFullMatchInfo(FullMatchInfoActivity fullMatchInfoActivity) {
        this.fullMatchInfoActivity = fullMatchInfoActivity;
    }

    @Override
    protected FullMatchInfoAndChampions doInBackground(String... params) {


        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        RequestInterface service = retrofit.create(RequestInterface.class);

        Call<ChampionsContainer> loadChampionsCall = service.getChampionList(DDRAGON_CHAMPION_VERSION);

        Map<Integer, Champion> allChampions = null;
        try {
            Response<ChampionsContainer> responseChampions = loadChampionsCall.execute();
            loadChampionsResponseCode = responseChampions.code();
            ChampionsContainer championsContainer = responseChampions.body();
            Timber.d("Response code Champions call: %s", loadChampionsResponseCode);
            if (loadChampionsResponseCode == HttpURLConnection.HTTP_OK) {
                if (championsContainer != null) {
                    allChampions = championsContainer.getChampionsById();
                }
            } else {
                Timber.e("API Error: %s", loadChampionsResponseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        RequestInterface service2 = RetrofitClientInstance.getInstance(params[0]).create(RequestInterface.class);

        Call<MatchContainer> loadMatchById = service2.loadMatchById(Long.parseLong(params[1]));

        MatchContainer match = null;
        try {
            Response<MatchContainer> loadMatchByIdResponse = loadMatchById.execute();
            loadMatchByIdResponseCode = loadMatchByIdResponse.code();
            Timber.i("Response code match by id call: %s", loadMatchByIdResponseCode);

            if (loadMatchByIdResponseCode == HttpURLConnection.HTTP_OK) {
                match = loadMatchByIdResponse.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new FullMatchInfoAndChampions(match, allChampions);
    }

    @Override
    protected void onPostExecute(FullMatchInfoAndChampions fullMatchInfoAndChampions) {
        fullMatchInfoActivity.fullMatchInfoLoaded(fullMatchInfoAndChampions, loadMatchByIdResponseCode, loadChampionsResponseCode);
    }
}
