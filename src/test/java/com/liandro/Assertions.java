package com.liandro;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Assertions {

    @Test
    public void testWithAssert() {
        Assert.assertEquals(true, true, "Assertion verification");

        Assert.assertFalse(false, "Assertion verification");

        Assert.assertTrue(true, "Assertion verification");
    }

}
