package com.ngti.leandro.lol.recentmatches;

import com.ngti.leandro.lol.model.match.MatchContainer;
import com.ngti.leandro.lol.model.champions.Champion;
import com.ngti.leandro.lol.model.matchlist.AllMatches;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class ChampionsAndMatches {
    private final Map<Integer, Champion> champions;
    private final ArrayList<AllMatches> matches;
    private final Map<Long, MatchContainer> matchesInfo;

    ChampionsAndMatches(Map<Integer, Champion> allChampions,
                        ArrayList<AllMatches> matches,
                        Map<Long, MatchContainer> matchesInfo) {
        this.champions = allChampions;
        this.matches = matches;
        this.matchesInfo = matchesInfo;
    }

    public AllMatches getAllMatches(int position) {
        return matches.get(position);
    }

    public Set<Map.Entry<Integer, Champion>> entrySet() {
        return champions.entrySet();
    }

    public int size() {
        return matches.size();
    }

    public MatchContainer getMatchGeneral(long position) {
        return matchesInfo.get(position);
    }

}
