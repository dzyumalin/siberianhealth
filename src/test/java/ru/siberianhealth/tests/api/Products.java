package ru.siberianhealth.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.siberianhealth.annotations.Layer;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

@Layer("api")
@Owner("Dmitriy")
@Feature("Main page")
public class Products {

    @Test
    @Tag("api")
    public void addToCartWithCookie() {
        Response response =
                given()
                        .contentType(JSON)
                        .body("{\"productArticle\":400273,\"optionArticle\":400273,\"amount\":\"1\",\"packageContract\":null}")
                        .cookie("shopenSIN=h21glt0rhe9r3qu0bui4pledjf;")
                        .when()
                        .post("https://ru.siberianhealth.com/ru/shop/ajax/cart/item/add/")
                        .then()
                        .statusCode(200)
                        .body("success", is(true))
                        .body("message[0]", is("Товар успешно добавлен в корзину"))
                        .extract().response();
        System.out.println(
                "Message: " + response.path("message[0]") + "\n"
                        + "Amount: " + response.path("data.cartData.amount") + "\n"
                        + "Sum: " + response.path("data.cartData.sum") + "\n"
                        + "Points: " + response.path("data.cartData.packages[0].points"));
    }

}
