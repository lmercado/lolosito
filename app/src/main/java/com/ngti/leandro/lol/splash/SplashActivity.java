package com.ngti.leandro.lol.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ngti.leandro.lol.R;
import com.ngti.leandro.lol.summoner.search.SummonerSearchActivity;

public class SplashActivity extends AppCompatActivity {

    private Button buttonAddSummonerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        buttonAddSummonerName = findViewById(R.id.addSummonerButton);

        buttonAddSummonerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, SummonerSearchActivity.class);
                startActivity(intent);
            }
        });
    }


}
