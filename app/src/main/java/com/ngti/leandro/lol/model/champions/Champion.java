package com.ngti.leandro.lol.model.champions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Champion {

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("key")
    private String key;

    @Expose
    @SerializedName("blurb")
    private String blurb;

    public String getChampionTitle() {
        return title;
    }

    public String getChampionId() {
        return id;
    }

    public String getChampionName() {
        return name;
    }

    public String getChampionBlurb() {
        return blurb;
    }

    public String getChampionKey() {
        return key;
    }
}
