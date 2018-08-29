package com.ngti.leandro.lol.fullmatchinfo;

import android.os.AsyncTask;

import com.ngti.leandro.lol.model.RequestInterface;
import com.ngti.leandro.lol.model.RetrofitClientInstance;
import com.ngti.leandro.lol.model.match.MatchContainer;
import com.ngti.leandro.lol.recentmatches.Matches;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import timber.log.Timber;

class LoadSingleMatch extends AsyncTask<String, Void, Map> {

    private FullMatchInfoActivity fullMatchInfoActivity;
    private int loadSummonerDataResponseCode;
    private int loadMatchListResponseCode;
    private int loadMatchByIdResponseCode;

    LoadSingleMatch(FullMatchInfoActivity fullMatchInfoActivity) {
        this.fullMatchInfoActivity = fullMatchInfoActivity;
    }

    @Override
    protected Map doInBackground(String... params) {

        RequestInterface service = RetrofitClientInstance.getInstance(params[0]).create(RequestInterface.class);


        Map<Long, MatchContainer> matchInfo = new HashMap<>(1);
        Call<MatchContainer> loadMatchById = service.loadMatchById(Long.parseLong(params[1]));

        try {
            Response<MatchContainer> loadMatchByIdResponse = loadMatchById.execute();
            loadMatchByIdResponseCode = loadMatchByIdResponse.code();
            Timber.i("Response code match by id call: %s", loadMatchByIdResponseCode);
            MatchContainer match = loadMatchByIdResponse.body();
            if (loadMatchByIdResponseCode == HttpURLConnection.HTTP_OK) {
                if (match != null) {
                    matchInfo.put(match.getGameId(), match);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchInfo;
    }

    @Override
    protected void onPostExecute(Map match) {
        fullMatchInfoActivity.matchLoaded(match, loadMatchByIdResponseCode);
    }

}

