package mypackage.services;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import mypackage.dto.CategoryDTO;
import mypackage.dto.UserDTO;

import static io.restassured.RestAssured.given;
import static mypackage.specs.RequestSpecs.initPostRequest;

public class CategoryService {

    public static final String CATEGORY_ENDPOINT = "/categories";


    @Step("Создание категории")
    public ValidatableResponse createCategory (CategoryDTO category) {
        return given().spec(initPostRequest(CATEGORY_ENDPOINT))
                .when()
                .body(category)
                .post("/")
                .then();
    }
}
