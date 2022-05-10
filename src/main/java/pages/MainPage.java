package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage {
    private static final String NAME_FIELD = "//a[contains(@class, 'nav-side_i') and contains(@data-l, 'userPage')]";
    private static final String MUSIC_BUTTON = "//*[@id='hook_Block_MusicToolbarButton']";
    private static final String TOOLBAR_MENU = "//*[contains(@class, 'toolbar-menu')]";
    private static final String LOGOUT = "//a[@data-l='t,logout']";
    private static final String SUBMIT_LOGOUT = "//input[@data-l='t,logout']";

    public MainPage() {
        check();
    }

    public String getName() {
        return $(byXpath(NAME_FIELD)).text();
    }

    public MusicPage goToMusic() {
        $(byXpath(MUSIC_BUTTON))
                .shouldBe(Condition.visible.because("Не отображается кнопка перехода к музыке"))
                .click();
        return new MusicPage();
    }

    public void logout() {
        $(byXpath(TOOLBAR_MENU)).shouldBe(Condition.visible.because("Не отображается toolbar")).click();
        $(byXpath(LOGOUT)).shouldBe(Condition.visible.because("Не отображается кнопка Выйти в меню")).click();
        $(byXpath(SUBMIT_LOGOUT)).shouldBe(Condition.visible.because("Не отображается кнопка Выйти")).click();
    }

    protected void check() {
        $(byXpath(NAME_FIELD)).shouldBe(Condition.visible.because("Не отображаются имя и фамилия пользователя"));
    }
}
