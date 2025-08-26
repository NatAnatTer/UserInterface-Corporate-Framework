package org.example.test;

import aquality.selenium.browser.Browser;
import org.example.driver.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected BaseTest() {
    }

    @BeforeMethod
    public Browser setUpPropertyOfDriver() {
        return DriverManager.getInstance().getBrowser();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.close();
    }
}
