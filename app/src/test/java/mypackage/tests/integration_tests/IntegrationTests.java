package mypackage.tests.integration_tests;

import io.qameta.allure.Allure;
import mypackage.database_connection.DataBaseConnectionPool;
import mypackage.dto.*;
import mypackage.services.AuthService;
import mypackage.services.CategoryService;
import mypackage.services.ProductService;
import mypackage.services.UserService;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class IntegrationTests {

    public static final Logger logger = Logger.getLogger(IntegrationTests.class);

    UserService userService;
    AuthService authService;

    CategoryService categoryService;
    ProductService productService;

    @BeforeMethod
    public void setUp() {
        logger.info("setuping tests");
        userService = new UserService();
        authService = new AuthService();
        categoryService = new CategoryService();
        productService = new ProductService();

    }


    @Test(testName = "интеграционный тест по созданию категории и продукта, а потом его обновление",
            dataProvider = "create_user",
            dataProviderClass = mypackage.data_providers.DataForTests.class)
    public void createCategoryAndProductThenUpdateProduct(UserDTO expectedUser, AuthDTO auth, CategoryDTO expectedCategory) {
        logger.info("create user");

        UserDTO actualUser = userService
                .createUser(expectedUser)
                .statusCode(201)
                .extract().jsonPath().getObject("", UserDTO.class);

        logger.info("create token");
        String accessToken = authService.authorize(auth)
                .statusCode(201)
                .extract()
                .jsonPath()
                .getObject("access_token", String.class);

        logger.info("create category");
        CategoryDTO actualCategory = categoryService.createCategory(expectedCategory, accessToken)
                .statusCode(201)
                .extract().jsonPath().getObject("", CategoryDTO.class);


        logger.info("create productDTO");
        ProductCreationDTO expectedProduct = new ProductCreationDTO().builder()
                .title("NVIDIA RTX 3070")
                .categoryId(actualCategory.getId())
                .price(1000)
                .description("NVIDIA CARD")
                .images(List.of("https://placeimg.com/640/480/any"))
                .build();

        logger.info("create product");
        ProductDTO actualProduct = productService.createProduct(expectedProduct, accessToken)
                .statusCode(201)
                .extract().jsonPath().getObject("", ProductDTO.class);

        logger.info("assert product and category");
        Allure.step("сравнение продукта", () ->
                assertThat(actualProduct.getTitle()).isEqualTo(expectedProduct.getTitle()).as("Продукт соответствует созданному"));
        Allure.step("сравнение категории", () ->

                assertThat(actualCategory.getName()).isEqualTo(expectedCategory.getName()).as("Категория соответствует созданной"));

        logger.info("delete product and category");
        productService.deleteProduct(actualProduct.getId(), accessToken).statusCode(200);
        categoryService.deleteCategory(actualCategory.getId(), accessToken).statusCode(200);
    }

    @Test
    public void testDb() throws SQLException {
        String sql = "select * from product;";
        try (Connection connection = DataBaseConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet res = preparedStatement.executeQuery();)
        {
            while (res.next()) {
                int index = res.getInt("id");
                String name = res.getString("name");
                String price = res.getString("price");
                String productId = res.getString("product_id");
                String version = res.getString("version");
                System.out.println(index + " - " + name + " - " + productId + " - " + version);
            }

            DataBaseConnectionPool.close();
        }



    }
}
