package com.ngti.leandro.lol.model.ddragon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class Slots {

    @Expose
    @SerializedName("runes")
    private List<Runes> runes;


    public List<Runes> getRunes() {
        return runes;
    }

    @Override
    public String toString() {
        return "Slots{" +
                "runes=" + runes +
                '}';
    }

}
