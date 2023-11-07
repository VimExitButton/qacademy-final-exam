package org.example.FinalExamUIAutomation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class Notifications {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://app.qacademy.rs/signin");
        wait = new WebDriverWait(driver, Duration.ofMillis(15000));
    }

    @Test
    void NotiicationTest() throws InterruptedException {
        WebElement usernameField = driver.findElement(By.cssSelector("input[id='username']"));
        WebElement passwordField = driver.findElement(By.id("password"));

        usernameField.sendKeys("av69");
        passwordField.sendKeys("av69");

        WebElement rememberMe = driver.findElement(By.xpath("//input[@type='checkbox']"));
        rememberMe.click();

        WebElement signInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        signInButton.click();

        Thread.sleep(3000);

        WebElement notificationsButton = driver.findElement(By.cssSelector("[data-test='nav-top-notifications-link']"));
        notificationsButton.click();

        WebElement noNotiications = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='empty-list-header']")));

        Thread.sleep(6000);
    }

    @AfterTest
    void tearDown(){
        driver.quit();}

}
