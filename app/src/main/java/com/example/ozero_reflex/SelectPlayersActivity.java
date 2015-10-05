package com.example.ozero_reflex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SelectPlayersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_players);
    }

    public void startTwoPlayer(View view)
    {
        Intent intent = new Intent(this, BuzzerActivity.class);
        intent.putExtra("layout", R.layout.two_player);
        intent.putExtra("players", 2);
        startActivity(intent);
    }

    public void startThreePlayer(View view)
    {
        Intent intent = new Intent(this, BuzzerActivity.class);
        intent.putExtra("layout", R.layout.three_player);
        intent.putExtra("players", 3);
        startActivity(intent);
    }

    public void startFourPlayer(View view)
    {
        Intent intent = new Intent(this, BuzzerActivity.class);
        intent.putExtra("layout", R.layout.four_player);
        intent.putExtra("players", 4);
        startActivity(intent);
    }
}
