package com.ngti.leandro.lol.model.ddragon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Spell {

    @Expose
    @SerializedName("key")
    private int key;

    @Expose
    @SerializedName("id")
    private String id;

    public int getKey() {
        return key;
    }

    public String getId() {
        return id;
    }
}
