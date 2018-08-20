package com.ngti.leandro.lol.model.champions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Champion {

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("key")
    private String key;

    @Expose
    @SerializedName("name")
    private String name;

    public String getChampionTitle() {
        return title;
    }

    public int getChampionId() {
        return id;
    }

    public String getChampionKey() {
        return key;
    }

    public String getChampionName() {
        return name;
    }

    @Override
    public String toString() {
        return "Champion{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
