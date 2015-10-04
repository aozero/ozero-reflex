package com.example.ozero_reflex;

import java.util.ArrayList;

public class Stats {

    private ArrayList<Integer> reactionStatsList = new ArrayList<>();
    private ArrayList<Integer> twoPlayerStatsList = new ArrayList<>();
    private ArrayList<Integer> threePlayerStatsList = new ArrayList<>();
    private ArrayList<Integer> fourPlayerStatsList = new ArrayList<>();

    // Add a reaction statistic to the list
    public void addReactionStat(Integer stat) {
        reactionStatsList.add(stat);
    }

    // Add two, three, and four player stat to their respective lists
    public void addTwoPlayerStat(Integer index) {
        try {
            twoPlayerStatsList.set(index, twoPlayerStatsList.get(index)+1);
        } catch (IndexOutOfBoundsException e) {
            // Initialize default values, then increment the one specified
            twoPlayerStatsList.add(0);
            twoPlayerStatsList.add(0);
            twoPlayerStatsList.set(index, twoPlayerStatsList.get(index) + 1);
        }
    }

    public void addThreePlayerStat(Integer index) {
        try {
            threePlayerStatsList.set(index, threePlayerStatsList.get(index)+1);
        } catch (IndexOutOfBoundsException e) {
            // Initialize default values, then increment the one specified
            for (int i = 0; i < 3; i++) {
                threePlayerStatsList.add(0);
            }
            threePlayerStatsList.set(index, threePlayerStatsList.get(index) + 1);
        }
    }

    public void addFourPlayerStat(Integer index) {
        try {
            fourPlayerStatsList.set(index, fourPlayerStatsList.get(index)+1);
        } catch (IndexOutOfBoundsException e) {
            // Initialize default values, then increment the one specified
            for (int i = 0; i < 4; i++) {
                fourPlayerStatsList.add(0);
            }
            fourPlayerStatsList.set(index, fourPlayerStatsList.get(index) + 1);
        }
    }
}
