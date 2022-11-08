package com.game.wheeloffortune;

public class Player {

    private static String name;
    private static int totalMoney;
    private static int currentRoundMoney;

    public Player() {

    }

    // bussiness
    public void buyVowel(){

    }


    public void spinWheel(){

    }


    public void solvePuzzle(){

    }

    // accessors
    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Player.name = name;
    }

    public static int getTotalMoney() {
        return totalMoney;
    }

    public static void setTotalMoney(int totalMoney) {
        Player.totalMoney = totalMoney;
    }

    public static int getCurrentRoundMoney() {
        return currentRoundMoney;
    }

    public static void setCurrentRoundMoney(int currentRoundMoney) {
        Player.currentRoundMoney = currentRoundMoney;
    }


    @Override
    public String toString() {
        return "Player name: " + name
                + "Total money: " + getTotalMoney();
    }

}
