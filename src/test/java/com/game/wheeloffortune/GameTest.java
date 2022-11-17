package com.game.wheeloffortune;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    private Game newGame, newGame2;
    private Player player1;
    private Player player2;

    @Before
    public void setUp() {
        player1 = new Player("Joe");
        player2 = new Player("Cindy");
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
    public void tearDown() {
        player1 = null;
        player2 = null;
        newGame = null;
        newGame2 = null;
    }

    @Test
    public void testIfTheMathIsCorrectWhenBuyingConsonants() {

        System.out.println(newGame2.getCurrentPlayersTurn().getName()); // first player to go
        newGame2.setValueOfWheelSpin(500);
        newGame2.pickLetter('L'); // Should add 1000 to earnings
        System.out.println(newGame2.getCurrentPlayersTurn().getCurrentRoundMoney());
        newGame2.setValueOfWheelSpin(750);
        newGame2.pickLetter('H'); // Should add 1000 to earnings
        assertEquals(1750, newGame2.getCurrentPlayersTurn().getCurrentRoundMoney());

    }

    @Test
    public void testGameToStringMethodPrintsCorrectly() {
        String expectedValue = "Player name: Joe\nTotal money: 0\nRound money: 0\n\n" +
                "Player name: Cindy\nTotal money: 0\nRound money: 0\n\n";
        assertEquals(expectedValue, newGame2.toString());
    }

    @Test
    public void testIfBankruptPassesTheTurnAndSetsThePlayersEarningsToZero() {
        String player1 = newGame2.getCurrentPlayersTurn().getName();
        newGame2.getCurrentPlayersTurn().setCurrentRoundMoney(1000);
        int spinWheel = 1000;
        while (spinWheel > 0) {
            spinWheel = newGame2.spinWheel();
        }
        System.out.println(spinWheel);
        if (spinWheel == 0) {
            newGame2.getNextPlayer();
            assertEquals(player1, newGame2.getCurrentPlayersTurn().getName());
            assertEquals(1000, newGame2.getCurrentPlayersTurn().getCurrentRoundMoney());
        } else if (spinWheel == -1) {
            newGame2.getNextPlayer();
            assertEquals(player1, newGame2.getCurrentPlayersTurn().getName());
            assertEquals(0, newGame2.getCurrentPlayersTurn().getCurrentRoundMoney());
        }
    }

    @Test
    public void testIfWinningPlayerMethodSetsCorrectlyAndDisplaysTheCorrectWinner() {
        newGame.getPlayers().get(0).setTotalMoney(10000);
        newGame.setWinningPlayer();
        String expectedValue = "Player name: Joe\nTotal money: 10000\nRound money: 0\n";
        assertEquals(expectedValue, newGame.getWinningPlayer());
    }

    @Test
    public void _testToEnsurePickConsonantThrowsAnIllegalArgumentExceptionAndThrowsTheRightMessage() {
        try {
            newGame.startRound();
            newGame.pickLetter('1');
        }
        catch (IllegalArgumentException e) {
            assertEquals("You must pick a valid consonant", e.getMessage());
        }
    }

    @Test
    public void _testToEnsureBuyAVowelThrowsAnIllegalArgumentExceptionAndThrowsTheRightMessage() {
        try {
            newGame.startRound();
            newGame.getCurrentPlayersTurn().setCurrentRoundMoney(1000);
            newGame.buyAVowel('1');
        }
        catch (IllegalArgumentException e) {
            assertEquals("You must pick a valid vowel", e.getMessage());
        }
    }

    @Test
    public void testThatThePlayersRoundMoneyIsResetWhenAPlayerCorrectlySolvesThePuzzleAndThatPlayersTotalMoneyIsIncreasedToTheCorrectAmount() {
        String solve = "HELLO";
        newGame2.setCurrentPlayersTurn(newGame2.getPlayers().get(0));
        newGame2.getCurrentPlayersTurn().setCurrentRoundMoney(1000);
        newGame2.getCurrentPlayersTurn().setTotalMoney(1000);
        assertEquals(1,newGame2.solvePuzzle(solve));
        assertEquals(2000, newGame2.getPlayers().get(0).getTotalMoney());
        assertEquals(0, newGame2.getPlayers().get(0).getCurrentRoundMoney());
    }

    @Test
    public void testThatTheCurrentPlayerLosesTheirTurnIfTheySolvePuzzleIncorrectlyButRetainsTheirRoundEarnings() {
        String solve = "GOODBYE";
        newGame2.setCurrentPlayersTurn(newGame2.getPlayers().get(0));
        newGame2.getCurrentPlayersTurn().setCurrentRoundMoney(1000);
        assertEquals(0,newGame2.solvePuzzle(solve));
        assertEquals(1000, newGame2.getPlayers().get(0).getCurrentRoundMoney());
    }

    @Test
    public void _testGetWinningPlayerReturnsTheStringThereIsNoWinnerForANullWinningPlayerField() {
        String actualValue = newGame2.getWinningPlayer();
        String expectedValue = "There is no winner!";
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void _testSetWinningPlayerMethodReplacesWinningPlayerFieldWithAMessageInsteadOfTempPlayerIfNoPlayerHasWonYet() {
        newGame2.setWinningPlayer();
        String expectedPlayer = "There is no winner!";
        String actualPlayer = newGame2.getWinningPlayer();
        System.out.println(actualPlayer);
        assertEquals(expectedPlayer, actualPlayer);
    }

    @Test
    public void _TestGetWinningPlayerObjectMethodThrowsExceptionAndHasCorrectMessageIfNoActualWinnerHasBeenEstablished() {
        String message = "There is now winner!";
        try {
            newGame2.getWinningPlayerObject();
            Assert.fail();
        } catch (NullPointerException e) {
            assertEquals(message,e.getMessage());
        }
    }

    @Test
    public void testThatTheSetUpPuzzleMethodProperlyEnsuresThereAreNoRepeatPuzzlesByIteratingThroughTheSetupPuzzleMethodMultipleTimesAndEnsuringThereAreNoRepeats() {
        for(int i = 0; i < 9; i++) {
            newGame2.startRound();
            newGame2.setCurrentRoundNumber(0);
        }
        assertEquals(10,newGame2.getUsedPuzzles().size());
    }
}
