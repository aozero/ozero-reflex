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

public class Buzzer {
    private boolean buttonWasTapped = false;
    private int players;

    private Context context;

    // For statistics saving
    private static final String FILENAME = "stats.sav";
    Stats stats = new Stats();

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

    public void setButtonWasTapped(boolean bool) {
        buttonWasTapped = bool;
    }

    public void setPlayers(Integer players) {
        this.players = players;
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
            saveInFile();
        }
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
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
    }
    }
}
