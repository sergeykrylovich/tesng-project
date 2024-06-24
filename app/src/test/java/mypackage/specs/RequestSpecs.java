package mypackage.specs;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecs {

    public static final String BASE_URI = "https://api.escuelajs.co/api/v1";

    @Step("инициализация спецификации для post запроса")
    public static RequestSpecification initPostRequest (String basePath) {
        return new RequestSpecBuilder().setBaseUri(BASE_URI)
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.ANY)
                .build();
    }

    @Step("инициализация спецификации для get запроса")
    public static RequestSpecification initGetRequest (String basePath) {
        return new RequestSpecBuilder().setBaseUri(BASE_URI)
                .setBasePath(basePath)
                .setAccept(ContentType.ANY)
                .build();
    }



}
