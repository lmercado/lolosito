package com.ngti.leandro.lol.recentmatches;

import android.os.AsyncTask;

import com.ngti.leandro.lol.model.RequestInterface;
import com.ngti.leandro.lol.model.RetrofitClientInstance;
import com.ngti.leandro.lol.model.champions.Champion;
import com.ngti.leandro.lol.model.champions.ChampionsContainer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import timber.log.Timber;

public class LoadChampions extends AsyncTask<String, Integer, Champions> {

    private RecentMatchesActivity recentMatchesActivity;
    private int loadChampionsResponseCode;

    LoadChampions(RecentMatchesActivity recentMatchesActivity) {
        this.recentMatchesActivity = recentMatchesActivity;
    }

    @Override
    protected Champions doInBackground(String... params) {
        RequestInterface service = RetrofitClientInstance.getInstance(params[0]).create(RequestInterface.class);

        Call<ChampionsContainer> loadChampionsCall = service.getChampionList();

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
