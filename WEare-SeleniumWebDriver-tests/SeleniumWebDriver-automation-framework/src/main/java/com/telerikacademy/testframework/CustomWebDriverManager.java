package com.telerikacademy.testframework;

import com.telerikacademy.testframework.enums.BrowserTypes;
import org.openqa.selenium.WebDriver;

public class CustomWebDriverManager {

    public enum CustomWebDriverManagerEnum {
        INSTANCE;
        private WebDriver driver = setupBrowser();

        public void quitDriver() {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }
        public void closeDriver() {
            if (driver != null) {
                driver.close();
                driver = null;
            }
        }

        public WebDriver getDriver() {
            if (driver == null) {
                setupBrowser();
            }
            return driver;
        }

        private WebDriver setupBrowser() {
            WebDriver driver = BrowserTypes.choseBrowser(BrowserTypes.CHROME);
            driver.manage().window().maximize();
            this.driver = driver;
            return driver;
        }
    }
}
