package com.liandro;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAsserts {

    SoftAssert softAssert = new SoftAssert();

    @Test
    public void testWithAssert() {
        softAssert.assertEquals(true, true, "Assertion verification");

        softAssert.assertFalse(false, "Assertion verification");

        softAssert.assertTrue(true, "Assertion verification");

        softAssert.assertAll();
    }


}
