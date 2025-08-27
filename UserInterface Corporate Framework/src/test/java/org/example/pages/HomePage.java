package org.example.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IElementFactory;
import org.openqa.selenium.By;

public class HomePage extends BaseForm {
    private static final String locator = "start__button";

    public HomePage() {
        super(locator);
    }

    public void clickHereLink() {
        String clickableLink = "start__link";
        IElementFactory elementFactory = AqualityServices.getElementFactory();
        elementFactory.getLink(By.className(clickableLink), clickableLink).click();
    }
}
