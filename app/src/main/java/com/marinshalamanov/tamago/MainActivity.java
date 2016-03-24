package com.marinshalamanov.tamago;

import android.content.Intent;
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
}
