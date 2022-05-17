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
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreatePostWithMusicTest extends BaseTest {

    private final User user = UserData.user1;
    private static MainPage mainPage;
    private static MusicPage musicPage;
    private static final String music = "Oshhh";
    private static final String text = "Текст для теста";

    @BeforeEach
    void setUp() {
        LoginPage loginPage = new LoginPage();
        mainPage = loginPage.login(user);
        musicPage = mainPage.goToMusic();
        musicPage.deleteAllMyMusic();
        musicPage.addMusic(music);
        mainPage = musicPage.close();
    }

    @Test
    void createPostWithMusicTest() {
        mainPage.createPost(music, text);
        Post post = new Post.PostBuilder()
                .setAuthor("Другой автор")
                .setText("Другой текст")
                .setMusic("Другая песня")
                .build();

        assertThat(post)
                .usingRecursiveComparison()
                .isEqualTo(mainPage.getLastPost());

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(mainPage.getLastPostAuthor())
                .as("Проверяем автора поста")
                .isEqualTo(user.getName());
        softly.assertThat(mainPage.getLastPostText())
                .as("Проверяем текст поста")
                .isEqualTo(text);
        softly.assertThat(mainPage.getLastPostMusic())
                .as("Проверяем музыку в посте")
                .isEqualTo(music);
        softly.assertAll();
    }

    @AfterAll
    static void setDown() {
        mainPage.deleteLastPost();
        musicPage = mainPage.goToMusic();
        musicPage.deleteAllMyMusic();
    }
}
