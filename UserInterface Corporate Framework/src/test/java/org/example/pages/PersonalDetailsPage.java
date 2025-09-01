package org.example.pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PersonalDetailsPage extends Form {
    public PersonalDetailsPage() {
        super(By.className("personal-details"), "Страница с персональными данными");
    }
}
