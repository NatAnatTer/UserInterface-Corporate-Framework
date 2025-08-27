package org.example.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IElementFactory;
import org.example.driver.DriverManager;
import org.openqa.selenium.By;

public abstract class BaseForm {
    private final String locator;

    public BaseForm(String locator) {
        this.locator = locator;
    }

    public Boolean isDisplayed() {
        DriverManager.getInstance().getBrowser().waitForPageToLoad();
        IElementFactory elementFactory = AqualityServices.getElementFactory();
        return elementFactory.getButton(By.className(locator), locator).state().waitForDisplayed();
    }
}
