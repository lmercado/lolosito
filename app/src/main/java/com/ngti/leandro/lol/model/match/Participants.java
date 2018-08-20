package com.ngti.leandro.lol.model.match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Participants {


    @Expose
    @SerializedName("participantId")
    private int participantId;

    @Expose
    @SerializedName("spell1Id")
    private int spell1Id;

    @Expose
    @SerializedName("spell2Id")
    private int spell2Id;

    @Expose
    @SerializedName("teamId")
    private int teamId;

    @Expose
    @SerializedName("championId")
    private int championId;

    @Expose
    @SerializedName("stats")
    private Stats stats;


    public int getParticipantId() {
        return participantId;
    }

    public int getSpell1Id() {
        return spell1Id;
    }

    public int getSpell2Id() {
        return spell2Id;
    }

    public int getTeamId() {
        return teamId;
    }

    public int getChampionId() {
        return championId;
    }

    public Stats getStats() {
        return stats;
    }

    @Override
    public String toString() {
        return "Participants{" +
                "participantId=" + participantId +
                ", spell1Id=" + spell1Id +
                ", spell2Id=" + spell2Id +
                ", teamId=" + teamId +
                ", championId=" + championId +
                ", stats=" + stats +
                '}';
    }
}
