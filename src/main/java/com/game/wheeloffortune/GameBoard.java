package com.game.wheeloffortune;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private String gamePuzzle;
    private String gameHint;
    private boolean[] isLetterRevealed;

    List<Character> lettersAlreadyGuessed = new ArrayList<>();


    public GameBoard(String gamePuzzle, String gameHint) {
        setGamePuzzle(gamePuzzle.toUpperCase());
        setIsLetterRevealed(new boolean[gamePuzzle.length()]);
        setGameHint(gameHint.toUpperCase());
    }


    public int guessLetter(char letterGuessed) {
        int numberOfTimesLetterAppears = 0;
        letterGuessed = Character.toUpperCase(letterGuessed);
        if(lettersAlreadyGuessed.contains(letterGuessed)) {
            return -1;
        } else {
            lettersAlreadyGuessed.add(letterGuessed);
        }
        for(int i = 0; i < gamePuzzle.length(); i++) {
            if(gamePuzzle.charAt(i) == letterGuessed) {
                isLetterRevealed[i] = true;
                numberOfTimesLetterAppears++;
            }
        }
        return numberOfTimesLetterAppears;
    }

    public boolean solvePuzzle(String puzzleGuess) {
        return puzzleGuess.toUpperCase().equals(this.gamePuzzle);
    }


    public String getGamePuzzle() {
        String returnValue = "";
        for (int i = 0; i < this.gamePuzzle.length(); i++) {
            if (gamePuzzle.charAt(i) == ' ') {
                returnValue = returnValue.concat(" ");
            } else if (isLetterRevealed[i]) {
                returnValue = returnValue.concat(String.valueOf(gamePuzzle.charAt(i)));
            } else {
                returnValue = returnValue.concat("\u25A0");
            }
        }
        return returnValue;
    }

    public void setGamePuzzle(String gamePuzzle) {
        this.gamePuzzle = gamePuzzle;
    }

    public boolean[] getIsLetterRevealed() {
        return isLetterRevealed;
    }

    public void setIsLetterRevealed(boolean[] isLetterRevealed) {
        this.isLetterRevealed = isLetterRevealed;
    }

    public String getGameHint() {
        return gameHint;
    }

    public void setGameHint(String gameHint) {
        this.gameHint = gameHint;
    }

    public String toString() {
        return String.format("Category: %s\nPuzzle: %s\n", getGameHint(),getGamePuzzle());
    }
}
