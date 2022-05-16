import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.LoginPage;
import pages.MainPage;
import utils.User;
import utils.UserData;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class LoginTest extends BaseTest {

    private static LoginPage loginPage;
    private static MainPage mainPage;

    @BeforeEach
    void setUp() {
        loginPage = new LoginPage();
    }

    @ParameterizedTest
    @MethodSource("getUsers")
    void loginTest(User user) {
        mainPage = loginPage.login(user);

//        assertEquals(mainPage.getName(), user.getName());

        assertThat(mainPage.getName())
                .withFailMessage("Имя юзера должно быть %s", user.getName())
                .isEqualTo(user.getName());
        assertThat(mainPage.getName())
                .as("Проверяем имя юзера на главной странице")
                .startsWith(user.getFirstName())
                .endsWith(user.getLastName());
    }

    @AfterEach
    public void setDown() {
        mainPage.logout();
    }

    private static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(UserData.user1);
        users.add(UserData.user2);
        users.add(UserData.user3);
        return users;
    }
}
