package com.liandro;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

public class DependsOnMethods {

    WebDriver driver;
    WebDriverWait wait;

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test1_SetUpChrome() {
        ChromeOptions chromeOptions = new ChromeOptions()
                .addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test(dependsOnMethods = "test1_SetUpChrome")
    public void test2_GoToUrl() {
        driver.navigate().to("https://www.saucedemo.com/v1/");

        takeScreenShot("Browsing to specific URL");
    }

    @Test(dependsOnMethods = "test2_GoToUrl")
    public void test3_SignIn() {
        WebElement inputUsername = driver.findElement(By.id("user-name"));
        WebElement inputPassword = driver.findElement(By.id("password"));
        WebElement btnLogin = driver.findElement(By.id("login-button"));


        inputUsername.click();
        inputUsername.clear();
        inputUsername.sendKeys("standard_user");

        inputPassword.click();
        inputPassword.clear();
        inputPassword.sendKeys("secret_sauce");

        btnLogin.click();

        WebElement appLogo = driver.findElement(By.className("app_logo"));
    }

    @Test(dependsOnMethods = "test3_SignIn")
    public void test4_VerifyLoggedPage() {
        WebElement appLogo = driver.findElement(By.className("app_logo"));

        wait.until(d -> appLogo.isDisplayed());
    }

    public void takeScreenShot(String step){
        LocalDateTime dateTime = LocalDateTime.now();

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(screenshot, new File(
                    "/Users/dernivalliandro/Documents/Workspace/testng/" + step + "__" + dateTime + ".jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}