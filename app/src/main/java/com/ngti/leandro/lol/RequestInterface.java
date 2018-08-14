package com.ngti.leandro.lol;

import com.ngti.leandro.lol.champions.ChampionsContainer;
import com.ngti.leandro.lol.allMatches.AllMatchesResponse;
import com.ngti.leandro.lol.summoner.search.SummonerData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RequestInterface {

    String MATCH_LIST_BY_ACCOUNT_ID = "/lol/match/v3/matchlists/by-account/{accountId}";
    String CHAMPIONS_LIST = "/lol/static-data/v3/champions";

    String AUTHORIZATION = "X-Riot-Token: RGAPI-ce3cff5e-56cf-495d-9022-2e8fed6335cd";
    String SUMMONER_BY_NAME = "/lol/summoner/v3/summoners/by-name/{summonerName}";


    @Headers(AUTHORIZATION)
    @GET(MATCH_LIST_BY_ACCOUNT_ID)
    Call<AllMatchesResponse.JSONResponse> loadMatchList(@Path("accountId")long accountId);

    @Headers(AUTHORIZATION)
    @GET(CHAMPIONS_LIST)
    Call<ChampionsContainer> getChampionList();

    @Headers(AUTHORIZATION)
    @GET(SUMMONER_BY_NAME)
    Call<SummonerData> getSummonerByName(@Path("summonerName")String summonerName);
}
