package com.ngti.leandro.lol.summoner.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ngti.leandro.lol.R;
import com.ngti.leandro.lol.recent.matches.RecentMatchesActivity;

public class SummonerSearchActivity extends AppCompatActivity {

    private Button searchButton;
    private Spinner serversSpinner;
    private EditText summonerTextEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        serversSpinner = findViewById(R.id.serversSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.servers_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        serversSpinner.setAdapter(adapter);

        summonerTextEditor = findViewById(R.id.summonerEditText);

        searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SummonerSearchActivity.this, RecentMatchesActivity.class);
                Bundle b = new Bundle();

                b.putString("server", serversSpinner.getSelectedItem().toString());
                b.putString("summoner", summonerTextEditor.getText().toString());

                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

}
