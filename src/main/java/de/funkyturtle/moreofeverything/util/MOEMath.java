package de.funkyturtle.moreofeverything.util;

import java.util.Random;

public class MOEMath {

    public static int getRandomRangedInt(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static double getRandomRangedDouble(double min, double max) {
        return min + new Random().nextDouble() * (max - min);
    }
}
