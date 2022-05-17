import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.MusicPage;
import utils.User;
import utils.UserData;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AddManyMusicTest extends BaseTest {

    private final User user = UserData.user1;
    private static MusicPage musicPage;
    private static final List<String> music = new ArrayList<>();

    @BeforeEach
    void setUp() {
        LoginPage loginPage = new LoginPage();
        MainPage mainPage = loginPage.login(user);
        musicPage = mainPage.goToMusic();
        musicPage.deleteAllMyMusic();
        music.add("Oshhh");
        music.add("Fluorescent Adolescent");
        music.add("Diet Mountain Dew");
    }

    @Test
    void addMusicTest() {
        for (String musicTrack : music) {
            musicPage.goToMyMusic();
            musicPage.addMusic(musicTrack);
        }
        musicPage.goToMyMusic();
        List<String> myMusic = musicPage.getMyMusicTitles();

        assertThat(myMusic)
                .isNotEmpty();
        assertThat(myMusic)
                .containsExactly("Diet Mountain Dew", "Fluorescent Adolescent", "Oshhh");
    }

    @AfterAll
    static void setDown() {
        musicPage.deleteAllMyMusic();
    }
}
