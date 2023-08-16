package com.liandro;

import org.testng.annotations.*;

public class ExecutionOrder {


    @BeforeClass
    public void setUp() {
        System.out.println("Set Up");
    }

    @Test(priority = 1)
    public void signIn() {
        System.out.println("Login");
    }

    @Test(priority = 2)
    public void searchTShirt() {
        System.out.println("Search For Product");
    }

    @Test(priority = 3)
    public void signOut() {
        System.out.println("Logout");
    }

    @AfterClass
    public void tearDown() {
        System.out.println("Tear Down");
    }

}
