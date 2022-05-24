import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.MusicPage;
import utils.MusicWrapper;
import utils.User;
import utils.UserData;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AddMusicTest extends BaseTest {

    private final User user = UserData.user1;
    private static MusicPage musicPage;
    private static final String musicTitle = "Oshhh";

    @BeforeEach
    void setUp() {
        LoginPage loginPage = new LoginPage();
        MainPage mainPage = loginPage.login(user);
        musicPage = mainPage.goToMusic();
        musicPage.deleteAllMyMusic();
    }

    @Test
    void addMusicTest() {
        SelenideElement searchResult = musicPage.searchMusic(musicTitle);
        MusicWrapper musicTrack = new MusicWrapper(searchResult);
        musicTrack.addToMyMusic();
        Map<String, MusicWrapper> myMusic = musicPage.getMyMusic();
        assertThat(myMusic.keySet()).contains(musicTitle);
    }

    @AfterAll
    static void setDown() {
        musicPage.deleteAllMyMusic();
    }
}
