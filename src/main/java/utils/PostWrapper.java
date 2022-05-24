package utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;

public class PostWrapper {

    private final SelenideElement post;
    private static final By POST_AUTHOR = byXpath(".//a[contains(@class, 'user-link')]");
    private static final By POST_TEXT = byXpath(".//*[contains(@class, 'media-text_cnt_tx')]");
    private static final By POST_MUSIC_TITLE = byXpath(".//a[contains(@class, 'track-with-cover_name')]/span");
    private static final By FEED_MENU = byXpath(".//*[@class='feed_menu']");
    private static final By DELETE_POST = byXpath(".//*[text()='Удалить заметку']");

    public PostWrapper(SelenideElement post) {
        post.shouldBe(Condition.visible.because("Пост не отображается"));
        this.post = post;
    }

    public String getAuthor() {
        return post
                .$(POST_AUTHOR)
                .shouldBe(Condition.visible.because("Не отображается автор поста"))
                .text();
    }

    public String getText() {
        return post
                .$(POST_TEXT)
                .shouldBe(Condition.visible.because("Не отображается текст поста"))
                .text();
    }

    public String getMusicTitle() {
        return post
                .$(POST_MUSIC_TITLE)
                .shouldBe(Condition.visible.because("Не отображается музыка в посте"))
                .text();
    }

    public void delete() {
        post
                .$(FEED_MENU)
                .shouldBe(Condition.visible.because("Не отображается меню поста"))
                .hover()
                .$(DELETE_POST)
                .shouldBe(Condition.visible.because("Не отображается кнопка Удалить пост"))
                .click();
    }
}
