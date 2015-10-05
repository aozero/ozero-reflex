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

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// Used for the multiplayer buzzer mode. Handles adding buttons taps to the stats and checking
// if they are the first tap
public class Buzzer {

    private boolean buttonWasTapped = false;
    // Amount of players
    private int players;

    // For statistics saving
    private static final String FILENAME = "stats.sav";
    Statistics stats = new Statistics();

    private Context context;

    public Buzzer(Context context) {
        this.context = context;
    }

    // Checks if it is the first to be tapped, if so, add the stat and return true to indicate
    // it was indeed the first tap
    public boolean buttonTap(Integer index) {
        if (!buttonWasTapped) {
            buttonWasTapped = true;
            stats.addBuzzerStat(index, players);
            saveInFile();
            return true;
        } else return false;
    }

    // Used by BuzzerActivity to set buttonWasTapped to false after the dialog is dismissed
    public void setButtonWasTapped(boolean bool) {
        buttonWasTapped = bool;
    }

    // Takes the amount of players from Buzzer Activity and saves it to an int
    public void setPlayers(Integer players) {
        this.players = players;
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
            saveInFile();
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
