package com.ui.tests;

import static com.constants.Size.*;

import com.ui.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductCheckoutTest extends TestBase {

    private static final String SEARCH_TERM = "Printed Summer Dress";

    private SearchResultPage searchResultPage;

    @BeforeMethod(description = "User Logs in to application and searches for a product")
    public void setup () {

        searchResultPage = homePage.goToLoginPage()
                .doLoginWith("colofak167@citdaca.com", "1qaz@wsx")
                .searchForAProduct(SEARCH_TERM);

    }

    @Test(description = "Verify if the logged user is able to buy a dress", groups = {"e2e", "smoke", "sanity"})
    public void checkoutTest () {

        String result = searchResultPage.clickOnTheProductAtIndex(0).changeSize(L).addProductToCart().proceedToCheckout()
                .goToConfirmAddressPage().goToShipmentPage().goToPaymentPage().makePaymentByWire();
        System.out.println(result);

        Assert.assertTrue(result.contains("complete"));

    }
}
