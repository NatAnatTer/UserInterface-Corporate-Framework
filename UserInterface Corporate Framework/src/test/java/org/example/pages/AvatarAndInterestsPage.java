package org.example.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.*;
import org.example.util.FileUploader;
import org.openqa.selenium.By;

import java.awt.*;
import java.io.File;
import java.util.List;

public class AvatarAndInterestsPage extends BaseForm {
    private static final String locator = "avatar-and-interests-page";
    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final IButton uploadButton = elementFactory.getButton(By.className("avatar-and-interests__upload-button"), "upload");
    private final ITextBox imageHolder = elementFactory.getTextBox(By.className("avatar-and-interests__avatar-image"), "image holder");
    private final ITextBox interests = elementFactory.getTextBox(By.className("avatar-and-interests__interests-list"), "list interests");
    private final PersonalDetailsPage personalDetailsPage = new PersonalDetailsPage();
    private final IButton nextButton = elementFactory.getButton(By.xpath("//button[@name='button' and contains(text(), 'Next')]"), "next button");

    public AvatarAndInterestsPage() {
        super(locator);
    }

    public void fillAvatarAndInterestsForm(int countOfInterests, String path) {
        checkInterests(countOfInterests);
        uploadAvatarImage(path);
    }

    public void uploadAvatarImage(String path) {
        File file = new File(path);
        String filePath = file.getAbsolutePath();
        uploadButton.state().waitForDisplayed();
        uploadButton.click();
        try {
            FileUploader.uploadFile(filePath);
        } catch (AWTException e) {
            System.err.println("Error uploading file: " + filePath + ", error: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Upload interrupted for file: " + filePath + ", error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void checkInterests(int countOfInterests) {
        List<IButton> listInterests = elementFactory
                .findElements(By.xpath("//*[@class='avatar-and-interests__interests-list__item']"), IButton.class);
        interests.state().waitForDisplayed();
        for (IButton item : listInterests) {
            if (item.getText().trim().equalsIgnoreCase("Unselect all")) {
                item.findChildElement(By.className("checkbox"), ICheckBox.class).click();
            }
        }
        int i = countOfInterests;
        while (i > 0) {
            listInterests.get(i).findChildElement(By.className("checkbox"), ICheckBox.class).click();
            i--;
        }
    }

    public boolean onNextButtonClick() {
        nextButton.state().waitForDisplayed();
        imageHolder.state().waitForDisplayed();
        nextButton.click();
        return personalDetailsPage.isDisplayed();
    }
}
