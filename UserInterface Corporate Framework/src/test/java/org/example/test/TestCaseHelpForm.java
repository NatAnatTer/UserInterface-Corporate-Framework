package org.example.test;

import org.example.pages.HomePage;
import org.example.pages.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCaseHelpForm extends BaseTest{
    private final RegistrationPage registrationPage = new RegistrationPage();
    private final HomePage homePage = new HomePage();

    @Test
    public void helpFormHideTest(){
        Assert.assertTrue(homePage.isDisplayed());
        homePage.clickHereLink();
        Assert.assertTrue(registrationPage.isDisplayed());
        Assert.assertFalse(registrationPage.helpFormIsHidden());
    }
}
