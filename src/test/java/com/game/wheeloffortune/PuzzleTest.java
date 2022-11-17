package com.game.wheeloffortune;

import com.game.wheeloffortune.utilities.Puzzles;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PuzzleTest {
    @Before
    public void setUp()throws Exception{

    }
    @Test
    public void testPuzzleNotSame(){
        Puzzles puzzlesTest = new Puzzles();
        Assert.assertNotSame(puzzlesTest.getRandomPuzzle(),
                puzzlesTest.getRandomPuzzle());
    }

    @Test
    public void setPuzzleHintNotNull(){
        Puzzles puzzlesNotNull = new Puzzles();
        Assert.assertNotNull(puzzlesNotNull.getRandomPuzzle());
    }


    @After
    public void tearDown() throws Exception{

    }
}
