package com.game.wheeloffortune;

import com.game.wheeloffortune.utilities.Wheel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WheelTest {
    @Before
    public void setUp() throws Exception {

    }
@Test
public void setTestingWheelMethod() {
    Wheel testingWheelMethod = new Wheel();
    Assert.assertNotNull(testingWheelMethod.
            getRandomWedgeOfTheWheel());
}

    @After
    public void tearDown() throws Exception {
    }
}