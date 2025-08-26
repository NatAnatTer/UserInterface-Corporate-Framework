package org.example.driver;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.example.object.ConfigObject;
import org.example.util.ParseDataUtil;

public class DriverManager {
    private static DriverManager instance;
    static Browser browser;
    private static final ConfigObject configObject = ParseDataUtil.getConfigObject();
    private DriverManager() {
    }
    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
            browser = AqualityServices.getBrowser();
            browser.maximize();
            browser.goTo(configObject.url());
            browser.waitForPageToLoad();
        }
        return instance;

    }
    public Browser getBrowser(){ return browser;}

    public static void close() {
        synchronized (DriverManager.class) {
            instance = null;
            browser.quit();
        }
    }
}
