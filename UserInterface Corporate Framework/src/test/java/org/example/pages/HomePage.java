package org.example.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IElementFactory;
import org.openqa.selenium.By;

public class HomePage extends BaseForm {
    private static final String locator = "start__button";
    IElementFactory elementFactory = AqualityServices.getElementFactory();

    public HomePage() {
        super(locator);
    }

    public void clickHereLink() {
        elementFactory.getLink(By.className("start__link"), "start link").click();
    }
}
