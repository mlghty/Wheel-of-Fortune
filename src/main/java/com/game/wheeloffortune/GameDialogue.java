package com.game.wheeloffortune;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameDialogue {


    private Integer numOfPlayer;
    private Scanner myObj;
    private List<Player> players;
    private Game currentGame;


    public GameDialogue() {
        players = new ArrayList<>();
        myObj = new Scanner(System.in);
//        currentGame = new Game();
        currentGame = new Game(new Player("joe"));
        currentGame.startRound();


        numOfPlayer = 1;
    }


    public boolean startGame() {

        boolean startGame = false;

        System.out.println("Press 'X' to Start Game: ");
        System.out.println("Press 'Q' to Exit Game: ");

        String pressedKey = myObj.nextLine();

        while (!startGame) {

            if (pressedKey.equalsIgnoreCase("x")) {
                System.out.println("Pressed x");
                startGame = true;
            } else if (pressedKey.equalsIgnoreCase("q")) {
                System.out.println("Pressed q");
                System.out.println("Exiting game...");
                break;
            } else {
                System.out.println("Error valid options are 'X' & 'Q'");
                System.out.println("Press 'X' to Start Game: ");
                System.out.println("Press 'Q' to Exit Game: ");
                pressedKey = myObj.nextLine();  // Read user input

            }
        }


        return startGame;
    }


    public int numberOfPlayers() {

        int numPlayers = 0;
        System.out.println("Select number of players [1-3]");

        Integer playerCount = Integer.parseInt(myObj.nextLine());

        while (playerCount < 0 || playerCount > 3) {
            System.out.println("Error please select between 1-3 players...");
            playerCount = Integer.parseInt(myObj.nextLine());

        }

        System.out.println("Number of Players: " + playerCount);

        numOfPlayer = numPlayers;
        return numPlayers;
    }


    public void setPlayerNames() {

        for (int i = 1; i <= numOfPlayer; i++) {
            System.out.println("Please enter name for Player " + i);
            String playerName = myObj.nextLine();
            players.add(new Player(playerName));
        }

        // sets players in game class after creating & assigning names
        currentGame.setPlayers(players);

        System.out.println(players);

    }


    public void displayCurrentPuzzle() {
//        currentGame.startRound();
        GameBoard currentGameBoard = currentGame.getCurrentGameBoard();
        String puzzleHint = currentGameBoard.getGameHint();
        System.out.println("Hint: " + puzzleHint);
        String puzzle = currentGameBoard.getGamePuzzle();
        System.out.println("Puzzle: " + puzzle);
//        System.out.println("\n \n \n \n \n \n");
        System.out.println("\n");
    }

    // temp until game is run on indepent terminal screen
    // and not under intellijs
    public void clearGameScreen() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }


    public void gameLoop() throws InterruptedException {

        boolean correctOption = false;
        int intUserInput = 0;
        int wheelValue = currentGame.getCurrentPlayersTurn().spinWheel();
        boolean isSolved = false;

        String solvePuzzleAttempt;
        String vowelPurchase;
        String guessedLetter;

        while (!isSolved) {
            displayCurrentPuzzle();


            System.out.println("Spinning Wheel...");
            System.out.println("Wheel Value:" + wheelValue + "\n");


            System.out.println(currentGame.getCurrentPlayersTurn() + "\n");

            while (!correctOption) {
                System.out.println("Options...");
                System.out.println("Press 1 to guess a letter.");
                System.out.println("Press 2 to buy a vowel.");
                System.out.println("Press 3 to SOLVE...");
                String pressedKey = myObj.nextLine();


                if (pressedKey.equals("1") || pressedKey.equals("2") || pressedKey.equals("3")) {
                    intUserInput = Integer.parseInt(pressedKey);
                    correctOption = true;

                }
            }


            switch (intUserInput) {
                case 1:
                    boolean isValidConsonants = false;
                    System.out.println("Guessing letter...");
                    System.out.println("Enter letter: ");
                    guessedLetter = myObj.nextLine();

                    while (!isValidConsonants) {

                        for (char consonant : currentGame.getConsonants()) {
                            if (guessedLetter.toUpperCase().charAt(0) == consonant) {
                                System.out.println("EQUALS!");
                                isValidConsonants = true;
                                break;
                            }
                        }


                        if (!isValidConsonants) {
                            System.out.println("Error Please enter valid consonant!");
                            guessedLetter = myObj.nextLine();
                        }


                    }


                    // Current puzzle is "Puzzle"
                    int letterMultiplier = currentGame.pickLetter(guessedLetter.toUpperCase().charAt(0));
                    System.out.println("Letter appeared " + letterMultiplier + " times!");
                    currentGame.getCurrentPlayersTurn().setCurrentRoundMoney(wheelValue * letterMultiplier);
                    break;
                case 2:

                    boolean isValidVowel = false;
                    if (currentGame.getCurrentPlayersTurn().getTotalMoney() >= 250) {
                        System.out.println("Buying vowel...");
                        System.out.println("Enter Vowel...");
                        vowelPurchase = myObj.nextLine();

                        while (!isValidVowel) {
                            for (char consonant : currentGame.getVowels()) {
                                if (vowelPurchase.toUpperCase().charAt(0) == consonant) {
                                    isValidVowel = true;
                                    break;
                                }
                            }

                            if (!isValidVowel) {
                                System.out.println("Error Please enter valid vowel!");
                                vowelPurchase = myObj.nextLine();

                            }

                        }

//                ('A', 'E', 'I', 'O', 'U'));

                        int vowelMultiplier = currentGame.buyAVowel(vowelPurchase.toUpperCase().charAt(0));


                        currentGame.getCurrentPlayersTurn().setCurrentRoundMoney(wheelValue * vowelMultiplier);
                        System.out.println(currentGame.getCurrentPlayersTurn());
                    }else{
                        System.out.println("Sorry not enough money...");
                        TimeUnit.SECONDS.sleep(2);

                    }
                    break;
                case 3:
                    System.out.println("Solve Puzzle: ");
                    solvePuzzleAttempt = myObj.nextLine();
                    int isPuzzleSolved = currentGame.solvePuzzle(solvePuzzleAttempt);
                    if (isPuzzleSolved == 1) {
                        System.out.println("Solved!");
                    } else {
                        System.out.println("Not Solved!");
                    }
                    break;
            }


            // reset vars
            clearGameScreen();
            intUserInput = 0;
            correctOption = false;

        }


    }


}


class Main1 {
    public static void main(String[] args) throws InterruptedException {
        GameDialogue gameDialogue = new GameDialogue();
        gameDialogue.gameLoop();

//        gameDialogue.startGame();
//        gameDialogue.numberOfPlayers();
//        gameDialogue.setPlayerNames();
//        gameDialogue.displayCurrentPuzzle();


    }
}