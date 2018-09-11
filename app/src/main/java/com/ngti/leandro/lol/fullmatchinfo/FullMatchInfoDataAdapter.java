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
import android.widget.Toast;

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
        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_full_match_info_summoner, parent, false);
                return new ViewHolder(view);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_full_match_info_header, parent, false);
                return new ViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        if (position == 0 || position == 6) {
            viewType = 1;
        } else {
            viewType = 0;
        }
        return viewType;
    }

    @Override
    public void onBindViewHolder(@NonNull FullMatchInfoDataAdapter.ViewHolder holder, int position) {

        if (match.getParticipants().length != 10) {
            Context context = holder.iv_score_icon.getContext();
            Toast.makeText(context, "Game mode not supported", Toast.LENGTH_SHORT).show();
        }

        String gameCreation = match.getGameCreation();
        String gameDuration = match.getGameDuration();
        String gameMode = match.getGameMode();
        Participants[] participants = match.getParticipants();
        ParticipantsIdentities[] participantsIdentities = match.getParticipantIdentities();
        Team[] teams = match.getTeams();


        //  ______ _____ _____   _____ _______   _    _ ______          _____  ______ _____
        // |  ____|_   _|  __ \ / ____|__   __| | |  | |  ____|   /\   |  __ \|  ____|  __ \
        // | |__    | | | |__) | (___    | |    | |__| | |__     /  \  | |  | | |__  | |__) |
        // |  __|   | | |  _  / \___ \   | |    |  __  |  __|   / /\ \ | |  | |  __| |  _  /
        // | |     _| |_| | \ \ ____) |  | |    | |  | | |____ / ____ \| |__| | |____| | \ \
        // |_|    |_____|_|  \_\_____/   |_|    |_|  |_|______/_/    \_\_____/|______|_|  \_\

        if (position == 0) {
            Context context = holder.iv_score_icon.getContext();
            loadUrlIntoHolder("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQbrso0kkbgb2d3UtiZbI_4jdg-nqH5zW46eZIyGZVRPAod7sGf", holder.iv_score_icon, context);

            boolean summonerWin = participants[1].getStats().isWin();

            int towerKills = teams[0].getTowerKills();
            int baronKills = teams[0].getBaronKills();
            int dragonKills = teams[0].getDragonKills();

            setTextIntoHolder(String.valueOf(towerKills), holder.tv_towers_kill_count);
            setTextIntoHolder(String.valueOf(baronKills), holder.tv_baron_kill_count);
            setTextIntoHolder(String.valueOf(dragonKills), holder.tv_dragon_kill_count);
            loadUrlIntoHolder("https://vignette.wikia.nocookie.net/leagueoflegends/images/3/38/Baron_NashorSquare.png", holder.iv_baron_icon, context);
            loadUrlIntoHolder("https://vignette.wikia.nocookie.net/leagueoflegends/images/c/c9/DragonSquare.png", holder.iv_dragon_icon, context);
            loadUrlIntoHolder("https://static.thenounproject.com/png/8172-200.png", holder.iv_tower_icon, context);

            if (summonerWin) {
                setTextIntoHolder("Victory", holder.tv_match_result);
                holder.tv_match_result.setTextColor(Color.parseColor("#1A78AE"));
            } else {
                setTextIntoHolder("Defeat", holder.tv_match_result);
                holder.tv_match_result.setTextColor(Color.parseColor("#CA443E"));
            }

            int finalTeamKill = participants[0].getStats().getKills() + participants[1].getStats().getKills() + participants[2].getStats().getKills() + participants[3].getStats().getKills() + participants[4].getStats().getKills();
            int finalTeamDeaths = participants[0].getStats().getDeaths() + participants[1].getStats().getDeaths() + participants[2].getStats().getDeaths() + participants[3].getStats().getDeaths() + participants[4].getStats().getDeaths();
            int finalTeamAssists = participants[0].getStats().getAssists() + participants[1].getStats().getAssists() + participants[2].getStats().getAssists() + participants[3].getStats().getAssists() + participants[4].getStats().getAssists();

            setTextIntoHolder(finalTeamKill + " / " + finalTeamDeaths + " / " + finalTeamAssists, holder.tv_team_final_kda);
        }

        //  _______ ______          __  __   __
        // |__   __|  ____|   /\   |  \/  | /_ |
        //    | |  | |__     /  \  | \  / |  | |
        //    | |  |  __|   / /\ \ | |\/| |  | |
        //    | |  | |____ / ____ \| |  | |  | |
        //    |_|  |______/_/    \_\_|  |_|  |_|

        if (position >= 1 && position <= 5) {
            Context context = holder.iv_full_info_champion_icon.getContext();

            int newPosition = position - 1;
            int participantChampionId = participants[newPosition].getChampionId();

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
            setTextIntoHolder(participantsIdentities[newPosition].getPlayer().getSummonerName(), holder.iv_full_info_summoner_name);


        /*
            Set summoner spells
         */
            int summonerSpellId1 = participants[newPosition].getSpell1Id();
            int summonerSpellId2 = participants[newPosition].getSpell2Id();

            loadUrlIntoHolder(Icons.getSummonerSpellUrl(allSpells.get(summonerSpellId1).getId()), holder.iv_full_info_champion_spell_1, context);
            loadUrlIntoHolder(Icons.getSummonerSpellUrl(allSpells.get(summonerSpellId2).getId()), holder.iv_full_info_champion_spell_2, context);

        /*
            Set summoner perks
         */
            int summonerPerk0 = participants[newPosition].getStats().getPerk0();
            int summonerPerkSubStyle = participants[newPosition].getStats().getPerkSubStyle();

            loadUrlIntoHolder(getPerk(summonerPerk0), holder.iv_full_info_champion_perk0, context);
            loadUrlIntoHolder(getPerkStyle(summonerPerkSubStyle), holder.iv_full_info_champion_perk_sub_style, context);

        /*
            Set summoner KDA
         */
            int summonerKills = participants[newPosition].getStats().getKills();
            int summonerDeaths = participants[newPosition].getStats().getDeaths();
            int summonerAssists = participants[newPosition].getStats().getAssists();

            setTextIntoHolder(String.valueOf(summonerKills) + "/" + summonerDeaths + "/" + summonerAssists, holder.tv_full_info_champion_kill_death_assists);
            setTextIntoHolder(MatchStats.calculateMatchKda(summonerKills, summonerDeaths, summonerAssists) + " KDA", holder.tv_full_info_champion_kda);

        /*
            Set summoner items
         */

            int summonerItem0 = participants[newPosition].getStats().getItem0();
            int summonerItem1 = participants[newPosition].getStats().getItem1();
            int summonerItem2 = participants[newPosition].getStats().getItem2();
            int summonerItem3 = participants[newPosition].getStats().getItem3();
            int summonerItem4 = participants[newPosition].getStats().getItem4();
            int summonerItem5 = participants[newPosition].getStats().getItem5();
            int summonerItem6 = participants[newPosition].getStats().getItem6();

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

            boolean summonerWin = participants[newPosition].getStats().isWin();

            if (summonerWin) {
                holder.itemView.setBackgroundColor(Color.parseColor("#A2CFEC"));
            } else {
                holder.itemView.setBackgroundColor(Color.parseColor("#E3B9B3"));
            }
        }

        //   _____ ______ _____ ____  _   _ _____    _    _ ______          _____  ______ _____
        //  / ____|  ____/ ____/ __ \| \ | |  __ \  | |  | |  ____|   /\   |  __ \|  ____|  __ \
        // | (___ | |__ | |   | |  | |  \| | |  | | | |__| | |__     /  \  | |  | | |__  | |__) |
        //  \___ \|  __|| |   | |  | | . ` | |  | | |  __  |  __|   / /\ \ | |  | |  __| |  _  /
        //  ____) | |___| |___| |__| | |\  | |__| | | |  | | |____ / ____ \| |__| | |____| | \ \
        // |_____/|______\_____\____/|_| \_|_____/  |_|  |_|______/_/    \_\_____/|______|_|  \_\

        if (position == 6) {
            Context context = holder.iv_score_icon.getContext();
            loadUrlIntoHolder("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQbrso0kkbgb2d3UtiZbI_4jdg-nqH5zW46eZIyGZVRPAod7sGf", holder.iv_score_icon, context);

            boolean summonerWin = participants[7].getStats().isWin();

            int towerKills = teams[1].getTowerKills();
            int baronKills = teams[1].getBaronKills();
            int dragonKills = teams[1].getDragonKills();

            setTextIntoHolder(String.valueOf(towerKills), holder.tv_towers_kill_count);
            setTextIntoHolder(String.valueOf(baronKills), holder.tv_baron_kill_count);
            setTextIntoHolder(String.valueOf(dragonKills), holder.tv_dragon_kill_count);
            loadUrlIntoHolder("https://vignette.wikia.nocookie.net/leagueoflegends/images/3/38/Baron_NashorSquare.png", holder.iv_baron_icon, context);
            loadUrlIntoHolder("https://vignette.wikia.nocookie.net/leagueoflegends/images/c/c9/DragonSquare.png", holder.iv_dragon_icon, context);
            loadUrlIntoHolder("https://static.thenounproject.com/png/8172-200.png", holder.iv_tower_icon, context);

            if (summonerWin) {
                setTextIntoHolder("Victory", holder.tv_match_result);
                holder.tv_match_result.setTextColor(Color.parseColor("#1A78AE"));
            } else {
                setTextIntoHolder("Defeat", holder.tv_match_result);
                holder.tv_match_result.setTextColor(Color.parseColor("#CA443E"));
            }

            int finalTeamKill = participants[5].getStats().getKills() + participants[6].getStats().getKills() + participants[7].getStats().getKills() + participants[8].getStats().getKills() + participants[9].getStats().getKills();
            int finalTeamDeaths = participants[5].getStats().getDeaths() + participants[6].getStats().getDeaths() + participants[7].getStats().getDeaths() + participants[8].getStats().getDeaths() + participants[9].getStats().getDeaths();
            int finalTeamAssists = participants[5].getStats().getAssists() + participants[6].getStats().getAssists() + participants[7].getStats().getAssists() + participants[8].getStats().getAssists() + participants[9].getStats().getAssists();

            setTextIntoHolder(finalTeamKill + " / " + finalTeamDeaths + " / " + finalTeamAssists, holder.tv_team_final_kda);
        }

        //  _______ ______          __  __   ___
        // |__   __|  ____|   /\   |  \/  | |__ \
        //    | |  | |__     /  \  | \  / |    ) |
        //    | |  |  __|   / /\ \ | |\/| |   / /
        //    | |  | |____ / ____ \| |  | |  / /_
        //    |_|  |______/_/    \_\_|  |_| |____|

        if (position > 6) {
            Context context = holder.iv_full_info_champion_icon.getContext();

            int newPosition = position - 2;
            int participantChampionId = participants[newPosition].getChampionId();

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
            setTextIntoHolder(participantsIdentities[newPosition].getPlayer().getSummonerName(), holder.iv_full_info_summoner_name);


        /*
            Set summoner spells
         */
            int summonerSpellId1 = participants[newPosition].getSpell1Id();
            int summonerSpellId2 = participants[newPosition].getSpell2Id();

            loadUrlIntoHolder(Icons.getSummonerSpellUrl(allSpells.get(summonerSpellId1).getId()), holder.iv_full_info_champion_spell_1, context);
            loadUrlIntoHolder(Icons.getSummonerSpellUrl(allSpells.get(summonerSpellId2).getId()), holder.iv_full_info_champion_spell_2, context);

        /*
            Set summoner perks
         */
            int summonerPerk0 = participants[newPosition].getStats().getPerk0();
            int summonerPerkSubStyle = participants[newPosition].getStats().getPerkSubStyle();

            loadUrlIntoHolder(getPerk(summonerPerk0), holder.iv_full_info_champion_perk0, context);
            loadUrlIntoHolder(getPerkStyle(summonerPerkSubStyle), holder.iv_full_info_champion_perk_sub_style, context);

        /*
            Set summoner KDA
         */
            int summonerKills = participants[newPosition].getStats().getKills();
            int summonerDeaths = participants[newPosition].getStats().getDeaths();
            int summonerAssists = participants[newPosition].getStats().getAssists();

            setTextIntoHolder(String.valueOf(summonerKills) + "/" + summonerDeaths + "/" + summonerAssists, holder.tv_full_info_champion_kill_death_assists);
            setTextIntoHolder(MatchStats.calculateMatchKda(summonerKills, summonerDeaths, summonerAssists) + " KDA", holder.tv_full_info_champion_kda);

        /*
            Set summoner items
         */

            int summonerItem0 = participants[newPosition].getStats().getItem0();
            int summonerItem1 = participants[newPosition].getStats().getItem1();
            int summonerItem2 = participants[newPosition].getStats().getItem2();
            int summonerItem3 = participants[newPosition].getStats().getItem3();
            int summonerItem4 = participants[newPosition].getStats().getItem4();
            int summonerItem5 = participants[newPosition].getStats().getItem5();
            int summonerItem6 = participants[newPosition].getStats().getItem6();

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

            boolean summonerWin = participants[newPosition].getStats().isWin();

            if (summonerWin) {
                holder.itemView.setBackgroundColor(Color.parseColor("#A2CFEC"));
            } else {
                holder.itemView.setBackgroundColor(Color.parseColor("#E3B9B3"));
            }
        }

    }


    public void setData(FullMatchInfoAndChampions fullMatchInfoAndChampions) {
        this.match = Objects.requireNonNull(fullMatchInfoAndChampions.getMatchInfo());
        this.champions = Objects.requireNonNull(fullMatchInfoAndChampions.getChampions());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return match != null ? match.getParticipants().length + 2 : 0;
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
        private ImageView iv_score_icon;
        private TextView tv_match_result;
        private TextView tv_team_final_kda;
        private ImageView iv_tower_icon;
        private ImageView iv_baron_icon;
        private ImageView iv_dragon_icon;
        private TextView tv_dragon_kill_count;
        private TextView tv_towers_kill_count;
        private TextView tv_baron_kill_count;


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
            iv_score_icon = itemView.findViewById(R.id.iv_icon_score);
            tv_match_result = itemView.findViewById(R.id.tv_match_result);
            tv_team_final_kda = itemView.findViewById(R.id.tv_team_final_kda);
            iv_tower_icon = itemView.findViewById(R.id.iv_tower_icon);
            iv_baron_icon = itemView.findViewById(R.id.iv_baron_icon);
            iv_dragon_icon = itemView.findViewById(R.id.iv_dragon_icon);
            tv_dragon_kill_count = itemView.findViewById(R.id.tv_dragon_kill_count);
            tv_towers_kill_count = itemView.findViewById(R.id.tv_baron_kill_count);
            tv_baron_kill_count = itemView.findViewById(R.id.tv_tower_kill_count);
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
