package com.ngti.leandro.lol.model.ddragon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Runes {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("key")
    private String key;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("shortDesc")
    private String shortDesc;

    @Expose
    @SerializedName("longDesc")
    private String longDesc;


    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    @Override
    public String toString() {
        return "Runes{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", longDesc='" + longDesc + '\'' +
                '}';
    }


}
