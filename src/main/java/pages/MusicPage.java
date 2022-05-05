package pages;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MusicPage extends BasePage {
    private static final String MY_MUSIC_BUTTON = "//a[@data-l='t,library']//*[text()='Моя музыка']//..";
    private static final String MUSIC_TRACKS = "//wm-track[@data-tsid='track']";
    private static final String PLAYING_MUSIC_TITLE = "//wm-player-track//span[@class='name']";
    private static final String PLAYING_MUSIC_ARTIST = "//wm-player-track//span[@class='artist']";
    private static final String MUSIC_SEARCH_INPUT = "//header//wm-search-input/input";

    public MusicPage() {
        check();
    }

    protected void check() {
        $(byXpath(MY_MUSIC_BUTTON)).shouldBe(Condition.visible);
    }

    public void playMusic(String input) {
        $(byXpath(MUSIC_SEARCH_INPUT)).shouldBe(Condition.visible.because("Не отображается поле для поиска музыки")).setValue(input).pressEnter();
        $$(byXpath(MUSIC_TRACKS)).get(0).click();
    }

    public String getPlayingMusicTitle() {
        return $(byXpath(PLAYING_MUSIC_TITLE)).text();
    }

    public String getPlayingMusicArtist() {
        return $(byXpath(PLAYING_MUSIC_ARTIST)).text();
    }
}
