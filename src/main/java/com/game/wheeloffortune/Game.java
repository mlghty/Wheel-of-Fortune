package com.game.wheeloffortune;

import java.util.*;

public class Game {

    private static final int NUMBER_OF_ROUNDS = 3;
    private static final int COST_OF_VOWEL = 250;
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
    private Optional<Player> winningPlayer;



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
        getPlayers().stream()
                .forEach(player -> player.setCurrentRoundMoney(0));
    }

    private void setUpPuzzle() {
        List<String> currentGamePuzzle = Puzzles.getRandomPuzzle();
        String currentRoundPuzzle = currentGamePuzzle.get(0);
        String currentRoundCategory = currentGamePuzzle.get(1);
        setCurrentGameBoard(new GameBoard(currentRoundPuzzle, currentRoundCategory));
    }

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
        if (CONSONANTS.contains(Character.valueOf(letterPicked))) {
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
                && VOWELS.contains(Character.valueOf(vowel))) {
            int occurrenceOfLetter = currentGameBoard.guessLetter(vowel);
            currentPlayersTurn.setCurrentRoundMoney(
                    currentPlayersTurn.getCurrentRoundMoney() - COST_OF_VOWEL
            );
            if (occurrenceOfLetter == 0) {
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
    // accessors

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
        if(winningPlayer.isPresent()) {
            return winningPlayer.get().toString();
        } else {
            return "There is no winner!";
        }
    }

    public void setWinningPlayer() {
        Comparator<Player> getWinner = Comparator.comparingInt(Player::getTotalMoney);
        this.winningPlayer = players.stream().max(getWinner);
    }

    @Override
    public String toString() {
        String returnValue = "";
        for(Player p : players) {
           returnValue = returnValue.concat(p.toString());
           returnValue = returnValue.concat("\n");
        }
        return returnValue;
    }

}