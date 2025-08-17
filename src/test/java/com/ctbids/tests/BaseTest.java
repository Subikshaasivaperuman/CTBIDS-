package com.ctbids.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.time.Duration;

public class BaseTest {
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    @BeforeSuite
    public void setupSuite() {
        System.out.println("Setting up WebDriver for test suite...");
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        System.out.println("WebDriver setup complete for test suite.");
    }

    @AfterSuite
    public void tearDownSuite() {
        if (driver != null) {
            System.out.println("Closing WebDriver...");
            driver.quit();
        }
    }
}
