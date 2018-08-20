package com.ngti.leandro.lol.model.matchlist;

import com.google.gson.annotations.SerializedName;
import com.ngti.leandro.lol.model.matchlist.AllMatches;

import java.util.List;

public class MatchesContainer {

    @SerializedName("matches")
    List<AllMatches> matches;

    public List<AllMatches> getMatches() {
        return matches;
    }

    @Override
    public String toString() {
        return "MatchesContainer{" +
                "matches=" + matches +
                '}';
    }
}
