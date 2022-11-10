package com.game.wheeloffortune;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GameTest {

    private Game newGame;
    private Player player1 = new Player();
    private Player player2 = new Player();
    @Before
    public void setUp() throws Exception {
        newGame = new Game(player1);
    }

    @Test
    public void testNewRound() {
        newGame.startRound();
        System.out.println(newGame.getCurrentGameBoard());
    }

    @After
    public void tearDown() throws Exception {
    }
}