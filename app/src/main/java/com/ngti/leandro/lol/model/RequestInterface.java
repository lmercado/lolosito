package com.ngti.leandro.lol.model;

import com.ngti.leandro.lol.model.champions.ChampionsContainer;
import com.ngti.leandro.lol.model.ddragon.SpellsContainer;
import com.ngti.leandro.lol.model.ddragon.VersionsContainer;
import com.ngti.leandro.lol.model.match.MatchContainer;
import com.ngti.leandro.lol.model.ddragon.RunesContainer;
import com.ngti.leandro.lol.model.matchlist.AllMatchesResponse;
import com.ngti.leandro.lol.model.summoner.SummonerData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RequestInterface {

    String AUTHORIZATION = "X-Riot-Token: RGAPI-b919ac0e-08dd-40f1-803b-6685fa1ed657";

    String MATCH_LIST_BY_ACCOUNT_ID = "/lol/match/v3/matchlists/by-account/{accountId}?endIndex=1";
    String CHAMPIONS_LIST = "/cdn/{version}/data/en_US/champion.json";
    String DDRAGON_API_VERSIONS = "/realms/na.json";
    String SUMMONER_BY_NAME = "/lol/summoner/v3/summoners/by-name/{summonerName}";
    String MATCH_BY_ID = "/lol/match/v3/matches/{matchId}";
    String DDRAGON_SUMMONER_SPELLS = "/cdn/{version}/data/en_US/summoner.json";
    String DDRAGON_RUNES = "/cdn/{version}/data/en_US/runesReforged.json";

    @Headers(AUTHORIZATION)
    @GET(MATCH_LIST_BY_ACCOUNT_ID)
    Call<AllMatchesResponse.JSONResponse> loadMatchList(@Path("accountId") long accountId);

    @Headers(AUTHORIZATION)
    @GET(CHAMPIONS_LIST)
    Call<ChampionsContainer> getChampionList(@Path("version") String version);

    @Headers(AUTHORIZATION)
    @GET(SUMMONER_BY_NAME)
    Call<SummonerData> getSummonerByName(@Path("summonerName") String summonerName);

    @Headers(AUTHORIZATION)
    @GET(MATCH_BY_ID)
    Call<MatchContainer> loadMatchById(@Path("matchId") long matchId);

    @GET(DDRAGON_API_VERSIONS)
    Call<VersionsContainer> getDdragonApiVersions();

    @GET(DDRAGON_SUMMONER_SPELLS)
    Call<SpellsContainer> getSummonerSpells(@Path("version") String version);

    @GET(DDRAGON_RUNES)
    Call<List<RunesContainer>> getRunes(@Path("version") String version);


}