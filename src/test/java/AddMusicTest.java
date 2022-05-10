import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.MusicPage;
import utils.User;
import utils.UserData;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddMusicTest extends BaseTest {

    private final User user = UserData.user;
    private static MusicPage musicPage;

    @BeforeEach
    void setUp() {
        LoginPage loginPage = new LoginPage();
        MainPage mainPage = loginPage.login(user);
        musicPage = mainPage.goToMusic();
        musicPage.deleteAllMyMusic();
    }

    @Test
    void addMusicTest() {
        String music = "Oshhh";
        musicPage.addMusic(music);
        musicPage.goToMyMusic();
        assertTrue(musicPage.getAddedMusicTitle().toLowerCase().contains(music.toLowerCase())
                || musicPage.getAddedMusicArtist().toLowerCase().contains(music.toLowerCase()));
    }
}
