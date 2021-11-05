package ru.siberianhealth.tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.siberianhealth.annotations.Layer;
import ru.siberianhealth.pages.MainPage;
import ru.siberianhealth.tests.TestBase;

import static com.codeborne.selenide.Selenide.open;
import static ru.siberianhealth.tests.TestData.OPEN_URL;

@Layer("web")
@Owner("Dmitriy")
@Feature("Main page")
public class Categories extends TestBase {

    MainPage mainPage = new MainPage();

    @Test
    @Tag("web")
    void checkCategoriesInMenu() {
        open(OPEN_URL);
        mainPage.setCookieUsageAgreement();
        mainPage.chooseDelivery();
        mainPage.setBurgerMenu();
        mainPage.checkCategoriesInMenu();
    }

    @Test
    @Tag("web")
    void checkHealthAndSubCategories() {
        open(OPEN_URL);
        mainPage.setCookieUsageAgreement();
        mainPage.chooseDelivery();
        mainPage.setBurgerMenu();
        mainPage.setCategoryInMenu();
        mainPage.checkSubCategoriesInMenu();
    }

}
