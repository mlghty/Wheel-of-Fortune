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

    //

    public int buyVowel(char vowelBOUGHT) {
//do some sort of validation that the letter is in the VOWELS
        vowelBOUGHT = Character.toUpperCase(vowelBOUGHT);
//        if(VOWELS.contains(vowelBOUGHT))
        currentRoundMoney = (currentRoundMoney - 250);
        return currentRoundMoney;
    }

    public int spinWheel() {
        int wheelValue = Wheel.getRandomWedgeOfTheWheel();
        return wheelValue;
    }

    public void pickConsonants() {
        //do some validation of if the letter is in the CONSONANTS list
//    int numberOfTimesLetterAppears = GameBoard.guessLetter;
//        currentRoundMoney = (currentRoundMoney +
//                (numberOfTimesLetterAppears * wheelValue));

    }

    public boolean solvePuzzle(String Puzzle) {
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
