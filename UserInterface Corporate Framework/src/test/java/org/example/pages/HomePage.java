package org.example.pages;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.example.util.LoggerUtil;
import org.openqa.selenium.By;
import org.slf4j.Logger;


public class HomePage extends Form {
    private final Logger logger = LoggerUtil.getLogger(HomePage.class);
    private final ILink nextButton = getElementFactory().getLink(By.className("start__link"), "start link");

    public HomePage() {
        super(By.className("start__button"), "Домашняя страница");
    }

    public void clickHereLink() {
        logger.info("Нажатие кнопки Далее");
        nextButton.click();
    }
}
