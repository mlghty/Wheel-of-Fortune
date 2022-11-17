package com.game.wheeloffortune;

import com.game.wheeloffortune.utilities.Wheel;

public class Player {

    private String playerName;
    private int totalMoney;
    private int currentRoundMoney;
    private String playerColor;
    private static Wheel playerWheel = new Wheel();

    public Player(String playerName) {
        totalMoney = 0;
        currentRoundMoney = 0;
        setName(playerName);
        playerColor = playerWheel.getRandomUniquePlayerColor();
    }

    public int spinWheel() {
        return Wheel.getRandomWedgeOfTheWheel();
    }

    public String getName() {
        return playerName;
    }

    public void setName(String playerName) {
        if (playerName.length() > 0) {
            this.playerName = playerName.substring(0, 1).toUpperCase() + playerName.substring(1);
        } else {
            System.out.println("Player name length must be greater > 0!");
        }
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) throws IllegalArgumentException {
        if (totalMoney >= 0) {
            this.totalMoney = totalMoney;
        } else {
            throw new IllegalArgumentException("Error totalMoney must be a int >= 0 !");
        }
    }

    public int getCurrentRoundMoney() {
        return currentRoundMoney;
    }

    public void setCurrentRoundMoney(int currentRoundMoney) throws IllegalArgumentException {
        if (currentRoundMoney >= 0) {
            this.currentRoundMoney = currentRoundMoney;
        } else {
            throw new IllegalArgumentException("Error currentRoundMoney must be a int >= 0 !");
        }
    }

    public String getPlayerColor() {
        return playerColor;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " name: " + getName()
                + "\nTotal money: " + getTotalMoney()
                + "\nRound money: " + getCurrentRoundMoney() + '\n';
    }

}