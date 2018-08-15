package com.ngti.leandro.lol.champions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Champions {

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

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Champions{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
