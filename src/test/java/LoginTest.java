import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    private final User user = new User("name", "login", "password");
    static LoginPage loginPage;

    @BeforeAll
    static void setup() {
        loginPage = new LoginPage();
    }

    @Test
    void loginTest() {
        open("http://ok.ru");
        loginPage = new LoginPage();
        MainPage mainPage = loginPage.login(user);
        assertEquals(mainPage.getName(), user.getName());
    }

    @AfterAll
    static void close() {
        closeWindow();
        closeWebDriver();
    }
}
