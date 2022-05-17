package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import utils.Post;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class MainPage extends BasePage {
    private static final By NAME_FIELD = byXpath("//a[contains(@class, 'nav-side_i') and contains(@data-l, 'userPage')]");
    private static final By MUSIC_BUTTON = byXpath("//*[@id='hook_Block_MusicToolbarButton']");
    private static final By TOOLBAR_MENU = byXpath("//*[contains(@class, 'toolbar-menu')]");
    private static final By LOGOUT = byXpath("//a[@data-l='t,logout']");
    private static final By SUBMIT_LOGOUT = byXpath("//input[@data-l='t,logout']");
    private static final By TOOLBAR = byXpath("//*[contains(@data-l, 'navigationToolbar')]");
    private static final By POST_FIELD = byXpath("//a[contains(@href, 'post')]");
    private static final By POST_INPUT = byXpath("//*[@data-module='postingForm/mediaText']");
    private static final By ADD_MUSIC_BUTTON = byXpath("//*[contains(@class, 'posting') and contains(@data-l, 'button.music')]");
    private static final By ADD_BUTTON = byXpath("//a[contains(@class,'button') and text()='Добавить']");
    private static final By SUBMIT_POSTING = byXpath("//*[contains(@class,'posting_submit')]");
    private static final By POST_AUTHOR = byXpath(".//a[contains(@class, 'user-link')]");
    private static final By POST_TEXT = byXpath(".//*[contains(@class, 'media-text_cnt_tx')]");
    private static final By POST_TIME = byXpath(".//*[contains(@class, 'feed_date')]");
    private static final By POST_MUSIC = byXpath(".//a[contains(@class, 'track-with-cover_name')]/span");
    private static final By FEED_MENU = byXpath("//*[@class='feed_menu']");
    private static final By DELETE_POST = byXpath("//*[text()='Удалить заметку']");

    public MainPage() {
        check();
    }

    @Override
    protected void check() {
        $(NAME_FIELD)
                .shouldBe(Condition.visible.because("Не отображаются имя и фамилия пользователя"));
        $(TOOLBAR)
                .shouldBe(Condition.visible.because("Не отображаются верхнее меню"));
        $(MUSIC_BUTTON)
                .shouldBe(Condition.visible.because("Не отображаются кнопка Музыка"));
    }

    public String getName() {
        return $(NAME_FIELD)
                .shouldBe(Condition.visible.because("Не отображаются имя и фамилия пользователя"))
                .text();
    }

    public MusicPage goToMusic() {
        $(MUSIC_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка перехода к музыке"))
                .click();
        return new MusicPage();
    }

    public void createPost(String music, String text) {
        $(POST_FIELD)
                .shouldBe(Condition.visible.because("Не отображается поле Напишите заметку"))
                .click();
        $(POST_INPUT)
                .shouldBe(Condition.visible.because("Не отображается поле для ввода текста"))
                .setValue(text);
        $(ADD_MUSIC_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка добавления музыки в пост"))
                .shouldBe(Condition.enabled.because("Кнопка добавления музыки в пост не активна"))
                .click();
        $(byXpath(getMusicXpath(music)))
                .shouldBe(Condition.visible.because("Не отображается песня для поста"))
                .click();
        $(ADD_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка Добавить"))
                .click();
        $(SUBMIT_POSTING)
                .shouldBe(Condition.visible.because("Не отображается кнопка Поделиться"))
                .click();
        refresh();
    }

    public Post getLastPost() {
        return new Post.PostBuilder()
                .setAuthor(getLastPostAuthor())
                .setText(getLastPostText())
                .setMusic(getLastPostMusic())
                .build();
    }

    public void deleteLastPost() {
        $(FEED_MENU)
                .shouldBe(Condition.visible.because("Не отображается меню поста"))
                .hover();
        $(DELETE_POST)
                .shouldBe(Condition.visible.because("Не отображается кнопка Удалить пост"))
                .click();
    }

    public String getMusicXpath(String music) {
        return ("//*[contains(@class, 'track') and contains(@data-json, '" + music + "')]");
    }

    public void logout() {
        $(TOOLBAR_MENU)
                .shouldBe(Condition.visible.because("Не отображается кнопка в верхнем меню"))
                .click();
        $(LOGOUT)
                .shouldBe(Condition.visible.because("Не отображается кнопка Выйти в меню"))
                .click();
        $(SUBMIT_LOGOUT)
                .shouldBe(Condition.visible.because("Не отображается кнопка Выйти"))
                .click();
    }

    public String getLastPostAuthor() {
        return $$(POST_AUTHOR)
                .get(0)
                .shouldBe(Condition.visible.because("Не отображается автор поста"))
                .text();
    }

    public String getLastPostText() {
        return $$(POST_TEXT)
                .get(0)
                .shouldBe(Condition.visible.because("Не отображается текст поста"))
                .text();
    }

    public String getLastPostTime() {
        return $$(POST_TIME)
                .get(0)
                .shouldBe(Condition.visible.because("Не отображается время поста"))
                .text();
    }

    public String getLastPostMusic() {
        return $$(POST_MUSIC)
                .get(0)
                .shouldBe(Condition.visible.because("Не отображается музыка в посте"))
                .text();
    }
}
