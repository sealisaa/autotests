import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.MusicPage;
import utils.User;
import utils.UserData;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MusicTest extends BaseTest {
    private final User user = UserData.user;

    @Test
    void musicTest() {
        MainPage mainPage = loginPage.login(user);
        MusicPage musicPage = mainPage.goToMusic();
        String musicSearchInput = "Oshhh";
        musicPage.playMusic(musicSearchInput);
        assertTrue(musicPage.getPlayingMusicTitle().toLowerCase().contains(musicSearchInput.toLowerCase())
                || musicPage.getPlayingMusicArtist().toLowerCase().contains(musicSearchInput.toLowerCase()));
    }
}