/*
    Copyright 2015 Alexander Ozero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

           http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */
package com.example.ozero_reflex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

// The UI to select the amount of players for the buzzer mode.
public class SelectPlayersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_players);
    }

    // The start*Player methods pass layouts and the number of players to BuzzerActivity so it knows
    // what layout to use and where in the list to add the stats to.
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
