package com.ngti.leandro.lol.Match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {

    @Expose
    @SerializedName("summonerName")
    private String summonerName;

    @Expose
    @SerializedName("profileIcon")
    private int profileIcon;

    @Expose
    @SerializedName("summonerId")
    private int summonerId;

    @Expose
    @SerializedName("accountId")
    private long accountId;


    public String getSummonerName() {
        return summonerName;
    }

    public int getProfileIcon() {
        return profileIcon;
    }

    public int getSummonerId() {
        return summonerId;
    }

    public long getAccountId() {
        return accountId;
    }

    @Override
    public String toString() {
        return "Player{" +
                "summonerName='" + summonerName + '\'' +
                ", profileIcon=" + profileIcon +
                ", summonerId=" + summonerId +
                ", accountId=" + accountId +
                '}';
    }
}
