package com.ngti.leandro.lol.recent.matches;

import com.ngti.leandro.lol.champions.Champions;
import com.ngti.leandro.lol.matches.Matches;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class ChampionsAndMatches {
    private final Map<Integer, Champions> champions;
    private final ArrayList<Matches> matches;

    public ChampionsAndMatches(Map<Integer, Champions> allChampions, ArrayList<Matches> matches) {
        this.champions = allChampions;
        this.matches = matches;
    }

    public Matches get(int position) {
        return matches.get(position);
    }

    public Set<Map.Entry<Integer, Champions>> entrySet() {
        return champions.entrySet();
    }

    public int size() {
        return matches.size();
    }
}
