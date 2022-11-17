package com.game.wheeloffortune.client;

import com.game.wheeloffortune.Game;
import com.game.wheeloffortune.GameBoard;
import com.game.wheeloffortune.Player;
import com.game.wheeloffortune.utilities.WOFAsciiArt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameDialogue {

    private Integer numOfPlayers;
    private Scanner userInputScanner;
    private List<Player> players;
    private Game currentGame;
    private Boolean startGame;
    private GameBoard currentGameBoard;


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLINK = "\033[5m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_BG_GREEN = "\u001b[42m";


    public GameDialogue() {
        players = new ArrayList<>();
        userInputScanner = new Scanner(System.in);
        currentGame = new Game();
        startGame = false;
    }

    public void startGame() throws InterruptedException {

        WOFAsciiArt.windowSizes();
        WOFAsciiArt.printWOFLogoBlink();

        System.out.println(ANSI_BG_GREEN + ANSI_RED + "Press 'X' to Start Game 'Q' to Exit!: " + ANSI_RESET);

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
                pressedKey = userInputScanner.nextLine();
            }
        }

        if (startGame) {
            System.out.println(ANSI_GREEN + "Starting Game..." + ANSI_RESET);
            TimeUnit.SECONDS.sleep(3);
            clearGameScreen();
        }
    }

    public void setNumberOfPlayers() {

        WOFAsciiArt.printReadyPlayer();
        WOFAsciiArt.printStarryNight();


        System.out.println(ANSI_GREEN + "Select number of players [1-3]" + ANSI_RESET);

        Integer playerCount = Integer.parseInt(userInputScanner.nextLine());

        while (playerCount < 1 || playerCount > 3) {
            System.out.println(ANSI_RED + "Error please select between 1-3 players..." + ANSI_RESET);
            playerCount = Integer.parseInt(userInputScanner.nextLine());

        }

        System.out.println(ANSI_GREEN + "Number of Players: " + playerCount + ANSI_RESET);

        numOfPlayers = playerCount;
        setPlayerNames();
    }

    public void setPlayerNames() {
        for (int i = 1; i <= numOfPlayers; i++) {
            System.out.println(ANSI_GREEN + "Please enter name for Player " + i + ANSI_RESET);
            String playerName = userInputScanner.nextLine();
            players.add(new Player(playerName));
        }
        // sets players in game class after creating & assigning names
        currentGame.setPlayers(players);
    }

    public void displayCurrentPuzzle() {
        currentGameBoard = currentGame.getCurrentGameBoard();
        String puzzleHint = currentGameBoard.getGameHint();
        String puzzle = currentGameBoard.getGamePuzzle();
        String roundNumber = String.valueOf(currentGame.getCurrentRoundNumber());

        System.out.println("Round #: " + roundNumber);
        System.out.println("Hint: " + puzzleHint);
        System.out.println("Puzzle: " + puzzle + "\n");
    }

    public void clearGameScreen() throws InterruptedException {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (final Exception e) {
            TimeUnit.MILLISECONDS.sleep(50L);
            System.out.print("\033\143");
        }

        for (int i = 0; i < 29; ++i) System.out.println();
    }

    private void playerBuyConsonant(String playerColor) throws InterruptedException {
        String guessedLetter;
        boolean isValidConsonants = false;
        String playerName = currentGame.getCurrentPlayersTurn().getName();
        int wheelValue = currentGame.spinWheel();


        clearGameScreen();
        WOFAsciiArt.printWOFBanner(playerColor, 10);

        displayCurrentPuzzle();
        System.out.print(playerColor + playerName + ANSI_RESET);
        System.out.println(" is Spinning the Wheel...");

        System.out.println("Wheel Value: " + wheelValue + "\n");


        if (wheelValue == 0) {

            System.out.println(ANSI_RED + "YOU LOSE A TURN! Next Players Turn: "
                    + currentGame.getCurrentPlayersTurn().getName()
                    + "\n"
                    + ANSI_RESET);

            TimeUnit.SECONDS.sleep(5);

            return;
        } else if (wheelValue == -1) {
            TimeUnit.SECONDS.sleep(3);
            WOFAsciiArt.printOutBankruptMessage();

            System.out.println(ANSI_RED
                    + "BANKRUPT! Next Players Turn: "
                    + currentGame.getCurrentPlayersTurn().getName()
                    + "\n" + ANSI_RESET);

            TimeUnit.SECONDS.sleep(3);
            return;
        }

        System.out.println("Guessing a Consonant...");
        System.out.println("Enter a Consonant: ");
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
                System.out.println("Letter " + guessedLetter + " appeared " + occurrences + " times!");
                TimeUnit.SECONDS.sleep(3);
            }
        }
    }

    private void playerBuyVowel(String playerColor) throws InterruptedException {
        String vowelPurchase;
        boolean isValidVowel = false;

        clearGameScreen();
        WOFAsciiArt.printWOFBanner(playerColor, 13);

        if (currentGame.getCurrentPlayersTurn().getCurrentRoundMoney() >= 250) {
            displayCurrentPuzzle();
            System.out.println("Buying a vowel cost $250...");
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
                    System.out.println("Vowel " + vowelPurchase + " appeared " + occurrences + " times!");
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
        String playerColor = currentGame.getCurrentPlayersTurn().getPlayerColor();

        clearGameScreen();
        WOFAsciiArt.printWOFBanner(playerColor, 13);
        displayCurrentPuzzle();

        System.out.println("Enter Your Guess to Solve the Puzzle: ");
        solvePuzzleAttempt = userInputScanner.nextLine();
        int isPuzzleSolved = currentGame.solvePuzzle(solvePuzzleAttempt);

        if (isPuzzleSolved == 1) {
            System.out.println(ANSI_CYAN + "Great Job! You Solved the Puzzle!" + ANSI_RESET);
            isSolved = true;
        } else {
            System.out.println(ANSI_RED + "Incorrect Guess! You did not solve the puzzle!" + ANSI_RESET);
        }

        TimeUnit.SECONDS.sleep(3);
        return isSolved;
    }

    public void gameLoop() throws InterruptedException {

        startGame();

        if (startGame) {
            setNumberOfPlayers();
            clearGameScreen();
            currentGame.startRound();
        }

        boolean correctOption = false;
        Integer intUserInput = 0;
        boolean isSolved = false;
        String playerName = null;
        String playerColor = null;

        while (!isSolved) {

            while (!correctOption) {

                playerName = String.valueOf(currentGame.getCurrentPlayersTurn());
                playerColor = currentGame.getCurrentPlayersTurn().getPlayerColor();
                WOFAsciiArt.printWOFBanner(playerColor, 6);
                displayCurrentPuzzle();
                System.out.println(playerColor + playerName + "\n" + ANSI_RESET);

                System.out.println("Options...");
                System.out.println("Press 1 to Spin the Wheel and Guess a Consonant...");
                System.out.println("Press 2 to Buy a Vowel for $250...");
                System.out.println("Press 3 to SOLVE...");

                String pressedKey = userInputScanner.nextLine();

                if (pressedKey.matches("[1-3]")) {
                    intUserInput = Integer.parseInt(pressedKey);
                    correctOption = true;
                } else {
                    System.out.println(ANSI_RED + "Incorrect options - Please Select from [1-3]" + ANSI_RESET);
                    TimeUnit.SECONDS.sleep(3);
                    clearGameScreen();
                }

            }

            switch (intUserInput) {
                case 1:
                    playerBuyConsonant(playerColor);
                    break;
                case 2:
                    playerBuyVowel(playerColor);
                    break;
                case 3:
                    isSolved = playerAttemptSolve(false);
                    break;
            }


            if (isSolved && currentGame.startRound() == 0) {
                clearGameScreen();
                currentGame.setWinningPlayer();
                String winningPlayerColor = currentGame.getWinningPlayerObject().getPlayerColor();
                WOFAsciiArt.printAsciiMessage("CONGRATULATIONS!", winningPlayerColor);
                WOFAsciiArt.printAsciiMessage(currentGame.getWinningPlayerObject().getName(), winningPlayerColor);
                WOFAsciiArt.printAsciiMessage(
                        "$" +
                                String.valueOf(currentGame.getWinningPlayerObject().getTotalMoney()),
                        winningPlayerColor);
                currentGame.getWinningPlayer();
                TimeUnit.SECONDS.sleep(100);
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

    public Integer getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(Integer numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

}
