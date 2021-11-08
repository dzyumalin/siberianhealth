package ru.siberianhealth.tests.api;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.*;
import static ru.siberianhealth.tests.TestData.OPEN_URL;

public class Specs {

    public static RequestSpecification request = with()
            .baseUri(OPEN_URL)
            .basePath("/shop/ajax/cart/item")
            .log().all()
            .contentType(JSON);

    public static RequestSpecification requestForEdit = with()
            .baseUri(OPEN_URL)
            .basePath("/shop/ajax/cart")
            .log().all()
            .contentType(JSON);

    public static RequestSpecification requestForGet = with()
            .baseUri(OPEN_URL)
            .basePath("/shop/ajax/action")
            .log().all()
            .contentType(JSON);

    public static ResponseSpecification response = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

}
