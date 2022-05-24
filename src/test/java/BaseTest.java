import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.*;

public abstract class BaseTest {

    private static final String okUrl = "http://ok.ru";

    @BeforeAll
    static void start() {
        open(okUrl);
    }

    @AfterAll
    static void close() {
//        closeWindow();
//        closeWebDriver();
    }
}
