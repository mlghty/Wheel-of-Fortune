package com.game.wheeloffortune;

import java.util.*;

public class Game {

    public static final int NUMBER_OF_ROUNDS = 3;
    public static final int COST_OF_VOWEL = 250;
    private static final List<Character> CONSONANTS = new ArrayList<>(List.of(
            'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N',
            'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z'));
    private static final List<Character> VOWELS = new ArrayList<>(List.of('A', 'E', 'I', 'O', 'U'));
    private int currentRoundNumber;
    private List<Player> players;
    private Player currentPlayersTurn;
    private int indexOfCurrentPlayer;
    private int numberOfPlayers;
    private GameBoard currentGameBoard;
    private int valueOfWheelSpin;
    private Player winningPlayer;

    private final List<String> usedPuzzles = new ArrayList<>();


    // constructors
    public Game() {
    }

    public Game(Player... players) {
        List<Player> playerList = new ArrayList<>();
        Collections.addAll(playerList, players);
        setPlayers(playerList);
    }

    public Game(List<Player> players) {
        setPlayers(players);
    }


    // business
    public int startRound() {
        if (currentRoundNumber < NUMBER_OF_ROUNDS) {
            setUpPuzzle();
            determineStartingPlayer();
            resetRoundEarnings();
            currentRoundNumber++;
            return 1;
        } else {
            setWinningPlayer();
            return 0;
        }
    }

    private void resetRoundEarnings() {
        getPlayers()
                .forEach(player -> player.setCurrentRoundMoney(0));
    }

    /*
     * Get a random puzzle from Puzzles then check to see if it is in usedPuzzles List
     * if it is then get another puzzle, if it isn't then send the puzzle and hint to gameBoard
     */
    private void setUpPuzzle() {
        boolean isAlreadyFound = false;
        while (!isAlreadyFound) {
            List<String> currentGamePuzzle = Puzzles.getRandomPuzzle();
            if (!usedPuzzles.contains(currentGamePuzzle.get(0))) {
                String currentRoundPuzzle = currentGamePuzzle.get(0);
                String currentRoundCategory = currentGamePuzzle.get(1);
                usedPuzzles.add(currentRoundPuzzle);
                setCurrentGameBoard(new GameBoard(currentRoundPuzzle, currentRoundCategory));
                isAlreadyFound = true;
            }
        }
    }

    // determineStartingPlayer() determines which player starts the round by random determination
    private void determineStartingPlayer() {
        numberOfPlayers = getPlayers().size();
        indexOfCurrentPlayer = (int) (Math.random() * numberOfPlayers);
        setCurrentPlayersTurn(players.get(indexOfCurrentPlayer));
    }


    public void getNextPlayer() {
        indexOfCurrentPlayer++;
        if (indexOfCurrentPlayer >= numberOfPlayers) {
            indexOfCurrentPlayer -= numberOfPlayers;
        }
        currentPlayersTurn = players.get(indexOfCurrentPlayer);
    }

    private void bankruptCurrentPlayer() {
        currentPlayersTurn.setCurrentRoundMoney(0);
    }

    public int spinWheel() {
        valueOfWheelSpin = currentPlayersTurn.spinWheel();
        if (valueOfWheelSpin == 0) {
            getNextPlayer();
            return valueOfWheelSpin;
        } else if (valueOfWheelSpin == -1) {
            bankruptCurrentPlayer();
            getNextPlayer();
            return valueOfWheelSpin;
        } else {
            return valueOfWheelSpin;
        }
    }

    public int pickLetter(char letterPicked) throws IllegalArgumentException {
        if (CONSONANTS.contains(letterPicked)) {
            int occurrenceOfLetter = currentGameBoard.guessLetter(letterPicked);
            if (occurrenceOfLetter > 0) {
                int winningsFromLetter = occurrenceOfLetter * valueOfWheelSpin;
                currentPlayersTurn.setCurrentRoundMoney(
                        currentPlayersTurn.getCurrentRoundMoney() + winningsFromLetter
                );
            } else {
                getNextPlayer();
            }
            return occurrenceOfLetter;
        } else {
            throw new IllegalArgumentException("You must pick a valid consonant");
        }
    }

    public int buyAVowel(char vowel) throws IllegalArgumentException {
        if (currentPlayersTurn.getCurrentRoundMoney() >= COST_OF_VOWEL
                && VOWELS.contains(vowel)) {
            int occurrenceOfLetter = currentGameBoard.guessLetter(vowel);
            currentPlayersTurn.setCurrentRoundMoney(
                    currentPlayersTurn.getCurrentRoundMoney() - COST_OF_VOWEL
            );
            if (occurrenceOfLetter < 0) {
                getNextPlayer();
            }
            return occurrenceOfLetter;
        } else {
            throw new IllegalArgumentException("You must pick a valid vowel");
        }
    }

    public int solvePuzzle(String puzzleGuess) {
        boolean result = currentGameBoard.solvePuzzle(puzzleGuess);
        int currentRoundEarnings = currentPlayersTurn.getCurrentRoundMoney();
        if (result) {
            currentPlayersTurn.setTotalMoney(currentRoundEarnings
                    + currentPlayersTurn.getTotalMoney());
            resetRoundEarnings();
            return 1;
        } else {
            getNextPlayer();
            return 0;
        }
    }

    // Accessors (Getters and Setters)

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
        this.numberOfPlayers = players.size();
    }

    public int getCurrentRoundNumber() {
        return currentRoundNumber;
    }

    public void setCurrentRoundNumber(int currentRoundNumber) {
        this.currentRoundNumber = currentRoundNumber;
    }

    public List<Character> getConsonants() {
        return CONSONANTS;
    }

    public List<Character> getVowels() {
        return VOWELS;
    }

    public Player getCurrentPlayersTurn() {
        return currentPlayersTurn;
    }

    public void setCurrentPlayersTurn(Player currentPlayersTurn) {
        this.currentPlayersTurn = currentPlayersTurn;
    }

    public GameBoard getCurrentGameBoard() {
        return currentGameBoard;
    }

    public void setCurrentGameBoard(GameBoard currentGameBoard) {
        this.currentGameBoard = currentGameBoard;
    }

    public int getValueOfWheelSpin() {
        return valueOfWheelSpin;
    }

    public void setValueOfWheelSpin(int valueOfWheelSpin) {
        this.valueOfWheelSpin = valueOfWheelSpin;
    }

    public String getWinningPlayer() {
        try {
            if (!winningPlayer.getName().equals("temp")) {

                return winningPlayer.toString();
            } else {
                return "There is no winner!";
            }
        } catch (NullPointerException e) {
            return "There is no winner!";
        }
    }

    public Player getWinningPlayerObject() {
        try {
            if (!winningPlayer.getName().equals("temp")) {
                return winningPlayer;
            } else {
                return null;
            }
        } catch (NullPointerException e) {
            return null;
        }
    }

    public void setWinningPlayer() {
        Player tempPlayer = new Player("temp");
        tempPlayer.setTotalMoney(0);
        for (Player p : players) {
            if (p.getTotalMoney() > tempPlayer.getTotalMoney()) {
                tempPlayer = p;
            }
        }
        this.winningPlayer = tempPlayer;
    }

    public List<String> getUsedPuzzles() {
        return usedPuzzles;
    }

    @Override
    public String toString() {
        String returnValue = "";
        for (Player p : players) {
            returnValue = returnValue.concat(p.toString());
            returnValue = returnValue.concat("\n");
        }
        return returnValue;
    }

}