package com.game.wheeloffortune;

public class Player {

    private String playerName;
    private int totalMoney;
    private int currentRoundMoney;

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
    public String getName() {
        return playerName;
    }

    public void setName(String name) {
        playerName = name;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        totalMoney = totalMoney;
    }

    public int getCurrentRoundMoney() {
        return currentRoundMoney;
    }

    public void setCurrentRoundMoney(int currentRoundMoney) {
        currentRoundMoney = currentRoundMoney;
    }


    @Override
    public String toString() {
        return "Player name: " + playerName
                + "\nTotal money: " + getTotalMoney()
                + "\nTotal round money: " + getCurrentRoundMoney() + '\n';
    }

}
