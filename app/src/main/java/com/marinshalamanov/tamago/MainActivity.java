package com.marinshalamanov.tamago;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartClicked(View view) {
        Intent intent = new Intent(this, TamagoActivity.class);
        startActivity(intent);
    }

    public void onResetClicked(View view) {
        SharedPreferences preferences = getSharedPreferences("main", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("clicks", 0);
        editor.apply();
    }
}
