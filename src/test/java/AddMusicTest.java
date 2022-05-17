import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.MusicPage;
import utils.User;
import utils.UserData;

import java.util.List;

public class AddMusicTest extends BaseTest {

    private final User user = UserData.user1;
    private static MusicPage musicPage;
    private static final String music = "Oshhh";

    @BeforeEach
    void setUp() {
        LoginPage loginPage = new LoginPage();
        MainPage mainPage = loginPage.login(user);
        musicPage = mainPage.goToMusic();
        musicPage.deleteAllMyMusic();
    }

    @Test
    void addMusicTest() {
        musicPage.addMusic(music);
        musicPage.goToMyMusic();
        List<String> myMusic = musicPage.getMyMusicTitles();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(myMusic)
                .as("Проверяем, что Моя музыка содержит треки")
                .isNotEmpty();
        softly.assertThat(myMusic)
                .as("Проверяем, что добавилась нужная песня")
                .contains(music);
        softly.assertThat(myMusic)
                .as("Проверяем, что музыка добавилась один раз")
                .hasSize(1);
        softly.assertAll();
    }

    @AfterAll
    static void setDown() {
        musicPage.deleteAllMyMusic();
    }
}
