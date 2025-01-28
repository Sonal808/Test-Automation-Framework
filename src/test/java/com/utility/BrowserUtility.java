package com.utility;

import com.constants.Browser;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BrowserUtility {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    Logger logger = LoggerUtility.getLogger(this.getClass());

    public WebDriver getDriver () {
        return driver.get();
    }

    public BrowserUtility (WebDriver driver) {
        super();
        this.driver.set(driver); // initialize the instance variable
    }

    public BrowserUtility (String browserName) {

        logger.info("Launching for" + browserName);
        if (browserName.equalsIgnoreCase("chrome")) {
            driver.set(new ChromeDriver());
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver.set(new EdgeDriver());
        } else {
            logger.error("Invalid Browser name Please select Chrome or Edge Browser name");
            System.err.print("Invalid Browser name Please select Chrome or Edge Browser name");
        }

    }

    public BrowserUtility (Browser browserName) {

        logger.info("Launching for" + browserName);

        if (browserName == Browser.CHROME) {
            driver.set(new ChromeDriver());
        } else if (browserName == Browser.EDGE) {
            driver.set(new EdgeDriver());
        } else if (browserName == Browser.FIREFOX) {
            driver.set(new FirefoxDriver());
        } else {
            logger.error("Invalid Browser name Please select Chrome or Edge Browser name");
            System.err.print("Invalid Browser name Please select Chrome or Edge Browser name");
        }

    }

    public BrowserUtility (Browser browserName, boolean isHeadless) {

        logger.info("Launching for" + browserName);

        if (browserName == Browser.CHROME) {
            if (isHeadless){
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("--window-size=1920,1080");
                driver.set(new ChromeDriver(options));
            }else {
                driver.set(new ChromeDriver());
            }

        } else if (browserName == Browser.EDGE) {
            if (isHeadless){
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless");
                options.addArguments("--disable-gpu");
                driver.set(new EdgeDriver(options));
            }else {
                driver.set(new EdgeDriver());
            }
        } else if (browserName == Browser.FIREFOX) {
            if (isHeadless){
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless");
                driver.set(new FirefoxDriver(options));
            }else {
                driver.set(new FirefoxDriver());
            }
        } else {
            logger.error("Invalid Browser name Please select Chrome or Edge Browser name");
            System.err.print("Invalid Browser name Please select Chrome or Edge Browser name");
        }

    }

    public void goToWebSite (String url) {
        logger.info("Visiting the Website" + url);
        driver.get().get(url);
    }

    public void maximizeWindow () {
        logger.info("Maximizing the browser window");
        driver.get().manage().window().maximize();
    }

    public void clickOn (By locator) {

        logger.info("Finding the element with the locator" + locator);
        WebElement element = driver.get().findElement(locator);// find the element
        logger.info("Element found and now performing click");
        element.click();
    }

    public void enterText (By locator, String textToEnter) {
        logger.info("Finding the element with the locator" + locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found and now enter text" + textToEnter);
        element.sendKeys(textToEnter);
    }

    public String getVisibleText (By locator) {
        logger.info("Finding the element with the locator" + locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found and now returning the visible" + element.getText());
        return element.getText();
    }

    public String takeScreenshot (String name) {
        TakesScreenshot screenshot = (TakesScreenshot) driver.get();
        File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
        String timeStamp = format.format(date);
        String path = System.getProperty("user.dir") + "//screenshots//" + name + " - " + timeStamp + ".png";
        File screenshotFile = new File(path);
        try {
            FileUtils.copyFile(screenshotData, screenshotFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }

    public void quit(){
        driver.get().quit();
    }
}
