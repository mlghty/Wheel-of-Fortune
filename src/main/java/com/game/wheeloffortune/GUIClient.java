package com.game.wheeloffortune;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GUIClient extends JFrame {

    public static final char[] TOP_CHARS = {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'};
    public static final char[] MID_CHARS = {'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'};
    public static final char[] BOT_CHARS = {'Z', 'X', 'C', 'V', 'B', 'N', 'M'};
    JFrame mainWindow;
    Game game;

    String filePath = new File("").getAbsolutePath();

    boolean wheelSpun = false;
    boolean vowelBought = false;
    private JPanel playerPanel;
    private List<Player> players;

    GUIClient(List<Player> players) {
        this.players = players;
        game = new Game(players);
        game.startRound();
        mainWindow = new JFrame();
        setMainWindow();
        updateWindow();
    }

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
        JPanel centerPanel = new JPanel(new BorderLayout());

        JPanel roundNumber = new JPanel(new BorderLayout());
        JLabel round = new JLabel();
        round.setText("Round Number: " + game.getCurrentRoundNumber());
        round.setFont(new Font("Courier", Font.BOLD, 24));
        round.setIconTextGap(-15);
        round.setHorizontalAlignment(JLabel.CENTER);
        roundNumber.add(round, BorderLayout.NORTH);
        if (wheelSpun) {
            JLabel value = new JLabel();
            value.setText("Current Wheel Value: " + game.getValueOfWheelSpin());
            value.setFont(new Font("Courier", Font.BOLD, 24));
            value.setIconTextGap(-15);
            value.setHorizontalAlignment(JLabel.CENTER);
            roundNumber.add(value, BorderLayout.CENTER);
        }
        centerPanel.add(roundNumber, BorderLayout.NORTH);

//        BufferedImage myPicture = null;
//        System.out.println(filePath);
//        try {
//            myPicture = ImageIO.read(new File("colorWheel.png"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//        centerPanel.add(picLabel,BorderLayout.CENTER);


        mainWindow.add(centerPanel);
        mainWindow.revalidate();
    }

    // Main Window South
    private void setPlayerWindows() {
        playerPanel = new JPanel(new BorderLayout());

        JPanel player1 = new JPanel(new BorderLayout());
        player1.setBackground(Color.BLUE);
        player1.setPreferredSize(new Dimension(200, 120));

        JLabel p1Name = new JLabel();
        p1Name.setText(game.getPlayers().get(0).getName().toUpperCase());
        p1Name.setFont(new Font("Courier", Font.BOLD, 24));
        p1Name.setIconTextGap(-15);
        if (game.getPlayers().get(0).equals(game.getCurrentPlayersTurn())) {
            p1Name.setForeground(Color.YELLOW);
        } else {
            p1Name.setForeground(Color.WHITE);
        }
        p1Name.setHorizontalAlignment(JLabel.CENTER);

        JLabel p1RoundMoney = new JLabel();
        p1RoundMoney.setText("<html>Round Money: <br>   $ " + game.getPlayers().get(0).getCurrentRoundMoney() + "</html>");
        p1RoundMoney.setFont(new Font("Courier", Font.BOLD, 16));
        p1RoundMoney.setIconTextGap(-15);
        p1RoundMoney.setForeground(Color.WHITE);
        p1RoundMoney.setBackground(Color.BLACK);
        p1RoundMoney.setHorizontalAlignment(JLabel.CENTER);

        JLabel p1TotalMoney = new JLabel();
        p1TotalMoney.setText("<html>Total Money: <br>   $ " + game.getPlayers().get(0).getTotalMoney() + "</html>");
        p1TotalMoney.setFont(new Font("Courier", Font.BOLD, 16));
        p1TotalMoney.setIconTextGap(-15);
        p1TotalMoney.setForeground(Color.WHITE);
        p1TotalMoney.setHorizontalAlignment(JLabel.CENTER);

        player1.add(p1Name, BorderLayout.NORTH);
        player1.add(p1RoundMoney, BorderLayout.CENTER);
        player1.add(p1TotalMoney, BorderLayout.SOUTH);


        JPanel player2 = new JPanel(new BorderLayout());
        JLabel p2Name = new JLabel();
        p2Name.setText(game.getPlayers().get(1).getName().toUpperCase());
        p2Name.setFont(new Font("Courier", Font.BOLD, 24));
        p2Name.setIconTextGap(-15);
        if (game.getPlayers().get(1).equals(game.getCurrentPlayersTurn())) {
            p2Name.setForeground(Color.YELLOW);
        } else {
            p2Name.setForeground(Color.WHITE);
        }
        p2Name.setHorizontalAlignment(JLabel.CENTER);

        JLabel p2RoundMoney = new JLabel();
        p2RoundMoney.setText("<html>Round Money: <br>   $ " + game.getPlayers().get(1).getCurrentRoundMoney() + "</html>");
        p2RoundMoney.setFont(new Font("Courier", Font.BOLD, 16));
        p2RoundMoney.setIconTextGap(-15);
        p2RoundMoney.setForeground(Color.WHITE);
        p2RoundMoney.setBackground(Color.BLACK);
        p2RoundMoney.setHorizontalAlignment(JLabel.CENTER);

        JLabel p2TotalMoney = new JLabel();
        p2TotalMoney.setText("<html>Total Money: <br>   $ " + game.getPlayers().get(1).getTotalMoney() + "</html>");
        p2TotalMoney.setFont(new Font("Courier", Font.BOLD, 16));
        p2TotalMoney.setIconTextGap(-15);
        p2TotalMoney.setForeground(Color.WHITE);
        p2TotalMoney.setHorizontalAlignment(JLabel.CENTER);

        player2.add(p2Name, BorderLayout.NORTH);
        player2.add(p2RoundMoney, BorderLayout.CENTER);
        player2.add(p2TotalMoney, BorderLayout.SOUTH);

        JPanel player3 = new JPanel(new BorderLayout());
        JLabel p3Name = new JLabel();
        p3Name.setText(game.getPlayers().get(2).getName().toUpperCase());
        p3Name.setFont(new Font("Courier", Font.BOLD, 24));
        p3Name.setIconTextGap(-15);
        if (game.getPlayers().get(2).equals(game.getCurrentPlayersTurn())) {
            p3Name.setForeground(Color.YELLOW);
        } else {
            p3Name.setForeground(Color.WHITE);
        }
        p3Name.setHorizontalAlignment(JLabel.CENTER);

        JLabel p3RoundMoney = new JLabel();
        p3RoundMoney.setText("<html>Round Money: <br>   $ " + game.getPlayers().get(2).getCurrentRoundMoney() + "</html>");
        p3RoundMoney.setFont(new Font("Courier", Font.BOLD, 16));
        p3RoundMoney.setIconTextGap(-15);
        p3RoundMoney.setForeground(Color.WHITE);
        p3RoundMoney.setBackground(Color.BLACK);
        p3RoundMoney.setHorizontalAlignment(JLabel.CENTER);

        JLabel p3TotalMoney = new JLabel();
        p3TotalMoney.setText("<html>Total Money: <br>   $ " + game.getPlayers().get(2).getTotalMoney() + "</html>");
        p3TotalMoney.setFont(new Font("Courier", Font.BOLD, 16));
        p3TotalMoney.setIconTextGap(-15);
        p3TotalMoney.setForeground(Color.WHITE);
        p3TotalMoney.setHorizontalAlignment(JLabel.CENTER);

        player3.add(p3Name, BorderLayout.NORTH);
        player3.add(p3RoundMoney, BorderLayout.CENTER);
        player3.add(p3TotalMoney, BorderLayout.SOUTH);

        player2.setBackground(Color.green);
        player3.setBackground(Color.red);


        player2.setPreferredSize(new Dimension(200, 120));
        player3.setPreferredSize(new Dimension(200, 120));
        JPanel players = new JPanel(new FlowLayout());
        players.add(player1);
        players.add(player2);
        players.add(player3);
        playerPanel.add(players, BorderLayout.SOUTH);
        mainWindow.add(playerPanel, BorderLayout.SOUTH);
        mainWindow.revalidate();
    }

    private void buttons() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton spinWheel = new JButton();
        spinWheel.setText("Spin Wheel");
        spinWheel.addActionListener(e -> {
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
        });
        JButton buyAVowel = new JButton();
        buyAVowel.setText("Buy a Vowel");
        buyAVowel.addActionListener(e -> {
            vowelBought = true;
            updateWindow();
        });

        JButton solvePuzzle = new JButton();
        solvePuzzle.setText("Solve Puzzle");
        solvePuzzle.addActionListener(e -> {
            int i = game.solvePuzzle(JOptionPane.showInputDialog("Solve Puzzle: "));
            if (i == 1) {
                JOptionPane.showMessageDialog(null,
                        "That is correct!", "Congratulations!", JOptionPane.WARNING_MESSAGE);
                if (game.getCurrentRoundNumber() >= Game.NUMBER_OF_ROUNDS) {
                    game.startRound();
                    int choice = JOptionPane.showConfirmDialog(null,
                            "Winner was: \n" + game.getWinningPlayer() +"\nCongratulations!\nPlay another game?",
                            "Winner!",JOptionPane.YES_NO_OPTION);
                    if(choice == 0) {
                        game = new Game(players);
                        for(Player p : game.getPlayers()) {
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
            updateWindow();
        });

        JPanel letters = new JPanel(new BorderLayout());

        JPanel topRow = new JPanel(new FlowLayout());
        for (char c : TOP_CHARS) {
            JButton button = new JButton();
            button.setText(String.valueOf(c));
            button.addActionListener(e -> {
                if (game.getConsonants().contains(c) && wheelSpun) {
                    int i = game.pickLetter(c);
                    if (i == 0) {
                        JOptionPane.showMessageDialog(null,
                                c + " was not in the Puzzle. Next Player's turn.",
                                "Incorrect", JOptionPane.WARNING_MESSAGE);
                    } else if (i == -1) {
                        JOptionPane.showMessageDialog(null,
                                c + " was already chosen! Next Player's turn.",
                                "Incorrect", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    if (game.getConsonants().contains(c) && !wheelSpun) {
                        JOptionPane.showMessageDialog(null,
                                "You must spin the wheel to pick a letter!",
                                "No Wheel Spun", JOptionPane.WARNING_MESSAGE);
                    } else {
                        if (vowelBought) {
                            int i = 0;
                            try {
                                i = game.buyAVowel(c);
                            } catch (IllegalArgumentException ex) {
                                JOptionPane.showMessageDialog(null,
                                        "You don't have enough money!",
                                        "No Money", JOptionPane.WARNING_MESSAGE);
                            }
                            if (i == 0) {
                                JOptionPane.showMessageDialog(null,
                                        c + " was not in the Puzzle. Next Player's turn.",
                                        "Incorrect", JOptionPane.WARNING_MESSAGE);
                            } else if (i == -1) {
                                JOptionPane.showMessageDialog(null,
                                        c + " was already chosen! Next Player's turn.",
                                        "Incorrect", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "That was not a valid input! Next Player's Turn",
                                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                }
                updateWindow();
                vowelBought = false;
                wheelSpun = false;
            });
            topRow.add(button);
        }
        letters.add(topRow, BorderLayout.NORTH);

        JPanel middleRow = new JPanel(new FlowLayout());
        for (char c : MID_CHARS) {
            JButton button = new JButton();
            button.setText(String.valueOf(c));
            button.addActionListener(e -> {
                if (game.getConsonants().contains(c) && wheelSpun) {
                    int i = game.pickLetter(c);
                    if (i == 0) {
                        JOptionPane.showMessageDialog(null,
                                c + " was not in the Puzzle. Next Player's turn.",
                                "Incorrect", JOptionPane.WARNING_MESSAGE);
                    } else if (i == -1) {
                        JOptionPane.showMessageDialog(null,
                                c + " was already chosen! Next Player's turn.",
                                "Incorrect", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    if (game.getConsonants().contains(c) && !wheelSpun) {
                        JOptionPane.showMessageDialog(null,
                                "You must spin the wheel to pick a letter!",
                                "No Wheel Spun", JOptionPane.WARNING_MESSAGE);
                    } else {
                        if (vowelBought) {
                            int i = 0;
                            try {
                                i = game.buyAVowel(c);
                            } catch (IllegalArgumentException ex) {
                                JOptionPane.showMessageDialog(null,
                                        "You don't have enough money!",
                                        "No Money", JOptionPane.WARNING_MESSAGE);
                            }
                            if (i == 0) {
                                JOptionPane.showMessageDialog(null,
                                        c + " was not in the Puzzle. Next Player's turn.",
                                        "Incorrect", JOptionPane.WARNING_MESSAGE);
                            } else if (i == -1) {
                                JOptionPane.showMessageDialog(null,
                                        c + " was already chosen! Next Player's turn.",
                                        "Incorrect", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "That was not a valid input! Next Player's Turn",
                                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                }
                updateWindow();
                vowelBought = false;
                wheelSpun = false;
            });
            middleRow.add(button);
        }
        letters.add(middleRow, BorderLayout.CENTER);

        JPanel bottomRow = new JPanel(new FlowLayout());
        for (char c : BOT_CHARS) {
            JButton button = new JButton();
            button.setText(String.valueOf(c));
            button.addActionListener(e -> {
                if (game.getConsonants().contains(c) && wheelSpun) {
                    int i = game.pickLetter(c);
                    if (i == 0) {
                        JOptionPane.showMessageDialog(null,
                                c + " was not in the Puzzle. Next Player's turn.",
                                "Incorrect", JOptionPane.WARNING_MESSAGE);
                    } else if (i == -1) {
                        JOptionPane.showMessageDialog(null,
                                c + " was already chosen! Next Player's turn.",
                                "Incorrect", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    if (game.getConsonants().contains(c) && !wheelSpun) {
                        JOptionPane.showMessageDialog(null,
                                "You must spin the wheel to pick a letter!",
                                "No Wheel Spun", JOptionPane.WARNING_MESSAGE);
                    } else {
                        if (vowelBought) {
                            int i = 0;
                            try {
                                i = game.buyAVowel(c);
                            } catch (IllegalArgumentException ex) {
                                JOptionPane.showMessageDialog(null,
                                        "You don't have enough money!",
                                        "No Money", JOptionPane.WARNING_MESSAGE);
                            }
                            if (i == 0) {
                                JOptionPane.showMessageDialog(null,
                                        c + " was not in the Puzzle. Next Player's turn.",
                                        "Incorrect", JOptionPane.WARNING_MESSAGE);
                            } else if (i == -1) {
                                JOptionPane.showMessageDialog(null,
                                        c + " was already chosen! Next Player's turn.",
                                        "Incorrect", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "That was not a valid input! Next Player's Turn",
                                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                }
                updateWindow();
                vowelBought = false;
                wheelSpun = false;
            });
            bottomRow.add(button);
        }
        letters.add(bottomRow, BorderLayout.SOUTH);

        buttonPanel.add(spinWheel);
        buttonPanel.add(buyAVowel);
        buttonPanel.add(solvePuzzle);
        playerPanel.add(buttonPanel, BorderLayout.NORTH);
        playerPanel.add(letters, BorderLayout.CENTER);
        mainWindow.revalidate();
    }

    public static void main() {

        List<Player> players = new ArrayList<>();
        for(int i = 1; i < 4; i++) {
            String pName = JOptionPane.showInputDialog(null,
                    "Input Player " + i + "'s Name: ","Player Name Input", JOptionPane.PLAIN_MESSAGE);
            players.add(new Player(pName));
        }
//        players.add(new Player("Joe"));
//        players.add(new Player("John"));
//        players.add(new Player("James"));
        new GUIClient(players);
    }
}
