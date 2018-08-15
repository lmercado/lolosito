package com.ngti.leandro.lol.Match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParticipantsIdentities {

    @Expose
    @SerializedName("participantId")
    private int participantId;

    @Expose
    @SerializedName("player")
    private Player player;

    public int getParticipantId() {
        return participantId;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "ParticipantsIdentities{" +
                "participantId=" + participantId +
                ", player=" + player +
                '}';
    }
}
