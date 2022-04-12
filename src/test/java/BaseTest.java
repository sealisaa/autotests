import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import pages.LoginPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.closeWindow;

public abstract class BaseTest {
    static LoginPage loginPage;

    @BeforeAll
    static void setup() {
        loginPage = new LoginPage();
    }

    @AfterAll
    static void close() {
        closeWindow();
        closeWebDriver();
    }
}
