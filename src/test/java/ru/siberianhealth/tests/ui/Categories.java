package ru.siberianhealth.tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.siberianhealth.annotations.Layer;
import ru.siberianhealth.pages.MainPage;
import ru.siberianhealth.tests.TestBase;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static ru.siberianhealth.tests.TestData.OPEN_URL;

@Layer("web")
@Owner("Dmitriy")
@Feature("Main page")
public class Categories extends TestBase {

    MainPage mainPage = new MainPage();

    @Test
    @Tag("web")
    void checkCategoriesInMenu() {
            step("Open " + OPEN_URL, () -> {
                    open(OPEN_URL);
            step("Set cookie agreement and choose delivery", () ->
                    mainPage.setCookieUsageAgreement());
                    mainPage.setDelivery();
            step("Click on menu and check categories", () ->
                    mainPage.setBurgerMenu());
                    mainPage.checkCategoriesInMenu();
        });
    }

    @Test
    @Tag("web")
    void checkHealthAndSubCategories() {
            step("Open " + OPEN_URL, () -> {
                    open(OPEN_URL);
            step("Set cookie agreement and choose delivery", () ->
                    mainPage.setCookieUsageAgreement());
                    mainPage.setDelivery();
            step("Click on menu and subcategories", () ->
                    mainPage.setBurgerMenu());
                    mainPage.setCategoryInMenu();
            step("Check subcategories", () ->
                    mainPage.checkSubCategoriesInMenu());
        });
    }

}
