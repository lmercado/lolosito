package com.ngti.leandro.lol.recent.matches;

import android.os.AsyncTask;
import android.util.Log;

import com.ngti.leandro.lol.Match.MatchGeneral;
import com.ngti.leandro.lol.RequestInterface;
import com.ngti.leandro.lol.RetrofitClientInstance;
import com.ngti.leandro.lol.champions.Champions;
import com.ngti.leandro.lol.champions.ChampionsContainer;
import com.ngti.leandro.lol.allMatches.AllMatches;
import com.ngti.leandro.lol.allMatches.AllMatchesResponse;
import com.ngti.leandro.lol.summoner.search.SummonerData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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





//        sleep();





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






//        sleep();






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






//        sleep();









        Call<AllMatchesResponse.JSONResponse> loadMatchList = service.loadMatchList(accountId);

        ArrayList<AllMatches> allMatches = null;
        try {
            Response<AllMatchesResponse.JSONResponse> response = loadMatchList.execute();

            if (response.code() == 200) {
                AllMatchesResponse.JSONResponse jsonResponse = response.body();
                allMatches = new ArrayList<>(Arrays.asList(jsonResponse.getMatches()));
            } else {
                Log.e("API", "API ERROR");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }




        System.out.println(allMatches.size());
        for (int i = 0; i < allMatches.size(); i++) {
            long gameId = allMatches.get(i).getGameId();
            System.out.println(allMatches.get(i).getGameId());

            Call<MatchGeneral> loadMatchById = service.loadMatchById(gameId);

            try {
                Response<MatchGeneral> response = loadMatchById.execute();
                System.out.println(response.toString());
                System.out.println(response.body().toString());

            } catch (IOException e) {
                e.printStackTrace();
            }

        }












        return new ChampionsAndMatches(allChampions, allMatches);













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
