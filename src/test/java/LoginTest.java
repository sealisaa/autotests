import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import utils.User;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends BaseTest {
    private final User user = new User("name", "login", "password");

    @Test
    void loginTest() {
        MainPage mainPage = loginPage.login(user);
        assertEquals(mainPage.getName(), user.getName());
    }

    @AfterAll
    static void close() {
        closeWindow();
        closeWebDriver();
    }
}
