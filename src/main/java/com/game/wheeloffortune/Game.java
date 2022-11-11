package com.game.wheeloffortune;

import java.util.*;

public class Game {

    private static final int NUMBER_OF_ROUNDS = 1;
    private static final int COST_OF_VOWEL = 250;
    private static final List<Character> CONSONANTS = new ArrayList<>(List.of(
            'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N',
            'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z'));
    private static final List<Character> VOWELS = new ArrayList<>(List.of('A', 'E', 'I', 'O', 'U'));
    private int currentRoundNumber;
    private int playerTurnChoice;
    private List<Player> players;
    private Player currentPlayersTurn;
    private int indexOfCurrentPlayer;
    private int numberOfPlayers;
    private GameBoard currentGameBoard;
    private int valueOfWheelSpin;






    // constructors
    public Game(Player... players) {
        List<Player> playerList = new ArrayList<>();
        Collections.addAll(playerList, players);
        setPlayers(playerList);
    }

    public Game(List<Player> players) {
        setPlayers(players);
    }


    // business
    public void startRound() {
        setUpPuzzle();
        determineStartingPlayer();
        currentRoundNumber++;
    }

    private void setUpPuzzle() {
        // TODO: Have this link to the Puzzle class which will return a Map.entry<K,V>
        Map.Entry<String,String> currentGamePuzzle = Map.entry("Puzzle", "Hint");//Puzzles.getRandomPuzzle();
        String currentRoundPuzzle = currentGamePuzzle.getKey();
        String currentRoundCategory = currentGamePuzzle.getValue();
        setCurrentGameBoard(new GameBoard(currentRoundPuzzle,currentRoundCategory));
    }

    private void determineStartingPlayer() {
        numberOfPlayers = getPlayers().size();
        indexOfCurrentPlayer = (int) (Math.random() * numberOfPlayers);
        setCurrentPlayersTurn(players.get(indexOfCurrentPlayer));
    }

    private void getNextPlayer() {
        indexOfCurrentPlayer++;
        if(indexOfCurrentPlayer >= numberOfPlayers) {
            indexOfCurrentPlayer -= numberOfPlayers;
        }
        currentPlayersTurn = players.get(indexOfCurrentPlayer);
    }

    private void bankruptCurrentPlayer() {
        currentPlayersTurn.setCurrentRoundMoney(0);
    }

    public int spinWheel() {
        valueOfWheelSpin = currentPlayersTurn.spinWheel();
        if(valueOfWheelSpin == 0) {
            getNextPlayer();
            return valueOfWheelSpin;
        } else if(valueOfWheelSpin == -1) {
            bankruptCurrentPlayer();
            getNextPlayer();
            return valueOfWheelSpin;
        } else {
            return valueOfWheelSpin;
        }
    }

    public void pickLetter(char letterPicked) {

        if (CONSONANTS.contains(Character.valueOf(letterPicked))) {
            int occurrenceOfLetter = currentGameBoard.guessLetter(letterPicked);
            int winningsFromLetter = occurrenceOfLetter * valueOfWheelSpin;
            currentPlayersTurn.setCurrentRoundMoney(
                    currentPlayersTurn.getCurrentRoundMoney() + winningsFromLetter
            );
        } else {
            throw new IllegalArgumentException("You must pick a valid consonant");
        }
    }

    public void buyAVowel(char vowel) {
        if(currentPlayersTurn.getCurrentRoundMoney() >= COST_OF_VOWEL
            && VOWELS.contains(Character.valueOf(vowel))) {
            currentGameBoard.guessLetter(vowel);
            currentPlayersTurn.setCurrentRoundMoney(
                    currentPlayersTurn.getCurrentRoundMoney() - COST_OF_VOWEL
            );
        } else {
            throw new IllegalArgumentException("You must pick a valid vowel");
        }
    }

    // accessors


    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
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

    public int getPlayerTurnChoice() {
        return playerTurnChoice;
    }

    public void setPlayerTurnChoice(int playerTurnChoice) {
        this.playerTurnChoice = playerTurnChoice;
    }

    @Override
    public String toString() {
        return "Game{}";
    }

}

// Deprecated Code - Handled in Client

//    private Optional<Player> winningPlayer, roundWinner;

//private final Comparator<Player> getWinner = new Comparator<Player>() {
//
//    @Override
//    public int compare(Player p1, Player p2) {
//        return p1.getTotalMoney() - p2.getTotalMoney();
//    }
//};
//    public void gameLoop() {
//        while(!winningPlayer.isPresent()) {
//            if(currentRoundNumber > NUMBER_OF_ROUNDS) {
//                winningPlayer = players.stream().max(getWinner);
//            }
//            roundLoop();
//            roundWinner = null;
//        }
//    }
//
//    private void roundLoop() {
//        startRound();
//        while(!roundWinner.isPresent()) {
//            playerTurn();
//        }
//    }
//
//    private void playerTurn() {
//        boolean turnOver = false;
//        while(!turnOver) {
//            int choice = getPlayerChoice();
//        }
//    }
//    private void gameTurn() {
//        boolean turnOver = false;
//        getPlayerChoice();
//        while(!turnOver) {
//
//        }
//    }

//    private String currentRoundPuzzle;
//    private String currentRoundCategory;