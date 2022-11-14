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
    }

    @Test
    public void testNewRound() {

        Player player1 = new Player("Joe");
        Game newGame = new Game(player1);
        newGame.startRound();
        System.out.println(newGame.getCurrentGameBoard());
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
        newGame2.startRound();
        GameBoard gb = new GameBoard("HELLO", "WORLD");
        newGame2.setCurrentGameBoard(gb);
        System.out.println(newGame2.getCurrentPlayersTurn().getName()); // first player to go
        newGame2.setValueOfWheelSpin(500);
        newGame2.pickLetter('L'); // Should add 1000 to earnings
        System.out.println(newGame2.getCurrentPlayersTurn().getCurrentRoundMoney());
        newGame2.setValueOfWheelSpin(750);
        newGame2.pickLetter('H'); // Should add 1000 to earnings
        assertEquals(1750, newGame2.getCurrentPlayersTurn().getCurrentRoundMoney());

    }
}

