package com.example.ozero_reflex;

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

    public void startReactionTimer(View view) {
        Intent intent = new Intent(this, ReactionTestActivity.class);
        startActivity(intent);
    }

    public void startSelectPlayers(View view) {
        Intent intent = new Intent(this, SelectPlayersActivity.class);
        startActivity(intent);
    }

    public void startStatistics(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }
}
