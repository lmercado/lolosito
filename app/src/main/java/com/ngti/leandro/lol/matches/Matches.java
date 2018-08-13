package com.ngti.leandro.lol.matches;

import com.google.gson.annotations.SerializedName;

public class Matches {
    @SerializedName("lane")
    private String lane;

    @SerializedName("gameId")
    private long gameId;

    @SerializedName("champion")
    private Integer champion;

    @SerializedName("platformId")
    private String platformId;

    @SerializedName("timepstamp")
    private long timestamp;

    @SerializedName("queue")
    private Integer queue;

    @SerializedName("role")
    private String role;

    @SerializedName("season")
    private Integer season;


    public String getLane() {
        return lane;
    }

    public long getGameId() {
        return gameId;
    }

    public Integer getChampion() {
        return champion;
    }

    public String getPlatformId() {
        return platformId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Integer getQueue() {
        return queue;
    }

    public String getRole() {
        return role;
    }

    public Integer getSeason() {
        return season;
    }
}
