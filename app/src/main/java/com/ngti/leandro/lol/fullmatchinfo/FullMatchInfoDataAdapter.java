package com.ngti.leandro.lol.fullmatchinfo;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ngti.leandro.lol.R;
import com.ngti.leandro.lol.model.champions.Champion;
import com.ngti.leandro.lol.model.match.MatchContainer;
import com.ngti.leandro.lol.model.match.Participants;
import com.ngti.leandro.lol.model.match.ParticipantsIdentities;
import com.ngti.leandro.lol.model.match.Team;
import com.ngti.leandro.lol.utils.Icons;
import com.ngti.leandro.lol.utils.MatchStats;

import java.util.Map;
import java.util.Objects;

import timber.log.Timber;

import static com.ngti.leandro.lol.splash.GetSpells.allSpells;
import static com.ngti.leandro.lol.utils.Icons.getItemIconUrl;
import static com.ngti.leandro.lol.utils.Icons.getPerk;
import static com.ngti.leandro.lol.utils.Icons.getPerkStyle;

public class FullMatchInfoDataAdapter extends RecyclerView.Adapter<FullMatchInfoDataAdapter.ViewHolder> {

    private MatchContainer match;
    private Map<Integer, Champion> champions;

    @NonNull
    @Override
    public FullMatchInfoDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_full_match_info_summoner, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FullMatchInfoDataAdapter.ViewHolder holder, int position) {
        Context context = holder.iv_full_info_champion_icon.getContext();
        String gameCreation = match.getGameCreation();
        String gameDuration = match.getGameDuration();
        String gameMode = match.getGameMode();

        Participants[] participants = match.getParticipants();
        ParticipantsIdentities[] participantsIdentities = match.getParticipantIdentities();
        Team[] teams = match.getTeams();

        int participantChampionId = participants[position].getChampionId();

        /*
            Load champions images on holders
         */
        for (Map.Entry<Integer, Champion> entry : champions.entrySet()) {
            Integer key = entry.getKey();
            if (participantChampionId == key) {
                String championName = entry.getValue().getChampionId();
                loadUrlIntoHolder(Icons.getChampionIconUrl(championName), holder.iv_full_info_champion_icon, context);
                Timber.d("Loading champion " + championName + " into holder");
            }
        }

        /*
            Set summoner name into holders
         */
        setTextIntoHolder(participantsIdentities[position].getPlayer().getSummonerName(), holder.iv_full_info_summoner_name);


        /*
            Set summoner spells
         */
        int summonerSpellId1 = participants[position].getSpell1Id();
        int summonerSpellId2 = participants[position].getSpell2Id();

        loadUrlIntoHolder(Icons.getSummonerSpellUrl(allSpells.get(summonerSpellId1).getId()), holder.iv_full_info_champion_spell_1, context);
        loadUrlIntoHolder(Icons.getSummonerSpellUrl(allSpells.get(summonerSpellId2).getId()), holder.iv_full_info_champion_spell_2, context);

        /*
            Set summoner perks
         */
        int summonerPerk0 = participants[position].getStats().getPerk0();
        int summonerPerkSubStyle = participants[position].getStats().getPerkSubStyle();

        loadUrlIntoHolder(getPerk(summonerPerk0), holder.iv_full_info_champion_perk0, context);
        loadUrlIntoHolder(getPerkStyle(summonerPerkSubStyle), holder.iv_full_info_champion_perk_sub_style, context);

        /*
            Set summoner KDA
         */
        int summonerKills = participants[position].getStats().getKills();
        int summonerDeaths = participants[position].getStats().getDeaths();
        int summonerAssists = participants[position].getStats().getAssists();

        setTextIntoHolder(String.valueOf(summonerKills) + "/" + summonerDeaths + "/" + summonerAssists, holder.tv_full_info_champion_kill_death_assists);
        setTextIntoHolder(MatchStats.calculateMatchKda(summonerKills, summonerDeaths, summonerAssists) + " KDA", holder.tv_full_info_champion_kda);

        /*
            Set summoner items
         */

        int summonerItem0 = participants[position].getStats().getItem0();
        int summonerItem1 = participants[position].getStats().getItem1();
        int summonerItem2 = participants[position].getStats().getItem2();
        int summonerItem3 = participants[position].getStats().getItem3();
        int summonerItem4 = participants[position].getStats().getItem4();
        int summonerItem5 = participants[position].getStats().getItem5();
        int summonerItem6 = participants[position].getStats().getItem6();

        loadUrlIntoHolder(getItemIconUrl(summonerItem0), holder.iv_full_info_champion_item0, context);
        loadUrlIntoHolder(getItemIconUrl(summonerItem1), holder.iv_full_info_champion_item1, context);
        loadUrlIntoHolder(getItemIconUrl(summonerItem2), holder.iv_full_info_champion_item2, context);
        loadUrlIntoHolder(getItemIconUrl(summonerItem3), holder.iv_full_info_champion_item3, context);
        loadUrlIntoHolder(getItemIconUrl(summonerItem4), holder.iv_full_info_champion_item4, context);
        loadUrlIntoHolder(getItemIconUrl(summonerItem5), holder.iv_full_info_champion_item5, context);
        loadUrlIntoHolder(getItemIconUrl(summonerItem6), holder.iv_full_info_champion_item6, context);

        /*
            Set summoner items
         */

        boolean summonerWin = participants[position].getStats().isWin();

        if (summonerWin) {
            holder.itemView.setBackgroundColor(Color.parseColor("#A2CFEC"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#E3B9B3"));
        }
    }

    public void setData(FullMatchInfoAndChampions fullMatchInfoAndChampions) {
        this.match = Objects.requireNonNull(fullMatchInfoAndChampions.getMatchInfo());
        this.champions = Objects.requireNonNull(fullMatchInfoAndChampions.getChampions());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return match != null ? match.getParticipants().length : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_full_info_champion_icon;
        private TextView iv_full_info_summoner_name;
        private ImageView iv_full_info_champion_spell_1;
        private ImageView iv_full_info_champion_spell_2;
        private ImageView iv_full_info_champion_perk0;
        private ImageView iv_full_info_champion_perk_sub_style;
        private TextView tv_full_info_champion_kill_death_assists;
        private TextView tv_full_info_champion_kda;
        private ImageView iv_full_info_champion_item0;
        private ImageView iv_full_info_champion_item1;
        private ImageView iv_full_info_champion_item2;
        private ImageView iv_full_info_champion_item3;
        private ImageView iv_full_info_champion_item4;
        private ImageView iv_full_info_champion_item5;
        private ImageView iv_full_info_champion_item6;

        public ViewHolder(View itemView) {
            super(itemView);
            final View view = itemView.findViewById(R.id.full_match_view);

            iv_full_info_champion_icon = itemView.findViewById(R.id.iv_full_info_champion_icon);
            iv_full_info_summoner_name = itemView.findViewById(R.id.iv_full_info_summoner_name);
            iv_full_info_champion_spell_1 = itemView.findViewById(R.id.iv_full_info_champion_spell_1);
            iv_full_info_champion_spell_2 = itemView.findViewById(R.id.iv_full_info_champion_spell_2);
            iv_full_info_champion_perk0 = itemView.findViewById(R.id.iv_full_info_champion_perk0);
            iv_full_info_champion_perk_sub_style = itemView.findViewById(R.id.iv_full_info_champion_perk_sub_style);
            tv_full_info_champion_kill_death_assists = itemView.findViewById(R.id.tv_full_info_champion_kill_death_assists);
            tv_full_info_champion_kda = itemView.findViewById(R.id.tv_full_info_champion_kda);
            iv_full_info_champion_item0 = itemView.findViewById(R.id.iv_full_info_champion_item0);
            iv_full_info_champion_item1 = itemView.findViewById(R.id.iv_full_info_champion_item1);
            iv_full_info_champion_item2 = itemView.findViewById(R.id.iv_full_info_champion_item2);
            iv_full_info_champion_item3 = itemView.findViewById(R.id.iv_full_info_champion_item3);
            iv_full_info_champion_item4 = itemView.findViewById(R.id.iv_full_info_champion_item4);
            iv_full_info_champion_item5 = itemView.findViewById(R.id.iv_full_info_champion_item5);
            iv_full_info_champion_item6 = itemView.findViewById(R.id.iv_full_info_champion_item6);
        }
    }

    static void loadUrlIntoHolder(String url, ImageView holder, Context context) {
        if (url != null) {
            Glide.with(context).load(url).into(holder);
        }
    }

    static void setTextIntoHolder(String text, TextView holderId) {
        holderId.setText(text);
    }
}
