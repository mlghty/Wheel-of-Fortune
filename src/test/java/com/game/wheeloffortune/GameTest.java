package com.game.wheeloffortune;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class GameTest {

    private Game newGame,newGame2;
    private Player player1 = new Player("Joe");
    private Player player2 = new Player("Cindy");
    @Before
    public void setUp() throws Exception {
        newGame = new Game(player1);
        newGame2 = new Game(player1, player2);
        newGame2.startRound();
        GameBoard gb = new GameBoard("HELLO", "WORLD");
        newGame2.setCurrentGameBoard(gb);
    }

    @Test
    public void testNewRound() {

        Player player1 = new Player("Joe");
        Game newGame = new Game(player1);
        newGame.startRound();
        System.out.println(newGame.getCurrentGameBoard());
        newGame.setWinningPlayer();
        System.out.println(newGame.getWinningPlayer());
    }

    @After
    public void tearDown() throws Exception {
    }

    // Test for the Try-Catch of illegal argument
    public static void main(String[] args) {
        Player player1 = new Player("Joe");
        Game newGame = new Game(player1);
        newGame.startRound();
        System.out.println(newGame.getCurrentGameBoard());

        boolean test = true;
        while (test) {
            try {
                Scanner scanner = new Scanner(System.in);
                String input;
                char letter;
                System.out.println(newGame.getCurrentGameBoard());
                System.out.println("Pick a letter");
                input = scanner.next();
                letter = input.charAt(0);
                newGame.pickLetter(letter);
                test = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }
        }
    }

    @Test
    public void testSpinWheel() {

        System.out.println(newGame2.getCurrentPlayersTurn().getName()); // first player to go
        newGame2.setValueOfWheelSpin(500);
        newGame2.pickLetter('L'); // Should add 1000 to earnings
        System.out.println(newGame2.getCurrentPlayersTurn().getCurrentRoundMoney());
        newGame2.setValueOfWheelSpin(750);
        newGame2.pickLetter('H'); // Should add 1000 to earnings
        assertEquals(1750, newGame2.getCurrentPlayersTurn().getCurrentRoundMoney());

    }

    @Test
    public void testIfBankruptPassesTheTurnAndSetsThePlayersEarningsToZero() {
        String player1 = newGame2.getCurrentPlayersTurn().getName();
        newGame2.getCurrentPlayersTurn().setCurrentRoundMoney(1000);
        int spinWheel = 1000;
        while(spinWheel > 0) {
            spinWheel = newGame2.spinWheel();
        }
        System.out.println(spinWheel);
        String player2 = newGame2.getCurrentPlayersTurn().getName();
        if(spinWheel == 0) {
            newGame2.getNextPlayer();
            assertEquals(player1, newGame2.getCurrentPlayersTurn().getName());
            assertEquals(1000,newGame2.getCurrentPlayersTurn().getCurrentRoundMoney());
        } else if(spinWheel == -1) {
            newGame2.getNextPlayer();
            assertEquals(player1, newGame2.getCurrentPlayersTurn().getName());
            assertEquals(0,newGame2.getCurrentPlayersTurn().getCurrentRoundMoney());
        }
    }
}

