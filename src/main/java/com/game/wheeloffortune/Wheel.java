package com.game.wheeloffortune;

import java.util.Random;

public class Wheel {
    // 24 Total I'm thinking a -1 will mean Bankrupt, 0 can be losing a turn

    private final int[] WEDGE_VALUES_OF_WHEEL = {200,250,300,350,400,450,500,550,600,
            650,700,750,800,850,900,950,1000,1050,1100,1150,1200,1250,0,-1};

    // returns a random wedge on the wheel
    public static int getRandomWedgeOfTheWheel(int [] WEDGE_VALUES_OF_WHEEL) {
        int randomPrizeValue = new Random().nextInt(WEDGE_VALUES_OF_WHEEL.length);
        return WEDGE_VALUES_OF_WHEEL[randomPrizeValue];
    }

}
