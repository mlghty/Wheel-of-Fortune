package com.game.wheeloffortune;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameDialogue {


    private Integer numOfPlayer;
    private Scanner userInputScanner;
    private List<Player> players;
    private Game currentGame;
    private Boolean startGame;


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLINK = "\033[5m";


    public GameDialogue() {
        players = new ArrayList<>();
        userInputScanner = new Scanner(System.in);
        currentGame = new Game();
        startGame = false;

//        currentGame = new Game(new Player("joe"));
//        currentGame.startRound();
//        numOfPlayer = 1;

    }

    public GameDialogue(Game gameTest) {
        players = new ArrayList<>();
        userInputScanner = new Scanner(System.in);
        currentGame = gameTest;
        startGame = false;
    }

    public void startGame() throws InterruptedException {


//        WOFAsciiArt.windowSizes();
//        WOFAsciiArt.printWOFLogoBlink();

        String test = "Press 'X' to Start Game 'Q' to Exit!: ";
        System.out.println(ANSI_GREEN + "Press 'X' to Start Game 'Q' to Exit!: " + ANSI_RESET);

        String pressedKey = userInputScanner.nextLine();

        while (!startGame) {
            if (pressedKey.equalsIgnoreCase("x")) {
                startGame = true;
            } else if (pressedKey.equalsIgnoreCase("q")) {
                System.out.println("Exiting game...");
                System.exit(0);
            } else {
                System.out.println("Error valid options are 'X' & 'Q'");
                System.out.println("Press 'X' to Start Game: ");
                System.out.println("Press 'Q' to Exit Game: ");
                pressedKey = userInputScanner.nextLine();  // Read user input
            }
        }

        if (startGame) {
            System.out.println("Starting Game...");
            TimeUnit.SECONDS.sleep(3);
            clearGameScreen();
        }

    }

    public void numberOfPlayers() {
        System.out.println("Select number of players [1-3]");

        Integer playerCount = Integer.parseInt(userInputScanner.nextLine());

        while (playerCount < 1 || playerCount > 3) {
            System.out.println("Error please select between 1-3 players...");
            playerCount = Integer.parseInt(userInputScanner.nextLine());

        }

        System.out.println("Number of Players: " + playerCount);

        numOfPlayer = playerCount;
        setPlayerNames();
    }

    public void setPlayerNames() {
        for (int i = 1; i <= numOfPlayer; i++) {
            System.out.println("Please enter name for Player " + i);
            String playerName = userInputScanner.nextLine();
            players.add(new Player(playerName));
        }
        // sets players in game class after creating & assigning names
        currentGame.setPlayers(players);
    }

    public void displayCurrentPuzzle() {
        GameBoard currentGameBoard = currentGame.getCurrentGameBoard();
        String puzzleHint = currentGameBoard.getGameHint();
        String puzzle = currentGameBoard.getGamePuzzle();

        System.out.println("Round #: " + currentGame.getCurrentRoundNumber());
        System.out.println("Hint: " + puzzleHint);
        System.out.println("Puzzle: " + puzzle + "\n");
    }

    // temp until game is run on independent terminal screen
    // and not under intelli js
    public void clearGameScreen() throws InterruptedException {

//        try {
//            String OperatingSystem = System.getProperty("os.name");
//            if (OperatingSystem.contains("Windows")) {
//                Runtime.getRuntime().exec("cls");
//            } else {
//                Runtime.getRuntime().exec("clear");
//            }
//        } catch (final Exception e) {
//            System.out.println("Clearing manually!");
//            TimeUnit.SECONDS.sleep(3);
//            for (int i = 0; i < 50; ++i) System.out.println();
//        }

        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (final Exception e) {
            System.out.println("Clearing manually!");
            TimeUnit.SECONDS.sleep(3);
            //for (int i = 0; i < 50; ++i) System.out.println();
            System.out.print("\033\143");
        }
    }

    private void playerBuyConsonant() throws InterruptedException {
        String guessedLetter;
        boolean isValidConsonants = false;
        String playerName = currentGame.getCurrentPlayersTurn().getName();
        String playerColor = currentGame.getCurrentPlayersTurn().getPlayerColor();


        int wheelValue = currentGame.spinWheel();
//        System.out.println(" Spinning Wheel...");
        System.out.print(playerColor  + playerName + ANSI_RESET);
        System.out.println(" Spinning Wheel...");

        System.out.println("Wheel Value: " + wheelValue + "\n");


        if (wheelValue == 0) {
            System.out.println( ANSI_RED + "YOU LOSE A TURN! Next Players Turn: "
                    + currentGame.getCurrentPlayersTurn().getName() + "\n" + ANSI_RESET);
            return;
        } else if (wheelValue == -1) {
            System.out.println(ANSI_RED + "BANKRUPT! Next Players Turn: "
                    + currentGame.getCurrentPlayersTurn().getName() + "\n" + ANSI_RESET);
            return;
        }

        System.out.println("Guessing letter...");
        System.out.println("Enter letter: ");
        guessedLetter = userInputScanner.nextLine();

        while (!isValidConsonants) {

            for (char consonant : currentGame.getConsonants()) {
                if (guessedLetter.toUpperCase().charAt(0) == consonant) {
                    isValidConsonants = true;
                    break;
                }
            }

            if (!isValidConsonants) {
                System.out.println(ANSI_RED + "Error invalid consonant you lose a turn!!" + ANSI_RESET);
                currentGame.getNextPlayer();
                TimeUnit.SECONDS.sleep(3);
                break;
            } else {
                int occurrences = currentGame.pickLetter(guessedLetter.toUpperCase().charAt(0));
                System.out.println("Letter appeared " + occurrences + " times!");
                TimeUnit.SECONDS.sleep(3);
            }

        }


    }

    private void playerBuyVowel() throws InterruptedException {
        String vowelPurchase;
        boolean isValidVowel = false;

        if (currentGame.getCurrentPlayersTurn().getCurrentRoundMoney() >= 250) {
            System.out.println("Buying vowel...");
            System.out.println("Enter Vowel...");
            vowelPurchase = userInputScanner.nextLine();

            while (!isValidVowel) {
                for (char consonant : currentGame.getVowels()) {
                    if (vowelPurchase.toUpperCase().charAt(0) == consonant) {
                        isValidVowel = true;
                        break;
                    }
                }
                if (!isValidVowel) {
                    System.out.println(ANSI_RED + "Invalid vowel, Sorry you lose a turn!" + ANSI_RESET);
                    currentGame.getNextPlayer();
                    TimeUnit.SECONDS.sleep(3);
                    break;

                } else {
                    int occurrences = currentGame.buyAVowel(vowelPurchase.toUpperCase().charAt(0));
                    System.out.println("Vowel occurrences: " + occurrences + "!!");
                    System.out.println(currentGame.getCurrentPlayersTurn());
                    TimeUnit.SECONDS.sleep(3);
                }

            }

        } else {
            System.out.println(ANSI_RED + "Sorry not enough money..." + ANSI_RESET);
            TimeUnit.SECONDS.sleep(2);

        }
    }

    private boolean playerAttemptSolve(boolean isSolved) throws InterruptedException {
        String solvePuzzleAttempt;

        System.out.println("Solve Puzzle: ");
        solvePuzzleAttempt = userInputScanner.nextLine();
        int isPuzzleSolved = currentGame.solvePuzzle(solvePuzzleAttempt);

        if (isPuzzleSolved == 1) {
            System.out.println("Solved!");
            isSolved = true;
        } else {
            System.out.println("Not Solved!");
        }

        TimeUnit.SECONDS.sleep(3);
        return isSolved;
    }

    public void gameLoop() throws InterruptedException {

        // commented out for convenience for now
        startGame();
        if (startGame) {
            numberOfPlayers();
            currentGame.startRound();
        }

        boolean correctOption = false;
        Integer intUserInput = 0;
        boolean isSolved = false;
        String playerName;
        String playerColor;

        while (!isSolved) {
            displayCurrentPuzzle();

            playerName = String.valueOf(currentGame.getCurrentPlayersTurn());
            playerColor = currentGame.getCurrentPlayersTurn().getPlayerColor();

//            System.out.println("Color: " + playerColor.concat("test"));

            System.out.println(playerColor + playerName + "\n" + ANSI_RESET);

            while (!correctOption) {

                System.out.println("Options...");
                System.out.println("Press 1 to guess a letter.");
                System.out.println("Press 2 to buy a vowel.");
                System.out.println("Press 3 to SOLVE...");

                String pressedKey = userInputScanner.nextLine();

                if (pressedKey.matches("[1-3]")) {
                    intUserInput = Integer.parseInt(pressedKey);
                    correctOption = true;
                } else {
                    System.out.println(ANSI_RED + "Incorrect options - Please Select from [1-3]" + ANSI_RESET);
                }
            }

            switch (intUserInput) {
                case 1:
                    playerBuyConsonant();
                    break;
                case 2:
                    playerBuyVowel();
                    break;
                case 3:
                    isSolved = playerAttemptSolve(false);
                    break;
            }


            if (isSolved && currentGame.startRound() == 0) {
                clearGameScreen();
                currentGame.setWinningPlayer();
                System.out.println("Winning Player!");
                System.out.println(currentGame.getWinningPlayer());
                WOFAsciiArt.winningPlayerMessage();
            } else {
                clearGameScreen();
                intUserInput = 0;
                correctOption = false;
                isSolved = false;
            }

        }

    }

    public void setUserInputScanner(Scanner userInputScanner) {
        this.userInputScanner = userInputScanner;
    }

    public Boolean getStartGame() {
        return startGame;
    }

    public Integer getNumOfPlayer() {
        return numOfPlayer;
    }

    public void setNumOfPlayer(Integer numOfPlayer) {
        this.numOfPlayer = numOfPlayer;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

}

class Main {
    public static void main(String[] args) throws InterruptedException {
        GameDialogue gameDialogue = new GameDialogue();
        gameDialogue.gameLoop();
    }
}