package com.ngti.leandro.lol.recentmatches;

import android.os.AsyncTask;

import com.ngti.leandro.lol.model.RequestInterface;
import com.ngti.leandro.lol.model.RetrofitClientInstance;
import com.ngti.leandro.lol.model.match.MatchContainer;
import com.ngti.leandro.lol.model.matchlist.AllMatches;
import com.ngti.leandro.lol.model.matchlist.AllMatchesResponse;
import com.ngti.leandro.lol.model.summoner.SummonerData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import timber.log.Timber;

class LoadMatches extends AsyncTask<String, Void, Matches> {

    private RecentMatchesActivity recentMatchesActivity;


    LoadMatches(RecentMatchesActivity recentMatchesActivity) {
        this.recentMatchesActivity = recentMatchesActivity;
    }

    @Override
    protected Matches doInBackground(String... params) {

        long accountId = 0;

        RequestInterface service = RetrofitClientInstance.getRetrofitInstance(params[0]).create(RequestInterface.class);

        int loadSummonerDataResponseCode;
        Call<SummonerData> summonerDataCall = service.getSummonerByName(params[1]);
        try {
            Response<SummonerData> responseSummonerData = summonerDataCall.execute();
            loadSummonerDataResponseCode = responseSummonerData.code();
            if (responseSummonerData.code() == 200) {
                accountId = responseSummonerData.body().getAccountId();
            } else {
                Timber.e("API Error: %s", responseSummonerData.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int loadMatchListResponseCode;
        Call<AllMatchesResponse.JSONResponse> loadMatchList = service.loadMatchList(accountId);
        ArrayList<AllMatches> allMatches = null;
        try {
            Response<AllMatchesResponse.JSONResponse> responseMatchList = loadMatchList.execute();
            if (responseMatchList.code() == 200) {
                AllMatchesResponse.JSONResponse jsonResponse = responseMatchList.body();
                allMatches = new ArrayList<>(Arrays.asList(jsonResponse.getMatches()));
            } else {
                Timber.e("API Error: %s", responseMatchList.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        int matchCount = 0;
        if (allMatches != null) {
            matchCount = allMatches.size();
        }

        Map<Long, MatchContainer> matchesInfo = new HashMap<>(matchCount);
        for (int i = 0; i < matchCount; i++) {
            long gameId = allMatches.get(i).getGameId();
            Call<MatchContainer> loadMatchById = service.loadMatchById(gameId);
            try {
                Response<MatchContainer> response = loadMatchById.execute();
                MatchContainer match = response.body();
                if (match != null) {
                    matchesInfo.put(match.getGameId(), match);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Matches(allMatches, matchesInfo);
    }

    @Override
    protected void onPostExecute(Matches matches) {
        recentMatchesActivity.matchesLoaded(matches);
    }

}
