package mypackage.tests.integration_tests;

import mypackage.dto.AuthDTO;
import mypackage.dto.UserDTO;
import mypackage.services.AuthService;
import mypackage.services.UserService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class IntegrationTests {

    UserService userService;
    AuthService authService;


    @Test(testName = "интеграционный тест по созданию категории и продукта, а потом его обновление",
            dataProvider = "create_user",
            dataProviderClass = mypackage.data_providers.DataForTests.class)
    public void createCategoryAndProductThenUpdateProduct(UserDTO expectedUser, AuthDTO authDTO) {
        UserDTO actualUser = userService
                .createUser(expectedUser)
                .statusCode(201)
                .extract().jsonPath().getObject("", UserDTO.class);

        String accessToken = authService.authorize(authDTO)
                .statusCode(201)
                .extract()
                .jsonPath()
                .getObject("access_token", String.class);


    }
}
