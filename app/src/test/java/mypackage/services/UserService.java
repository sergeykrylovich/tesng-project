package mypackage.services;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import mypackage.dto.UserDTO;
import mypackage.specs.RequestSpecs;

import static io.restassured.RestAssured.given;
import static mypackage.specs.RequestSpecs.*;

public class UserService {


    public static final String USER_ENDPOINT = "/users";


    @Step("запрос всех юзеров")
    public ValidatableResponse getAllUsers () {
        return given().spec(initGetRequest(USER_ENDPOINT))
                .when()
                .get("/")
                .then();
    }

    @Step("Создание юзера")
    public ValidatableResponse createUser (UserDTO user) {
        return given().spec(initPostRequest(USER_ENDPOINT))
                .when()
                .body(user)
                .post("/")
                .then();
    }

    @Step("Удаление юзера по его id")
    public ValidatableResponse deleteUser (Integer id) {
        return given().spec(initGetRequest(USER_ENDPOINT))
                .pathParam("id", id)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(200);
    }


}
