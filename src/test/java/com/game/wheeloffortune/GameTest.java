package com.game.wheeloffortune;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    private Game newGame, newGame2;
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
        assertTrue(expectedValue.equals(newGame2.toString()));
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
        String player2 = newGame2.getCurrentPlayersTurn().getName();
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
        assertTrue(expectedValue.equals(newGame.getWinningPlayer()));
    }

    @Test
    public void _testToEnsurePickConsonantThrowsAnIllegalArgumentExceptionAndThrowsTheRightMessage() {
        try {
            newGame.startRound();
            newGame.pickLetter('1');
        }
        catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("You must pick a valid consonant"));
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
            assertTrue(e.getMessage().equals("You must pick a valid vowel"));
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
    public void testGetWinningPlayerReturnsTheStringThereIsNoWinner() {
        
    }
}
