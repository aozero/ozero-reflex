package com.example.ozero_reflex;

import java.util.ArrayList;

public class Stats {

    private ArrayList<Integer> statsList = new ArrayList<>();

    // Add a reaction statistic to the list
    public void addReactionStat(Integer stat) {
        statsList.add(stat);
    }

    public void createBuzzerStat(Integer players) {
        for ( int i = 0; i < players; i++) {
            statsList.add(0);
        }
    }

    public void addBuzzerStat(Integer index) {
        statsList.set(index, statsList.get(index)+1);
    }
}
