package com.ui.tests;

import com.ui.pages.AddressPage;
import com.ui.pages.MyAccountPage;
import com.ui.pojo.Address;
import com.utility.FakeAddressUtility;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewFirstAddressTest extends TestBase{

    private MyAccountPage myAccountPage;
    private AddressPage addressPage;
    private Address address;
    @BeforeMethod(description = "Valid First time user logs into the application")
    public void setup () {

        myAccountPage = homePage.goToLoginPage().doLoginWith("colofak167@citdaca.com", "1qaz@wsx");
        address = FakeAddressUtility.getFakeAddress();
    }

    @Test
    public void addNewAddress(){
       String newAddress =  myAccountPage.goToAddressPage().saveAddress(address);
        Assert.assertEquals(newAddress,address.getAddressAlias().toUpperCase());

    }
}
