package org.example.test;

import org.example.object.ConfigObject;
import org.example.pages.HomePage;
import org.example.pages.RegistrationPage;
import org.example.util.ParseDataUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCaseTimer extends BaseTest {
    private final RegistrationPage registrationPage = new RegistrationPage();
    private final HomePage homePage = new HomePage();
    private static final ConfigObject configObject = ParseDataUtil.getConfigObject();

    @Test
    public void timerTest() {
        Assert.assertTrue(homePage.isDisplayed(), "Home page is not displayed");
        homePage.clickHereLink();
        Assert.assertTrue(registrationPage.isDisplayed(), "Registration page is not displayed");
        Assert.assertEquals(registrationPage.getTimer(), configObject.timer(), "Timer is not zero");
    }
}
