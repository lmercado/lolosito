package com.ngti.leandro.lol.recentmatches;

import com.ngti.leandro.lol.model.champions.Champion;

import java.util.Map;
import java.util.Set;

public class Champions {

    private final Map<Integer, Champion> champions;

    public Champions(Map<Integer, Champion> allChampions) {
        this.champions = allChampions;
    }

    public Set<Map.Entry<Integer, Champion>> entrySet() {
        return champions.entrySet();
    }

}
