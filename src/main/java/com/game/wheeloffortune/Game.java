package com.game.wheeloffortune;

import java.util.List;

public class Game {

    private static List<Player> players;
    private static String currentRoundPuzzle;
    private static String currentRoundCategory;

    private static int currentRoundNumber;

    private static List<String> consonants;
    private static List<String> vowels;


    // constructors
    public Game() {
    }


    // business

    // accessors


    public static List<Player> getPlayers() {
        return players;
    }

    public static void setPlayers(List<Player> players) {
        Game.players = players;
    }

    public static String getCurrentRoundPuzzle() {
        return currentRoundPuzzle;
    }

    public static void setCurrentRoundPuzzle(String currentRoundPuzzle) {
        Game.currentRoundPuzzle = currentRoundPuzzle;
    }

    public static String getCurrentRoundCategory() {
        return currentRoundCategory;
    }

    public static void setCurrentRoundCategory(String currentRoundCategory) {
        Game.currentRoundCategory = currentRoundCategory;
    }

    public static int getCurrentRoundNumber() {
        return currentRoundNumber;
    }

    public static void setCurrentRoundNumber(int currentRoundNumber) {
        Game.currentRoundNumber = currentRoundNumber;
    }

    public static List<String> getConsonants() {
        return consonants;
    }

    public static void setConsonants(List<String> consonants) {
        Game.consonants = consonants;
    }

    public static List<String> getVowels() {
        return vowels;
    }

    public static void setVowels(List<String> vowels) {
        Game.vowels = vowels;
    }

    @Override
    public String toString() {
        return "Game{}";
    }

}
