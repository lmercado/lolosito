package com.ngti.leandro.lol.utils;

import java.text.DecimalFormat;

public class MatchStats {

    public static String calculateMatchKda(int kills, int deaths, int assists) {
        double dKills = kills;
        double dDeaths = deaths;
        double dAssists = assists;
        double kda;
        DecimalFormat df = new DecimalFormat("#.##");
        kda = (dKills + dAssists) / Math.max(1, dDeaths);
        String formatted = df.format(kda);
        return String.valueOf(formatted);
    }
}
