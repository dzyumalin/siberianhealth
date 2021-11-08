package ru.siberianhealth.tests.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import ru.siberianhealth.helpers.AllureRestAssuredFilter;
import ru.siberianhealth.tests.TestBase;
import ru.siberianhealth.tests.api.Specs;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static ru.siberianhealth.tests.TestData.OPEN_URL;

public class ProductsCheck extends TestBase {

    @Test
    @DisplayName("Add product and check cart (API + UI)")
    void loginWithCookieTest() {
        step("Get cookie and set it to browser", () -> {
            String productsCookie =
                    given()
                            .spec(Specs.request)
                            .filter(AllureRestAssuredFilter.withCustomTemplates())
                            .body("{\"productArticle\":400273,\"optionArticle\":400273,\"amount\":\"2\",\"packageContract\":null}")
                            .when()
                            .post("/add/")
                            .then()
                            .spec(Specs.response)
                            .extract()
                            .cookie("shopenSIN");

            step("Open content, cookie can be set when site is opened", () ->
                    open("https://ru.siberianhealth.com/shopen/web/favicon.ico"));

            step("Set cookie to to browser", () ->
                    getWebDriver().manage().addCookie(
                            new Cookie("shopenSIN", productsCookie)));
        });

        step("Open main page", () ->
                open(OPEN_URL));

        step("Verify successful authorization", () ->
                $(".navbar_mobile-cart__price").shouldHave(text("440")));
                $(".navbar_mobile-cart__points").shouldHave(text("11"));
    }

}
