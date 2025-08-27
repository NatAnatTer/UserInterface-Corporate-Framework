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

    public RegistrationPage() {
        super(locator);
    }

    public void inputLoginForm(String email, String password) {
        loginFormPage.inputLoginForm(email, password);
    }

    public void setAvatarAndInterests() {
        avatarAndInterestsPage.uploadAvatarImage();
    }

    public void cookiesAccept() {
        IElementFactory elementFactory = AqualityServices.getElementFactory();
        ILabel cookies = elementFactory.getLabel(By.className("cookies"), "cookies");
        IButton cookieButton = elementFactory.getButton(By.xpath("//button[@name='button' and contains(text(), 'Not really, no')]"), "accept");
        cookies.state().waitForDisplayed();
        cookieButton.state().waitForDisplayed();
        cookieButton.click();

    }
public Boolean cookiesFormIsDisplayed(){
    IElementFactory elementFactory = AqualityServices.getElementFactory();
    ILabel cookies = elementFactory.getLabel(By.className("cookies"), "cookies");
   return cookies.state().isDisplayed();
}

}
