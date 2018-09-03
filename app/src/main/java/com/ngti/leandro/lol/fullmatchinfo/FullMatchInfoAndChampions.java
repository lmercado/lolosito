package com.ngti.leandro.lol.fullmatchinfo;

import com.ngti.leandro.lol.model.champions.Champion;
import com.ngti.leandro.lol.model.match.MatchContainer;

import java.util.Map;
import java.util.Set;

public class FullMatchInfoAndChampions {

    private final Map<Integer, Champion> champions;
    private final MatchContainer matchesInfo;

    public FullMatchInfoAndChampions(MatchContainer matchInfo,
                                     Map<Integer, Champion> allChampions) {
        this.champions = allChampions;
        this.matchesInfo = matchInfo;
    }

    public Map<Integer, Champion> getChampions() {
        return champions;
    }

    public MatchContainer getMatchInfo() {
        return matchesInfo;
    }

}
