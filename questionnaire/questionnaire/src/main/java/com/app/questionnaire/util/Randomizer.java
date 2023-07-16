package com.app.questionnaire.util;

public class Randomizer {
    public static int generate(int min, int max) {
        int rangeLength = max - min + 1;
        return (int)(Math.random() * rangeLength) + min;
    }

    public static boolean doesEventHappen(double probability) {
        double randomValueFrom0To1 = Math.random();
        return probability > randomValueFrom0To1;
    }
}
