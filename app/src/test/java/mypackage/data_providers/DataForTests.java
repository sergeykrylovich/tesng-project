package mypackage.data_providers;

import mypackage.dto.AuthDTO;
import mypackage.dto.UserDTO;
import org.testng.annotations.DataProvider;

public class DataForTests {

    @DataProvider(name = "create_user")
    public Object[][] createUserMethod() {
        UserDTO user = new UserDTO().builder()
                .name("Sergio")
                .email("sergio@myemail.com")
                .password("4444")
                .avatar("https://api.lorem.space/image/face?w=640&h=480&r=867")
                .build();
        AuthDTO authDTO = new AuthDTO(user.getPassword(), user.getEmail());

        return new Object [][] {{user, authDTO}};
    }
}
