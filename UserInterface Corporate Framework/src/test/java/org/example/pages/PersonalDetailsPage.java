package org.example.pages;

import aquality.selenium.forms.Form;
import org.example.util.LoggerUtil;
import org.openqa.selenium.By;
import org.slf4j.Logger;

public class PersonalDetailsPage extends Form {

    public PersonalDetailsPage() {
        super(By.className("personal-details"), "Страница с персональными данными");
        Logger logger = LoggerUtil.getLogger(PersonalDetailsPage.class);
        logger.info("Инициализация класса");
    }
}
