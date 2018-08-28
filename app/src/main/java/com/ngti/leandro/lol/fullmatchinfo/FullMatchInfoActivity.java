package com.ngti.leandro.lol.fullmatchinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ngti.leandro.lol.R;

public class FullMatchInfoActivity extends AppCompatActivity {

    private static final String KEY_SERVER_NAME = "server";
    private static final String KEY_SUMMONER_NAME = "summoner";
    private static String KEY_MATCH_ID = "matchId";

    private String server;
    private String matchId;
    public static String summoner;

    public static Intent getLaunchIntent(Context context, String serverName, String summonerName, String matchId) {
        final Intent intent = new Intent(context, FullMatchInfoActivity.class);

        final Bundle arguments = new Bundle(3);
        arguments.putString(KEY_SERVER_NAME, serverName);
        arguments.putString(KEY_SUMMONER_NAME, summonerName);
        arguments.putString(KEY_MATCH_ID, matchId);


        intent.putExtras(arguments);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_match_info);

        processIntent(getIntent());
    }

    private void processIntent(Intent intent) {
        final Bundle extras = intent.getExtras();

        if (extras != null) {
            server = extras.getString(KEY_SERVER_NAME);
            summoner = extras.getString(KEY_SUMMONER_NAME);
            matchId = extras.getString(KEY_MATCH_ID);
        }
    }

    @Override
    public void onBackPressed() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
