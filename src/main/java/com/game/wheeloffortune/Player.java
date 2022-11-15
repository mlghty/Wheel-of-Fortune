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

    // business

    public int spinWheel() {
        return Wheel.getRandomWedgeOfTheWheel();
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
       this.totalMoney = totalMoney;
    }

    public int getCurrentRoundMoney() {
        return currentRoundMoney;
    }

    public void setCurrentRoundMoney(int currentRoundMoney) {
        this.currentRoundMoney = currentRoundMoney;
    }


    @Override
    public String toString() {
        return  getClass().getSimpleName() +" name: " + playerName
                + "\nTotal money: " + getTotalMoney()
                + "\nRound money: " + getCurrentRoundMoney() + '\n';
    }

}