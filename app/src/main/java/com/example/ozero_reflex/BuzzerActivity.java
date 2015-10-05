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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

// The UI for the buzzer mode. Handles dialogs and lets buttons call things from Buzzer
public class BuzzerActivity extends AppCompatActivity {

    private Buzzer buzzer = new Buzzer(BuzzerActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Layout set by SelectPlayersActivity depending on the amount of players chosen
        Bundle parameters = getIntent().getExtras();
        setContentView(parameters.getInt("layout"));
        int players = parameters.getInt("players");
        // Amount of players is passed to Buzzer for use there too
        buzzer.setPlayers(players);

        // Load statistics file
        buzzer.loadFromFile();
    }

    // Creates a dialog to bring up a message and reset buttonWasTapped for more buzzer mode rounds
    private void buildMessageDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BuzzerActivity.this);
        builder.setCancelable(false);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.dialog_ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        buzzer.setButtonWasTapped(false);
                    }
                });
        builder.show();
    }

    // The following four methods are called when the respective player buttons are tapped.
    public void playerOneTap(View v) {
        // buttonTap returns true if this was the first button tapped. If it does, announce it
        // with a dialog
        if(buzzer.buttonTap(0)) {
            buildMessageDialog("Player 1 tapped first!");
        }
    }
    public void playerTwoTap(View v) {
        if(buzzer.buttonTap(1)) {
            buildMessageDialog("Player 2 tapped first!");
        }
    }
    public void playerThreeTap(View v) {
        if(buzzer.buttonTap(2)) {
            buildMessageDialog("Player 3 tapped first!");
        }
    }
    public void playerFourTap(View v) {
        if(buzzer.buttonTap(3)) {
            buildMessageDialog("Player 4 tapped first!");
        }
    }
}
