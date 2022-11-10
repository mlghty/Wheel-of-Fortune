package com.game.wheeloffortune;

public class Player {

    private static String playerName;
    private static int totalMoney;
    private static int currentRoundMoney;



    public Player(String playerName) {
        totalMoney = 0;
        currentRoundMoney = 0;
        setName(playerName);
    }

    // bussiness

    //
    public void buyVowel(){

    }


    public int spinWheel(){
        int wheelValue = Wheel.getRandomWedgeOfTheWheel();
        return wheelValue;
    }

    public boolean solvePuzzle(String Puzzle){
        return false;
    }

    // accessors
    public static String getName() {
        return playerName;
    }

    public static void setName(String name) {
        Player.playerName = name;
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
        return "Player name: " + playerName
                + "\nTotal money: " + getTotalMoney()
                + "\nTotal round money: " + getCurrentRoundMoney();
    }

}
