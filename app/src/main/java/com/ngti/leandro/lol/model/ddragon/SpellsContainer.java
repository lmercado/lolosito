package com.ngti.leandro.lol.model.ddragon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class SpellsContainer {

    @Expose
    @SerializedName("data")
    private Map<String, Spell> data;

    public Map<String, Spell> getData() {
        return data;
    }

    public Map<Integer, Spell> getSpellById() {
        final Map<Integer, Spell> spellsById = new HashMap<>(getData().size());

        for (Map.Entry<String,Spell> entry : getData().entrySet()) {
            final Spell spell = entry.getValue();
            spellsById.put(spell.getKey(), spell);
        }
        return spellsById;
    }

}
