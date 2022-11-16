package com.game.wheeloffortune;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class GUIClient extends JFrame {
    JFrame mainWindow;
    Game game;
    private JPanel playerPanel;

    GUIClient() {
        game = new Game(new Player("Joe"), new Player("Joseph"),
                new Player("Cindy"));
        game.startRound();
        mainWindow = new JFrame();
        setMainWindow();
        setPlayerWindows();
        displayPuzzle();
        buttons();
    }

    private void setMainWindow() {
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setSize(1200, 720);
        mainWindow.setTitle("Wheel of Fortune: Java Edition");
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
    }

    private void displayPuzzle() {

        JPanel puzzlePanel = new JPanel();
        puzzlePanel.setLayout(new BorderLayout());
        String puzzle = game.getCurrentGameBoard().getGamePuzzle();
        String hint = game.getCurrentGameBoard().getGameHint();

        JLabel hintLabel = new JLabel();
        hintLabel.setText("Puzzle Category: " + hint);
        hintLabel.setPreferredSize(new Dimension((25 * (hint.length() + 17)),40));
        hintLabel.setHorizontalAlignment(JLabel.CENTER);
        hintLabel.setFont(new Font("Courier", Font.BOLD, 24));
        puzzlePanel.add(hintLabel, BorderLayout.NORTH);

        JPanel currentPuzzle = new JPanel(new FlowLayout());
        int characterLengthOfPuzzle = game.getCurrentGameBoard().getGamePuzzle().length();
        for(int i = 0; i < characterLengthOfPuzzle; i++) {
            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(25,40));
            if(puzzle.charAt(i) != '\u25A0') {
                label.setText(String.valueOf(puzzle.charAt(i)));
            }
            if(puzzle.charAt(i) == ' ') {

            } else {
                label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            }
            label.setFont(new Font("Courier", Font.BOLD, 24));
            currentPuzzle.add(label);
        }
        puzzlePanel.add(currentPuzzle, BorderLayout.CENTER);
        mainWindow.add(puzzlePanel,BorderLayout.NORTH);
        mainWindow.revalidate();
    }

    private void setPlayerWindows() {
        playerPanel = new JPanel(new BorderLayout());

        JPanel player1  = new JPanel(new BorderLayout());
        player1.setBackground(Color.BLUE);
        player1.setPreferredSize(new Dimension(200, 120));

        JLabel p1Name = new JLabel();
        p1Name.setText(game.getPlayers().get(0).getName().toUpperCase());
        p1Name.setFont(new Font("Courier", Font.BOLD, 24));
        p1Name.setIconTextGap(-15);
        p1Name.setForeground(Color.WHITE);
        p1Name.setHorizontalAlignment(JLabel.CENTER);

        JLabel p1RoundMoney = new JLabel();
        p1RoundMoney.setText("<html>Round Money: <br>   $ " + String.valueOf(game.getPlayers().get(0).getCurrentRoundMoney()) + "</html>");
        p1RoundMoney.setFont(new Font("Courier", Font.BOLD, 16));
        p1RoundMoney.setIconTextGap(-15);
        p1RoundMoney.setForeground(Color.WHITE);
        p1RoundMoney.setBackground(Color.BLACK);
        p1RoundMoney.setHorizontalAlignment(JLabel.CENTER);

        JLabel p1TotalMoney = new JLabel();
        p1TotalMoney.setText("<html>Total Money: <br>   $ " + String.valueOf(game.getPlayers().get(0).getTotalMoney()) + "</html>");
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
        p2Name.setForeground(Color.WHITE);
        p2Name.setHorizontalAlignment(JLabel.CENTER);
        player2.add(p2Name, BorderLayout.NORTH);

        JPanel player3 = new JPanel(new BorderLayout());
        JLabel p3Name = new JLabel();
        p3Name.setText(game.getPlayers().get(2).getName().toUpperCase());
        p3Name.setFont(new Font("Courier", Font.BOLD, 24));
        p3Name.setIconTextGap(-15);
        p3Name.setForeground(Color.WHITE);
        p3Name.setHorizontalAlignment(JLabel.CENTER);
        player3.add(p3Name, BorderLayout.NORTH);

        player2.setBackground(Color.green);
        player3.setBackground(Color.red);


        player2.setPreferredSize(new Dimension(200, 120));
        player3.setPreferredSize(new Dimension(200, 120));
        JPanel players = new JPanel(new FlowLayout());
        players.add(player1);
        players.add(player2);
        players.add(player3);
        playerPanel.add(players,BorderLayout.SOUTH);
        mainWindow.add(playerPanel, BorderLayout.SOUTH);
        mainWindow.revalidate();
    }

    private void buttons() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton pickLetter = new JButton();
        pickLetter.setText("Spin Wheel");
        JButton buyAVowel = new JButton();
        buyAVowel.setText("Buy a Vowel");
        JButton solvePuzzle = new JButton();
        solvePuzzle.setText("Solve Puzzle");

        JPanel letters = new JPanel(new BorderLayout());

        JPanel topRow = new JPanel(new BorderLayout());

        JPanel bottomRow = new JPanel(new BorderLayout());


        buttonPanel.add(pickLetter);
        buttonPanel.add(buyAVowel);
        buttonPanel.add(solvePuzzle);
        playerPanel.add(buttonPanel, BorderLayout.NORTH);
        mainWindow.revalidate();
    }

    public static void main(String[] args) {
        GUIClient guiClient = new GUIClient();
    }
}
