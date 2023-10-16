package com.telerikacademy.testframework;

import com.telerikacademy.testframework.enums.BrowserTypes;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class CustomWebDriverManager {

    public enum CustomWebDriverManagerEnum {
        INSTANCE;
        private  WebDriver driver = setupBrowser();

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


        public  WebDriver getDriver() {
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
