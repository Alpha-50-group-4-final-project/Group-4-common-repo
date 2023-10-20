package com.telerikacademy.testframework.enums;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public enum BrowserTypes {
    EDGE,
    CHROME,
    FIREFOX,
    EDGE_HEADLESS,
    CHROME_HEADLESS,
    FIREFOX_HEADLESS;

    public static WebDriver choseBrowser(BrowserTypes driverType) {
        switch (driverType) {
            case EDGE:
                return new EdgeDriver();
            case CHROME:
                return new ChromeDriver();
            case FIREFOX:
                return new FirefoxDriver();
            case EDGE_HEADLESS:
                EdgeOptions option = new EdgeOptions();
                option.addArguments("--headless=new");
                return new EdgeDriver(option);
            case CHROME_HEADLESS:
                ChromeOptions optionChrome = new ChromeOptions();
                optionChrome.addArguments("--headless=new");
                return new ChromeDriver(optionChrome);
            case FIREFOX_HEADLESS:
                FirefoxOptions optionFirefox = new FirefoxOptions();
                optionFirefox.addArguments("--headless");
                return new FirefoxDriver(optionFirefox);
        }
        return null;
    }

}

