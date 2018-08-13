package com.ngti.leandro.lol.summoner.search;

import com.google.gson.annotations.SerializedName;

public class SummonerData {

    @SerializedName("accountId")
    public long accountId;

    public long getAccountId() {
        return accountId;
    }
}
