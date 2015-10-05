package com.example.ozero_reflex;

import java.util.ArrayList;
import java.util.Collections;

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
    // TODO? Merge buzzer stats into one list
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

    public void clearStats() {
        reactionStatsList.clear();
        twoPlayerStatsList.clear();
        threePlayerStatsList.clear();
        fourPlayerStatsList.clear();
    }

    public ArrayList<Integer> getLastAmount(Integer amtToGet){
        if(reactionStatsList.size() < amtToGet){
            amtToGet = reactionStatsList.size();
        }
        ArrayList<Integer> lastAmt = new ArrayList<>();
        for (int i = 0; i < amtToGet ; i++) {
            lastAmt.add(reactionStatsList.get(i));
        }
        return lastAmt;
    }

    public Integer getAverage(ArrayList<Integer> list) {
        Integer sum = 0;
        for (Integer i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        return sum / list.size();
    }

    public Integer getMedian(ArrayList<Integer> list) {
        if(list.size() % 2 == 0) {
            return((list.get(list.size()/2) + (list.get(list.size()-1))) / 2);
        } else {
            return(list.get(list.size() / 2));
        }
    }

    private String minReactionStats() {
        if(reactionStatsList.isEmpty()) {
            return("Minimum" +
                    "\n\tLast Ten: --ms" +
                    "\n\tLast Hundred: --ms" +
                    "\n\tAll Time: --ms\n");
        } else {
            return ("Minimum" +
                    "\n\tLast Ten: " + Collections.min(getLastAmount(10)) + "ms" +
                    "\n\tLast Hundred: " + Collections.min(getLastAmount(100)) + "ms" +
                    "\n\tAll Time: " + Collections.min(reactionStatsList) + "ms\n");
        }
    }

    private String maxReactionStats() {
        if (reactionStatsList.isEmpty()) {
            return("Maximum" +
                    "\n\tLast Ten: --ms" +
                    "\n\tLast Hundred: --ms" +
                    "\n\tAll Time: --ms\n");
        } else {
            return ("Maximum" +
                    "\n\tLast Ten: " + Collections.max(getLastAmount(10)) + "ms" +
                    "\n\tLast Hundred: " + Collections.max(getLastAmount(100)) + "ms" +
                    "\n\tAll Time: " + Collections.max(reactionStatsList) + "ms\n");
        }
    }

    private String avgReactionStats() {
        if (reactionStatsList.isEmpty()) {
            return("Average" +
                    "\n\tLast Ten: --ms" +
                    "\n\tLast Hundred: --ms" +
                    "\n\tAll Time: --ms\n");
        } else {
            return ("Average" +
                    "\n\tLast Ten: " + getAverage(getLastAmount(10)) + "ms" +
                    "\n\tLast Hundred: " + getAverage(getLastAmount(100)) + "ms" +
                    "\n\tAll Time: " + getAverage(reactionStatsList) + "ms\n");
        }
    }

    private String medReactionStats() {
        if (reactionStatsList.isEmpty()) {
            return("Median" +
                    "\n\tLast Ten: --ms" +
                    "\n\tLast Hundred: --ms" +
                    "\n\tAll Time: --ms\n");
        } else {
            return ("Median" +
                    "\n\tLast Ten: " + getMedian(getLastAmount(10)) + "ms" +
                    "\n\tLast Hundred: " + getMedian(getLastAmount(100)) + "ms" +
                    "\n\tAll Time: " + getMedian(reactionStatsList) + "ms\n");
        }
    }

    private String twoPlayerStats() {
        if (twoPlayerStatsList.isEmpty()) {
            return("Two Player Mode" +
                    "\n\tPlayer One: --" +
                    "\n\tPlayer Two: --\n");
        } else {
            return ("Two Player Mode" +
                    "\n\tPlayer One: " + twoPlayerStatsList.get(0) +
                    "\n\tPlayer Two: " + twoPlayerStatsList.get(1) + "\n");
        }
    }

    private String threePlayerStats() {
        if (threePlayerStatsList.isEmpty()) {
            return("Three Player Mode" +
                    "\n\tPlayer One: --" +
                    "\n\tPlayer Two: --" +
                    "\n\tPlayer Three: --\n");
        } else {
            return ("Three Player Mode" +
                    "\n\tPlayer One: " + threePlayerStatsList.get(0) +
                    "\n\tPlayer Two: " + threePlayerStatsList.get(1) +
                    "\n\tPlayer Three: " + threePlayerStatsList.get(2) + "\n");
        }
    }

    private String fourPlayerStats() {
        if(fourPlayerStatsList.isEmpty()) {
            return("Four Player Mode" +
                    "\n\tPlayer One: --" +
                    "\n\tPlayer Two: --" +
                    "\n\tPlayer Three: --" +
                    "\n\tPlayer Four: --\n");
        } else {
            return ("Four Player Mode" +
                    "\n\tPlayer One: " + fourPlayerStatsList.get(0) +
                    "\n\tPlayer Two: " + fourPlayerStatsList.get(1) +
                    "\n\tPlayer Three: " + fourPlayerStatsList.get(2) +
                    "\n\tPlayer Four: " + fourPlayerStatsList.get(3) + "\n");
        }
    }

    public ArrayList<String> getStatsStrings() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("REACTION TIMES");
        strings.add(minReactionStats());
        strings.add(maxReactionStats());
        strings.add(avgReactionStats());
        strings.add(medReactionStats());
        strings.add("BUZZER MODE WINS");
        strings.add(twoPlayerStats());
        strings.add(threePlayerStats());
        strings.add(fourPlayerStats());

        return strings;
    }

    public String getStatsAsString() {
        String string = new String();
        string = "REACTION TIMES\n\n" + minReactionStats() + maxReactionStats() +
                avgReactionStats() + medReactionStats() + "\nBUZZER MODE WINS\n\n" +
                twoPlayerStats() + threePlayerStats() + fourPlayerStats();
        return string;
    }
}
