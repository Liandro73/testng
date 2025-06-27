package com.liandro;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class DataDrivenTesting {

    WebDriver driver;
    WebDriverWait wait;

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public void setUpChrome() {
        ChromeOptions chromeOptions = new ChromeOptions()
                .addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.navigate().to("https://www.saucedemo.com/v1/");
    }

    @Test(dataProvider = "loginData")
    public void test1_SignIn(String username, String password) {
        setUpChrome();

        WebElement inputUsername = driver.findElement(By.id("user-name"));
        WebElement inputPassword = driver.findElement(By.id("password"));
        WebElement btnLogin = driver.findElement(By.id("login-button"));


        inputUsername.click();
        inputUsername.clear();
        inputUsername.sendKeys(username);

        inputPassword.click();
        inputPassword.clear();
        inputPassword.sendKeys(password);

        btnLogin.click();

        verifyScenario(username);
    }

    public void verifyScenario(String username) {
        switch(username) {
            case "standard_user":
            case "problem_user":
            case "performance_glitch_user":
                WebElement appLogo = driver.findElement(By.className("app_logo"));
                wait.until(d -> appLogo.isDisplayed());
                break;
            case "locked_out_user":
                WebElement errorMessage = driver.findElement(By.xpath("//*[text()='Sorry, this user has been locked out.']"));
                wait.until(d -> errorMessage.isDisplayed());
                break;
            default:
                System.out.println("Invalid user, please verify your data");
        }
        System.out.println(username);
    }

    @DataProvider
    public Object[][] loginData() {
        Object[][] data = new Object[4][2];

        Object[] validUser = {"standard_user", "secret_sauce"};
        Object[] lockedOutUser = {"locked_out_user", "secret_sauce"};
        Object[] userWithProblem = {"problem_user", "secret_sauce"};
        Object[] performanceGlitchUser = {"performance_glitch_user", "secret_sauce"};

        data[0] = validUser;
        data[1] = lockedOutUser;
        data[2] = userWithProblem;
        data[3] = performanceGlitchUser;

        return data;
    }

}