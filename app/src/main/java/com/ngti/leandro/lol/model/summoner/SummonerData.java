package com.ngti.leandro.lol.model.summoner;

import com.google.gson.annotations.SerializedName;

public class SummonerData {

    @SerializedName("accountId")
    public long accountId;

    public long getAccountId() {
        return accountId;
    }
}
