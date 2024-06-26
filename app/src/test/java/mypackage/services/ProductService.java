package mypackage.services;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import mypackage.dto.ProductCreationDTO;

import static io.restassured.RestAssured.given;
import static mypackage.specs.RequestSpecs.initPostRequest;

public class ProductService {

    public static final String PRODUCT_ENDPOINT = "/products";


    @Step("Создание продукат через энпоинт")
    public ValidatableResponse createProduct(ProductCreationDTO product, String accessToken) {
        return given().spec(initPostRequest(PRODUCT_ENDPOINT))
                .auth().oauth2(accessToken)
                .when()
                .body(product)
                .post()
                .then();
    }

    @Step("Удалить продукт по его id через эндпоинт")
    public ValidatableResponse deleteProduct(Integer productId, String accessToken) {
        return given().spec(initPostRequest(PRODUCT_ENDPOINT))
                .auth().oauth2(accessToken)
                .pathParam("productId", productId)
                .when()
                .delete("/{productId}")
                .then();
    }
}
