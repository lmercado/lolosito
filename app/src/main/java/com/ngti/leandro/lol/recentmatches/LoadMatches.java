package com.ngti.leandro.lol.recentmatches;

import android.os.AsyncTask;

import com.ngti.leandro.lol.model.RequestInterface;
import com.ngti.leandro.lol.model.RetrofitClientInstance;
import com.ngti.leandro.lol.model.match.MatchContainer;
import com.ngti.leandro.lol.model.matchlist.AllMatches;
import com.ngti.leandro.lol.model.matchlist.AllMatchesResponse;
import com.ngti.leandro.lol.model.summoner.SummonerData;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import timber.log.Timber;

class LoadMatches extends AsyncTask<String, Void, Matches> {

    private RecentMatchesActivity recentMatchesActivity;
    private int loadSummonerDataResponseCode;
    private int loadMatchListResponseCode;
    private int loadMatchByIdResponseCode;

    LoadMatches(RecentMatchesActivity recentMatchesActivity) {
        this.recentMatchesActivity = recentMatchesActivity;
    }

    @Override
    protected Matches doInBackground(String... params) {
        long accountId = 0;

        RequestInterface service = RetrofitClientInstance.getInstance(params[0]).create(RequestInterface.class);

        Call<SummonerData> summonerDataCall = service.getSummonerByName(params[1]);
        try {
            Response<SummonerData> responseSummonerData = summonerDataCall.execute();
            loadSummonerDataResponseCode = responseSummonerData.code();

            Timber.i("Response code summoner data call: %s", loadSummonerDataResponseCode);
            if (loadSummonerDataResponseCode == HttpURLConnection.HTTP_OK) {
                accountId = responseSummonerData.body().getAccountId();
            } else {
                Timber.e("API Error: %s", responseSummonerData.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Call<AllMatchesResponse.JSONResponse> loadMatchList = service.loadMatchList(accountId);
        ArrayList<AllMatches> allMatches = null;
        try {
            Response<AllMatchesResponse.JSONResponse> responseMatchList = loadMatchList.execute();
            loadMatchListResponseCode = responseMatchList.code();
            Timber.i("Response code match list call: %s", loadMatchListResponseCode);
            if (loadMatchListResponseCode == HttpURLConnection.HTTP_OK) {
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
                Response<MatchContainer> loadMatchByIdResponse = loadMatchById.execute();
                loadMatchByIdResponseCode = loadMatchByIdResponse.code();
                Timber.i("Response code match by id call: %s", loadMatchByIdResponseCode);
                MatchContainer match = loadMatchByIdResponse.body();
                if (loadMatchByIdResponseCode == HttpURLConnection.HTTP_OK) {
                    if (match != null) {
                        matchesInfo.put(match.getGameId(), match);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Matches(allMatches, matchesInfo);
    }

    @Override
    protected void onPostExecute(Matches matches) {
        recentMatchesActivity.matchesLoaded(matches, loadMatchByIdResponseCode, loadMatchListResponseCode, loadSummonerDataResponseCode);
    }

}
