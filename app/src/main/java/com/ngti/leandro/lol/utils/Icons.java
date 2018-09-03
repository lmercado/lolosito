package com.ngti.leandro.lol.utils;

import static com.ngti.leandro.lol.splash.GetGameVersion.DDRAGON_CHAMPION_VERSION;
import static com.ngti.leandro.lol.splash.GetGameVersion.DDRAGON_ITEM_VERSION;
import static com.ngti.leandro.lol.splash.GetGameVersion.DDRAGON_SUMMONER_VERSION;

public class Icons {

    private static String BASE_URL = "http://ddragon.leagueoflegends.com/cdn";

    public static String getChampionIconUrl(String championName) {
        return BASE_URL + "/" + DDRAGON_CHAMPION_VERSION + "/img/champion/" + championName + ".png";
    }

    public static String getItemIconUrl(int itemId) {
        if (itemId != 0) {
            return BASE_URL + "/" + DDRAGON_ITEM_VERSION + "/img/item/" + itemId + ".png";
        }
        return null;
    }

    public static String getSummonerSpellUrl(String spell) {
        return BASE_URL + "/" + DDRAGON_SUMMONER_VERSION + "/img/spell/" + spell + ".png";
    }

    public static String getPerk(int perkId) {
        return "http://opgg-static.akamaized.net/images/lol/perk/" + perkId + ".png";
    }

    public static String getPerkStyle(int perkStyleId) {
        return "http://opgg-static.akamaized.net/images/lol/perkStyle/" + perkStyleId + ".png";
    }

}
