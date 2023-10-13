package com.telerikacademy.testframework;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.telerikacademy.testframework.Utils.*;
import static java.lang.String.format;

public class UserActions {

    final WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public UserActions() {
        this.driver = getWebDriver();
    }

    public static void loadBrowser(String baseUrlKey) {
        getWebDriver().get(getConfigPropertyByKey(baseUrlKey));
    }

    public static void quitDriver() {
        tearDownWebDriver();
    }
    public static void closeDriver() {
        closingDownWebDriver();
    }

    public void clickElement(String key, Object... arguments) {
        String locator = getLocatorValueByKey(key, arguments);

        LOGGER.info("Clicking on element " + key);
        WebElement element = driver.findElement(By.xpath(locator));
        element.click();
    }

    public void typeValueInField(String value, String field, Object... fieldArguments) {
        String locator = getLocatorValueByKey(field, fieldArguments);
        WebElement element = driver.findElement(By.xpath(locator));
        element.sendKeys(value);
        LOGGER.info(format("%s was typed into %s.", value, field));
    }


    public void pressKey(Keys key) {
        Actions action = new Actions(driver);
        action.sendKeys(key).perform();
    }

    public void pressKeyCopyAll() {
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL);
        actions.sendKeys("a");
        actions.keyUp(Keys.CONTROL);
        actions.build().perform();
    }

    //############# WAITS #########
    public void waitForElementVisible(String locatorKey, Object... arguments) {
        int defaultTimeout = Integer.parseInt(getConfigPropertyByKey("config.defaultTimeoutSeconds"));

        waitForElementVisibleUntilTimeout(locatorKey, defaultTimeout, arguments);
    }

    public void waitForElementClickable(String locatorKey, Object... arguments) {
        int defaultTimeout = Integer.parseInt(getConfigPropertyByKey("config.defaultTimeoutSeconds"));

        waitForElementToBeClickableUntilTimeout(locatorKey, defaultTimeout, arguments);
    }

    //############# WAITS #########
    public void waitForElementPresent(String locator, Object... arguments) {
        // TODO: Implement the method
        // 1. Initialize Wait utility with default timeout from properties
        int defaultTimeout = Integer.parseInt(getConfigPropertyByKey("config.defaultTimeoutSeconds"));
        // 2. Use the method that checks for Element present
        // 3. Fail the test with meaningful error message in case the element is not present
        waitForElementPresenceUntilTimeout(locator, defaultTimeout, arguments);
    }

    public void assertElementPresent(String locator, Object... arguments) {
        Assertions.assertNotNull((driver.findElement(By.xpath(getUIMappingByKey(locator)))), "Element is not found.");
    }

    public void assertElementAttribute(String locator, String attributeName, String attributeValue, Object... arguments) {
        // TODO: Implement the method
        // 1. Find Element using the locator value from Properties
        String xpath = getLocatorValueByKey(locator, arguments);
        WebElement element = driver.findElement(By.xpath(xpath));
        // 2. Get the element attribute
        String value = element.getAttribute(attributeName);
        // 3. Assert equality with expected value
        Assertions.assertEquals(value, attributeValue, "Attribute value is not corresponding with expected.");
    }

    private String getLocatorValueByKey(String locator) {
        return format(getUIMappingByKey(locator));
    }

    private String getLocatorValueByKey(String locator, Object[] arguments) {
        return format(getUIMappingByKey(locator), arguments);
    }

    private void waitForElementVisibleUntilTimeout(String locator, int seconds, Object... locatorArguments) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception exception) {
            Assertions.fail("Element with locator: '" + xpath + "' was not found.");
        }
    }

    private void waitForElementToBeClickableUntilTimeout(String locator, int seconds, Object... locatorArguments) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception exception) {
            Assertions.fail("Element with locator: '" + xpath + "' was not found.");
        }
    }

    private void waitForElementPresenceUntilTimeout(String locator, int seconds, Object... locatorArguments) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        } catch (Exception exception) {
            Assertions.fail("Element with locator: '" + xpath + "' was not found.");
        }
    }

    public void navigateToPage(String locator) {
        driver.get(getLocatorValueByKey(locator));
    }

    private String elementText(String key, Object... arguments) {
        String locator = getLocatorValueByKey(key, arguments);
        WebElement element = driver.findElement(By.xpath(locator));
        LOGGER.info(format("%s is the reponse text from %s", element.getText(), key));
        return element.getText();
    }

    public void assertElementText(String locator, String message, Object... arguments) {
        waitForElementVisible(locator);
        Assertions.assertEquals(message, elementText(locator), "Response message is not correct.");

    }

    public void clearingFiled(String key, Object... arguments) {
        String locator = getLocatorValueByKey(key, arguments);
        LOGGER.info(key + "was cleaned");
        WebElement element = driver.findElement(By.xpath(locator));
        element.clear();
    }

    public boolean isElementVisible(String locator, Object... arguments) {
        int defaultTimeout = Integer.parseInt(getConfigPropertyByKey("config.defaultTimeoutSeconds"));
        Duration timeout = Duration.ofSeconds(defaultTimeout);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String xpath = getLocatorValueByKey(locator, arguments);

        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public void moveToElementAndClickOnit(String key,Object...arguments){
        String locator = getLocatorValueByKey(key, arguments);
        WebElement element = driver.findElement(By.xpath(locator));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }
}
