package org.example.FinalExam;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class BankAccounts {
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
    void BankAccountTest() throws InterruptedException{
        WebElement usernameField = driver.findElement(By.cssSelector("input[id='username']"));
        WebElement passwordField = driver.findElement(By.id("password"));

        usernameField.sendKeys("av69");
        passwordField.sendKeys("av69");

        WebElement rememberMe = driver.findElement(By.xpath("//input[@type='checkbox']"));
        rememberMe.click();

        WebElement signInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        signInButton.click();

        Thread.sleep(3000);

        WebElement bankAccButton = driver.findElement(By.cssSelector("[data-test='sidenav-bankaccounts']"));
         bankAccButton.click();

        Thread.sleep(2000);

        WebElement createBankAcc = driver.findElement(By.cssSelector("[data-test='bankaccount-new']"));
        createBankAcc.click();

        WebElement bankNameField = driver.findElement(By.cssSelector("input[id='bankaccount-bankName-input']"));
        WebElement routingNumberField = driver.findElement(By.id("bankaccount-routingNumber-input"));
        WebElement accountNumberField = driver.findElement(By.id("bankaccount-accountNumber-input"));

        bankNameField.sendKeys("Pineapple");
        routingNumberField.sendKeys("123456789");
        accountNumberField.sendKeys("1234asdfg");

        WebElement saveButton = driver.findElement(By.xpath("//button[@type='submit']"));
        saveButton.click();

        Thread.sleep(2000);


    }
    @Test
    void deleteAccount () throws InterruptedException {
        WebElement deleteButton = driver.findElement(By.cssSelector("[data-test='bankaccount-delete']"));
        deleteButton.click();

        Thread.sleep(6000);

    }

    @AfterTest
    void tearDown(){
        driver.quit();}


}
