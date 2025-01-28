package com.ui.tests;

import static org.testng.Assert.*;

import com.ui.pojo.User;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.ui.listeners.TestListener.class)

public class LoginTest extends TestBase {
    @Test(description = "Verify with the valid user is able to login into the Application",
            groups = {"e2e", "sanity"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
            dataProvider = "LoginTestDataProvider",
            retryAnalyzer = com.ui.listeners.MyRetryAnalyser.class)

    public void loginTest (User user) {
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Sonal Perera");

    }
 /*   @Test(description = "Verify with the valid user is able to login into the Application",
            groups = {"e2e", "sanity"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
            dataProvider = "LoginTestCSVDataProvider",
            retryAnalyzer = com.ui.listeners.MyRetryAnalyser.class)

    public void loginCSVTest (User user) {
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Sonal Perera");

    }

    @Test(description = "Verify with the valid user is able to login into the Application",
            groups = {"e2e", "sanity"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
            dataProvider = "LoginTestExcelDataProvider",
            retryAnalyzer = com.ui.listeners.MyRetryAnalyser.class)

    public void loginExcelTest (User user) {

        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Sonal Perera1");

    }*/
}
