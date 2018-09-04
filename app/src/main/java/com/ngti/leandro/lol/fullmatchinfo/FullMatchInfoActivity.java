package com.ngti.leandro.lol.fullmatchinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.ngti.leandro.lol.R;
import com.ngti.leandro.lol.model.match.MatchContainer;
import com.ngti.leandro.lol.recentmatches.Champions;
import com.ngti.leandro.lol.recentmatches.Matches;
import com.ngti.leandro.lol.recentmatches.RecentMatchesActivity;

import java.net.HttpURLConnection;
import java.util.Objects;

public class FullMatchInfoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FullMatchInfoDataAdapter adapter;
    private ProgressBar progressBarFullMatchInfo;

    final Context context = this;

    private static final String KEY_SERVER_NAME = "server";
    private static final String KEY_SUMMONER_NAME = "summoner";
    private static String KEY_MATCH_ID = "matchId";
    private String server;
    public static long matchId;
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
        progressBarFullMatchInfo = findViewById(R.id.progressBarFullMatchInfo);

        processIntent(getIntent());

        new LoadMatchAndChampionsFullMatchInfo(this).execute(server, String.valueOf(matchId));

        initViews();

    }

    private void initViews() {
        recyclerView = findViewById(R.id.recycler_view_full_match_info);
        progressBarFullMatchInfo = findViewById(R.id.progressBarFullMatchInfo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new FullMatchInfoDataAdapter();
        recyclerView.setAdapter(adapter);
    }


    private void processIntent(Intent intent) {
        final Bundle extras = intent.getExtras();

        if (extras != null) {
            server = extras.getString(KEY_SERVER_NAME);
            summoner = extras.getString(KEY_SUMMONER_NAME);
            matchId = extras.getLong(KEY_MATCH_ID);
        }
    }


    public void fullMatchInfoLoaded(FullMatchInfoAndChampions matchAndChampions, int matchLoadResponse, int championsLoadResponse) {
        if (matchLoadResponse == HttpURLConnection.HTTP_OK && championsLoadResponse == HttpURLConnection.HTTP_OK) {
            adapter.setData(matchAndChampions);
        } else {
            if (matchLoadResponse != 0 || championsLoadResponse != 0) {
                Toast.makeText(context, "API Error. Try again later", Toast.LENGTH_LONG).show();
                finish();
            }
        }
        progressBarFullMatchInfo.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

}
