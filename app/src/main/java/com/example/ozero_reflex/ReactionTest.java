package com.example.ozero_reflex;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Alexander Ozero on 04/10/2015.
 */
public class ReactionTest {

    private TextView text;
    private Button button;
    private Context context;

    private long startTime;
    private long reactionTime;
    public boolean timerStarted = false;
    // For statistics saving
    private static final String FILENAME = "stats.sav";
    Stats stats = new Stats();

    static final int minTime = 10;
    static final int maxTime = 2000;

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

    public ReactionTest(Context context) {
        this.context = context;
    }

    public ReactionTest(View text, View button, Context context) {
        this.text = (TextView) text;
        this.button = (Button) button;
        this.context = context;
    }

    public void pause() {
        timerH.removeCallbacks(timerR);
    }

    public void resume() {
        loadFromFile();
    }

    // Pick a random time for the timer
    public int randomTime() {
        Random random = new Random();
        return random.nextInt(maxTime - minTime) + minTime;
    }

    public void startTest() {
        text.setText(R.string.reaction_timer_wait);
        timerH.postDelayed(timerR, randomTime());
    }

    public void doTest() {
        reactionTime = System.currentTimeMillis() - startTime;
        timerStarted = false;
        stats.addReactionStat((int) reactionTime);
        saveInFile();
    }

    public Boolean getTimerStarted() {
        return timerStarted;
    }

    public Long getReactionTime() {
        return reactionTime;
    }

    public Stats getStats() {
        loadFromFile();
        return stats;
    }

    public void clearStats() {
        stats.clearStats();
        saveInFile();
    }

    // From CMPUT 301 Lab 3
    public void loadFromFile() {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
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
    public void saveInFile() {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
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
