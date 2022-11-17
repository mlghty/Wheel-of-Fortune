package com.game.wheeloffortune;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static com.game.wheeloffortune.WOFAsciiArt.setWindowSizesManually;
import static org.junit.Assert.*;

public class GameDialogueTest {

    private GameDialogue gameDialogueTest;

    @Before
    public void setUp() {
        setWindowSizesManually();
        gameDialogueTest = new GameDialogue();
    }

    @Test
    public void _start_Valid_Game_Test() throws InterruptedException {

        ByteArrayInputStream input = new ByteArrayInputStream("X".getBytes());
        Scanner sc = new Scanner(input);
        gameDialogueTest.setUserInputScanner(sc);
        gameDialogueTest.startGame();

        boolean startGame = gameDialogueTest.getStartGame();
        assertTrue(startGame);

    }

    @Test
    public void _test_numberOfPlayers_one_player_both_getter_setters() {


        Integer expectedNumOfPlayers = 1;

        ByteArrayInputStream input = new ByteArrayInputStream("1\nJoe".getBytes());
        Scanner sc = new Scanner(input);
        gameDialogueTest.setUserInputScanner(sc);
        gameDialogueTest.numberOfPlayers();


        Integer actualNumOfPlayers = gameDialogueTest.getNumOfPlayers();

        assertEquals(expectedNumOfPlayers, actualNumOfPlayers);

    }

    @Test
    public void _test_numberOfPlayers_two_players_both_getter_setters() {


        Integer expectedNumOfPlayers = 2;

        ByteArrayInputStream input = new ByteArrayInputStream("2\nJoe\nCindy".getBytes());
        Scanner sc = new Scanner(input);
        gameDialogueTest.setUserInputScanner(sc);
        gameDialogueTest.numberOfPlayers();


        Integer actualNumOfPlayers = gameDialogueTest.getNumOfPlayers();

        assertEquals(expectedNumOfPlayers, actualNumOfPlayers);

    }

    @Test
    public void _test_numberOfPlayers_three_players_both_getter_setters() {


        Integer expectedNumOfPlayers = 3;

        ByteArrayInputStream input = new ByteArrayInputStream("3\nJoeG\nCindy\nJoeR".getBytes());
        Scanner sc = new Scanner(input);
        gameDialogueTest.setUserInputScanner(sc);
        gameDialogueTest.numberOfPlayers();


        Integer actualNumOfPlayers = gameDialogueTest.getNumOfPlayers();

        assertEquals(expectedNumOfPlayers, actualNumOfPlayers);

    }

}