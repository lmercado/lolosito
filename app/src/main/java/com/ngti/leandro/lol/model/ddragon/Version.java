package com.ngti.leandro.lol.model.ddragon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Version {

    @Expose
    @SerializedName("item")
    private String item;

    @Expose
    @SerializedName("rune")
    private String rune;

    @Expose
    @SerializedName("mastery")
    private String mastery;

    @Expose
    @SerializedName("summoner")
    private String summoner;

    @Expose
    @SerializedName("champion")
    private String champion;

    @Expose
    @SerializedName("profileicon")
    private String profileicon;

    @Expose
    @SerializedName("map")
    private String map;

    @Expose
    @SerializedName("language")
    private String language;

    @Expose
    @SerializedName("store")
    private String store;

    public String getItem() {
        return item;
    }

    public String getRune() {
        return rune;
    }

    public String getMastery() {
        return mastery;
    }

    public String getSummoner() {
        return summoner;
    }

    public String getChampion() {
        return champion;
    }

    public String getProfileicon() {
        return profileicon;
    }

    public String getMap() {
        return map;
    }

    public String getLanguage() {
        return language;
    }

    public String getStore() {
        return store;
    }
}
