package com.game.wheeloffortune;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

public class GameBoardTest {

    private GameBoard gameBoard, gameBoard2;
    boolean puzzleSolved = false;


    @Before
    public void setUp() throws Exception {
        gameBoard = new GameBoard(
                "Sang is the best Java Instructor",
                "General Java Knowledge");

        gameBoard2 = new GameBoard(
                "Beat the Clock",
                "Classic TV");

    }

    @Test
    public void guessLetterTestTheNumberOfTimesTheLetterAppearsS() {
        int expectedValue = 4;
        Assert.assertEquals(expectedValue,
                gameBoard.guessLetter('S'));
    }

    @Test
    public void guessLetterTestTheNumberOfTimesTheLetterAppearsB() {
        int expectedValue = 1;
        Assert.assertEquals(expectedValue,
                gameBoard2.guessLetter('B'));
    }

    @Test
    public void guessLetterNotInPuzzleShouldReturnZero() {
        int expectedValue = 0;
        Assert.assertEquals(expectedValue,
                gameBoard2.guessLetter('Z'));
    }

    @Test
    public void solvePuzzleTestShouldReturnTrue() {
        Assert.assertTrue(gameBoard2.solvePuzzle("Beat the Clock"));
    }

    @Test
    public void solvePuzzleTestShouldReturnFalse() {
        Assert.assertFalse(gameBoard2.solvePuzzle("Classic TV"));
    }


//        System.out.println(gameBoard);
        /*while(!puzzleSolved) {
            int choice;
            Scanner option = new Scanner(System.in);
            System.out.println(gameBoard.getGamePuzzle());
            System.out.println("1. Guess Letter 2. Solve Puzzle");
            choice = option.nextInt();
            option.nextLine();
            switch(choice) {
                case 1:
                    System.out.println("Choose a letter");
                    String letter = option.next();
                    option.nextLine();
                    gameBoard.guessLetter(letter.charAt(0));
                    break;
                case 2:
                    System.out.println("Attempt to solve the puzzle: ");
                    String solution = option.nextLine().strip();
                    puzzleSolved = gameBoard.solvePuzzle(solution);
                    break;
            }
        }*/

}
