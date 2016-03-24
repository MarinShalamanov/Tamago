package com.marinshalamanov.tamago;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TamagoActivity extends AppCompatActivity {

    private int numClicks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tamago);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences preferences = getSharedPreferences("main", MODE_PRIVATE);
        numClicks = preferences.getInt("clicks", 0);

        TextView score = (TextView) findViewById(R.id.score);
        score.setText(Integer.toString(numClicks));
    }

    public void eggClicked (View view) {
        numClicks++;

        SharedPreferences preferences = getSharedPreferences("main", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("clicks", numClicks);
        editor.apply();

        TextView score = (TextView) findViewById(R.id.score);
        score.setText(Integer.toString(numClicks));

if (numClicks >= 10) {
    ImageView egg = (ImageView)findViewById(R.id.egg);
    ImageView broken = (ImageView)findViewById(R.id.broken);

    egg.setVisibility(View.INVISIBLE);
    broken.setVisibility(View.VISIBLE);
}
    }


}
