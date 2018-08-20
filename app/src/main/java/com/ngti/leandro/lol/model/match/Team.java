package com.ngti.leandro.lol.model.match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Team {

    @Expose
    @SerializedName("teamId")
    private int teamId;

    @Expose
    @SerializedName("win")
    private String win;

    @Expose
    @SerializedName("dragonKills")
    private int dragonKills;

    @Expose
    @SerializedName("baronKills")
    private int baronKills;

    @Expose
    @SerializedName("towerKills")
    private int towerKills;

    @Expose
    @SerializedName("inhibitorKills")
    private int inhibitorKills;

    public int getTeamId() {
        return teamId;
    }

    public String getWin() {
        return win;
    }

    public int getDragonKills() {
        return dragonKills;
    }

    public int getBaronKills() {
        return baronKills;
    }

    public int getTowerKills() {
        return towerKills;
    }

    public int getInhibitorKills() {
        return inhibitorKills;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", win='" + win + '\'' +
                ", dragonKills=" + dragonKills +
                ", baronKills=" + baronKills +
                ", towerKills=" + towerKills +
                ", inhibitorKills=" + inhibitorKills +
                '}';
    }
}
