package com.ngti.leandro.lol;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ngti.leandro.lol.model.match.Participants;
import com.ngti.leandro.lol.model.match.Team;
import com.ngti.leandro.lol.model.match.MatchContainer;
import com.ngti.leandro.lol.utils.MatchStats;
import com.ngti.leandro.lol.model.match.ParticipantsIdentities;
import com.ngti.leandro.lol.model.champions.Champion;
import com.ngti.leandro.lol.model.matchlist.AllMatches;
import com.ngti.leandro.lol.recentmatches.ChampionsAndMatches;

import java.util.Map;

import static com.ngti.leandro.lol.recentmatches.RecentMatchesActivity.summoner;
import static com.ngti.leandro.lol.splash.GetSpells.allSpells;
import static com.ngti.leandro.lol.utils.Icons.getChampionIconUrl;
import static com.ngti.leandro.lol.utils.Icons.getDefaultItemIconUrl;
import static com.ngti.leandro.lol.utils.Icons.getItemIconUrl;
import static com.ngti.leandro.lol.utils.Icons.getSummonerSpellUrl;
import static com.ngti.leandro.lol.utils.GameModes.getMatchModeByQueueId;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ChampionsAndMatches championsAndMatches;

    public DataAdapter() {
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recent_match, parent, false);
        parent.setBackgroundColor(Color.parseColor("#EDEDED"));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        Context context = holder.iv_champion_icon.getContext();
        String championName = "";
        int summonerParticipantId = 0;
        int summonerSpellId1 = 0;
        int summonerSpellId2 = 0;
        int summonerTeamId = 0;
        String summonerWin = null;
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


        AllMatches allMatches = championsAndMatches.getAllMatches(position);


        long matchId = championsAndMatches.getAllMatches(position).getGameId();

        MatchContainer matchInfo = championsAndMatches.getMatchGeneral(matchId);

        for (Map.Entry<Integer, Champion> entry : championsAndMatches.entrySet()) {
            Integer key = (Integer) entry.getKey();



            if (allMatches.getChampion().equals(key)) {
                championName = entry.getValue().getKey();
                Glide.with(context).load(getChampionIconUrl(championName)).into(holder.iv_champion_icon);
                holder.tv_champion_name.setText(entry.getValue().getName());
                holder.tv_game_type.setText(getMatchModeByQueueId(allMatches.getQueue()));

                ParticipantsIdentities[] participantIdentities = matchInfo.getParticipantIdentities();

                for (ParticipantsIdentities participantIdentity : participantIdentities) {
                    if (participantIdentity.getPlayer().getSummonerName().toLowerCase().equals(summoner)) {
                        summonerParticipantId = participantIdentity.getParticipantId();
                    }
                }


                Participants[] participants = matchInfo.getParticipants();

                for (Participants participant : participants) {
                    if (participant.getParticipantId() == summonerParticipantId) {
                        summonerTeamId = participant.getTeamId();
                        summonerSpellId1 = participant.getSpell1Id();
                        summonerSpellId2 = participant.getSpell2Id();
                        summonerItem0 = participant.getStats().getItem0();
                        summonerItem1 = participant.getStats().getItem1();
                        summonerItem2 = participant.getStats().getItem2();
                        summonerItem3 = participant.getStats().getItem3();
                        summonerItem4 = participant.getStats().getItem4();
                        summonerItem5 = participant.getStats().getItem5();
                        summonerItem6 = participant.getStats().getItem6();
                        summonerKills = participant.getStats().getKills();
                        summonerDeaths = participant.getStats().getDeaths();
                        summonerAssists = participant.getStats().getAssists();
                        summonerChampLevel = participant.getStats().getChampLevel();
                        summonerGoldSpent = participant.getStats().getGoldSpent();
                        summonerGoldEarned = participant.getStats().getGoldEarned();
                        summonerWardsPlaced = participant.getStats().getWardsPlaced();
                        summonerDoubleKills = participant.getStats().getDoubleKills();
                        summonerTripleKills = participant.getStats().getTripleKills();
                        summonerQuadraKills = participant.getStats().getQuadraKills();
                        summonerPentaKills = participant.getStats().getPentaKills();
                        summonerTotalDamageTaken = participant.getStats().getTotalDamageTaken();
                        summonerTotalDamageDealtToChampions = participant.getStats().getTotalDamageDealtToChampions();
                        summonerTotalDamageDealt = participant.getStats().getTotalDamageDealt();
                        summonerTotalHeal = participant.getStats().getTotalHeal();
                        summonerTotalMinionsKilled = participant.getStats().getTotalMinionsKilled();
                        summonerPerk0 = participant.getStats().getPerk0();
                        summonerPerk1 = participant.getStats().getPerk1();
                        summonerPerk2 = participant.getStats().getPerk2();
                        summonerPerk3 = participant.getStats().getPerk3();
                        summonerPerk4 = participant.getStats().getPerk4();
                        summonerPerk5 = participant.getStats().getPerk5();
                        summonerPerkPrimaryStyle = participant.getStats().getPerkPrimaryStyle();
                        summonerPerkSubStyle = participant.getStats().getPerkSubStyle();
                    }

                }

                Team[] teams = matchInfo.getTeams();


                for (Team team : teams) {
                    if (team.getTeamId() == summonerTeamId) {
                        summonerWin = team.getWin();

                    }
                }

                holder.tv_champion_final_level.setText("Level " + summonerChampLevel);
                holder.tv_kill_death_assists.setText(new StringBuilder().append(summonerKills).append("/").append(summonerDeaths).append("/").append(summonerAssists).toString());
                holder.tv_champion_cs.setText(String.valueOf(summonerTotalMinionsKilled + " CS"));



                if (summonerItem0 != 0) {
                    Glide.with(context).load(getItemIconUrl(summonerItem0)).into(holder.iv_champion_item0);
                } else {
                    Glide.with(context).load(getDefaultItemIconUrl()).into(holder.iv_champion_item0);
                }

                if (summonerItem1 != 0) {
                    Glide.with(context).load(getItemIconUrl(summonerItem1)).into(holder.iv_champion_item1);
                } else {
                    Glide.with(context).load(getDefaultItemIconUrl()).into(holder.iv_champion_item1);

                }

                if (summonerItem2 != 0) {
                    Glide.with(context).load(getItemIconUrl(summonerItem2)).into(holder.iv_champion_item2);
                } else {
                    Glide.with(context).load(getDefaultItemIconUrl()).into(holder.iv_champion_item2);

                }

                if (summonerItem3 != 0) {
                    Glide.with(context).load(getItemIconUrl(summonerItem3)).into(holder.iv_champion_item3);
                } else {
                    Glide.with(context).load(getDefaultItemIconUrl()).into(holder.iv_champion_item3);
                }

                if (summonerItem4 != 0) {
                    Glide.with(context).load(getItemIconUrl(summonerItem4)).into(holder.iv_champion_item4);
                } else {
                    Glide.with(context).load(getDefaultItemIconUrl()).into(holder.iv_champion_item4);
                }

                if (summonerItem5 != 0) {
                    Glide.with(context).load(getItemIconUrl(summonerItem5)).into(holder.iv_champion_item5);
                } else {
                    Glide.with(context).load(getDefaultItemIconUrl()).into(holder.iv_champion_item5);
                }

                if (summonerItem6 != 0) {
                    Glide.with(context).load(getItemIconUrl(summonerItem6)).into(holder.iv_champion_item6);
                } else {
                    Glide.with(context).load(getDefaultItemIconUrl()).into(holder.iv_champion_item6);
                }

                holder.tv_kda.setText(MatchStats.calculateMatchKda(summonerKills, summonerDeaths, summonerAssists) + " KDA");

                if (summonerWin.equals("Win")) {
                    holder.itemView.setBackgroundColor(Color.parseColor("#A2CFEC"));
                } else {
                    holder.itemView.setBackgroundColor(Color.parseColor("#E3B9B3"));
                }

                Glide.with(context).load(getSummonerSpellUrl(allSpells.get(summonerSpellId1).getId())).into(holder.iv_champion_spell_1);
                Glide.with(context).load(getSummonerSpellUrl(allSpells.get(summonerSpellId2).getId())).into(holder.iv_champion_spell_2);

                break;
            }
        }
    }



    public void setData(ChampionsAndMatches championsAndMatches) {
        this.championsAndMatches = championsAndMatches;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (championsAndMatches != null) {
            return championsAndMatches.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_champion_name;
        private TextView tv_game_type;
        private ImageView iv_champion_icon;
        private ImageView iv_champion_spell_1;
        private ImageView iv_champion_spell_2;
        private TextView tv_kill_death_assists;
        private TextView tv_kda;
        private TextView tv_champion_final_level;
        private TextView tv_champion_cs;
        private ImageView iv_champion_item0;
        private ImageView iv_champion_item1;
        private ImageView iv_champion_item2;
        private ImageView iv_champion_item3;
        private ImageView iv_champion_item4;
        private ImageView iv_champion_item5;
        private ImageView iv_champion_item6;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_champion_name = (TextView) itemView.findViewById(R.id.tv_champion_name);
            tv_game_type = (TextView) itemView.findViewById(R.id.tv_game_type);
            iv_champion_icon = (ImageView) itemView.findViewById(R.id.iv_champion_icon);
            iv_champion_spell_1 = (ImageView) itemView.findViewById(R.id.iv_champion_spell_1);
            iv_champion_spell_2 = (ImageView) itemView.findViewById(R.id.iv_champion_spell_2);
            tv_kill_death_assists = (TextView) itemView.findViewById(R.id.tv_kill_death_assists);
            tv_kda = (TextView) itemView.findViewById(R.id.tv_kda);
            tv_champion_final_level = (TextView) itemView.findViewById(R.id.tv_champion_final_level);
            tv_champion_cs = (TextView) itemView.findViewById(R.id.tv_champion_cs);
            iv_champion_item0 = (ImageView) itemView.findViewById(R.id.iv_champion_item0);
            iv_champion_item1 = (ImageView) itemView.findViewById(R.id.iv_champion_item1);
            iv_champion_item2 = (ImageView) itemView.findViewById(R.id.iv_champion_item2);
            iv_champion_item3 = (ImageView) itemView.findViewById(R.id.iv_champion_item3);
            iv_champion_item4 = (ImageView) itemView.findViewById(R.id.iv_champion_item4);
            iv_champion_item5 = (ImageView) itemView.findViewById(R.id.iv_champion_item5);
            iv_champion_item6 = (ImageView) itemView.findViewById(R.id.iv_champion_item6);
        }
    }
}
