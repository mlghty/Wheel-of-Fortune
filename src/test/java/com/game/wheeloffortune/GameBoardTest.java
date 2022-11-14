package com.game.wheeloffortune;

import org.junit.Test;

import java.util.Scanner;

public class GameBoardTest {
    public static void main(String[] args) {
        String gamePuzzle = "Sang is the best Java Instructor";
        String gameHint = "General Java Knowledge";
        boolean puzzleSolved = false;
        GameBoard gameBoard = new GameBoard(gamePuzzle, gameHint);
        System.out.println(gameBoard);
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
}