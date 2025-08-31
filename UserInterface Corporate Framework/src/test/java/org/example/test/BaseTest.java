package org.example.test;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.example.object.ConfigObject;
import org.example.util.ParseDataUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
 //   static Browser browser;
    private static final ConfigObject configObject = ParseDataUtil.getConfigObject();
    protected BaseTest() {
    }

    @BeforeMethod
    public Browser setUpPropertyOfDriver() {
        AqualityServices.getBrowser().maximize();
        AqualityServices.getBrowser().goTo(configObject.url());
        AqualityServices.getBrowser().waitForPageToLoad();
        return AqualityServices.getBrowser();
    }

    @AfterMethod
    public void tearDown() {
        AqualityServices.getBrowser().quit();
    }
}
