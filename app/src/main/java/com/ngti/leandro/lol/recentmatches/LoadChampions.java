package com.ngti.leandro.lol.recentmatches;

import android.os.AsyncTask;

import com.ngti.leandro.lol.model.RequestInterface;
import com.ngti.leandro.lol.model.champions.Champion;
import com.ngti.leandro.lol.model.champions.ChampionsContainer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

import static com.ngti.leandro.lol.splash.GetGameVersion.DDRAGON_CHAMPION_VERSION;

public class LoadChampions extends AsyncTask<String, Integer, Champions> {

    private RecentMatchesActivity recentMatchesActivity;
    private int loadChampionsResponseCode;

    private static Retrofit retrofit;
    private static String BASE_URL = "https://ddragon.leagueoflegends.com";

    LoadChampions(RecentMatchesActivity recentMatchesActivity) {
        this.recentMatchesActivity = recentMatchesActivity;
    }

    @Override
    protected Champions doInBackground(String... params) {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        RequestInterface service = retrofit.create(RequestInterface.class);

        Call<ChampionsContainer> loadChampionsCall = service.getChampionList(DDRAGON_CHAMPION_VERSION);

//        System.out.println(loadChampionsCall.request());

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
        return new Champions(allChampions);
    }

    @Override
    protected void onPostExecute(Champions champions) {
        recentMatchesActivity.championsLoaded(champions, loadChampionsResponseCode);
    }
}
