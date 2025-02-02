package com.ui.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class InvalidCredLoginTest extends TestBase {

    private static final String INVALID_EMAIL_ADDRESS = "sonalperera88@gmail.com";
    private static final String INVALID_PASSWORD = "wsxxdert";

    @Test(description = "Verify if the proper message is shown for the user when they enter invalid credentials",
            groups = {"e2e", "sanity"})

    public void loginTest () {
        assertEquals(homePage.goToLoginPage().doLoginWithInvalidCredentials(INVALID_EMAIL_ADDRESS, INVALID_PASSWORD).getErrorMessage(), "Authentication failed.");

    }
}
