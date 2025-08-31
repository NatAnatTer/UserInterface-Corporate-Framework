package org.example.pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.example.util.LoggerUtil;
import org.openqa.selenium.By;
import org.slf4j.Logger;

import java.util.List;

public class LoginFormPage extends Form {
    private final Logger logger = LoggerUtil.getLogger(LoginFormPage.class);
    private final ITextBox password = getElementFactory()
            .getTextBox(new By.ByXPath("//input[@placeholder='Choose Password']"), "password");
    private final ITextBox loginBody = getElementFactory()
            .getTextBox(new By.ByXPath("//input[@placeholder='Your email']"), "loginBody");
    private final ITextBox loginDomain = getElementFactory()
            .getTextBox(new By.ByXPath("//input[@placeholder='Domain']"), "loginDomain");
    private final IButton loginDomainTLDHeader = getElementFactory()
            .getButton(By.className("dropdown__header"), "loginDomainTLD");
    private final ICheckBox checkPermission = getElementFactory().getCheckBox(By.className("checkbox"), "checkbox");

    protected LoginFormPage() {
        super(By.className("login-form"), "Форма ввода регистрационных данных");
    }


    public void inputLoginForm(String email, String password) {
        logger.info("Ввод пароля и почты");
        enterPassword(password);
        enterEmail(email);
        clickOnSubmitCheckbox();
        clickOnNextButton();
    }

    private void enterPassword(String enteredPassword) {
        password.state().waitForDisplayed();
        password.clearAndType(enteredPassword);
    }

    private void enterEmail(String email) {
        List<String> parseEmail = parseEmail(email);
        String emailBody = parseEmail.get(0);
        String domain = parseEmail.get(1);
        String domainTLD = parseEmail.get(2);
        loginBody.state().waitForDisplayed();
        loginBody.clearAndType(emailBody);
        loginDomain.state().waitForDisplayed();
        loginDomain.clearAndType(domain);
        loginDomainTLDHeader.state().waitForDisplayed();
        loginDomainTLDHeader.click();
        String locatorOfDomain = String.format("%s%s%s", "//*[contains(@class,'dropdown__list-item') and contains(text(),'", domainTLD, "')]");
        IButton loginDomainTLD = getElementFactory()
                .getButton(new By.ByXPath(locatorOfDomain), "loginDomainTLD");
        loginDomainTLD.state().waitForDisplayed();
        loginDomainTLD.click();
    }

    private List<String> parseEmail(String email) {
        String emailBody;
        String domain;
        String domainTLD;
        String[] parseEmail = email.split("@");
        emailBody = parseEmail[0];
        String[] parseDomain = parseEmail[1].split("\\.");
        domain = parseDomain[0];
        domainTLD = "." + parseDomain[1];
        return List.of(emailBody, domain, domainTLD);
    }

    private void clickOnNextButton() {
        getElementFactory().getLink(By.className("button--secondary"), "next").click();
    }

    private void clickOnSubmitCheckbox() {
        if (checkPermission.isChecked()) {
            checkPermission.uncheck();
        } else {
            checkPermission.check();
        }
    }
}
