package mypackage.services;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import mypackage.dto.CategoryDTO;
import mypackage.dto.UserDTO;

import static io.restassured.RestAssured.given;
import static mypackage.specs.RequestSpecs.initPostRequest;

public class CategoryService {

    public static final String CATEGORY_ENDPOINT = "/categories";


    @Step("Создание категории через эндпоинт")
    public ValidatableResponse createCategory (CategoryDTO category, String accessToken) {
        return given().spec(initPostRequest(CATEGORY_ENDPOINT))
                .auth().oauth2(accessToken)
                .when()
                .body(category)
                .post("/")
                .then();
    }

    @Step("Удалить категоррию по его id через эндпоинт")
    public ValidatableResponse deleteCategory(Integer categoryId, String accessToken) {
        return given().spec(initPostRequest(CATEGORY_ENDPOINT))
                .auth().oauth2(accessToken)
                .pathParam("categoryId", categoryId)
                .when()
                .delete("/{categoryId}")
                .then();
    }
}
