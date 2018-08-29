package com.ngti.leandro.lol.fullmatchinfo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.ngti.leandro.lol.recentmatches.Matches;
import com.ngti.leandro.lol.recentmatches.MatchesDataAdapter;

import java.util.Map;

public class MatchInfoDataAdapter extends RecyclerView.Adapter<MatchesDataAdapter.ViewHolder> {

    private FullMatchInfoActivity fullMatchInfoActivity;
    private Map match;


    public MatchInfoDataAdapter() {
    }

    MatchInfoDataAdapter(FullMatchInfoActivity fullMatchInfoActivity) {
        this.fullMatchInfoActivity = fullMatchInfoActivity;
    }

    @NonNull
    @Override
    public MatchesDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesDataAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setData(Map match) {
        this.match = match;
        notifyDataSetChanged();
    }
}
