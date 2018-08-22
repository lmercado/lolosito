package com.ngti.leandro.lol.recentmatches;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ngti.leandro.lol.DataAdapter;
import com.ngti.leandro.lol.R;

import static com.ngti.leandro.lol.utils.Icons.getItemIconUrl;

public class SummonerMatchStats {

    private static String championName = "";
    private static int championKey = 0;
    String summonerWin = null;
    int summonerParticipantId = 0;
    int summonerSpellId1 = 0;
    int summonerSpellId2 = 0;
    int summonerTeamId = 0;
    int summonerItem0 = 0;
    int summonerItem1 = 0;
    int summonerItem2 = 0;
    int summonerItem3 = 0;
    int summonerItem4 = 0;
    int summonerItem5 = 0;
    int summonerItem6 = 0;
    int participantId;
    int summonerKills = 0;
    int summonerDeaths = 0;
    int summonerAssists = 0;
    int summonerChampLevel = 0;
    int summonerGoldSpent;
    int summonerGoldEarned;
    int summonerWardsPlaced;
    int summonerDoubleKills;
    int summonerTripleKills;
    int summonerQuadraKills;
    int summonerPentaKills;
    long summonerTotalDamageTaken;
    long summonerTotalDamageDealtToChampions;
    long summonerTotalDamageDealt;
    long summonerTotalHeal;
    int summonerTotalMinionsKilled = 0;
    int summonerPerk0;
    int summonerPerk1;
    int summonerPerk2;
    int summonerPerk3;
    int summonerPerk4;
    int summonerPerk5;
    int summonerPerkPrimaryStyle;
    int summonerPerkSubStyle;


    public static int getChampionKey(Integer champion, Integer key) {
        if (champion.equals(key)) {
            return championKey;
        } else {
            return 0;
        }
    }

    public static void loadUrlIntoHolder(String url, ImageView holder, Context context) {
        if (url != null) {
            Glide.with(context).load(url).into(holder);
        }
    }

    public static void setTextIntoHolder(String text, TextView holderId) {
        holderId.setText(text);
    }


}
