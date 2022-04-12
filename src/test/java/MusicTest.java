import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.MusicPage;
import utils.User;
import utils.UserData;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MusicTest extends BaseTest {
    private final User user = UserData.user;
    private final String musicSearchInput = "Oshhh";

    @Test
    void loginTest() {
        MainPage mainPage = loginPage.login(user);
        MusicPage musicPage = mainPage.goToMusic();
        musicPage.playMusic(musicSearchInput);
        assertTrue(musicPage.getPlayingMusicTitle().toLowerCase().contains(musicSearchInput.toLowerCase())
                || musicPage.getPlayingMusicArtist().toLowerCase().contains(musicSearchInput.toLowerCase()));
    }
}