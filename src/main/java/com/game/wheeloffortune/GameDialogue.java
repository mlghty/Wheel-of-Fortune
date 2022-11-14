package com.game.wheeloffortune;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameDialogue {

    private Integer numOfPlayer;
    private Scanner userInputScanner;
    private List<Player> players;
    private Game currentGame;
    private Boolean startGame;

    public GameDialogue() {
        players = new ArrayList<>();
        userInputScanner = new Scanner(System.in);
        currentGame = new Game();
        startGame = false;

//        currentGame = new Game(new Player("joe"));
//        currentGame.startRound();
//        numOfPlayer = 1;

    }

    public void startGame() throws InterruptedException {

        System.out.println("WHEEL OF FORTUNE \n \n \n \n");

        System.out.println("Press 'X' to Start Game: ");
        System.out.println("Press 'Q' to Exit Game: ");

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
    public void clearGameScreen() {
        try {
            String OperatingSystem = System.getProperty("os.name");
            if (OperatingSystem.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            for (int i = 0; i < 50; ++i) System.out.println();
        }
    }

    private void playerBuyConsonant() throws InterruptedException {
        String guessedLetter;
        boolean isValidConsonants = false;
        String playerName = currentGame.getCurrentPlayersTurn().getName();


        int wheelValue = currentGame.spinWheel();
        System.out.println(playerName + " Spinning Wheel...");
        System.out.println("Wheel Value: " + wheelValue + "\n");


        if (wheelValue == 0) {
            System.out.println("YOU LOSE A TURN! Next Players Turn: "
                    + currentGame.getCurrentPlayersTurn().getName() + "\n");
        } else if (wheelValue == -1) {
            System.out.println("BANKRUPT! Next Players Turn: "
                    + currentGame.getCurrentPlayersTurn().getName() + "\n");
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
                System.out.println("Error invalid consonant you lose a turn!!");
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
                    System.out.println("Invalid vowel, Sorry you lose a turn!");
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
            System.out.println("Sorry not enough money...");
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

//        if (startGame) {
//            numberOfPlayers();
//            setPlayerNames();
//            currentGame.startRound();
//        }


        // comment out when complete
        numberOfPlayers();
        currentGame.startRound();

        boolean correctOption = false;
        Integer intUserInput = 0;
        boolean isSolved = false;

        while (!isSolved) {
            displayCurrentPuzzle();
            System.out.println(currentGame.getCurrentPlayersTurn() + "\n");

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
                    System.out.println("Incorrect options - Please Select from [1-3]");
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

            // reset vars
//            clearGameScreen();
//            intUserInput = 0;
//            correctOption = false;

            if (isSolved && currentGame.startRound() == 0) {
                clearGameScreen();
                currentGame.setWinningPlayer();
                System.out.println("Winning Player!");
                System.out.println(currentGame.getWinningPlayer());
            } else {
                clearGameScreen();
                intUserInput = 0;
                correctOption = false;
                isSolved = false;
            }

        }

//        if (isSolved && currentGame.startRound() == 0) {
//            currentGame.setWinningPlayer();
//            System.out.println("Winning Player!");
//            System.out.println(currentGame.getWinningPlayer());
//        }else{
//            isSolved = false;
//        }

    }

}

class Main {
    public static void main(String[] args) throws InterruptedException {
        GameDialogue gameDialogue = new GameDialogue();
        gameDialogue.gameLoop();
    }
}