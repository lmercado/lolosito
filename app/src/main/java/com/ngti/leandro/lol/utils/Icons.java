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

    public static String getPrimaryPerkUrl(int perkId) {
        switch (perkId) {
            case 8100:
                return BASE_URL + "/" + "img/perk-images/Styles/7200_Domination.png";
            case 8300:
                return BASE_URL + "/" + "img/perk-images/Styles/7203_Whimsy.png";
            case 8000:
                return BASE_URL + "/" + "img/perk-images/Styles/7201_Precision.png";
            case 8400:
                return BASE_URL + "/" + "img/perk-images/Styles/7204_Resolve.png";
            case 8200:
                return BASE_URL + "/" + "img/perk-images/Styles/7202_Sorcery.png";

        }
        return null;
    }

    public static String getSecondaryPerkUrl(int perkId) {
        return "http://opgg-static.akamaized.net/images/lol/perk/" + perkId + ".png";
    }

}
