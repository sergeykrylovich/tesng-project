package mypackage.tests.component_tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import mypackage.dto.UserDTO;
import mypackage.services.UserService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.List;


public class UserTests {
    
    public UserService userService;

    @BeforeMethod(description = "инициализация userService")
    public void initTest() {
        userService = new UserService();

    }

    @Test(testName = "Тест на получение всех юзеров")
    public void getAllUsersTest() {
        List<UserDTO> listOfUsers = userService.getAllUsers()
                .statusCode(200)
                .extract().jsonPath().getList("", UserDTO.class);

        Allure.step("assert", () -> assertThat(listOfUsers.size()).isPositive());
    }

    @Test(testName = "Тест на создание юзера")
    public void createUserTest() {

        UserDTO expectedUser = new UserDTO().builder()
                .name("Sergio")
                .email("sergio@myemail.com")
                .password("4444")
                .avatar("https://api.lorem.space/image/face?w=640&h=480&r=867")
                .build();


        UserDTO actualUser = userService.createUser(expectedUser)
                .statusCode(201)
                .extract().jsonPath().getObject("", UserDTO.class);

        Allure.step("сравниваем имя ожидаемого юзера и фактического", () ->
                assertThat(actualUser.getName()).isEqualTo(expectedUser.getName()));

        userService.deleteUser(actualUser.getId());
    }
}
