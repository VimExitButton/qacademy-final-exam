package org.example.FinalExamUIAutomation;

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

public class Registration {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    void setupBrowser(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("https://app.qacademy.rs/signup");
        wait= new WebDriverWait(driver, Duration.ofMillis(15000));


    }
    @Test
    void RegistrationTest() throws InterruptedException{


        WebElement firstNameField = driver.findElement(By.id("firstName"));
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement confirmPasswordField = driver.findElement(By.id("confirmPassword"));

        firstNameField.sendKeys("av6969");
        lastNameField.sendKeys("av6969");
        usernameField.sendKeys("av6969");
        passwordField.sendKeys("av6969");
        confirmPasswordField.sendKeys("av6969");

        WebElement signUpButton = driver.findElement(By.xpath("//button[@type='submit']"));
        signUpButton.click();

        Thread.sleep(5000);


    }
    @AfterTest
    void tearDown(){ driver.quit(); }


}
