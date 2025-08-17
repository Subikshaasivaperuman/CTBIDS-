package com.ctbids.tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class CTBidsLogin extends BaseTest {
    public static boolean isLoggedIn = false;

    @BeforeMethod
    public void setup() {
        // No need to create a new driver here as it's handled by BaseTest
    }

    @Test
    public void openCTBidsTest() {
        try {
            System.out.println("Starting test...");
            
            // Navigate to the website
            System.out.println("Navigating to https://buyeruat.ctbids.com...");
            driver.get("https://buyeruat.ctbids.com");
            
            // Create wait object
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            // Wait for page to load and try to find the sign in button
            System.out.println("Looking for Sign In button...");
            
            // Try multiple ways to find the Sign In button
            WebElement signInButton = null;
            
            // Try finding the Sign In button by class and text
            try {
                signInButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@type='button' and contains(@class, 'btn-grey') and contains(text(), 'Sign In')]")
                ));
                System.out.println("Found Sign In button by class and text");
            } catch (Exception e) {
                System.out.println("Couldn't find button by class and text");
            }
            
            // Try finding by button text
            if (signInButton == null) {
                try {
                    signInButton = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(), 'Sign In')]")
                    ));
                    System.out.println("Found Sign In button by text");
                } catch (Exception e) {
                    System.out.println("Couldn't find button by text");
                }
            }
            
            // Try finding by CSS selector
            if (signInButton == null) {
                try {
                    signInButton = wait.until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button.btn-grey")
                    ));
                    System.out.println("Found Sign In button by CSS selector");
                } catch (Exception e) {
                    System.out.println("Couldn't find button by CSS selector");
                    System.out.println("Error: " + e.getMessage());
                }
            }
            
            if (signInButton != null) {
                System.out.println("Sign In button found, clicking...");
                signInButton.click();
                
                // Wait for login form to appear
                Thread.sleep(2000);
                
                // Find and fill email field
                System.out.println("Looking for email field...");
                WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("input[type='email']")
                ));
                emailField.sendKeys("1915053@nec.edu.in");
                
                // Find and fill password field
                System.out.println("Looking for password field...");
                WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("input[type='password']")
                ));
                passwordField.sendKeys("Subi@123");
                
                // Find and click terms checkbox
                System.out.println("Looking for terms checkbox...");
                WebElement termsCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("input[type='checkbox']")
                ));
                if (!termsCheckbox.isSelected()) {
                    termsCheckbox.click();
                }
                
                // Find and click the login submit button
                System.out.println("Looking for login submit button...");
                WebElement loginSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@type='submit' and contains(text(), 'Sign In')]")
                ));
                loginSubmitButton.click();
                
                // Wait for login to complete
                Thread.sleep(3000);
                System.out.println("Login submitted successfully.");
                isLoggedIn = true;
            } else {
                throw new RuntimeException("Could not find Sign In button");
            }
            
            System.out.println("Test completed successfully.");
        } catch (Exception e) {
            System.out.println("Test failed with error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Test failed", e);
        }
    }

    @AfterMethod
    public void tearDown() {
        // No need to quit the driver here as it's handled by BaseTest
    }
}
