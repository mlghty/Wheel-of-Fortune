package com.game.wheeloffortune;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player playerTest;
    private int actualMoney;
    private int expectedMoney;
    private String actualName;
    private String expectedName;

    @Before
    public void setUp() {
        playerTest = new Player("Joe");
        actualMoney = 0;
        expectedMoney = 0;
        actualName = "";
        expectedName = "";
    }

    @Test
    public void _test_spinWheel_not_null() {
        Integer playerSpinWheelValue = playerTest.spinWheel();
        assertNotNull(playerSpinWheelValue);
    }

    @Test
    public void _test_getName_return_valid() {
        expectedName = "Joe";
        actualName = playerTest.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void _test_setName_return_valid() {
        expectedName = "Cindy";
        playerTest.setName(expectedName);
        actualName = playerTest.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void _test_setTotalMoney_return_valid() {
        actualMoney = 1000;
        playerTest.setTotalMoney(actualMoney);
        expectedMoney = playerTest.getTotalMoney();

        assertEquals(expectedMoney, actualMoney);
    }

    @Test(expected = IllegalArgumentException.class)
    public void _test_setTotalMoney_return_invalid() {
        actualMoney = -1000;
        playerTest.setTotalMoney(actualMoney);
    }

    @Test
    public void _test_getTotalMoney_valid() {
        actualMoney = 1000;
        playerTest.setTotalMoney(actualMoney);
        expectedMoney = playerTest.getTotalMoney();

        assertEquals(expectedMoney, actualMoney);
    }

    @Test
    public void _test_getCurrentRoundMoney_valid() {
        actualMoney = 1000;
        playerTest.setCurrentRoundMoney(actualMoney);
        expectedMoney = playerTest.getCurrentRoundMoney();

        assertEquals(expectedMoney, actualMoney);
    }

    @Test(expected = IllegalArgumentException.class)
    public void _test_getCurrentRoundMoney_invalid() {
        actualMoney = -1000;
        playerTest.setCurrentRoundMoney(actualMoney);
    }

    @Test
    public void _test_setCurrentRoundMoney_valid() {
        actualMoney = 1000;
        playerTest.setCurrentRoundMoney(actualMoney);
        expectedMoney = playerTest.getCurrentRoundMoney();

        assertEquals(expectedMoney, actualMoney);
    }
}