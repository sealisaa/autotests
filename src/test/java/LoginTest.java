import org.junit.jupiter.api.Test;
import pages.MainPage;
import utils.User;
import utils.UserData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends BaseTest {
    private final User user = UserData.user;

    @Test
    void loginTest() {
        MainPage mainPage = loginPage.login(user);
        assertEquals(mainPage.getName(), user.getName());
    }
}
