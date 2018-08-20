package com.ngti.leandro.lol.model.ddragon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VersionsContainer {

    @Expose
    @SerializedName("n")
    private Version allVersions;

    public Version allVersions() {
        return allVersions;
    }

}
