package com.ngti.leandro.lol;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ngti.leandro.lol.Match.ParticipantsIdentities;
import com.ngti.leandro.lol.allMatches.AllMatches;
import com.ngti.leandro.lol.champions.Champions;
import com.ngti.leandro.lol.recent.matches.ChampionsAndMatches;

import java.util.Map;

import static com.ngti.leandro.lol.utils.GameModes.getMatchModeByQueueId;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ChampionsAndMatches championsAndMatches;

    public DataAdapter() {
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recent_match, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        Context context = holder.iv_champion_icon.getContext();
        String championName = "";

        AllMatches match = championsAndMatches.get(position);

        for (Map.Entry<Integer, Champions> entry : championsAndMatches.entrySet()) {
            Integer key = (Integer) entry.getKey();

            if (match.getChampion().equals(key)) {
                championName = entry.getValue().getKey();
                Glide.with(context).load("http://ddragon.leagueoflegends.com/cdn/8.15.1/img/champion/" + championName + ".png").into(holder.iv_champion_icon);
                holder.tv_champion_name.setText(entry.getValue().getName());
                holder.tv_game_type.setText(getMatchModeByQueueId(match.getQueue()));


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
        }
    }
}
