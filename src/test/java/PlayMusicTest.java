import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.MusicPage;
import utils.User;
import utils.UserData;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayMusicTest extends BaseTest {

    private static LoginPage loginPage;
    private final User user = UserData.user;

    @BeforeEach
    void setUp() {
        loginPage = new LoginPage();
    }

    @Test
    void playMusicTest() {
        MainPage mainPage = loginPage.login(user);
        MusicPage musicPage = mainPage.goToMusic();
        String music = "Oshhh";
        musicPage.playMusic(music);
        assertTrue(musicPage.getPlayingMusicTitle().toLowerCase().contains(music.toLowerCase())
                || musicPage.getPlayingMusicArtist().toLowerCase().contains(music.toLowerCase()));
    }
}