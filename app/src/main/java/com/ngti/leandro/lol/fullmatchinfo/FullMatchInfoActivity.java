package com.ngti.leandro.lol.fullmatchinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ngti.leandro.lol.R;
import com.ngti.leandro.lol.recentmatches.Matches;

import java.net.HttpURLConnection;
import java.util.Map;

public class FullMatchInfoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MatchInfoDataAdapter adapter;
    private ProgressBar progressBarFullMatchInfo;

    final Context context = this;

    private static final String KEY_SERVER_NAME = "server";
    private static final String KEY_SUMMONER_NAME = "summoner";
    private static String KEY_MATCH_ID = "matchId";
    private String server;
    private long matchId;
    public static String summoner;

    public static Intent getLaunchIntent(Context context, String serverName, String summonerName, long matchId) {
        final Intent intent = new Intent(context, FullMatchInfoActivity.class);

        final Bundle arguments = new Bundle(3);
        arguments.putString(KEY_SERVER_NAME, serverName);
        arguments.putString(KEY_SUMMONER_NAME, summonerName);
        arguments.putLong(KEY_MATCH_ID, matchId);

        intent.putExtras(arguments);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_match_info);

        processIntent(getIntent());

        try {
            initViews();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initViews() throws InterruptedException {
//        recyclerView = findViewById(R.id.card_recycler_view);
        progressBarFullMatchInfo = findViewById(R.id.progressBarFullMatchInfo);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new MatchInfoDataAdapter();
//        recyclerView.setAdapter(adapter);

//        new LoadChampions(this).execute(server);
        new LoadSingleMatch(this).execute(server, String.valueOf(matchId));

    }

    private void processIntent(Intent intent) {
        final Bundle extras = intent.getExtras();

        if (extras != null) {
            server = extras.getString(KEY_SERVER_NAME);
            summoner = extras.getString(KEY_SUMMONER_NAME);
            matchId = extras.getLong(KEY_MATCH_ID);
        }
    }

    public void matchLoaded(Map match, Integer responseByMatchId) {
        if (responseByMatchId == HttpURLConnection.HTTP_OK) {
            adapter.setData(match);
        } else {
            if (responseByMatchId != 0) {
                Toast.makeText(context, "API Error. Try again later", Toast.LENGTH_LONG).show();
                finish();
            }
        }
        progressBarFullMatchInfo.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
