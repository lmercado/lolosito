package com.ngti.leandro.lol.utils;

public class GameModes {

    public static String getMatchModeByQueueId(int id) {
        switch (id) {
            case 72:
            case 73:
            case 78:
            case 450:
            case 920:
                return "Howling Abyss";
            case 75:
            case 76:
            case 83:
            case 310:
            case 313:
            case 325:
            case 400:
            case 420:
            case 430:
            case 440:
            case 600:
            case 830:
            case 840:
            case 850:
            case 900:
            case 700:
            case 940:
            case 950:
            case 960:
            case 1010:
            case 1020:
                return "Summoner's Rift";
            case 98:
            case 460:
            case 470:
            case 800:
            case 810:
            case 820:
                return "Twisted Treeline";
            case 100:
                return "Butcher's Bridge";
            case 317:
                return "Crystal Scar";
            case 610:
                return "Cosmic Ruins";
            case 980:
            case 990:
                return "Valoran City Park";
            case 1000:
                return "Overcharge";
            case 1200:
                return "Nexus Blitz";
            default:
                return null;

        }

    }
}
