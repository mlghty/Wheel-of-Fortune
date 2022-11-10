package com.game.wheeloffortune;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Game {

    private static final int NUMBER_OF_ROUNDS = 1;
    private int currentRoundNumber;
    private List<Player> players;
    private Player currentPlayersTurn;
//    private String currentRoundPuzzle;
//    private String currentRoundCategory;
    private GameBoard currentGameBoard;



    private static final List<Character> CONSONANTS = new ArrayList<>(List.of(
            'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N',
            'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z'));
    private static final List<Character> VOWELS = new ArrayList<>(List.of('A', 'E', 'I', 'O', 'U'));


    // constructors
    public Game(Player... players) {
        List<Player> playerList = new ArrayList<>();
        Collections.addAll(playerList, players);
        setPlayers(playerList);
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
        int numberOfPlayers = getPlayers().size();
        int player = (int) (Math.random() * numberOfPlayers);
        setCurrentPlayersTurn(players.get(player));
    }

    public void gameLoop() {

    }
    // accessors


    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

//    public String getCurrentRoundPuzzle() {
//        return currentRoundPuzzle;
//    }
//
//    public void setCurrentRoundPuzzle(String currentRoundPuzzle) {
//        this.currentRoundPuzzle = currentRoundPuzzle;
//    }
//
//    public String getCurrentRoundCategory() {
//        return currentRoundCategory;
//    }
//
//    public void setCurrentRoundCategory(String currentRoundCategory) {
//        this.currentRoundCategory = currentRoundCategory;
//    }

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

    @Override
    public String toString() {
        return "Game{}";
    }

}
