package org.example.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.Element;
import aquality.selenium.elements.interfaces.*;
import org.openqa.selenium.By;

import java.util.List;

public class AvatarAndInterestsPage extends BaseForm {
    private static final String locator = "avatar-and-interests-page";
    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final IButton uploadButton = elementFactory.getButton(By.className("avatar-and-interests__upload-button"), "upload");
    private final ITextBox uploadImage = elementFactory.getTextBox(By.className("avatar-and-interests__avatar-box"), "avatar image");
    private final ITextBox interests = elementFactory.getTextBox(By.className("avatar-and-interests__interests-list"), "list interests");
    private final ITextBox imageHolder = elementFactory.getTextBox(By.className("avatar-and-interests__avatar-image"), "image holder");
    private final PersonalDetailsPage personalDetailsPage = new PersonalDetailsPage();
    private final IButton nextButton = elementFactory.getButton(By.xpath("//button[@name='button' and contains(text(), 'Next')]"), "next button");

    public AvatarAndInterestsPage() {
        super(locator);
    }

    public boolean fillAvatarAndInterestsForm(int countOfInterests) {

       // checkInterests(countOfInterests);
        uploadAvatarImage();
      //  return onNextButtonClick();
        return true;
    }

    public void uploadAvatarImage() {
        uploadButton.state().waitForDisplayed();
       // uploadButton.click();

      //  visibilityHidden((Element) imageHolder);
      //  imageHolder.state().waitForDisplayed();
        String filePath = "src/test/resources/2025-08-26 17.37.58.jpg";
        uploadImage.sendKeys(filePath);
      //  imageHolder.sendKeys(filePath);
    }
    private void visibilityHidden(Element element){
        String script = "arguments[0].style.display = 'block';"
       + "arguments[0].style.visibility = 'visible';"
       + "arguments[0].style.opacity = '1';"
       + "arguments[0].style.position = 'relative';"
       + "arguments[0].style.zIndex = '9999';"
        +"arguments[0].removeAttribute('hidden');";
       AqualityServices.getBrowser().executeScript(script, element.getElement());
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
        nextButton.click();
        return personalDetailsPage.isDisplayed();
    }
}
