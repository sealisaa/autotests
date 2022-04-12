package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage {
    private static final String NAME_FIELD = "//a[contains(@class, 'nav-side_i') and contains(@data-l, 'userPage')]";
    private static final String MUSIC_BUTTON = "//*[@id='hook_Block_MusicToolbarButton']";;

    public MainPage() {
        check();
    }

    public String getName() {
        return $(byXpath(NAME_FIELD)).text();
    }

    public MusicPage goToMusic() {
        $(byXpath(MUSIC_BUTTON)).click();
        return new MusicPage();
    }

    protected void check() {
        $(byXpath(NAME_FIELD)).shouldBe(Condition.visible);
    }
}
