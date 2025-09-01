package org.example.pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.example.util.LoggerUtil;
import org.openqa.selenium.By;
import org.slf4j.Logger;

public class RegistrationPage extends Form {
    private final Logger logger = LoggerUtil.getLogger(RegistrationPage.class);
    private final LoginFormPage loginFormPage = new LoginFormPage();
    private final ILabel helpForm = getElementFactory().getLabel(By.className("help-form"), "help form");
    private final IButton hideButton = getElementFactory().getButton(By.xpath("//button[contains(@class,'help-form__send-to-bottom-button')]"), "hide");
    private final ILabel timer = getElementFactory().getLabel(By.xpath("//*[@class='view__row']//*[contains(@class,'timer')]"), "timer");
    private final ILabel cookies = getElementFactory().getLabel(By.className("cookies"), "cookies");
    private final IButton cookieButton = getElementFactory().getButton(By.xpath("//button[@name='button' and contains(text(), 'Not really, no')]"), "accept");

    public RegistrationPage() {
        super(By.className("game"), "Страница регистрации");
    }

    public void inputLoginForm() {
        loginFormPage.inputLoginForm();
    }

    public boolean cookiesAccept() {
        logger.info("Метод приема cookie");
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
