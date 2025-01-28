package com.ui.tests;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static com.constants.Browser.CHROME;

public class TestBase {

    protected HomePage homePage;
    Logger logger = LoggerUtility.getLogger(this.getClass());
    private boolean isLambdaTest;

    @Parameters({"browser", "isLambdaTest", "isHeadless"})
    @BeforeMethod(description = "Load Home page of the Website")
    public void setUp (@Optional("chrome") String browser,
                       @Optional("false") boolean isLambdaTest,
                       @Optional("true") boolean isHeadless, ITestResult result) {

        WebDriver lambdaDriver;
        this.isLambdaTest = isLambdaTest;
        if (isLambdaTest) {
            lambdaDriver = LambdaTestUtility.initializeLambdaTestSession(browser, result.getMethod().getMethodName());
            homePage = new HomePage(lambdaDriver);
        } else {
            logger.info("Load the Home page of the website");
            homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);
        }
    }

    public BrowserUtility getInstance () {
        return homePage;
    }

    @AfterMethod
    public void tearDown () {
        if (isLambdaTest) {
            LambdaTestUtility.quitSession(); // close the browser session on lambda test
        } else {
            homePage.quit();
        }
    }
}
