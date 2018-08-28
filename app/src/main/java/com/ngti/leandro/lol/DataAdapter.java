package com.ngti.leandro.lol;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ngti.leandro.lol.model.champions.Champion;
import com.ngti.leandro.lol.model.ddragon.RunesContainer;
import com.ngti.leandro.lol.model.match.MatchContainer;
import com.ngti.leandro.lol.model.match.Participants;
import com.ngti.leandro.lol.model.match.ParticipantsIdentities;
import com.ngti.leandro.lol.model.match.Team;
import com.ngti.leandro.lol.model.matchlist.AllMatches;
import com.ngti.leandro.lol.recentmatches.Champions;
import com.ngti.leandro.lol.recentmatches.Matches;
import com.ngti.leandro.lol.utils.MatchStats;

import java.util.Map;

import static com.ngti.leandro.lol.recentmatches.RecentMatchesActivity.summoner;
import static com.ngti.leandro.lol.recentmatches.SummonerMatchStats.loadUrlIntoHolder;
import static com.ngti.leandro.lol.recentmatches.SummonerMatchStats.setTextIntoHolder;
import static com.ngti.leandro.lol.splash.GetSpells.allSpells;
import static com.ngti.leandro.lol.utils.GameModes.getMatchModeByQueueId;
import static com.ngti.leandro.lol.utils.Icons.getChampionIconUrl;
import static com.ngti.leandro.lol.utils.Icons.getItemIconUrl;
import static com.ngti.leandro.lol.utils.Icons.getPrimaryPerkUrl;
import static com.ngti.leandro.lol.utils.Icons.getSecondaryPerkUrl;
import static com.ngti.leandro.lol.utils.Icons.getSummonerSpellUrl;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private Matches matches;
    private Champions champions;
    private View singleMatchView;

    private final Callback mCallback;

    public DataAdapter(final Callback callback) {
        mCallback = callback;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recent_match, parent, false);
        parent.setBackgroundColor(Color.parseColor("#EDEDED"));
        singleMatchView = view.findViewById(R.id.single_match_view);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {

        Context context = holder.iv_champion_icon.getContext();
        String championName = "";
        String championId = "";
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
        int summonerPerk0 = 0;
        int summonerPerk1;
        int summonerPerk2;
        int summonerPerk3;
        int summonerPerk4;
        int summonerPerk5;
        int summonerPerkPrimaryStyle = 0;
        int summonerPerkSubStyle = 0;

        AllMatches allMatches = matches.getAllMatches(position);

        long matchId = matches.getAllMatches(position).getGameId();

        MatchContainer matchInfo = matches.getMatchGeneral(matchId);

        for (Map.Entry<Integer, Champion> entry : champions.entrySet()) {
            Integer key = entry.getKey();


            if (allMatches.getChampion().equals(key)) {
                championName = entry.getValue().getChampionName();
                championId = entry.getValue().getChampionId();

                loadUrlIntoHolder(getChampionIconUrl(championId), holder.iv_champion_icon, context);
                setTextIntoHolder(championName, holder.tv_champion_name);
                setTextIntoHolder(getMatchModeByQueueId(allMatches.getQueue()), holder.tv_game_type);

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

                loadUrlIntoHolder(getPrimaryPerkUrl(RunesContainer.getRuneIconById(summonerPerkPrimaryStyle)), holder.iv_champion_rune_primary_style, context);
                loadUrlIntoHolder(getSecondaryPerkUrl(summonerPerk0), holder.iv_champion_rune_secondary_style, context);

                setTextIntoHolder("Level " + summonerChampLevel, holder.tv_champion_final_level);
                setTextIntoHolder(String.valueOf(summonerKills) + "/" + summonerDeaths + "/" + summonerAssists, holder.tv_kill_death_assists);
                setTextIntoHolder((String.valueOf(summonerTotalMinionsKilled + " CS")), holder.tv_champion_cs);
                setTextIntoHolder(MatchStats.calculateMatchKda(summonerKills, summonerDeaths, summonerAssists) + " KDA", holder.tv_kda);

                loadUrlIntoHolder(getItemIconUrl(summonerItem0), holder.iv_champion_item0, context);
                loadUrlIntoHolder(getItemIconUrl(summonerItem1), holder.iv_champion_item1, context);
                loadUrlIntoHolder(getItemIconUrl(summonerItem2), holder.iv_champion_item2, context);
                loadUrlIntoHolder(getItemIconUrl(summonerItem3), holder.iv_champion_item3, context);
                loadUrlIntoHolder(getItemIconUrl(summonerItem4), holder.iv_champion_item4, context);
                loadUrlIntoHolder(getItemIconUrl(summonerItem5), holder.iv_champion_item5, context);
                loadUrlIntoHolder(getItemIconUrl(summonerItem6), holder.iv_champion_item6, context);

                if (allSpells != null) {
                    loadUrlIntoHolder(getSummonerSpellUrl(allSpells.get(summonerSpellId1).getId()), holder.iv_champion_spell_1, context);
                    loadUrlIntoHolder(getSummonerSpellUrl(allSpells.get(summonerSpellId2).getId()), holder.iv_champion_spell_2, context);
                }


                if (summonerWin.equals("Win")) {
                    holder.itemView.setBackgroundColor(Color.parseColor("#A2CFEC"));
                } else {
                    holder.itemView.setBackgroundColor(Color.parseColor("#E3B9B3"));
                }

                break;
            }
        }
    }

    public void setData(Matches matches) {
        this.matches = matches;
        notifyDataSetChanged();
    }

    public void setData(Champions champions) {
        this.champions = champions;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (matches != null) {
            return matches.size();
        }
        return 0;
    }

    public interface Callback{
        void onMatchClicked(AllMatches match);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv_champion_name;
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
        private ImageView iv_champion_rune_primary_style;
        private ImageView iv_champion_rune_secondary_style;

        ViewHolder(View itemView) {
            super(itemView);

            final View view = itemView.findViewById(R.id.single_match_view);
            view.setOnClickListener(this);

            tv_champion_name = itemView.findViewById(R.id.tv_champion_name);
            tv_game_type = itemView.findViewById(R.id.tv_game_type);
            iv_champion_icon = itemView.findViewById(R.id.iv_champion_icon);
            iv_champion_spell_1 = itemView.findViewById(R.id.iv_champion_spell_1);
            iv_champion_spell_2 = itemView.findViewById(R.id.iv_champion_spell_2);
            tv_kill_death_assists = itemView.findViewById(R.id.tv_kill_death_assists);
            tv_kda = itemView.findViewById(R.id.tv_kda);
            tv_champion_final_level = itemView.findViewById(R.id.tv_champion_final_level);
            tv_champion_cs = itemView.findViewById(R.id.tv_champion_cs);
            iv_champion_item0 = itemView.findViewById(R.id.iv_champion_item0);
            iv_champion_item1 = itemView.findViewById(R.id.iv_champion_item1);
            iv_champion_item2 = itemView.findViewById(R.id.iv_champion_item2);
            iv_champion_item3 = itemView.findViewById(R.id.iv_champion_item3);
            iv_champion_item4 = itemView.findViewById(R.id.iv_champion_item4);
            iv_champion_item5 = itemView.findViewById(R.id.iv_champion_item5);
            iv_champion_item6 = itemView.findViewById(R.id.iv_champion_item6);
            iv_champion_rune_primary_style = itemView.findViewById(R.id.iv_champion_perk_primary_style);
            iv_champion_rune_secondary_style = itemView.findViewById(R.id.iv_champion_perk_secondary_style);
        }

        @Override
        public void onClick(View v) {
            mCallback.onMatchClicked(matches.getAllMatches(getAdapterPosition()));
        }
    }
}
