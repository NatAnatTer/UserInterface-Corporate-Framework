package org.example.pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.example.util.LoggerUtil;
import org.openqa.selenium.By;
import org.slf4j.Logger;

import java.util.List;
import java.util.Random;

public class LoginFormPage extends Form {
    private static final Logger logger = LoggerUtil.getLogger(LoginFormPage.class);
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

    public void inputLoginForm(int lengthOfEmailBody, int lengthOfDomain, int lengthOfPassword) {
        logger.info("Ввод пароля и почты, подтверждение");
        enterEmailAndPassword(lengthOfEmailBody, lengthOfDomain, lengthOfPassword);
        clickOnSubmitCheckbox();
        clickOnNextButton();
    }

    private void enterEmailAndPassword(int lengthOfEmailBody, int lengthOfDomain, int lengthOfPassword) {
        logger.info("Получаем случайным образом сгенерированные email и пароль");
        String emailBody = getRandomEmail(lengthOfEmailBody);
        String domain = getRandomEmail(lengthOfDomain);
        String passwordGenerated = getRandomPassword(lengthOfPassword, emailBody);
        logger.info("Вводим случайным образом сгенерированные email и пароль на форму для ввода");
        loginBody.state().waitForDisplayed();
        loginBody.clearAndType(emailBody);
        loginDomain.state().waitForDisplayed();
        loginDomain.clearAndType(domain);
        loginDomainTLDHeader.state().waitForDisplayed();
        loginDomainTLDHeader.click();
        List<IButton> listOfDomains = getElementFactory()
                .findElements(By.className("dropdown__list-item"), IButton.class);
        listOfDomains.get(getRandomDomainTLD(listOfDomains.size())).click();
        password.state().waitForDisplayed();
        password.clearAndType(passwordGenerated);
    }

    public static String getRandomEmail(int lengthOfEmailBody) {
        logger.info("Генерируем случайным образом строку заданной длины для email и домена");
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(lengthOfEmailBody);
        for (int i = 0; i < lengthOfEmailBody; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    public static int getRandomDomainTLD(int lengthOfListDomain) {
        logger.info("Генерируем случайным образом домен");
        Random random = new Random(lengthOfListDomain - 1);
        return random.nextInt(lengthOfListDomain - 1);
    }

    public static String getRandomPassword(int lengthOfPassword, String emailBody) {
        logger.info("Генерируем случайным образом пароль");
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String charactersCyrillic = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        String charactersOfCapitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String charactersOfNumeral = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(lengthOfPassword);
        int indexCyrillic = random.nextInt(charactersCyrillic.length());
        sb.append(charactersCyrillic.charAt(indexCyrillic));
        int indexOfCapitalLetters = random.nextInt(charactersOfCapitalLetters.length());
        sb.append(charactersOfCapitalLetters.charAt(indexOfCapitalLetters));
        int indexOfNumeral = random.nextInt(charactersOfNumeral.length());
        sb.append(charactersOfNumeral.charAt(indexOfNumeral));
        int indexOfEmailBody = random.nextInt(emailBody.length());
        sb.append(emailBody.charAt(indexOfEmailBody));
        for (int i = 0; i < lengthOfPassword - 4; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
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
