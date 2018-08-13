package com.ngti.leandro.lol.recent.matches;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ngti.leandro.lol.RequestInterface;
import com.ngti.leandro.lol.RetrofitClientInstance;
import com.ngti.leandro.lol.champions.Champions;
import com.ngti.leandro.lol.champions.ChampionsContainer;
import com.ngti.leandro.lol.matches.Matches;
import com.ngti.leandro.lol.matches.MatchesResponse;
import com.ngti.leandro.lol.summoner.search.SummonerData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

class LoadMatchAndChampions extends AsyncTask<String, Void, ChampionsAndMatches> {

    private RecentMatchesActivity recentMatchesActivity;


    public LoadMatchAndChampions(RecentMatchesActivity recentMatchesActivity) {
        this.recentMatchesActivity = recentMatchesActivity;
    }

    @Override
    protected ChampionsAndMatches doInBackground(String... params) {

        long accountId = 0;

        RequestInterface service = RetrofitClientInstance.getRetrofitInstance(params[0]).create(RequestInterface.class);
        sleep();
        Call<ChampionsContainer> loadChampionsCall = service.getChampionList();

        Map<Integer, Champions> allChampions = null;
        try {
            Response<ChampionsContainer> responseChampions = loadChampionsCall.execute();
            ChampionsContainer champions = responseChampions.body();

            if (responseChampions.code() == 200) {
                if (champions != null) {
                    System.out.println(responseChampions.code());
                    allChampions = champions.getChampionsById();

                }
            } else {
                Log.e("API", "API ERROR");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        sleep();

        Call<SummonerData> summonerDataCall = service.getSummonerByName(params[1]);

        try {
            Response<SummonerData> responseSummonerData = summonerDataCall.execute();

            if (responseSummonerData.code() == 200) {
                accountId = responseSummonerData.body().getAccountId();
            } else {
                Log.e("API", "API ERROR");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        sleep();

        Call<MatchesResponse.JSONResponse> loadMatchCall = service.loadMatchList(accountId);

        ArrayList<Matches> matches = null;
        try {
            Response<MatchesResponse.JSONResponse> response = loadMatchCall.execute();

            if (response.code() == 200) {
                MatchesResponse.JSONResponse jsonResponse = response.body();
                matches = new ArrayList<>(Arrays.asList(jsonResponse.getMatches()));
            } else {
                Log.e("API", "API ERROR");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ChampionsAndMatches(allChampions, matches);
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(ChampionsAndMatches championsAndMatches) {
       recentMatchesActivity.matchesAndChampionsLoaded(championsAndMatches);
    }


}
