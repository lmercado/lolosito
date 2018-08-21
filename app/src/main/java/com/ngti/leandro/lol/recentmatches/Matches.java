package com.ngti.leandro.lol.recentmatches;

import com.ngti.leandro.lol.model.match.MatchContainer;
import com.ngti.leandro.lol.model.matchlist.AllMatches;

import java.util.ArrayList;
import java.util.Map;

public class Matches {
    private final ArrayList<AllMatches> matches;
    private final Map<Long, MatchContainer> matchesInfo;

    Matches(ArrayList<AllMatches> matches,
            Map<Long, MatchContainer> matchesInfo) {
        this.matches = matches;
        this.matchesInfo = matchesInfo;
    }

    public AllMatches getAllMatches(int position) {
        return matches.get(position);
    }

    public int size() {
        return matches.size();
    }

    public MatchContainer getMatchGeneral(long position) {
        return matchesInfo.get(position);
    }

}
