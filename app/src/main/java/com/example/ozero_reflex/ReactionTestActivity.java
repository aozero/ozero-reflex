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
import android.widget.Button;
import android.widget.TextView;

// The UI for the reaction timer test. Handles dialogs and button clicks.
public class ReactionTestActivity extends AppCompatActivity {

    // To assist with onPause() and onResume()
    private boolean wasPaused = false;
    private boolean messageDismissed = false;

    private Button button;
    private TextView text;
    private ReactionTest reactionTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_test);
        text = (TextView) findViewById(R.id.reactionText);
        button = (Button) findViewById(R.id.reactionButton);
        reactionTest = new ReactionTest(text, ReactionTestActivity.this);
        // Loads the stats save file
        reactionTest.loadFromFile();
        // Bring up the instructions
        buildMessageDialog("When the text changes, tap as quickly as you can. " +
                "As you close this dialog, the timer will begin!");
        // Set up the onClick listener
        initializeListener();
    }

    @Override
    public void onPause() {
        super.onPause();
        reactionTest.pause();
        wasPaused = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (wasPaused) { // if wasPaused then this is not onResume() being called by onCreate()
            reactionTest.resume();
            if (messageDismissed) {
                buildMessageDialog("When the text changes, tap as quickly as you can. " +
                        "As you close this dialog, the timer will begin!");
                text.setText(R.string.reaction_timer_wait);
            } // else Let things resume as they were
        }
    }

    private void buildMessageDialog(String message) {
        messageDismissed = false;
        AlertDialog.Builder builder = new AlertDialog.Builder(ReactionTestActivity.this);
        builder.setCancelable(false);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.dialog_ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        messageDismissed = true;
                        reactionTest.startTest();
                    }
                });
        builder.show();
    }

    private void initializeListener() {
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (reactionTest.getTimerStarted()) {
                    reactionTest.doTest();
                    buildMessageDialog("Your reaction time is " +
                            String.valueOf(reactionTest.getReactionTime()) + "ms.");
                } else {
                    reactionTest.pause();
                    text.setText(R.string.reaction_timer_wait);
                    buildMessageDialog("Wait for the text to change! Timer reset.");
                }
            }
        });
    }
}
