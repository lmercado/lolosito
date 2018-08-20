package com.ngti.leandro.lol.utils;

public class MatchStats {

    public static String calculateMatchKda(int kills, int deaths, int assists) {
        float kda;
        kda = (kills + (1 / 3 * assists) - deaths);
        return String.valueOf(kda);
    }
}
