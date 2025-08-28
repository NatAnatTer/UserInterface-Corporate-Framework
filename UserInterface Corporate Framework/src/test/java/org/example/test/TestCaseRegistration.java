package org.example.test;

import org.example.object.ConfigObject;
import org.example.pages.AvatarAndInterestsPage;
import org.example.pages.HomePage;
import org.example.pages.RegistrationPage;
import org.example.util.ParseDataUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

public class TestCaseRegistration extends BaseTest {
    private final HomePage homePage = new HomePage();
    private final RegistrationPage registrationPage = new RegistrationPage();
    private static final ConfigObject configObject = ParseDataUtil.getConfigObject();
    private final AvatarAndInterestsPage avatarAndInterestsPage = new AvatarAndInterestsPage();

    @Test
    public void firstTest() throws InterruptedException, AWTException {
        Assert.assertTrue(homePage.isDisplayed(), "Home page is not displayed");
        homePage.clickHereLink();
        Assert.assertTrue(registrationPage.isDisplayed());
        registrationPage.inputLoginForm(configObject.personalDataObject().email(),configObject.personalDataObject().password());
        Assert.assertTrue(avatarAndInterestsPage.isDisplayed());
        Assert.assertTrue(avatarAndInterestsPage.fillAvatarAndInterestsForm(configObject.countOfInterests()), "Personal page is not displayed");
    }

}
