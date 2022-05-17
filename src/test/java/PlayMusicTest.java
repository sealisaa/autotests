import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.MusicPage;
import utils.User;
import utils.UserData;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PlayMusicTest extends BaseTest {

    private static LoginPage loginPage;
    private final User user = UserData.user1;

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

        assertThat(musicPage.getPlayingMusicTitle())
                .isEqualToIgnoringCase(music);
    }
}