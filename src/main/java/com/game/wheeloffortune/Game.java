package com.game.wheeloffortune;

import java.util.*;

public class Game {

    private static final int NUMBER_OF_ROUNDS = 1;
    private static final int COST_OF_VOWEL = 250;
    private int currentRoundNumber;
    private List<Player> players;
    private Player currentPlayersTurn;
    private GameBoard currentGameBoard;
    private Optional<Player> winningPlayer;
//    private String currentRoundPuzzle;
//    private String currentRoundCategory;

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
        int numberOfPlayers = getPlayers().size();
        int player = (int) (Math.random() * numberOfPlayers);
        setCurrentPlayersTurn(players.get(player));
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

    @Override
    public String toString() {
        return "Game{}";
    }

}
