package com.game.wheeloffortune;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUIClient extends JFrame {

    // Static Fields
    public static final char[] TOP_CHARS = {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'};
    public static final char[] MID_CHARS = {'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'};
    public static final char[] BOT_CHARS = {'Z', 'X', 'C', 'V', 'B', 'N', 'M'};

    public static final char[][] CHARACTERS = {TOP_CHARS, MID_CHARS, BOT_CHARS};
    public static final String[] BORDERS_ROW_NAMES = {"North", "Center", "South"};
    public static final Color[] PLAYER_COLORS = {Color.RED, new Color(0, 179, 24), Color.BLUE};

    // Fields
    JFrame mainWindow;
    Game game;
    boolean wheelSpun = false;
    boolean vowelBought = false;
    private JPanel playerPanel;
    private final List<Player> players;

    // Constructor
    GUIClient(List<Player> players) {
        this.players = players;
        game = new Game(players);
        game.startRound();
        mainWindow = new JFrame();
        setMainWindow();
        updateWindow();
    }

    // Business Methods
    /* -------------UI Methods-----------------------------------------------------*/
    // Create main Window that the game is viewed in
    private void setMainWindow() {
        ImageIcon icon = new ImageIcon("colorWheel.png");
        mainWindow.setIconImage(icon.getImage());
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setSize(1200, 720);
        mainWindow.setTitle("Wheel of Fortune: Java Edition");
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
    }

    // Used to refresh the contents of the window any time a change is made
    private void updateWindow() {
        mainWindow.getContentPane().removeAll();
        displayPuzzle();
        setPlayerWindows();
        buttons();
        displayWheel();
    }

    // Main Window North
    private void displayPuzzle() {

        JPanel puzzlePanel = new JPanel();
        puzzlePanel.setLayout(new BorderLayout());
        String puzzle = game.getCurrentGameBoard().getGamePuzzle();
        String hint = game.getCurrentGameBoard().getGameHint();

        JLabel hintLabel = new JLabel();
        hintLabel.setText("Puzzle Category: " + hint);
        hintLabel.setPreferredSize(new Dimension((25 * (hint.length() + 17)), 40));
        hintLabel.setHorizontalAlignment(JLabel.CENTER);
        hintLabel.setFont(new Font("Courier", Font.BOLD, 24));
        puzzlePanel.add(hintLabel, BorderLayout.NORTH);

        JPanel currentPuzzle = new JPanel(new FlowLayout());
        int characterLengthOfPuzzle = game.getCurrentGameBoard().getGamePuzzle().length();
        for (int i = 0; i < characterLengthOfPuzzle; i++) {
            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(25, 40));
            if (puzzle.charAt(i) != '\u25A0') {
                label.setText(String.valueOf(puzzle.charAt(i)));
            }
            if (puzzle.charAt(i) != ' ') {
                label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            }
            label.setFont(new Font("Courier", Font.BOLD, 24));
            currentPuzzle.add(label);
        }
        puzzlePanel.add(currentPuzzle, BorderLayout.CENTER);
        mainWindow.add(puzzlePanel, BorderLayout.NORTH);
        mainWindow.revalidate();
    }

    // Main Window Center
    private void displayWheel() {
        JPanel centerPanel = new JPanel(new BorderLayout()); // This creates a panel within the center frame of the main window

        JPanel roundNumber = new JPanel(new BorderLayout()); // A panel to hold the Round Numbers
        JLabel round = new JLabel(); // A label to display the round numbers

        // These 4 lines format the text in the label
        round.setText("Round Number: " + game.getCurrentRoundNumber());
        round.setFont(new Font("Courier", Font.BOLD, 24));
        round.setIconTextGap(-15);
        round.setHorizontalAlignment(JLabel.CENTER);

        roundNumber.add(round, BorderLayout.NORTH); // Adds the round number panel to the center panel in the main window

        // If the spin wheel option was used, this will ensure the value of the wheel spin is displayed
        if (wheelSpun) {
            JLabel value = new JLabel();
            value.setText("Current Wheel Value: " + game.getValueOfWheelSpin());
            value.setFont(new Font("Courier", Font.BOLD, 24));
            value.setIconTextGap(-15);
            value.setHorizontalAlignment(JLabel.CENTER);
            roundNumber.add(value, BorderLayout.CENTER);
        }
        centerPanel.add(roundNumber, BorderLayout.NORTH);

        mainWindow.add(centerPanel);
        mainWindow.revalidate();
    }

    // Main Window South
    private void setPlayerWindows() {
        playerPanel = new JPanel(new BorderLayout());
        JPanel playersInPanel = new JPanel(new FlowLayout());
        for (int i = 0; i < 3; i++) {
            playersInPanel.add(addPlayerToPanel(i));
        }
        playerPanel.add(playersInPanel, BorderLayout.SOUTH);
        mainWindow.add(playerPanel, BorderLayout.SOUTH);
        mainWindow.revalidate();
    }
    private void buttons() {
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton spinWheel = new JButton();
        spinWheel.setText("Spin Wheel");
        spinWheel.setEnabled(!wheelSpun && !vowelBought);
        spinWheel.addActionListener(e -> validateWheelSpin());

        JButton buyAVowel = new JButton();
        buyAVowel.setText("Buy a Vowel");
        buyAVowel.setEnabled(!vowelBought && !wheelSpun &&
                game.getCurrentPlayersTurn().getCurrentRoundMoney() >= Game.COST_OF_VOWEL);
        buyAVowel.addActionListener(e -> {
            vowelBought = true;
            updateWindow();
        });

        JButton solvePuzzle = new JButton();
        solvePuzzle.setText("Solve Puzzle");
        solvePuzzle.setEnabled(!wheelSpun && !vowelBought);
        solvePuzzle.addActionListener(e -> solvePuzzle());

        JPanel letters = new JPanel(new BorderLayout());
        for (int i = 0; i < 3; i++) {
            letters.add(fillRow(CHARACTERS[i]), BORDERS_ROW_NAMES[i]);
        }

        buttonPanel.add(spinWheel);
        buttonPanel.add(buyAVowel);
        buttonPanel.add(solvePuzzle);
        playerPanel.add(buttonPanel, BorderLayout.NORTH);
        playerPanel.add(letters, BorderLayout.CENTER);
        mainWindow.revalidate();
    }

    private JPanel fillRow(char[] row) {
        JPanel rowOfLetters = new JPanel(new FlowLayout());
        for (char c : row) {
            rowOfLetters.add(addLetter(c));
        }
        return rowOfLetters;
    }
    private JButton addLetter(char c) {
        JButton button = new JButton();
        button.setText(String.valueOf(c));
        button.setEnabled(false);
        if (game.getConsonants().contains(c) && wheelSpun) {
            button.setEnabled(true);
        } else if (game.getVowels().contains(c) && vowelBought) {
            button.setEnabled(true);
        }
        button.addActionListener(e -> letterActionMethod(c));
        return button;
    }
    private JPanel addPlayerToPanel(int i) {
        JPanel player = new JPanel(new BorderLayout());
        player.setBackground(PLAYER_COLORS[i]);
        player.setPreferredSize(new Dimension(200, 120));

        JLabel pName = new JLabel();
        pName.setText(game.getPlayers().get(i).getName().toUpperCase());
        pName.setFont(new Font("Courier", Font.BOLD, 24));
        pName.setIconTextGap(-15);
        if (game.getPlayers().get(i).equals(game.getCurrentPlayersTurn())) {
            pName.setForeground(Color.CYAN);
        } else {
            pName.setForeground(Color.WHITE);
        }
        pName.setHorizontalAlignment(JLabel.CENTER);

        JLabel pRoundMoney = new JLabel();
        pRoundMoney.setText("<html>Round Money: <br>   $ " +
                game.getPlayers().get(i).getCurrentRoundMoney() + "</html>");
        pRoundMoney.setFont(new Font("Courier", Font.BOLD, 16));
        pRoundMoney.setIconTextGap(-15);
        pRoundMoney.setForeground(Color.WHITE);
        pRoundMoney.setBackground(Color.BLACK);
        pRoundMoney.setHorizontalAlignment(JLabel.CENTER);

        JLabel pTotalMoney = new JLabel();
        pTotalMoney.setText("<html>Total Money: <br>   $ " +
                game.getPlayers().get(i).getTotalMoney() + "</html>");
        pTotalMoney.setFont(new Font("Courier", Font.BOLD, 16));
        pTotalMoney.setIconTextGap(-15);
        pTotalMoney.setForeground(Color.WHITE);
        pTotalMoney.setHorizontalAlignment(JLabel.CENTER);

        player.add(pName, BorderLayout.NORTH);
        player.add(pRoundMoney, BorderLayout.CENTER);
        player.add(pTotalMoney, BorderLayout.SOUTH);
        return (player);
    }
    /*------------------------------------------------------------------------------*/
    /*----------------------Game Logic----------------------------------------------*/
    private void solvePuzzle() {
        int i = game.solvePuzzle(JOptionPane.showInputDialog("Solve Puzzle: "));
        if (i == 1) {
            JOptionPane.showMessageDialog(null,
                    "That is correct!", "Congratulations!", JOptionPane.WARNING_MESSAGE);
            if (game.getCurrentRoundNumber() >= Game.NUMBER_OF_ROUNDS) {
                game.startRound();
                int choice = JOptionPane.showConfirmDialog(null,
                        "Winner was: \n" + game.getWinningPlayer() + "\nCongratulations!\nPlay another game?",
                        "Winner!", JOptionPane.YES_NO_OPTION);
                if (choice == 0) {
                    game = new Game(players);
                    for (Player p : game.getPlayers()) {
                        p.setTotalMoney(0);
                        p.setCurrentRoundMoney(0);
                    }
                    game.startRound();
                } else if (choice == 1) {
                    System.exit(0);
                }
            } else {
                game.startRound();
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Sorry that wasn't correct. Next Player's turn.",
                    "Incorrect", JOptionPane.WARNING_MESSAGE);
        }
        vowelBought = false;
        wheelSpun = false;
        updateWindow();
    }

    private void validateWheelSpin() {
        int i = game.spinWheel();
        if (i == 0) {
            JOptionPane.showMessageDialog(null,
                    "You spun LOSE A TURN!", "Lose a Turn", JOptionPane.WARNING_MESSAGE);

        } else if (i == -1) {
            JOptionPane.showMessageDialog(null,
                    "You spun BANKRUPT! You lose your turn and all your round winnings!",
                    "Bankrupt", JOptionPane.WARNING_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(null,
                    "Wheel Value: " + game.getValueOfWheelSpin(),
                    "Wheel Spin", JOptionPane.INFORMATION_MESSAGE);
            wheelSpun = true;
        }
        updateWindow();
    }

    private void letterActionMethod(char c) {

        if (game.getConsonants().contains(c) && wheelSpun) {
            pickConsonant(c);
        } else {
            if (game.getConsonants().contains(c) && !wheelSpun) {
                JOptionPane.showMessageDialog(null,
                        "You must spin the wheel to pick a letter!",
                        "No Wheel Spun", JOptionPane.WARNING_MESSAGE);
                updateWindow();
            } else {
                if (vowelBought) {
                    pickVowel(c);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "That was not a valid input! Next Player's Turn",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    vowelBought = false;
                    wheelSpun = false;
                    updateWindow();
                }

            }
        }
        vowelBought = false;
        wheelSpun = false;
        updateWindow();
    }

    private void pickConsonant(char c) {
        int i = game.pickLetter(c);
        vowelBought = false;
        wheelSpun = false;
        updateWindow();
        if (i == 0) {
            JOptionPane.showMessageDialog(null,
                    c + " was not in the Puzzle. Next Player's turn.",
                    "Incorrect", JOptionPane.WARNING_MESSAGE);
            vowelBought = false;
            wheelSpun = false;
            updateWindow();
        } else if (i == -1) {
            JOptionPane.showMessageDialog(null,
                    c + " was already chosen! Next Player's turn.",
                    "Incorrect", JOptionPane.WARNING_MESSAGE);
            vowelBought = false;
            wheelSpun = false;
            updateWindow();
        }
    }

    private void pickVowel(char c) {
        int i = 0;
        try {
            i = game.buyAVowel(c);
            vowelBought = false;
            wheelSpun = false;
            updateWindow();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null,
                    "You don't have enough money!",
                    "No Money", JOptionPane.WARNING_MESSAGE);
            updateWindow();
        }
        if (i == 0) {
            JOptionPane.showMessageDialog(null,
                    c + " was not in the Puzzle. Next Player's turn.",
                    "Incorrect", JOptionPane.WARNING_MESSAGE);
            vowelBought = false;
            wheelSpun = false;
            updateWindow();
        } else if (i == -1) {
            JOptionPane.showMessageDialog(null,
                    c + " was already chosen! Next Player's turn.",
                    "Incorrect", JOptionPane.WARNING_MESSAGE);
            vowelBought = false;
            wheelSpun = false;
            updateWindow();
        }
    }
    /*------------------------------------------------------------------------------*/

    // Public Method to Start a Game
    public static void runClient() {

        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            String pName = JOptionPane.showInputDialog(null,
                    "Input Player " + i + "'s Name: ", "Player Name Input", JOptionPane.PLAIN_MESSAGE);
            players.add(new Player(pName));
        }
        new GUIClient(players);
    }
}