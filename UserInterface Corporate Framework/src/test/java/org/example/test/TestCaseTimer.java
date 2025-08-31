package org.example.test;

import org.example.object.ConfigObject;
import org.example.pages.HomePage;
import org.example.pages.RegistrationPage;
import org.example.util.LoggerUtil;
import org.example.util.ParseDataUtil;
import org.example.util.TestInvokedMethodListenerUtil;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestInvokedMethodListenerUtil.class)
public class TestCaseTimer extends BaseTest {
    private final Logger logger = LoggerUtil.getLogger(TestCaseTimer.class);
    private static final ConfigObject configObject = ParseDataUtil.getConfigObject();

    @Test
    public void timerTest() {
        RegistrationPage registrationPage = new RegistrationPage();
        HomePage homePage = new HomePage();
        logger.info("Шаг 1. Старт тестирования. Переход на главную страницу");
        Assert.assertTrue(homePage.state().isDisplayed(), "Домашняя страница открыта");
        logger.info("Шаг 2. Клик по ссылке HERE. Переход на страницу регистрации");
        homePage.clickHereLink();
        Assert.assertTrue(registrationPage.state().isDisplayed(), "Страница регистрации не отображается");
        logger.info("Шаг 2. Получение значения таймера и сравнение с тестовыми данными");
        Assert.assertEquals(registrationPage.getTimer(), configObject.timer(), "Данные таймера и тестовых данных не совпадают");
    }
}
