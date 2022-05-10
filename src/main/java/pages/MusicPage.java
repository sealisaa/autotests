package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MusicPage extends BasePage {
    private static final String MY_MUSIC_BUTTON = "//a[@data-l='t,library']//*[text()='Моя музыка']//..";
    private static final String MUSIC_TRACK = ".//wm-track[@data-tsid='track']";
    private static final String PLAYING_MUSIC_TITLE = "//wm-player-track//span[@class='name']";
    private static final String PLAYING_MUSIC_ARTIST = "//wm-player-track//span[@class='artist']";
    private static final String MUSIC_SEARCH_INPUT = "//header//wm-search-input/input";
    private static final String ADD_MUSIC_BUTTON = "//wm-track-add-button[@data-tsid='track_add']";
    private static final String MUSIC_TITLE = ".//a[@data-tsid='track_artist']";
    private static final String MUSIC_ARTIST = ".//a[@data-tsid='track_name']";
    private static final String DELETE_MUSIC_BUTTON = ".//wm-icon[@data-tsid='remove_track']";

    public MusicPage() {
        check();
    }

    protected void check() {
        $(byXpath(MY_MUSIC_BUTTON)).shouldBe(Condition.visible.because("Не отображается кнопка Моя музыка"));
    }

    public void searchMusic(String music) {
        $(byXpath(MUSIC_SEARCH_INPUT))
                .shouldBe(Condition.visible.because("Не отображается поле для поиска музыки"))
                .setValue(music)
                .pressEnter();
    }

    public void playMusic(String music) {
        searchMusic(music);
        $$(byXpath(MUSIC_TRACK)).shouldBe(CollectionCondition.sizeNotEqual(0).because("Ни одного трека не найдено"))
                .get(0)
                .click();
    }

    public String getPlayingMusicTitle() {
        return $(byXpath(PLAYING_MUSIC_TITLE))
                .shouldBe(Condition.visible.because("Не отображается название трека"))
                .text();
    }

    public String getPlayingMusicArtist() {
        return $(byXpath(PLAYING_MUSIC_ARTIST))
                .shouldBe(Condition.visible.because("Не отображается исполнитель"))
                .text();
    }

    public void addMusic(String music) {
        searchMusic(music);
        $$(byXpath(MUSIC_TRACK)).shouldBe(CollectionCondition.sizeNotEqual(0).because("Ни одного трека не найдено"))
                .get(0)
                .hover();
        $(byXpath(ADD_MUSIC_BUTTON))
                .shouldBe(Condition.visible.because("Не отображается кнопка добавления музыки"))
                .click();
    }

    public void goToMyMusic() {
        $(byXpath(MY_MUSIC_BUTTON))
                .shouldBe(Condition.visible.because("Не отображается кнопка Моя музыка"))
                .click();
    }

    public void deleteAllMyMusic() {
        goToMyMusic();
        ElementsCollection myMusic = $$(byXpath(MUSIC_TRACK));
        for (SelenideElement music : myMusic) {
            music
                    .shouldBe(Condition.visible.because("Не отображается музыка"))
                    .hover()
                    .$(byXpath(DELETE_MUSIC_BUTTON))
                    .shouldBe(Condition.visible.because("Не отображается кнопка удаления музыки"))
                    .click();
        }
    }

    public String getAddedMusicTitle() {
        SelenideElement addedMusic = $$(byXpath(MUSIC_TRACK))
                .shouldBe(CollectionCondition.sizeNotEqual(0).because("Ни одного трека не найдено"))
                .get(0);
        return getMusicTitle(addedMusic);
    }

    public String getAddedMusicArtist() {
        SelenideElement addedMusic = $$(byXpath(MUSIC_TRACK))
                .shouldBe(CollectionCondition.sizeNotEqual(0).because("Ни одного трека не найдено"))
                .get(0);
        return getMusicArtist(addedMusic);
    }

    public String getMusicTitle(SelenideElement music) {
        return music.$(byXpath(MUSIC_TITLE))
                .shouldBe(Condition.visible.because("Не отображается название трека"))
                .text();
    }

    public String getMusicArtist(SelenideElement music) {
        return music.$(byXpath(MUSIC_ARTIST))
                .shouldBe(Condition.visible.because("Не отображается исполнитель"))
                .text();
    }

    public void deleteMusic(String musicToDelete) {
        goToMyMusic();
        ElementsCollection myMusic = $$(byXpath(MUSIC_TRACK));
        myMusic.shouldBe(CollectionCondition.sizeNotEqual(0).because("Ни одного трека не найдено"));
        for (SelenideElement music : myMusic) {
            if (getMusicTitle(music).equals(musicToDelete)) {
                music
                        .shouldBe(Condition.visible.because("Не отображается музыка"))
                        .hover()
                        .$(byXpath(DELETE_MUSIC_BUTTON))
                        .shouldBe(Condition.visible.because("Не отображается кнопка удаления музыки"))
                        .click();
            }
        }
    }

    public boolean isMusicDeleted(String deletedMusic) {
        goToMyMusic();
        ElementsCollection myMusic = $$(byXpath(MUSIC_TRACK));
        for (SelenideElement music : myMusic) {
            if (getMusicTitle(music).equals(deletedMusic)) {
                return false;
            }
        }
        return true;
    }
}
