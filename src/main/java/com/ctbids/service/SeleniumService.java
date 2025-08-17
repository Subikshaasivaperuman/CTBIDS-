package com.ctbids.service;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.time.Duration;

@Service
public class SeleniumService {

    private WebDriver driver;
    private WebDriverWait wait;

    @PostConstruct
    public void init() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Run in headless mode by default
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @PreDestroy
    public void cleanup() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String navigateToUrl(String url) {
        driver.get(url);
        return "Successfully navigated to " + url;
    }

    public String clickElement(String cssSelector) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
        element.click();
        return "Successfully clicked element with selector: " + cssSelector;
    }

    public String enterText(String cssSelector, String text) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
        element.clear();
        element.sendKeys(text);
        return "Successfully entered text into element with selector: " + cssSelector;
    }

    public String getText(String cssSelector) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
        return element.getText();
    }

    public boolean elementExists(String cssSelector) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
