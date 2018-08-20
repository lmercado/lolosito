package com.ngti.leandro.lol.model.match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class MatchContainer {

    @Expose
    @SerializedName("gameId")
    private long gameId;

    @Expose
    @SerializedName("gameDuration")
    private String gameDuration;

    @Expose
    @SerializedName("gameCreation")
    private String gameCreation;

    @Expose
    @SerializedName("gameMode")
    private String gameMode;

    @Expose
    @SerializedName("participantIdentities")
    private ParticipantsIdentities[] participantIdentities;

    @Expose
    @SerializedName("teams")
    private Team[] teams;

    @Expose
    @SerializedName("participants")
    private Participants[] participants;

    public long getGameId() {
        return gameId;
    }

    public String getGameDuration() {
        return gameDuration;
    }

    public String getGameCreation() {
        return gameCreation;
    }

    public ParticipantsIdentities[] getParticipantIdentities() {
        return participantIdentities;
    }

    public Team[] getTeams() {
        return teams;
    }

    public Participants[] getParticipants() {
        return participants;
    }

    public String getGameMode() {
        return gameMode;
    }

    @Override
    public String toString() {
        return "MatchContainer{" +
                "gameId=" + gameId +
                ", gameDuration='" + gameDuration + '\'' +
                ", gameCreation='" + gameCreation + '\'' +
                ", gameMode='" + gameMode + '\'' +
                ", participantIdentities=" + Arrays.toString(participantIdentities) +
                ", teams=" + Arrays.toString(teams) +
                ", participants=" + Arrays.toString(participants) +
                '}';
    }
}
