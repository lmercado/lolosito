package com.ngti.leandro.lol.allMatches;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllMatchesContainer {

    @SerializedName("matches")
    List<AllMatches> matches;

    public List<AllMatches> getMatches() {
        return matches;
    }

    @Override
    public String toString() {
        return "AllMatchesContainer{" +
                "matches=" + matches +
                '}';
    }
}
