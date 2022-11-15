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

    public void setTotalMoney(int totalMoney) throws IllegalArgumentException {

        if (totalMoney >= 0) {
            this.totalMoney = totalMoney;
        } else {
            throw new IllegalArgumentException("Error totalMoney must be a int > 0 !");
        }
    }

    public int getCurrentRoundMoney() {
        return currentRoundMoney;
    }

    public void setCurrentRoundMoney(int currentRoundMoney) throws IllegalArgumentException {
        if (currentRoundMoney >= 0) {
            this.currentRoundMoney = currentRoundMoney;
        } else {
            throw new IllegalArgumentException("Error currentRoundMoney must be a int > 0 !");
        }
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() + " name: " + playerName
                + "\nTotal money: " + getTotalMoney()
                + "\nRound money: " + getCurrentRoundMoney() + '\n';
    }

}