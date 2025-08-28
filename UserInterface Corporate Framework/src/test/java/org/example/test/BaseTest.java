package org.example.test;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.example.object.ConfigObject;
import org.example.util.ParseDataUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    static Browser browser;
    private static final ConfigObject configObject = ParseDataUtil.getConfigObject();
    protected BaseTest() {
    }

    @BeforeMethod
    public Browser setUpPropertyOfDriver() {
        browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(configObject.url());
        browser.waitForPageToLoad();
        return browser;
    }

    @AfterMethod
    public void tearDown() {
            browser.quit();
    }
}
