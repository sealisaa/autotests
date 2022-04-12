import org.junit.jupiter.api.BeforeAll;
import pages.LoginPage;

public abstract class BaseTest {
    static LoginPage loginPage;

    @BeforeAll
    static void setup() {
        loginPage = new LoginPage();
    }
}
