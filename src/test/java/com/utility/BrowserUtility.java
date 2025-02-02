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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BrowserUtility {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private WebDriverWait wait;
    private Logger logger = LoggerUtility.getLogger(this.getClass());

    public WebDriver getDriver () {
        return driver.get();
    }

    public BrowserUtility (WebDriver driver) {
        super();
        this.driver.set(driver); // initialize the instance variable

        wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
    }

    public BrowserUtility (String browserName) {

        logger.info("Launching for%s".formatted(browserName));
        if (browserName.equalsIgnoreCase("chrome")) {
            driver.set(new ChromeDriver());
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver.set(new EdgeDriver());
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
        } else {
            logger.error("Invalid Browser name Please select Chrome or Edge Browser name");
            System.err.print("Invalid Browser name Please select Chrome or Edge Browser name");
        }
    }

    public BrowserUtility (Browser browserName) {

        logger.info("Launching for{}", browserName);

        if (browserName == Browser.CHROME) {
            driver.set(new ChromeDriver());
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
        } else if (browserName == Browser.EDGE) {
            driver.set(new EdgeDriver());
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
        } else if (browserName == Browser.FIREFOX) {
            driver.set(new FirefoxDriver());
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
        } else {
            logger.error("Invalid Browser name Please select Chrome or Edge Browser name or Firefox");
            System.err.print("Invalid Browser name Please select Chrome or Edge Browser name or Firefox");
        }
    }

    public BrowserUtility (Browser browserName, boolean isHeadless) {

        logger.info("Launching for%s".formatted(browserName));

        if (browserName == Browser.CHROME) {
            if (isHeadless) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("--window-size=1920,1080");
                driver.set(new ChromeDriver(options));
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
            } else {
                driver.set(new ChromeDriver());
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
            }

        } else if (browserName == Browser.EDGE) {
            if (isHeadless) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless");
                options.addArguments("--disable-gpu");
                driver.set(new EdgeDriver(options));
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
            } else {
                driver.set(new EdgeDriver());
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
            }
        } else if (browserName == Browser.FIREFOX) {
            if (isHeadless) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless");
                driver.set(new FirefoxDriver(options));
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
            } else {
                driver.set(new FirefoxDriver());
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
            }
        } else {
            logger.error("Invalid Browser name or arguments Please select Chrome or Edge Browser or Firefox or headless");
            System.err.print("Invalid Browser name or arguments Please select Chrome or Edge Browser or Firefox or headless");
        }
    }

    public void goToWebSite (String url) {
        logger.info("Visiting the Website{}", url);
        driver.get().get(url);
    }

    public void maximizeWindow () {
        logger.info("Maximizing the browser window");
        driver.get().manage().window().maximize();
    }

    public void clickOn (By locator) {

        logger.info("Finding the click element with the locator" + locator);
        //WebElement element = driver.get().findElement(locator);// find the element
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator)); // synchronize method
        logger.info("Element found and now performing click");
        element.click();
    }

    public void clickOnCheckBox (By locator) {

        logger.info("Finding the click element with the locator" + locator);
        //WebElement element = driver.get().findElement(locator);// find the element
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); // synchronize method
        logger.info("Element found and now performing click");
        element.click();
    }

    public void clickOn (WebElement element) {
        // find the element
        logger.info("Element found and now performing click");
        element.click();
    }

    public void enterText (By locator, String textToEnter) {
        logger.info("Finding the text element with the locator" + locator);
        //WebElement element = driver.get().findElement(locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        logger.info("Element found and now enter text" + textToEnter);
        element.sendKeys(textToEnter);
    }

    public void clearText (By textBoxLocator) {
        logger.info("Finding the text element with the locator for clearing" + textBoxLocator);
        //WebElement element = driver.get().findElement(textBoxLocator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textBoxLocator));
        logger.info("Element found and now clearing the textbox field");
        element.clear();
    }

    public void enterSpecialKey (By locator, Keys keyToEnter) {
        logger.info("Finding the text to enter element with the locator" + locator);
        //WebElement element = driver.get().findElement(locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        logger.info("Element found and now enter special key" + keyToEnter);
        element.sendKeys(keyToEnter);
    }

    public void selectFromDropDown (By dropDownLocator, String optionToSelect) {
        logger.info("Finding element with the locator" + dropDownLocator);
        WebElement element = driver.get().findElement(dropDownLocator);
        Select select = new Select(element);
        select.selectByVisibleText(optionToSelect);
        logger.info("Selecting the Option" + optionToSelect);

    }

    public String getVisibleText (By locator) {
        logger.info("Finding the visible text element with the locator" + locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found and now returning the visible text of that locator" + element.getText());
        return element.getText();
    }

    public List<String> getALLVisibleText (By locator) {
        logger.info("Finding ALL elements with the locator" + locator);
        List<WebElement> elementList = driver.get().findElements(locator);

        logger.info("Elements found and now printing the List of elements");
        List<String> visibleTextList = new ArrayList<String>();
        for (WebElement element : elementList) {

            System.out.println(getVisibleText(element));
            visibleTextList.add(getVisibleText(element));

        }
        return visibleTextList;
    }

    public List<WebElement> getALLElements (By locator) {
        logger.info("Finding ALL elements with the locator" + locator);
        List<WebElement> elementList = driver.get().findElements(locator);
        logger.info("Elements found and now printing the List of elements");

        return elementList;
    }

    public String getVisibleText (WebElement element) {
        logger.info("Element found and now returning the visible text of that element" + element.getText());
        return element.getText();
    }

    public String takeScreenshot (String name) {
        TakesScreenshot screenshot = (TakesScreenshot) driver.get();
        File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
        String timeStamp = format.format(date);
        /*String path = System.getProperty("user.dir") + "//screenshots//" + name + " - " + timeStamp + ".png";*/
        String path = "./screenshots/" + name + " - " + timeStamp + ".png";
        File screenshotFile = new File(path);
        try {
            FileUtils.copyFile(screenshotData, screenshotFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }

    public void quit () {
        driver.get().quit();
    }
}
