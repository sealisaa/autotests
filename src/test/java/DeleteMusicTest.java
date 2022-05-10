import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.MusicPage;
import utils.User;
import utils.UserData;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteMusicTest extends BaseTest {

    private final User user = UserData.user;
    private static MusicPage musicPage;
    private static final String musicToDelete = "Oshhh";

    @BeforeEach
    void setUp() {
        LoginPage loginPage = new LoginPage();
        MainPage mainPage = loginPage.login(user);
        musicPage = mainPage.goToMusic();
        musicPage.addMusic(musicToDelete);
    }

    @Test
    void deleteMusicTest() {
        musicPage.deleteMusic(musicToDelete);
        assertTrue(musicPage.isMusicDeleted(musicToDelete));
    }
}
