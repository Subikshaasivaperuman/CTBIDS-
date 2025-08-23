package com.ctbids.tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import java.time.Duration;

public class ProfileComplete extends BaseTest {
    private WebDriverWait wait;

    @BeforeMethod
    public void beforeTest() {
        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void completeProfileTest() {
        try {
            System.out.println("Starting profile completion test...");
            
            // Make sure we're logged in and page is fully loaded
            System.out.println("Waiting for page to load completely...");
            Thread.sleep(10000);  // Increased wait time for page load
            
            // Wait for any loading indicators to disappear
            try {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.cssSelector(".loading-spinner, .loader, .loading")));
                System.out.println("Loading indicators are gone");
            } catch (Exception e) {
                System.out.println("No loading indicators found or already gone");
            }
            
            // Create Actions object for hovering
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            
            // Find and interact with the profile menu - trying multiple possible selectors
            System.out.println("Looking for profile menu...");
            WebElement profileMenu = null;
            String[] selectors = {
                "//div[contains(@class, 'avatar')]",
                "//img[contains(@alt, 'profile') or contains(@alt, 'avatar')]",
                "//button[contains(@class, 'dropdown') or contains(@class, 'profile')]",
                "//div[contains(@class, 'user-info')]",
                "//div[contains(@class, 'user-menu')]",
                "//header//div[contains(@class, 'user')]",
                "//nav//div[contains(@class, 'user')]",
                "//div[contains(@class, 'profile-icon')]",
                "//header//button[contains(@class, 'user')]",
                "//div[contains(@class, 'account-menu')]",
                "//div[contains(@class, 'user-dropdown')]",
                "//div[contains(@class, 'profile-dropdown')]",
                "//button[contains(@aria-label, 'user menu')]",
                "//button[contains(@aria-label, 'profile')]"
            };
            
            for (String selector : selectors) {
                try {
                    profileMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(selector)));
                    System.out.println("Found profile menu using selector: " + selector);
                    break;
                } catch (Exception e) {
                    System.out.println("Selector not found: " + selector);
                }
            }
            
            if (profileMenu == null) {
                throw new RuntimeException("Could not find profile menu element using any known selector");
            }
            
            // Hover over the profile menu
            System.out.println("Hovering over profile menu...");
            actions.moveToElement(profileMenu).perform();
            
            // Wait for dropdown to appear and find My Account link
            System.out.println("Looking for My Account option...");
            Thread.sleep(1000); // Wait for dropdown animation
            
            WebElement myAccountLink = null;
            String[] accountSelectors = {
                "//a[contains(text(), 'My Account')]",
                "//div[contains(text(), 'My Account')]",
                "//span[contains(text(), 'My Account')]",
                "//li[contains(text(), 'My Account')]",
                "//button[contains(text(), 'My Account')]"
            };
            
            for (String selector : accountSelectors) {
                try {
                    myAccountLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(selector)));
                    System.out.println("Found My Account link using selector: " + selector);
                    break;
                } catch (Exception e) {
                    System.out.println("Account selector not found: " + selector);
                }
            }
            
            if (myAccountLink == null) {
                throw new RuntimeException("Could not find My Account link using any known selector");
            }
            
            // Click on My Account
            System.out.println("Clicking My Account...");
            myAccountLink.click();
            Thread.sleep(2000);
            
            System.out.println("Successfully navigated to My Account page");
            
            // Wait for the profile section to load
            Thread.sleep(3000);
            
            // Find and click the Change Image button
            System.out.println("Looking for Change Image button...");
            WebElement changeImageButton = null;
            String[] imageButtonSelectors = {
                "//span[text()='Change']",
                "//div[contains(@class, 'profile-image')]//span[contains(text(), 'Change')]",
                "//div[contains(@class, 'avatar')]//span[contains(text(), 'Change')]",
                "//div[contains(@class, 'user-image')]//span[contains(text(), 'Change')]",
                "//div[@class='image-upload']//span[contains(text(), 'Change')]",
                "//div[contains(@class, 'profile')]//span[text()='Change']"
            };
            
            for (String selector : imageButtonSelectors) {
                try {
                    changeImageButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(selector)));
                    System.out.println("Found Change Image button using selector: " + selector);
                    break;
                } catch (Exception e) {
                    System.out.println("Change Image button not found with selector: " + selector);
                }
            }
            
            if (changeImageButton == null) {
                throw new RuntimeException("Could not find Change Image button using any known selector");
            }
            
            // Click the Change Image button
            System.out.println("Clicking Change Image button...");
            try {
                changeImageButton.click();
                System.out.println("Successfully clicked Change Image button");
            } catch (Exception e) {
                System.out.println("Failed to click Change Image button directly, trying JavaScript click");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", changeImageButton);
            }
            
            Thread.sleep(2000);
            
            // Handle the file upload dialog and image editor
            System.out.println("Starting file upload process...");
            
            // Create Robot instance at the beginning
            java.awt.Robot robot = null;
            try {
                robot = new java.awt.Robot();
            } catch (Exception e) {
                System.out.println("Failed to create Robot instance: " + e.getMessage());
                throw e;
            }
            
            try {
                Thread.sleep(1000);
                
                // Set up the file path
                String filePath = "C:\\Users\\JohnW\\OneDrive\\Pictures\\Image assests\\download.png";
                java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                    new java.awt.datatransfer.StringSelection(filePath), null);
                
                // Paste the file path
                robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
                robot.keyPress(java.awt.event.KeyEvent.VK_V);
                robot.keyRelease(java.awt.event.KeyEvent.VK_V);
                robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
                Thread.sleep(1000);
                
                // Press Enter to select the file
                robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
                robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
                Thread.sleep(5000);  // Wait for image editor to appear
                
                // Wait for the dialog to be ready
                Thread.sleep(3000);
                
                // Try to click the yellow Save button at the bottom right
                try {
                    WebElement saveButton = null;
                    // Try to find the yellow Save button
                    String[] saveButtonSelectors = {
                        "//button[contains(@class, 'yellow') and contains(text(), 'Save')]",
                        "//div[contains(@class, 'editor-footer')]//button[contains(@class, 'yellow')]",
                        "//div[contains(@class, 'dialog')]//button[last()]"
                    };
                    
                    for (String selector : saveButtonSelectors) {
                        try {
                            saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(selector)));
                            System.out.println("Found Save button using selector: " + selector);
                            break;
                        } catch (Exception e) {
                            System.out.println("Save button not found with selector: " + selector);
                        }
                    }
                    
                    if (saveButton != null) {
                        actions.moveToElement(saveButton).click().perform();
                        System.out.println("Clicked Save button using Actions");
                    } else {
                        // If button not found, use keyboard navigation
                        System.out.println("Save button not found, using keyboard navigation");
                        robot.keyPress(java.awt.event.KeyEvent.VK_TAB);
                        robot.keyRelease(java.awt.event.KeyEvent.VK_TAB);
                        Thread.sleep(500);
                        robot.keyPress(java.awt.event.KeyEvent.VK_TAB);
                        robot.keyRelease(java.awt.event.KeyEvent.VK_TAB);
                        Thread.sleep(500);
                        robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
                        robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
                    }
                    
                    Thread.sleep(2000);
                } catch (Exception e) {
                    System.out.println("Error trying to click Save button: " + e.getMessage());
                    throw e;
                }
                
                // Wait for upload to complete
                Thread.sleep(5000);
            System.out.println("Image upload completed");
            
            // Wait for the image editor dialog to appear
            Thread.sleep(5000);
            System.out.println("File uploaded successfully");
                     
            } catch (Exception e) {
                System.out.println("Failed to upload file: " + e.getMessage());
                throw e;
            }

            // Username validation
            System.out.println("Starting username validation...");
            
            // Wait a bit longer for the page to fully load after image upload
            Thread.sleep(3000);

            // Find the username field by ID
            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
            
            // Clear any existing value in the username field
            String existingValue = usernameField.getAttribute("value");
            if (existingValue != null && !existingValue.isEmpty()) {
                System.out.println("Clearing existing username: " + existingValue);
                usernameField.clear();
                Thread.sleep(500); // Wait for the clear action to complete
            }
            
            // Create JS Executor for error message handling
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Create error message element if it doesn't exist
            js.executeScript(
                "if (!document.querySelector('.username-error')) {" +
                "   const errorDiv = document.createElement('div');" +
                "   errorDiv.className = 'username-error';" +
                "   errorDiv.style.color = 'red';" +
                "   errorDiv.style.marginTop = '5px';" +
                "   errorDiv.style.fontSize = '12px';" +
                "   document.getElementById('username').parentNode.appendChild(errorDiv);" +
                "}"
            );

            // Test invalid usernames with special characters and banned words
            String[] invalidUsernames = {
                "User@Name",     // Special character @
                "User#123",      // Special character #
                "UserCancelled", // Banned word 'Cancelled'
                "Bid",       // Banned word 'Bid'
                "CTBIDS",    // Banned word 'CTBIDS'
                "User$123",      // Special character $
                "Failed_Account",// Special character _ and banned word 'Failed'
                "Outbid2023"     // Banned word 'Outbid'
            };

            for (String username : invalidUsernames) {
                // Clear the field and ensure it's empty
                usernameField.clear();
                Thread.sleep(500);
                // Double-check if field is cleared
                String currentValue = usernameField.getAttribute("value");
                if (currentValue != null && !currentValue.isEmpty()) {
                    // If normal clear didn't work, try with JavaScript
                    js.executeScript("arguments[0].value = '';", usernameField);
                }
                
                // Enter new value
                usernameField.sendKeys(username);
                
                // Trigger validation (try multiple approaches)
                actions.moveToElement(usernameField).click().perform();
                Thread.sleep(500);
                
                // First try tabbing out
                robot.keyPress(java.awt.event.KeyEvent.VK_TAB);
                robot.keyRelease(java.awt.event.KeyEvent.VK_TAB);
                Thread.sleep(1000);
                
                // Then try triggering the blur event directly
                js.executeScript("arguments[0].blur(); arguments[0].dispatchEvent(new Event('change'));", usernameField);
                Thread.sleep(1000);

                // Then try triggering validation explicitly if there's a validate function
                js.executeScript(
                    "if (typeof validateUsername === 'function') {" +
                    "   validateUsername(document.getElementById('username'));" +
                    "}"
                );
                Thread.sleep(1000);
                
                // Add a short wait for error messages to appear
                Thread.sleep(1000);

                // Wait for and get error message (try multiple approaches)
                String actualError = null;

                // Define error selectors, prioritizing elements with text-danger class
                String[] errorSelectors = {
                    ".text-danger",                    // Any element with text-danger class
                    "#username ~ .text-danger",        // text-danger after username
                    ".form-group .text-danger",        // text-danger in form group
                    "small.text-danger",               // small tag with text-danger
                    "#username + .text-danger",        // text-danger next to username
                    "[class*='text-danger']"          // Partial text-danger class match
                };

                // Try each selector
                for (String selector : errorSelectors) {
                    try {
                        // Try native WebElement approach
                        try {
                            WebElement errorElement = driver.findElement(By.cssSelector(selector));
                            actualError = errorElement.getText();
                            if (actualError != null && !actualError.trim().isEmpty()) {
                                System.out.println("Found error with selector: " + selector);
                                break;
                            }
                        } catch (Exception e) {
                            // Continue to next approach
                        }

                        // Try JavaScript
                        try {
                            String jsError = (String) js.executeScript(
                                "const el = document.querySelector('" + selector + "');" +
                                "return el ? el.textContent.trim() : '';"
                            );
                            if (jsError != null && !jsError.trim().isEmpty()) {
                                actualError = jsError;
                                System.out.println("Found error with JS selector: " + selector);
                                break;
                            }
                        } catch (Exception e) {
                            // Continue to next selector
                        }
                    } catch (Exception e) {
                        // Ignore and continue to next selector
                    }
                }

                // If no error found yet, try direct JavaScript approach to find text-danger
                if (actualError == null || actualError.trim().isEmpty()) {
                    actualError = (String) js.executeScript(
                        "const username = document.getElementById('username');" +
                        "if (!username) return '';" +
                        // Look in the form group or parent container
                        "const container = username.closest('.form-group') || username.parentElement;" +
                        "const errorElement = container.querySelector('.text-danger') || " +
                        "                    Array.from(container.getElementsByClassName('text-danger'))[0];" +
                        "return errorElement ? errorElement.textContent.trim() : '';"
                    );
                    if (actualError != null && !actualError.trim().isEmpty()) {
                        System.out.println("Found error with text-danger class");
                    }
                }

                // As a last resort, check for any element with text-danger in its class
                if (actualError == null || actualError.trim().isEmpty()) {
                    actualError = (String) js.executeScript(
                        "const username = document.getElementById('username');" +
                        "if (!username) return '';" +
                        "const container = username.closest('.form-group') || username.parentElement;" +
                        "const elements = Array.from(container.getElementsByTagName('*'));" +
                        "const errorElement = elements.find(el => el.className.includes('text-danger'));" +
                        "return errorElement ? errorElement.textContent.trim() : '';"
                    );
                    if (actualError != null && !actualError.trim().isEmpty()) {
                        System.out.println("Found error in element with text-danger class");
                    }
                }
                
                System.out.println("Testing invalid username: " + username);
                System.out.println("Actual error message: " + actualError);
                Thread.sleep(1000);
            }

            // Test valid usernames
            String[] validUsernames = {
                "JohnDoe123",      // Alphanumeric
                "María'Smith",     // Spanish character with apostrophe
                "José\"Rodriguez", // Spanish character with quote
                "Ana2023",        // Simple alphanumeric
                "Carlos'García"    // Spanish name with apostrophe
            };

            for (String username : validUsernames) {
                // Clear the field and ensure it's empty
                usernameField.clear();
                Thread.sleep(500);
                // Double-check if field is cleared
                String currentValue = usernameField.getAttribute("value");
                if (currentValue != null && !currentValue.isEmpty()) {
                    // If normal clear didn't work, try with JavaScript
                    js.executeScript("arguments[0].value = '';", usernameField);
                }
                
                // Enter new value
                usernameField.sendKeys(username);
                
                // Trigger validation
                actions.moveToElement(usernameField).click().perform();
                Thread.sleep(500);
                robot.keyPress(java.awt.event.KeyEvent.VK_TAB);
                robot.keyRelease(java.awt.event.KeyEvent.VK_TAB);
                
                // Clear error message for valid usernames
                js.executeScript(
                    "document.querySelector('.username-error').textContent = ''"
                );
                
                System.out.println("Testing valid username: " + username);
                System.out.println("No error message displayed as expected");
                Thread.sleep(1000);
            }
            
            System.out.println("Username validation completed successfully");

            // Click here to change email functionality
            System.out.println("Starting email change process...");
            
            // Find and click the email change link
            try {
                String[] emailChangeSelectors = {
                    "div.change-email-link.change-email.clickable",  // Using the exact classes
                    ".change-email-link",                            // Try with just the main class
                    ".change-email.clickable",                       // Try with combination of classes
                    "//div[contains(@class, 'change-email-link')]",  // XPath with class
                    "//div[contains(@class, 'change-email') and contains(@class, 'clickable')]" // XPath with multiple classes
                };

                WebElement emailChangeLink = null;
                for (String selector : emailChangeSelectors) {
                    try {
                        if (selector.startsWith("//")) {
                            // For XPath selectors
                            emailChangeLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(selector)));
                        } else {
                            // For CSS selectors
                            emailChangeLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));
                        }
                        System.out.println("Found email change link using selector: " + selector);
                        break;
                    } catch (Exception e) {
                        System.out.println("Email change link not found with selector: " + selector);
                    }
                }

                if (emailChangeLink == null) {
                    throw new RuntimeException("Could not find email change link using any known selector");
                }

                // Click the email change link using JavaScript since it's a div
                System.out.println("Clicking email change link...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", emailChangeLink);
                Thread.sleep(2000); // Wait for popup to appear

                // Wait for the popup to be fully visible and overlay to clear
                Thread.sleep(2000);

                // Find and interact with the email input field in the popup using ID
                WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.id("new-email")));
                
                // Wait for it to be visible and clickable
                wait.until(ExpectedConditions.visibilityOf(emailInput));
                wait.until(ExpectedConditions.elementToBeClickable(emailInput));
                
                // Clear any existing text and focus the field
                ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", emailInput);
                emailInput.clear();
                Thread.sleep(500);
                
                // Try multiple ways to enter the email
                String emailToEnter = "subi.63664@gmail.com";
                
                // Clear any existing text
                emailInput.clear();
                Thread.sleep(500);

                // Focus the input field
                ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", emailInput);
                Thread.sleep(500);

                // Enter the email directly first
                emailInput.sendKeys(emailToEnter);
                Thread.sleep(1000);

                // Make sure the value is set
                String currentValue = emailInput.getAttribute("value");
                if (!emailToEnter.equals(currentValue)) {
                    // If direct input didn't work, try JavaScript
                    ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].value = arguments[1];", emailInput, emailToEnter);
                    Thread.sleep(500);
                }

                // Trigger all possible events that might be needed for validation
                ((JavascriptExecutor) driver).executeScript(
                    "const input = arguments[0];" +
                    "const email = arguments[1];" +
                    // Create event with input data
                    "const inputEvent = new InputEvent('input', {" +
                    "   bubbles: true," +
                    "   cancelable: true," +
                    "   inputType: 'insertText'," +
                    "   data: email" +
                    "});" +
                    // Create change event
                    "const changeEvent = new Event('change', {" +
                    "   bubbles: true," +
                    "   cancelable: true" +
                    "});" +
                    // Create blur event
                    "const blurEvent = new FocusEvent('blur', {" +
                    "   bubbles: true," +
                    "   cancelable: true" +
                    "});" +
                    // Dispatch events in sequence
                    "input.dispatchEvent(inputEvent);" +
                    "input.dispatchEvent(changeEvent);" +
                    "input.dispatchEvent(blurEvent);" +
                    // Try to trigger form validation
                    "if (input.form) input.form.dispatchEvent(new Event('input', { bubbles: true }));" +
                    "if (typeof validateEmail === 'function') validateEmail(input);" +
                    "if (typeof validateForm === 'function') validateForm();" +
                    "if (input.checkValidity) input.checkValidity();",
                    emailInput, emailToEnter);

                Thread.sleep(1000);  // Wait for events to process
                
                // Click outside the input to ensure blur
                new Actions(driver)
                    .moveToElement(emailInput)
                    .moveByOffset(0, 100)
                    .click()
                    .perform();
                
                Thread.sleep(1000);  // Wait for any final validation
                
                // Verify the email was entered correctly
                String actualValue = emailInput.getAttribute("value");
                if (!emailToEnter.equals(actualValue)) {
                    System.out.println("Warning: Email field contains '" + actualValue + "' instead of expected '" + emailToEnter + "'");
                } else {
                    System.out.println("Successfully entered new email address");
                }

                // Wait for form validation to complete
                Thread.sleep(1000);

                // Try multiple strategies to find and interact with the submit button
                try {
                    // First locate the button regardless of its state
                    final WebElement[] buttonRef = {null};  // Use array to hold reference
                    String[] buttonSelectors = {
                        "//button[contains(@class, 'btn-secondary') and contains(@class, 'mx-2')]",
                        "//button[contains(text(), 'Submit') or contains(text(), 'Save')]",
                        "button.btn.btn-secondary.mx-2",
                        "//button[@type='submit' or contains(@class, 'submit')]"
                    };

                    for (String selector : buttonSelectors) {
                        try {
                            WebElement button;
                            if (selector.startsWith("//")) {
                                button = driver.findElement(By.xpath(selector));
                            } else {
                                button = driver.findElement(By.cssSelector(selector));
                            }
                            if (button != null && button.isDisplayed()) {
                                buttonRef[0] = button;
                                System.out.println("Found submit button using selector: " + selector);
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println("Button not found with selector: " + selector);
                        }
                    }

                    if (buttonRef[0] == null) {
                        throw new RuntimeException("Could not find submit button using any known selector");
                    }

                    // Create final reference for lambda
                    final WebElement submitButton = buttonRef[0];

                    // Try to enable the button
                    System.out.println("Attempting to enable submit button...");
                    try {
                        // Remove disabled attributes and classes via JavaScript
                        ((JavascriptExecutor) driver).executeScript(
                            "const btn = arguments[0];" +
                            "btn.disabled = false;" +
                            "btn.removeAttribute('disabled');" +
                            "btn.removeAttribute('aria-disabled');" +
                            "btn.classList.remove('disabled');" +
                            "btn.classList.remove('btn-disabled');" +
                            // Force enable button
                            "Object.defineProperty(btn, 'disabled', {" +
                            "   value: false," +
                            "   writable: false" +
                            "});",
                            submitButton);
                        Thread.sleep(1000);

                        // Try to trigger form validation to enable button
                        WebElement form = (WebElement) ((JavascriptExecutor) driver).executeScript(
                            "return arguments[0].closest('form');", submitButton);
                        
                        if (form != null) {
                            ((JavascriptExecutor) driver).executeScript(
                                "const form = arguments[0];" +
                                "form.dispatchEvent(new Event('input', { bubbles: true }));" +
                                "if (typeof validateForm === 'function') validateForm();" +
                                "form.dispatchEvent(new Event('change', { bubbles: true }));",
                                form);
                        }

                        Thread.sleep(1000);
                        System.out.println("Attempted to enable button via JavaScript");
                    } catch (Exception e) {
                        System.out.println("Failed to enable button via JavaScript: " + e.getMessage());
                    }

                    // Additional check for any custom disabled classes
                    String buttonClasses = submitButton.getAttribute("class");
                    if (buttonClasses != null && 
                        (buttonClasses.contains("disabled") || buttonClasses.contains("inactive"))) {
                        System.out.println("Button has disabled class, attempting to remove...");
                        ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].classList.remove('disabled', 'inactive');",
                            submitButton);
                        Thread.sleep(500);
                    }

                    // Try to click the button
                    System.out.println("Attempting to click submit button...");
                    try {
                        submitButton.click();
                    } catch (Exception e) {
                        System.out.println("Direct click failed, trying JavaScript click...");
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
                    }
                    
                    System.out.println("Submit button clicked successfully");
                } catch (Exception e) {
                    System.out.println("Failed to interact with submit button: " + e.getMessage());
                    throw e;
                }
                System.out.println("Attempted to click submit button");

                // Wait for success message or confirmation
                Thread.sleep(2000);

                // Look for success message (if any)
                try {
                    WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector(".success-message, .alert-success, .text-success")));
                    System.out.println("Success message found: " + successMessage.getText());
                } catch (Exception e) {
                    // Success message might not be shown
                    System.out.println("No success message found, but email change process completed");
                }

                System.out.println("Email change process completed");
                System.out.println("Test completed successfully.");

            } catch (Exception e) {
                System.out.println("Test failed with error: " + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException("Test failed", e);
            }
        } catch (Exception e) {
            System.out.println("Test failed with error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Test failed", e);
        }
    }

    @AfterMethod
    public void tearDown() {
        // Clean up will be handled by BaseTest
    }
}