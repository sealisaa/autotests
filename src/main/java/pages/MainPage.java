package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.PostWrapper;

import java.util.ArrayList;
import java.util.List;

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
    private static final By POST = byXpath(".//*[@class='feed']");

    private final List<PostWrapper> posts = new ArrayList<>();

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
        ElementsCollection postCollection = $$(POST);
        for (SelenideElement post : postCollection) {
            post
                    .shouldBe(Condition.visible.because("Не отображается пост"));
            posts.add(new PostWrapper(post));
        }
    }

    public PostWrapper getPostByText(String text) {
        for (PostWrapper post : posts) {
            if (post.getText().equals(text)) {
                return post;
            }
        }
        return null;
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
}
