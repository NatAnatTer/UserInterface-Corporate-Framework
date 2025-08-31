package org.example.test;

import org.example.object.ConfigObject;
import org.example.pages.AvatarAndInterestsPage;
import org.example.pages.HomePage;
import org.example.pages.PersonalDetailsPage;
import org.example.pages.RegistrationPage;
import org.example.util.LoggerUtil;
import org.example.util.ParseDataUtil;
import org.example.util.TestInvokedMethodListenerUtil;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestInvokedMethodListenerUtil.class)
public class TestCaseRegistration extends BaseTest {
    private final Logger logger = LoggerUtil.getLogger(TestCaseRegistration.class);
    private final HomePage homePage = new HomePage();
    private final RegistrationPage registrationPage = new RegistrationPage();
    private final AvatarAndInterestsPage avatarAndInterestsPage = new AvatarAndInterestsPage();
    private static final ConfigObject configObject = ParseDataUtil.getConfigObject();
    PersonalDetailsPage personalDetailsPage = new PersonalDetailsPage();

    @Test
    public void firstTest() {
        logger.info("Шаг 1. Старт тестирования. Переход на главную страницу");
        Assert.assertTrue(homePage.state().isDisplayed(), "Домашняя страница открыта");
        logger.info("Шаг 2. Клик по ссылке HERE. Переход на страницу регистрации");
        homePage.clickHereLink();
        logger.info("Проверка: страница регистрации открыта");
        Assert.assertTrue(registrationPage.state().isDisplayed());
        logger.info("Шаг 3. Ввод логина и пароля");
        registrationPage.inputLoginForm(configObject.personalDataObject().email(), configObject.personalDataObject().password());
        logger.info("Проверка: страница ввода интересов и изображения профиля открыта");
        avatarAndInterestsPage.state().waitForDisplayed();
        Assert.assertTrue(avatarAndInterestsPage.state().isDisplayed(), "Страница Avatar and interests не отображается");
        logger.info("Шаг 4. Заполнение интересов и ввод изображения профиля");
        avatarAndInterestsPage.fillAvatarAndInterestsForm(configObject.countOfInterests(), configObject.filePath());
        logger.info("Проверка: страница Персональных данных по кнопке делее открыта");
        avatarAndInterestsPage.onNextButtonClick();
        Assert.assertTrue(personalDetailsPage.state().isDisplayed(), "Страница персональных данных не отображается");
    }
}
