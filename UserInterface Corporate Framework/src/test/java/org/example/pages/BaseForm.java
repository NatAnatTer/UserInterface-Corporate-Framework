package org.example.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IElementFactory;
import org.openqa.selenium.By;

public abstract class BaseForm {
    private final String locator;
    IElementFactory elementFactory = AqualityServices.getElementFactory();

    public BaseForm(String locator) {
        this.locator = locator;
    }

    public Boolean isDisplayed() {
        return elementFactory.getButton(By.className(locator), locator).state().waitForDisplayed();
    }
}
