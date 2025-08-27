package org.example.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILabel;
import org.openqa.selenium.By;

public class RegistrationPage extends BaseForm {
    private final static String locator = "game";
    private final LoginFormPage loginFormPage = new LoginFormPage();
    private final AvatarAndInterestsPage avatarAndInterestsPage = new AvatarAndInterestsPage();
    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final ILabel helpForm = elementFactory.getLabel(By.className("help-form"), "help form");
    private final IButton hideButton = elementFactory.getButton(By.xpath("//button[contains(@class,'help-form__send-to-bottom-button')]"), "hide");
    private final ILabel timer = elementFactory.getLabel(By.xpath("//*[@class='view__row']//*[contains(@class,'timer')]"), "timer");
    private final ILabel cookies = elementFactory.getLabel(By.className("cookies"), "cookies");
    private final IButton cookieButton = elementFactory.getButton(By.xpath("//button[@name='button' and contains(text(), 'Not really, no')]"), "accept");

    public RegistrationPage() {
        super(locator);
    }

    public void inputLoginForm(String email, String password) {
        loginFormPage.inputLoginForm(email, password);
    }

    public boolean cookiesAccept() {
        cookies.state().waitForDisplayed();
        cookieButton.state().waitForDisplayed();
        cookieButton.click();
        return cookies.state().isDisplayed();
    }

    public boolean helpFormIsHidden() {
        helpForm.state().waitForDisplayed();
        hideButton.state().waitForDisplayed();
        hideButton.click();
        hideButton.state().waitForNotDisplayed();
        return hideButton.state().isDisplayed();
    }

    public String getTimer() {
        timer.state().waitForDisplayed();
        return timer.getText().trim();
    }

}
