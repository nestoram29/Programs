/*
Cocktail sort in Java. This program was created to find the worst, best, and average cases for Cocktail sort.
*/

package com.company;

import java.util.Random;

public class Main {
    //<editor-fold desc="variables">
    static final int NUM_ELEMENTS = 5000;
    static final int NUM_RUNS = 500;

    static Integer list[];

    static Integer swaps;
    static Integer comps;

    static long averageTime;
    static long averageSwaps;
    static long averageComps;

    static Integer maxSwaps;
    static Integer maxComps;
    static Integer minSwaps;
    static Integer minComps;

    static Integer bestCaseSwaps;
    static Integer bestCaseComps;
    static Integer worstCaseSwaps;
    static Integer worstCaseComps;
    //</editor-fold>

    public static void main(String[] args) {
        setVars();

        bestCaseFill();
        sortRun();
        bestCaseComps = comps;
        bestCaseSwaps = swaps;

        worstCaseFill();
        sortRun();
        worstCaseComps = comps;
        worstCaseSwaps = swaps;

        for(int i = 1; i <= NUM_RUNS; i++) {
            arrayFill();

            averageTime += sortRun();
            averageComps += comps;
            averageSwaps += swaps;

            if(swaps > maxSwaps) {
                maxSwaps = swaps;
            }

            if(swaps < minSwaps) {
                minSwaps = swaps;
            }

            if(comps > maxComps) {
                maxComps = comps;
            }

            if(comps < minComps) {
                minComps = comps;
            }
        }

        averageSwaps /= NUM_RUNS;
        averageComps /= NUM_RUNS;
        averageTime /= NUM_RUNS;
        averageTime /= 1000000;

        output();
    }

    static long sortRun() {
        swaps = 0;
        comps = 0;

        long startTime = System.nanoTime();
        cocktailSort();
        long endTime = System.nanoTime();

        long duration = (endTime - startTime); //this needs some work

        return duration;
    }

    static void cocktailSort() {
        boolean swapped = true;
        int start = 0;
        int end = list.length;

        while(swapped) {
            swapped = false;

            for(int i = start; i < end - 1; i++) {
                comps++;
                if(list[i] > list[i + 1]) {
                    int temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    swapped = true;
                    swaps++;
                }
            }

            if(!swapped) break;

            swapped = false;

            end = end - 1;

            for(int i = end-1; i >= start; i--) {
                comps++;
                if(list[i] > list[i + 1]) {
                    int temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    swapped = true;
                    swaps++;
                }
            }

            start = start + 1;
        }
    }

    static void arrayFill() {
        list = new Integer[NUM_ELEMENTS];
        Random rand = new Random();

        for(int i = 0; i < NUM_ELEMENTS; i++) {
            list[i] = rand.nextInt(NUM_ELEMENTS);
        }
    }

    static void worstCaseFill() {
        list = new Integer[NUM_ELEMENTS];
        for(int i = 0; i < NUM_ELEMENTS; i++) {
            list[i] = NUM_ELEMENTS - i;
        }
    }

    static void bestCaseFill() {
        list = new Integer[NUM_ELEMENTS];
        for(int i = 0; i < NUM_ELEMENTS; i++) {
            list[i] = i;
        }
    }

    static void output() {
        System.out.println("Avg Time:        " + averageTime + "ms");
        System.out.println("Avg Swaps:       " + averageSwaps);
        System.out.println("Avg Comparisons: " + averageComps);
        System.out.println("-------------------------------");
        System.out.println("Min Swaps:       " + minSwaps);
        System.out.println("Min Comparisons: " + minComps);
        System.out.println("Max Swaps:       " + maxSwaps);
        System.out.println("Max Comparisons: " + maxComps);
        System.out.println("-------------------------------");
        System.out.println("WC Swaps:        " + worstCaseSwaps);
        System.out.println("WC Comps:        " + worstCaseComps);
        System.out.println("-------------------------------");
        System.out.println("BC Swaps:        " + bestCaseSwaps);
        System.out.println("BC Comps:        " + bestCaseComps);
    }

    static void setVars() {
        swaps = 0;
        comps = 0;
        maxSwaps = 0;
        maxComps = 0;
        minSwaps = 999999999;
        minComps = 999999999;
        bestCaseSwaps = 0;
        bestCaseComps = 0;
        worstCaseSwaps = 0;
        worstCaseComps = 0;
    }
}
