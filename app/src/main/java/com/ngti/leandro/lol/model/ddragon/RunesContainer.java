package com.ngti.leandro.lol.model.ddragon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ngti.leandro.lol.splash.GetRunes;

import java.util.List;

public class RunesContainer {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("icon")
    private String icon;

    @Expose
    @SerializedName("slots")
    private List<Slots> slots;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public List<Slots> getSlots() {
        return slots;
    }

    public static int getRuneIconById(int id) {
        int runeId;
        for (RunesContainer runes : GetRunes.allRunes) {
            if (runes.getId() == id) {
                runeId = runes.getId();

                return runeId;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "RunesContainer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", slots=" + slots +
                '}';
    }
}
