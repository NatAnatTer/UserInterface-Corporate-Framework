package org.example.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ITextBox;
import org.openqa.selenium.By;

import java.util.List;

public class LoginFormPage extends BaseForm {
    private static final String locator = "login-form";
    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final ITextBox password = elementFactory
            .getTextBox(new By.ByXPath("//input[@placeholder='Choose Password']"), "password");
    private final ITextBox loginBody = elementFactory
            .getTextBox(new By.ByXPath("//input[@placeholder='Your email']"), "loginBody");
    private final ITextBox loginDomain = elementFactory
            .getTextBox(new By.ByXPath("//input[@placeholder='Domain']"), "loginDomain");
    private final IButton loginDomainTLDHeader = elementFactory
            .getButton(By.className("dropdown__header"), "loginDomainTLD");

    public LoginFormPage() {
        super(locator);
    }

    public void inputLoginForm(String email, String password) {
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
        IButton loginDomainTLD = elementFactory
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
        System.out.println(emailBody);
        String[] parseDomain = parseEmail[1].split("\\.");
        domain = parseDomain[0];
        System.out.println(domain);
        domainTLD = "." + parseDomain[1];
        System.out.println(domainTLD);
        return List.of(emailBody, domain, domainTLD);
    }

    private void clickOnNextButton() {
        elementFactory.getLink(By.className("button--secondary"), "next").click();
    }

    private void clickOnSubmitCheckbox() {
        if(elementFactory.getCheckBox(By.className("checkbox"), "checkbox").isChecked()){
            elementFactory.getCheckBox(By.className("checkbox"), "checkbox").uncheck();
        }
        else {
            elementFactory.getCheckBox(By.className("checkbox"), "checkbox").check();
        }
    }
}
