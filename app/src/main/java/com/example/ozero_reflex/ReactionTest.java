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

import android.content.Context;
import android.os.Handler;
import android.view.View;
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
import java.util.Random;

// Used for the single player reaction timer mode. Handles reaction stats, testing, and
// reaction time measurement
public class ReactionTest {

    private TextView text;
    private Context context;
    // For reaction time measuring
    private long startTime;
    private long reactionTime;
    private boolean timerStarted = false;
    // For statistics saving
    private static final String FILENAME = "stats.sav";
    private Statistics stats = new Statistics();

    private static final int minTime = 10;
    private static final int maxTime = 2000;

    // For randomly starting the timer
    private Handler timerH = new Handler();
    private Runnable timerR = new Runnable() {

        @Override
        public void run() {
            startTime = System.currentTimeMillis();
            text.setText(R.string.reaction_timer_now);
            timerStarted = true;
        }
    };

    // Constructors
    public ReactionTest(Context context) {
        this.context = context;
    }

    public ReactionTest(View text, Context context) {
        this.text = (TextView) text;
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

    public Statistics getStats() {
        loadFromFile();
        return stats;
    }

    public void clearStats() {
        stats.clearStats();
        saveInFile();
        loadFromFile();
    }

    // From CMPUT 301 Lab 3. Used and modified with permission from J. Campbell
    // https://github.com/joshua2ua/lonelyTwitter
    public void loadFromFile() {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            stats = gson.fromJson(in, Statistics.class);
        } catch (FileNotFoundException e) {
            stats = new Statistics();
        }
    }

    // From CMPUT 301 Lab 3. Used and modified with permission from J. Campbell
    // https://github.com/joshua2ua/lonelyTwitter
    public void saveInFile() {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(stats, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
