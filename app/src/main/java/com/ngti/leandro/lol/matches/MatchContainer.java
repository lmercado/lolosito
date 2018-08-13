package com.ngti.leandro.lol.matches;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MatchContainer {

    @SerializedName("matches")
    List<Matches> matches;

    public List<Matches> getMatches() {
        return matches;
    }

}
