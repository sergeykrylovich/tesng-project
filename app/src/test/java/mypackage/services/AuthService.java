package mypackage.services;

import io.restassured.response.ValidatableResponse;
import mypackage.dto.AuthDTO;
import mypackage.specs.RequestSpecs;

import static io.restassured.RestAssured.given;
import static mypackage.specs.RequestSpecs.*;

public class AuthService {

    public static final String AUTH_ENDPOINT = "/auth/login";

    public ValidatableResponse authorize (AuthDTO authData) {
        return given().spec(initPostRequest(AUTH_ENDPOINT))
                .body(authData)
                .when()
                .post()
                .then();
    }
}
