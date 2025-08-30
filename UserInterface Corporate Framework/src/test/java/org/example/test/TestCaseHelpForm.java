package org.example.test;

import org.example.pages.HomePage;
import org.example.pages.RegistrationPage;
import org.example.util.LoggerUtil;
import org.example.util.TestInvokedMethodListenerUtil;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestInvokedMethodListenerUtil.class)
public class TestCaseHelpForm extends BaseTest {
    private final Logger logger = LoggerUtil.getLogger(TestCaseHelpForm.class);
    private final RegistrationPage registrationPage = new RegistrationPage();
    private final HomePage homePage = new HomePage();

    @Test
    public void helpFormHideTest() {
        logger.info("Шаг 1. Старт тестирования. Переход на главную страницу");
       // Assert.assertTrue(homePage.isDisplayed(), "Домашняя страница открыта");
        logger.info("Шаг 2. Клик по ссылке HERE. Переход на страницу регистрации");
        homePage.clickHereLink();
        logger.info("Проверка: страница регистрации открыта");
        Assert.assertTrue(registrationPage.state().isDisplayed(), "Страница регистрации не отображается");
        logger.info("Проверка: диалоговое окно help form не закрылось при нажатии на кнопку скрыть");
        Assert.assertFalse(registrationPage.helpFormIsHidden(), "Help form отображается");
    }
}
