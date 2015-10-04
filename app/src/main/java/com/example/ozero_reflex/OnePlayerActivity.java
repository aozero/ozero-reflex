package com.example.ozero_reflex;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;

import com.google.gson.Gson;

public class OnePlayerActivity extends AppCompatActivity {

    private long startTime;
    private long reactionTime;
    private boolean timerStarted = false;
    // To assist with onPause() and onResume()
    private boolean wasPaused = false;
    private boolean messageDismissed = false;
    // For statistics saving
    private static final String FILENAME = "stats.sav";
    Stats stats = new Stats();

    static final int minTime = 10;
    static final int maxTime = 2000;

    private Button button;
    private TextView text;

    // Handlers and Runnables:
    // https://stackoverflow.com/questions/4597690/android-timer-how 2015-09-27
    // User: Dave.B
    Handler timerH = new Handler();
    Runnable timerR = new Runnable() {

        @Override
        public void run() {
            startTime = System.currentTimeMillis();
            text.setText(R.string.reaction_timer_now);
            timerStarted = true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_player);
        text = (TextView) findViewById(R.id.reactionText);
        button = (Button) findViewById(R.id.reactionButton);
        // Loads the stats save file
        loadFromFile();
        // Bring up the instructions
        buildMessageDialog("When the text changes, tap as quickly as you can. " +
                "As you close this dialog, the timer will begin!");
        // Set up the onClick listener
        initializeListener();
    }

    @Override
    public void onPause() {
        super.onPause();
        timerH.removeCallbacks(timerR);
        wasPaused = true;
        //saveInFile();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (wasPaused) {
            loadFromFile();
            if (messageDismissed) {
                buildMessageDialog("When the text changes, tap as quickly as you can. " +
                        "As you close this dialog, the timer will begin!");
                text.setText(R.string.reaction_timer_wait);
                //timerH.postDelayed(timerR, randomTime());
                //wasPaused = false;
            } // else Let things resume as they were
        }
    }

    private void buildMessageDialog(String message) {
        messageDismissed = false;
        AlertDialog.Builder builder = new AlertDialog.Builder(OnePlayerActivity.this);
        builder.setCancelable(false);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.dialog_ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        messageDismissed = true;
                        text.setText(R.string.reaction_timer_wait);
                        timerH.postDelayed(timerR, randomTime());
                    }
                });
        builder.show();
    }

    // Pick a random time for the timer
    public int randomTime() {
        Random random = new Random();
        return random.nextInt(maxTime - minTime) + minTime;
    }

    private void initializeListener() {
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (timerStarted) {
                    reactionTime = System.currentTimeMillis() - startTime;
                    timerStarted = false;
                    stats.addReactionStat((int) reactionTime);
                    saveInFile();
                    buildMessageDialog("Your reaction time is " + String.valueOf(reactionTime)
                            + "ms.");
                } else {
                    timerH.removeCallbacks(timerR);
                    text.setText(R.string.reaction_timer_wait);
                    buildMessageDialog("Wait for the text to change! Timer reset.");
                }
            }
        });
    }

    // From CMPUT 301 Lab 3
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            stats = gson.fromJson(in, Stats.class);
        } catch (FileNotFoundException e) {
            stats = new Stats();
        } /* catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } */
    }

    // From CMPUT 301 Lab 3
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(stats, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }
}
