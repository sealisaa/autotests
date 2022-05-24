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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AddManyMusicTest extends BaseTest {

    private final User user = UserData.user1;
    private static MusicPage musicPage;
    private static final List<String> musicTitles = new ArrayList<>();

    @BeforeEach
    void setUp() {
        LoginPage loginPage = new LoginPage();
        MainPage mainPage = loginPage.login(user);
        musicPage = mainPage.goToMusic();
        musicPage.deleteAllMyMusic();
        musicTitles.add("Oshhh");
        musicTitles.add("Fluorescent Adolescent");
        musicTitles.add("Diet Mountain Dew");
    }

    @Test
    void addMusicTest() {
        for (String musicTitle : musicTitles) {
            musicPage.goToMyMusic();
            SelenideElement searchResult = musicPage.searchMusic(musicTitle);
            MusicWrapper musicTrack = new MusicWrapper(searchResult);
            musicTrack.addToMyMusic();
        }
        Map<String, MusicWrapper> myMusic = musicPage.getMyMusic();
        assertThat(myMusic)
                .isNotEmpty();
        assertThat(myMusic.keySet())
                .containsAll(musicTitles);
    }

    @AfterAll
    static void setDown() {
        musicPage.deleteAllMyMusic();
    }
}
