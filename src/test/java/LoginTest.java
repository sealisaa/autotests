import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import utils.User;
import utils.UserData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends BaseTest {

    private static LoginPage loginPage;
    private final User user = UserData.user;

    @BeforeEach
    void setUp() {
        loginPage = new LoginPage();
    }

    @Test
    void loginTest() {
        MainPage mainPage = loginPage.login(user);
        assertEquals(mainPage.getName(), user.getName());
    }
}
