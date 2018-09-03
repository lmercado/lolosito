package com.ngti.leandro.lol.model.match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats {

    @Expose
    @SerializedName("kills")
    private int kills;

    @Expose
    @SerializedName("deaths")
    private int deaths;

    @Expose
    @SerializedName("assists")
    private int assists;

    @Expose
    @SerializedName("champLevel")
    private int champLevel;

    @Expose
    @SerializedName("goldSpent")
    private int goldSpent;

    @Expose
    @SerializedName("goldEarned")
    private int goldEarned;

    @Expose
    @SerializedName("wardsPlaced")
    private int wardsPlaced;

    @Expose
    @SerializedName("doubleKills")
    private int doubleKills;

    @Expose
    @SerializedName("tripleKills")
    private int tripleKills;

    @Expose
    @SerializedName("quadraKills")
    private int quadraKills;

    @Expose
    @SerializedName("pentaKills")
    private int pentaKills;

    @Expose
    @SerializedName("totalDamageTaken")
    private long totalDamageTaken;

    @Expose
    @SerializedName("totalDamageDealtToChampions")
    private long totalDamageDealtToChampions;

    @Expose
    @SerializedName("totalDamageDealt")
    private long totalDamageDealt;

    @Expose
    @SerializedName("totalHeal")
    private long totalHeal;

    @Expose
    @SerializedName("totalMinionsKilled")
    private int totalMinionsKilled;

    @Expose
    @SerializedName("perk0")
    private int perk0;

    @Expose
    @SerializedName("perk1")
    private int perk1;

    @Expose
    @SerializedName("perk2")
    private int perk2;

    @Expose
    @SerializedName("perk3")
    private int perk3;

    @Expose
    @SerializedName("perk4")
    private int perk4;

    @Expose
    @SerializedName("perk5")
    private int perk5;

    @Expose
    @SerializedName("item0")
    private int item0;

    @Expose
    @SerializedName("item1")
    private int item1;

    @Expose
    @SerializedName("item2")
    private int item2;

    @Expose
    @SerializedName("item3")
    private int item3;

    @Expose
    @SerializedName("item4")
    private int item4;

    @Expose
    @SerializedName("item5")
    private int item5;

    @Expose
    @SerializedName("item6")
    private int item6;

    @Expose
    @SerializedName("perkPrimaryStyle")
    private int perkPrimaryStyle;

    @Expose
    @SerializedName("perkSubStyle")
    private int perkSubStyle;

    @Expose
    @SerializedName("win")
    private boolean win;

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getAssists() {
        return assists;
    }

    public int getChampLevel() {
        return champLevel;
    }

    public int getGoldSpent() {
        return goldSpent;
    }

    public int getGoldEarned() {
        return goldEarned;
    }

    public int getWardsPlaced() {
        return wardsPlaced;
    }

    public int getDoubleKills() {
        return doubleKills;
    }

    public int getTripleKills() {
        return tripleKills;
    }

    public int getQuadraKills() {
        return quadraKills;
    }

    public int getPentaKills() {
        return pentaKills;
    }

    public long getTotalDamageTaken() {
        return totalDamageTaken;
    }

    public long getTotalDamageDealtToChampions() {
        return totalDamageDealtToChampions;
    }

    public long getTotalDamageDealt() {
        return totalDamageDealt;
    }

    public long getTotalHeal() {
        return totalHeal;
    }

    public int getTotalMinionsKilled() {
        return totalMinionsKilled;
    }

    public int getPerk0() {
        return perk0;
    }

    public int getPerk1() {
        return perk1;
    }

    public int getPerk2() {
        return perk2;
    }

    public int getPerk3() {
        return perk3;
    }

    public int getPerk4() {
        return perk4;
    }

    public int getPerk5() {
        return perk5;
    }

    public int getItem0() {
        return item0;
    }

    public int getItem1() {
        return item1;
    }

    public int getItem2() {
        return item2;
    }

    public int getItem3() {
        return item3;
    }

    public int getItem4() {
        return item4;
    }

    public int getItem5() {
        return item5;
    }

    public int getItem6() {
        return item6;
    }

    public int getPerkPrimaryStyle() {
        return perkPrimaryStyle;
    }

    public int getPerkSubStyle() {
        return perkSubStyle;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "kills=" + kills +
                ", deaths=" + deaths +
                ", assists=" + assists +
                ", champLevel=" + champLevel +
                ", goldSpent=" + goldSpent +
                ", goldEarned=" + goldEarned +
                ", wardsPlaced=" + wardsPlaced +
                ", doubleKills=" + doubleKills +
                ", tripleKills=" + tripleKills +
                ", quadraKills=" + quadraKills +
                ", pentaKills=" + pentaKills +
                ", totalDamageTaken=" + totalDamageTaken +
                ", totalDamageDealtToChampions=" + totalDamageDealtToChampions +
                ", totalDamageDealt=" + totalDamageDealt +
                ", totalHeal=" + totalHeal +
                ", totalMinionsKilled=" + totalMinionsKilled +
                ", perk0=" + perk0 +
                ", perk1=" + perk1 +
                ", perk2=" + perk2 +
                ", perk3=" + perk3 +
                ", perk4=" + perk4 +
                ", perk5=" + perk5 +
                ", item0=" + item0 +
                ", item1=" + item1 +
                ", item2=" + item2 +
                ", item3=" + item3 +
                ", item4=" + item4 +
                ", item5=" + item5 +
                ", item6=" + item6 +
                ", perkPrimaryStyle=" + perkPrimaryStyle +
                ", perkSubStyle=" + perkSubStyle +
                '}';
    }

    public boolean isWin() {
        return win;
    }
}
