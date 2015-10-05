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

import java.util.ArrayList;
import java.util.Collections;

public class Stats {

    private ArrayList<Integer> reactionStatsList = new ArrayList<>();
    private ArrayList<Integer> buzzerStatsList = new ArrayList<>();

    // Add a reaction statistic to the list
    public void addReactionStat(Integer stat) {
        reactionStatsList.add(stat);
    }

    // Add two, three, and four player stats
    public void addBuzzerStat(Integer index, Integer players) {
        int start = 0;

        switch(players) {
            case 2:
                start = 0;
                break;
            case 3:
                start = 2;
                break;
            case 4:
                start = 5;
                break;
        }
        try {
            buzzerStatsList.set(start+index, buzzerStatsList.get(start+index)+1);
        } catch (IndexOutOfBoundsException e) {
            // Initialize default values, then increment the one specified
            for (int i = 0; i < 9; i++) {
                buzzerStatsList.add(0);
            }
            buzzerStatsList.set(start+index, buzzerStatsList.get(start+index)+1);
        }
    }

    /*
    public void addThreePlayerStat(Integer index) {
        try {
            buzzerStatsList.set(index+2, buzzerStatsList.get(index+2)+1);
        } catch (IndexOutOfBoundsException e) {
            // Initialize default values, then increment the one specified
            for (int i = 0; i < 9; i++) {
                buzzerStatsList.add(0);
            }
            buzzerStatsList.set(index+2, buzzerStatsList.get(index+2) + 1);
        }
    }

    public void addFourPlayerStat(Integer index) {
        try {
            buzzerStatsList.set(index+5, buzzerStatsList.get(index+5)+1);
        } catch (IndexOutOfBoundsException e) {
            // Initialize default values, then increment the one specified
            for (int i = 0; i < 9; i++) {
                buzzerStatsList.add(0);
            }
            buzzerStatsList.set(index+5, buzzerStatsList.get(index+5)+1);
        }
    }*/

    public void clearStats() {
        reactionStatsList.clear();
        buzzerStatsList.clear();
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
        if (buzzerStatsList.isEmpty()) {
            return("Two Player Mode" +
                    "\n\tPlayer One: --" +
                    "\n\tPlayer Two: --\n");
        } else {
            return ("Two Player Mode" +
                    "\n\tPlayer One: " + buzzerStatsList.get(0) +
                    "\n\tPlayer Two: " + buzzerStatsList.get(1) + "\n");
        }
    }

    private String threePlayerStats() {
        if (buzzerStatsList.isEmpty()) {
            return("Three Player Mode" +
                    "\n\tPlayer One: --" +
                    "\n\tPlayer Two: --" +
                    "\n\tPlayer Three: --\n");
        } else {
            return ("Three Player Mode" +
                    "\n\tPlayer One: " + buzzerStatsList.get(2) +
                    "\n\tPlayer Two: " + buzzerStatsList.get(3) +
                    "\n\tPlayer Three: " + buzzerStatsList.get(4) + "\n");
        }
    }

    private String fourPlayerStats() {
        if(buzzerStatsList.isEmpty()) {
            return("Four Player Mode" +
                    "\n\tPlayer One: --" +
                    "\n\tPlayer Two: --" +
                    "\n\tPlayer Three: --" +
                    "\n\tPlayer Four: --\n");
        } else {
            return ("Four Player Mode" +
                    "\n\tPlayer One: " + buzzerStatsList.get(5) +
                    "\n\tPlayer Two: " + buzzerStatsList.get(6) +
                    "\n\tPlayer Three: " + buzzerStatsList.get(7) +
                    "\n\tPlayer Four: " + buzzerStatsList.get(8) + "\n");
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
        String string;
        string = "REACTION TIMES\n\n" + minReactionStats() + maxReactionStats() +
                avgReactionStats() + medReactionStats() + "\nBUZZER MODE WINS\n\n" +
                twoPlayerStats() + threePlayerStats() + fourPlayerStats();
        return string;
    }
}
