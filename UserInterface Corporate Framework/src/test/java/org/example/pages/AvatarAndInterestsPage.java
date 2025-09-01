package org.example.pages;

import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.example.util.FileUploader;
import org.example.util.LoggerUtil;
import org.openqa.selenium.By;
import org.slf4j.Logger;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class AvatarAndInterestsPage extends Form {
    private final Logger logger = LoggerUtil.getLogger(AvatarAndInterestsPage.class);
    private final IButton uploadButton = getElementFactory().getButton(By.className("avatar-and-interests__upload-button"), "upload");
    private final ITextBox imageHolder = getElementFactory().getTextBox(By.className("avatar-and-interests__avatar-image"), "image holder");
    private final ITextBox interests = getElementFactory().getTextBox(By.className("avatar-and-interests__interests-list"), "list interests");
    private final IButton nextButton = getElementFactory().getButton(By.xpath("//button[@name='button' and contains(text(), 'Next')]"), "next button");

    public AvatarAndInterestsPage() {
        super(By.className("avatar-and-interests-page"), "Страница ввода изображения профиля и интересов");
    }

    public void fillAvatarAndInterestsForm(int countOfInterests, String pathToAvatar) {
        logger.info("Заполнение формы выбора интересов и фото профиля");
        uploadAvatarImage(pathToAvatar);
        checkInterests(countOfInterests);
    }

    public void uploadAvatarImage(String path) {
        logger.info("Загрузка на страницу фото профиля");
        File file = new File(path);
        String filePath = file.getAbsolutePath();
        clickOnAvatarUploadButton();
        FileUploader.uploadFile(filePath);
    }

    public void clickOnAvatarUploadButton() {
        uploadButton.state().waitForDisplayed();
        uploadButton.click();
    }

    public void checkInterests(int countOfInterests) {
        logger.info("Заполнение списка интересов");
        List<IButton> listInterests = getElementFactory()
                .findElements(By.xpath("//*[@class='avatar-and-interests__interests-list__item']//label[not(@for='interest_unselectall')]"), IButton.class);
        logger.info("Ожидаем появления списка интересов");
        interests.state().waitForDisplayed();
        IButton unselectAll = getElementFactory()
                .getButton(By.xpath("//*[@class='avatar-and-interests__interests-list__item']//label[@for='interest_unselectall']"), "unselect all");
        logger.info("Деактивируем список интересов нажатием чекбокса unselect all");
        unselectAll.findChildElement(By.className("checkbox"), ICheckBox.class).click();
        logger.info("Выбираем указанное количество случайных интересов");
        int i = countOfInterests;
        while (i > 0) {
            Set<Integer> indexesOfInterests = new HashSet<>();
            Random random = new Random(listInterests.size() - 1);
            while (indexesOfInterests.size() < countOfInterests) {
                int randomIndex = random.nextInt(listInterests.size() - 1);
                if (!indexesOfInterests.contains(randomIndex)) {
                    indexesOfInterests.add(randomIndex);
                    listInterests.get(randomIndex).findChildElement(By.className("checkbox"), ICheckBox.class).click();
                    i--;
                }
            }
        }
    }

    public void onNextButtonClick() {
        logger.info("Клик по кнопке перехода на страницу персональных данных");
        nextButton.state().waitForDisplayed();
        imageHolder.state().waitForDisplayed();
        nextButton.click();
    }
}
