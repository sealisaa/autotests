import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.MusicPage;
import utils.User;
import utils.UserData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteMusicTest extends BaseTest {

    private final User user = UserData.user1;
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
        List<String> myMusic = musicPage.getMyMusicTitles();

//        assertTrue(musicPage.isMusicDeleted(musicToDelete));

        assertThat(myMusic)
                .as("Проверяем, что трек удалился")
                .doesNotContain(musicToDelete);
    }
}
