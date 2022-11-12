package com.game.wheeloffortune;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class GameTest {

    private Game newGame;
    private Player player1 = new Player("Joe");
    private Player player2 = new Player("Cindy");
    @Before
    public void setUp() throws Exception {
        newGame = new Game(player1);
    }

    @Test
    public void testNewRound() {

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
                System.out.println("Pick a letter");
                input = scanner.next();
                letter = input.charAt(0);
                newGame.pickLetter(letter);
            } catch (IllegalArgumentException e) {
                System.out.println("Try again!");
            }
        }
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
                System.out.println("Pick a letter");
                input = scanner.next();
                letter = input.charAt(0);
                newGame.pickLetter(letter);
            } catch (IllegalArgumentException e) {
                System.out.println("Try again!");
            }
        }
    }
}

