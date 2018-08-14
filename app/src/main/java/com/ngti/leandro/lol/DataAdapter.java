package com.ngti.leandro.lol;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ngti.leandro.lol.champions.Champions;
import com.ngti.leandro.lol.allMatches.AllMatches;
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
        if (championsAndMatches!= null) {
            return championsAndMatches.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_champion_name;
        private TextView tv_game_type;
        private ImageView iv_champion_icon;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_champion_name = (TextView) itemView.findViewById(R.id.tv_champion_name);
            tv_game_type = (TextView) itemView.findViewById(R.id.tv_game_type);
            iv_champion_icon = (ImageView) itemView.findViewById(R.id.iv_champion_icon);


        }
    }
}
