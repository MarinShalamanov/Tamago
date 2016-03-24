package com.marinshalamanov.tamago;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        SharedPreferences preferences = getSharedPreferences("main", MODE_PRIVATE);
        int statAppsOpened = preferences.getInt("statAppsOpened", 0);

        TextView statAppsOpenedTV = (TextView) findViewById(R.id.statAppOpened);
        statAppsOpenedTV.setText(Integer.toString(statAppsOpened));

        int statEggsBroken = preferences.getInt("statEggsBroken", 0);
        TextView statEggsBrokenTV = (TextView) findViewById(R.id.statEggsBroken);
        statEggsBrokenTV.setText(Integer.toString(statEggsBroken));
    }
}
