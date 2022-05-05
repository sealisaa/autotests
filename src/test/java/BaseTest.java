import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import pages.LoginPage;

import static com.codeborne.selenide.Selenide.*;

public abstract class BaseTest {
    static LoginPage loginPage;

    @BeforeAll
    static void setup() {
        open("http://ok.ru");
        loginPage = new LoginPage();
    }

    @AfterAll
    static void close() {
        closeWindow();
        closeWebDriver();
    }
}
