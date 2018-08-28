package com.ngti.leandro.lol.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.ngti.leandro.lol.R;
import com.ngti.leandro.lol.recentmatches.RecentMatchesActivity;
import com.ngti.leandro.lol.utils.CheckNetwork;

public class SummonerServerSearchActivity extends AppCompatActivity {

    private ImageButton searchButton;
    private Spinner serversSpinner;
    private EditText summonerTextEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final Context context = this;

        serversSpinner = findViewById(R.id.serversSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.servers_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        serversSpinner.setAdapter(adapter);

        summonerTextEditor = findViewById(R.id.summonerEditText);

        searchButton = findViewById(R.id.searchButton);

        summonerTextEditor.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() == 0) {
                    searchButton.setVisibility(View.INVISIBLE);
                } else {
                    searchButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Do Nothing
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do Nothing
            }

        });

        serversSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Do nothing
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (CheckNetwork.isInternetAvailable(context)) {
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    final String serverName = serversSpinner.getSelectedItem().toString();
                    final String summonerName = summonerTextEditor.getText().toString();
                    final Intent intent = RecentMatchesActivity.getLaunchIntent(SummonerServerSearchActivity.this, serverName, summonerName);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                } else {
                    Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}
