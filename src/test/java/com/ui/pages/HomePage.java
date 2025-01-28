package com.ui.pages;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import static com.utility.PropertiesUtil.*;

import com.utility.JSONUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public final class  HomePage extends BrowserUtility {

 private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),\"Sign in\")]");
 Logger logger = LoggerUtility.getLogger(this.getClass());
    public HomePage (Browser browserName, boolean isHeadless) {
        super(browserName,isHeadless);
        //goToWebSite(readProperty(QA,"URL"));// to call the parent class constructor from the child constructor
        goToWebSite(JSONUtility.readJson(QA).getUrl());
        maximizeWindow();
    }
    public HomePage (WebDriver driver) {
        super(driver);
        //goToWebSite(readProperty(QA,"URL"));// to call the parent class constructor from the child constructor
        goToWebSite(JSONUtility.readJson(QA).getUrl());
        maximizeWindow();
    }

    public LoginPage goToLoginPage(){// page functions ----> cannot use void

        logger.info("Trying to perform click to go to signin page");
        clickOn(SIGN_IN_LINK_LOCATOR);
        LoginPage loginPage = new LoginPage(getDriver ());
        return loginPage;

 }

}
