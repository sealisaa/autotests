import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.MusicPage;
import utils.Post;
import utils.User;
import utils.UserData;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreatePostWithMusicTest extends BaseTest {

    private final User user = UserData.user1;
    private static MainPage mainPage;
    private static final String music = "Oshhh";
    private static final String text = "Это текст для теста";

    @BeforeEach
    void setUp() {
        LoginPage loginPage = new LoginPage();
        mainPage = loginPage.login(user);
        MusicPage musicPage = mainPage.goToMusic();
        musicPage.deleteAllMyMusic();
        musicPage.addMusic(music);
        mainPage = musicPage.close();
    }

    @Test
    void createPostWithMusicTest() {
        mainPage.createPost(music, text);
        Post post = new Post.PostBuilder()
                .setAuthor(user.getName())
                .setText(text)
//                .setTime("00:24")
                .setMusic(music)
                .build();

        assertThat(post).usingRecursiveComparison().isEqualTo(mainPage.getLastPost());

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(mainPage.getLastPostAuthor())
                .as("Проверяем автора поста")
                .isEqualTo(user.getName());
        softly.assertThat(mainPage.getLastPostText())
                .as("Проверяем текст поста")
                .isGreaterThan(text);
        softly.assertThat(mainPage.getLastPostMusic())
                .as("Проверяем музыку в посте")
                .isEqualTo("music");
        softly.assertAll();
    }

    @AfterAll
    static void setDown() {
    }
}
