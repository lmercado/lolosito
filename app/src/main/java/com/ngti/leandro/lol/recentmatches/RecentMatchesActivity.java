package com.ngti.leandro.lol.recentmatches;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.ngti.leandro.lol.DataAdapter;
import com.ngti.leandro.lol.R;

public class RecentMatchesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private ProgressBar progressBarRecentMatches;
    private String server;
    public static String summoner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_matches);

        server = getIntent().getStringExtra("server");
        summoner = getIntent().getStringExtra("summoner").toLowerCase();

        try {
            initViews();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initViews() throws InterruptedException {
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        progressBarRecentMatches = findViewById(R.id.progressBarRecentMatches);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new DataAdapter();
        recyclerView.setAdapter(adapter);
        new LoadMatchAndChampions(this).execute(server, summoner);
    }

    public void matchesAndChampionsLoaded(ChampionsAndMatches champsAndMatches) {
        adapter.setData(champsAndMatches);
        progressBarRecentMatches.setVisibility(View.INVISIBLE);
    }
}

