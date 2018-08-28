package com.ngti.leandro.lol.recentmatches;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ngti.leandro.lol.DataAdapter;
import com.ngti.leandro.lol.R;
import com.ngti.leandro.lol.fullmatchinfo.FullMatchInfoActivity;
import com.ngti.leandro.lol.model.matchlist.AllMatches;
import com.ngti.leandro.lol.search.SummonerServerSearchActivity;

import java.net.HttpURLConnection;

import timber.log.Timber;

public class RecentMatchesActivity extends AppCompatActivity implements DataAdapter.Callback {

    private static final String KEY_SERVER_NAME = "server";
    private static final String KEY_SUMMONER_NAME = "summoner";

    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private ProgressBar progressBarRecentMatches;
    private String server;
    public static String summoner;
    final Context context = this;

    public static Intent getLaunchIntent(Context context, String serverName, String summonerName) {
        final Intent intent = new Intent(context, RecentMatchesActivity.class);

        final Bundle arguments = new Bundle(2);
        arguments.putString(KEY_SERVER_NAME, serverName);
        arguments.putString(KEY_SUMMONER_NAME, summonerName);

        intent.putExtras(arguments);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_matches);

        processIntent(getIntent());

        try {
            initViews();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMatchClicked(AllMatches match) {
        String matchId = String.valueOf(match.getGameId());
        final Intent intent = FullMatchInfoActivity.getLaunchIntent(RecentMatchesActivity.this, KEY_SERVER_NAME, KEY_SUMMONER_NAME, matchId);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void processIntent(Intent intent) {
        final Bundle extras = intent.getExtras();

        if (extras != null) {
            server = extras.getString(KEY_SERVER_NAME);
            summoner = extras.getString(KEY_SUMMONER_NAME);
        }
    }

    private void initViews() throws InterruptedException {
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        progressBarRecentMatches = findViewById(R.id.progressBarRecentMatches);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new DataAdapter(this);
        recyclerView.setAdapter(adapter);

        new LoadChampions(this).execute(server);
        new LoadMatches(this).execute(server, summoner);

    }


    public void championsLoaded(Champions champions, Integer response) {
        if (response == HttpURLConnection.HTTP_OK) {
            adapter.setData(champions);
        } else {
            if (response != 0) {
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

    @Override
    public void onBackPressed() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


}

