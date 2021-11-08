package ru.siberianhealth.pages;

import com.codeborne.selenide.SelenideElement;
import ru.siberianhealth.pages.components.AccessCookies;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private SelenideElement burgerMenu = $("#mobiburger-menu"),
                            menu = $$(".navbar_mobile__menu").findBy(visible),
                            categoryInMenu = $$(".navbar_mobile__menu").findBy(visible),
                            subCategoryInMenu = $x("//*[@id=\"mobi-menu\"]");

    public void setBurgerMenu() {
        burgerMenu.click();
    }

    public void setCategoryInMenu() {
        categoryInMenu.shouldHave(text("Здоровье")).click();
    }

    public void setCookieUsageAgreement() {
        new AccessCookies().cookieUsageAgreement();
    }

    public void setDelivery() {
        new AccessCookies().chooseDelivery();
    }

    public void checkCategoriesInMenu() {
        menu.shouldHave(text("Главная страница"),
                text("Здоровье"),
                text("Спорт"),
                text("Питание"),
                text("Красота"),
                text("Разное"),
                text("Новый год 2022"),
                text("Новинки"),
                text("Каталоги в PDF и прайс-лист"));
    }

    public void checkSubCategoriesInMenu() {
        subCategoryInMenu.shouldHave(text("Назначение"),
                text("Серия"));
    }

}
