package com.ngti.leandro.lol.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ngti.leandro.lol.R;
import com.ngti.leandro.lol.search.SummonerServerSearchActivity;
import com.ngti.leandro.lol.utils.CheckNetwork;

import java.net.HttpURLConnection;

import timber.log.Timber;

public class SplashActivity extends AppCompatActivity {

    private Button buttonAddSummonerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Context context = this;

        if (CheckNetwork.isInternetAvailable(context)) {

            new GetGameVersion(this).execute();
            new GetSpells(this).execute();

        } else {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show();
        }

        buttonAddSummonerName = findViewById(R.id.addSummonerButton);


        buttonAddSummonerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, SummonerServerSearchActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

    }

    public void apiVersionsLoaded(Integer apiResponseCode) {
        if (apiResponseCode == HttpURLConnection.HTTP_OK) {
            Timber.i("Ddragon versions loaded %s", apiResponseCode);
        } else {
            Toast.makeText(this, "Ddragon API error " + apiResponseCode, Toast.LENGTH_LONG).show();
        }
    }


}
