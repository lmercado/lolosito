package com.ngti.leandro.lol.recentmatches;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ngti.leandro.lol.DataAdapter;
import com.ngti.leandro.lol.R;

import java.net.HttpURLConnection;

public class RecentMatchesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private ProgressBar progressBarRecentMatches;
    private String server;
    public static String summoner;
    final Context context = this;


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

        new LoadChampions(this).execute(server);
        new LoadMatches(this).execute(server, summoner);
    }

    public void championsLoaded(Champions champions, Integer response) {
        if (response == HttpURLConnection.HTTP_OK) {
            adapter.setData(champions);
        } else {
            if (response != 0 ) {
                Toast.makeText(context, "API Error. Try again later", Toast.LENGTH_LONG).show();
                finish();
            }

        }
    }

    public void matchesLoaded(Matches matches, Integer responseSummoner, Integer responseMatches, Integer responseByMatchId) {
        if (responseByMatchId == HttpURLConnection.HTTP_OK && responseMatches == HttpURLConnection.HTTP_OK && responseSummoner == HttpURLConnection.HTTP_OK) {
            adapter.setData(matches);
        } else {
            if (responseByMatchId != 0 || responseMatches != 0 || responseSummoner != 0) {
                Toast.makeText(context, "API Error. Try again later", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

}

