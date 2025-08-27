package org.example.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ITextBox;
import org.openqa.selenium.By;

public class AvatarAndInterestsPage extends BaseForm {
    private static final String locator = "avatar-and-interests-page";
    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    //    private final ITextBox password = elementFactory
//            .getTextBox(new By.ByXPath("//input[@placeholder='Choose Password']"), "password");
    private final IButton uploadButton = elementFactory.getButton(By.className("avatar-and-interests__upload-button"), "upload");

    private final ITextBox uploadImage = elementFactory.getTextBox(By.className("avatar-and-interests__avatar-box"), "avatar image");


    public AvatarAndInterestsPage() {
        super(locator);
    }

    public void uploadAvatarImage() {
          uploadButton.state().waitForDisplayed();
         uploadButton.click();
        String filePath = "src/test/resources/2025-08-26 17.37.58.jpg";
        uploadImage.type(filePath);
    }
}
