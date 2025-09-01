package org.example.pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.example.object.ConfigObject;
import org.example.util.LoggerUtil;
import org.example.util.ParseDataUtil;
import org.example.util.RandomGenerator;
import org.openqa.selenium.By;
import org.slf4j.Logger;

import java.util.List;

public class RegistrationPage extends Form {
    private final Logger logger = LoggerUtil.getLogger(RegistrationPage.class);
    private final ILabel helpForm = getElementFactory().getLabel(By.className("help-form"), "help form");
    private final IButton hideButton = getElementFactory().getButton(By.xpath("//button[contains(@class,'help-form__send-to-bottom-button')]"), "hide");
    private final ILabel timer = getElementFactory().getLabel(By.xpath("//*[@class='view__row']//*[contains(@class,'timer')]"), "timer");
    private final ILabel cookies = getElementFactory().getLabel(By.className("cookies"), "cookies");
    private final IButton cookieButton = getElementFactory().getButton(By.xpath("//button[@name='button' and contains(text(), 'Not really, no')]"), "accept");
    private final ITextBox password = getElementFactory()
            .getTextBox(new By.ByXPath("//input[@placeholder='Choose Password']"), "password");
    private final ITextBox loginBody = getElementFactory()
            .getTextBox(new By.ByXPath("//input[@placeholder='Your email']"), "loginBody");
    private final ITextBox loginDomain = getElementFactory()
            .getTextBox(new By.ByXPath("//input[@placeholder='Domain']"), "loginDomain");
    private final IButton loginDomainTLDHeader = getElementFactory()
            .getButton(By.className("dropdown__header"), "loginDomainTLD");
    private final ICheckBox checkPermission = getElementFactory().getCheckBox(By.className("checkbox"), "checkbox");
    private final IButton nextButton = getElementFactory().getButton(By.className("button--secondary"), "next");
    private final String dropDownListLocator = "dropdown__list-item";
    private static final ConfigObject configObject = ParseDataUtil.getConfigObject();

    public RegistrationPage() {
        super(By.className("game"), "Страница регистрации");
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

    public void inputLoginForm() {
        logger.info("Ввод пароля и почты, подтверждение");
        enterEmailAndPassword();
        clickOnSubmitCheckbox();
        clickOnNextButton();
    }

    private void enterEmailAndPassword() {
        logger.info("Получаем случайным образом сгенерированные email и пароль");
        String emailBody = RandomGenerator.getRandomEmail(configObject.personalDataObject().lengthOfEmailBody());
        String domain = RandomGenerator.getRandomEmail(configObject.personalDataObject().lengthOfDomain());
        String passwordGenerated = RandomGenerator.getRandomPassword(configObject.personalDataObject().lengthOfPassword(), emailBody);
        logger.info("Вводим случайным образом сгенерированные email и пароль на форму для ввода");
        loginBody.state().waitForDisplayed();
        loginBody.clearAndType(emailBody);
        loginDomain.state().waitForDisplayed();
        loginDomain.clearAndType(domain);
        loginDomainTLDHeader.state().waitForDisplayed();
        loginDomainTLDHeader.click();
        List<IButton> listOfDomains = getElementFactory()
                .findElements(By.className(dropDownListLocator), IButton.class);
        listOfDomains.get(RandomGenerator.getRandomDomainTLD(listOfDomains.size())).click();
        password.state().waitForDisplayed();
        password.clearAndType(passwordGenerated);
    }

    private void clickOnNextButton() {
        nextButton.click();
    }

    private void clickOnSubmitCheckbox() {
        if (checkPermission.isChecked()) {
            checkPermission.uncheck();
        } else {
            checkPermission.check();
        }
    }
}
