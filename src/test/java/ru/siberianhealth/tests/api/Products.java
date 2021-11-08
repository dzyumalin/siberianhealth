package ru.siberianhealth.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.siberianhealth.annotations.Layer;
import ru.siberianhealth.helpers.AllureRestAssuredFilter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@Layer("api")
@Owner("Dmitriy")
@Feature("Main page")
public class Products {

    @Test
    @Tag("api")
    @DisplayName("Get products (API)")
    public void getProducts() {
        given()
                .spec(Specs.requestForGet)
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .post("/list/get/")
                .then()
                .spec(Specs.response)
                .body("data[3].id", is(85598),
                        "data[3].name", is("Гелевая зубная паста + батончик со скидкой 25%"),
                        "data[3].present.products[0].sku", is("500504"),
                        "data[3].present.products[0].catalogLink", is ("/ru/shop/catalog/product/500504/"));
    }

    @Test
    @Tag("api")
    @DisplayName("Add to cart product (API)")
    public void addToCartCookie() {
        Response response =
                given()
                        .spec(Specs.request)
                        .filter(AllureRestAssuredFilter.withCustomTemplates())
                        .body("{\"productArticle\":400273,\"optionArticle\":400273,\"amount\":\"2\",\"packageContract\":null}")
                        .when()
                        .post("/add/")
                        .then()
                        .spec(Specs.response)
                        .body("success", is(true))
                        .body("message[0]", is("Товар успешно добавлен в корзину"))
                        .extract().response();
        System.out.println(
                "Message: " + response.path("message[0]") + "\n"
                        + "Amount: " + response.path("data.cartData.amount") + "\n"
                        + "Sum: " + response.path("data.cartData.sum") + "\n"
                        + "Points: " + response.path("data.cartData.packages[0].points"));
    }

    @Test
    @Tag("api")
    @DisplayName("Add to cart product with cookie (API)")
    public void addToCartAndDeleteWithCookie() {
                given()
                        .spec(Specs.request)
                        .filter(AllureRestAssuredFilter.withCustomTemplates())
                        .body("{\"productArticle\":400273,\"optionArticle\":400273,\"amount\":\"2\",\"packageContract\":null}")
                        .cookie("shopenSIN=h21glt0rhe9r3qu0bui4pledjf;")
                        .when()
                        .post("/add/")
                        .then()
                        .spec(Specs.response)
                        .body("success", is(true))
                        .body("message[0]", is("Товар успешно добавлен в корзину"))
                        .body("data.cartData.amount", is(2))
                        .body("data.cartData.sum", is(440))
                        .extract().response();

                given()
                        .spec(Specs.requestForEdit)
                        .filter(AllureRestAssuredFilter.withCustomTemplates())
                        .body("{\"number\":null,\"amount\":0,\"sum\":0,\"points\":11," +
                                "\"vat\":0,\"packages\":[{\"contract\":\"\"," +
                                "\"name\":\"\",\"avatarUrl\":\"\",\"sum\":440," +
                                "\"points\":11,\"count\":2,\"items\":[],\"kits\":[]," +
                                "\"isCertificate\":false,\"isOwner\":true}]," +
                                "\"certificatedPackages\":[],\"actions\":{\"Constancy\":[]," +
                                "\"SSNN\":[],\"Club1000\":[]}}")
                        .cookie("shopenSIN=h21glt0rhe9r3qu0bui4pledjf;")
                        .when()
                        .post("/edit/")
                        .then()
                        .spec(Specs.response)
                        .body("success", is(true))
                        .body("data.cartData.amount", is(0))
                        .body("data.cartData.sum", is(0))
                        .extract().response();
    }

}
