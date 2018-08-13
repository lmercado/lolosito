package com.ngti.leandro.lol.champions;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class ChampionsContainer {

    @SerializedName("data")
    public Map<String, Champions> champions;

    public Map<Integer, Champions> getChampionsById() {
        final Map<Integer, Champions> championsById = new HashMap<>(getChampions().size());

        for (Map.Entry<String,Champions> entry : getChampions().entrySet()) {
            final Champions champions = entry.getValue();
            championsById.put(champions.getId(), champions);
        }

        return championsById;
    }

    public Map<String, Champions> getChampions() {
        return champions;
    }

    public void setChampions(Map<String, Champions> champions) {
        this.champions = champions;
    }
}
