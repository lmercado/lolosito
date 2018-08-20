package com.ngti.leandro.lol.model.champions;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class ChampionsContainer {

    @SerializedName("data")
    public Map<String, Champion> champions;

    public Map<Integer, Champion> getChampionsById() {
        final Map<Integer, Champion> championsById = new HashMap<>(getChampions().size());

        for (Map.Entry<String, Champion> entry : getChampions().entrySet()) {
            final Champion champions = entry.getValue();
            championsById.put(champions.getChampionId(), champions);
        }

        return championsById;
    }

    public Map<String, Champion> getChampions() {
        return champions;
    }

    public void setChampions(Map<String, Champion> champions) {
        this.champions = champions;
    }
}
