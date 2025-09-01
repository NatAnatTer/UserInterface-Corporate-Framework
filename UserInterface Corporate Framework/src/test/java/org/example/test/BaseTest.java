package org.example.test;

import aquality.selenium.browser.AqualityServices;
import org.example.util.ParseDataUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected BaseTest() {
    }

    @BeforeMethod
    public void setUpPropertyOfDriver() {
        AqualityServices.getBrowser().maximize();
        AqualityServices.getBrowser().goTo(ParseDataUtil.parseSettings());
        AqualityServices.getBrowser().waitForPageToLoad();
    }

    @AfterMethod
    public void tearDown() {
        AqualityServices.getBrowser().quit();
    }
}
