package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class MusicPage extends BasePage {
    private static final By FOR_YOU_BUTTON = byXpath("//a[@data-tsid='showcase']");
    private static final By MY_MUSIC_BUTTON = byXpath("//a[@data-tsid='library']");
    private static final By RADIO_BUTTON = byXpath("//a[@data-tsid='radio']");
    private static final By COLLECTIONS_BUTTON = byXpath("//a[@data-tsid='collections']");
    private static final By MUSIC_TRACK = byXpath(".//wm-track[@data-tsid='track']");
    private static final By PLAYING_MUSIC_TITLE = byXpath("//wm-player-track//span[@class='name']");
    private static final By PLAYING_MUSIC_ARTIST = byXpath("//wm-player-track//span[@class='artist']");
    private static final By MUSIC_SEARCH_INPUT = byXpath("//header//wm-search-input/input");
    private static final By ADD_MUSIC_BUTTON = byXpath("//wm-track-add-button[@data-tsid='track_add']");
    private static final By MUSIC_TITLE = byXpath(".//a[@data-tsid='track_name']");
    private static final By MUSIC_ARTIST = byXpath(".//a[@data-tsid='track_artist']");
    private static final By DELETE_MUSIC_BUTTON = byXpath(".//wm-icon[@data-tsid='remove_track']");
    private static final By CLOSE_BUTTON = byXpath("//*[@id='music_layer_holder']/*[contains(@class, 'toolbar-layer_close')]");
    private static final By ROLLBACK = byXpath("//span[text()='Восстановить']");

    public MusicPage() {
        check();
    }

    @Override
    protected void check() {
        $(FOR_YOU_BUTTON).shouldBe(Condition.visible.because("Не отображается кнопка Для вас"));
        $(MY_MUSIC_BUTTON).shouldBe(Condition.visible.because("Не отображается кнопка Моя музыка"));
        $(RADIO_BUTTON).shouldBe(Condition.visible.because("Не отображается кнопка Радио"));
        $(COLLECTIONS_BUTTON).shouldBe(Condition.visible.because("Не отображается кнопка Коллекции"));
    }

    public void searchMusic(String music) {
        $(MUSIC_SEARCH_INPUT)
                .shouldBe(Condition.visible.because("Не отображается поле для поиска музыки"))
                .setValue(music)
                .pressEnter();
    }

    public void playMusic(String music) {
        searchMusic(music);
        $$(MUSIC_TRACK)
                .shouldBe(CollectionCondition.sizeNotEqual(0).because("Ни одного трека не найдено"))
                .get(0)
                .click();
    }

    public String getPlayingMusicTitle() {
        return $(PLAYING_MUSIC_TITLE)
                .shouldBe(Condition.visible.because("Не отображается название трека"))
                .text();
    }

    public String getPlayingMusicArtist() {
        return $(PLAYING_MUSIC_ARTIST)
                .shouldBe(Condition.visible.because("Не отображается исполнитель"))
                .text();
    }

    public void addMusic(String music) {
        goToMyMusic();
        searchMusic(music);
        $$(MUSIC_TRACK)
                .shouldBe(CollectionCondition.sizeNotEqual(0).because("Ни одного трека не найдено"))
                .get(0)
                .hover();
        $(ADD_MUSIC_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка добавления музыки"))
                .click();
    }

    public void goToMyMusic() {
        $(MY_MUSIC_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка Моя музыка"))
                .click();
    }

    public List<String> getMyMusicTitles() {
        goToMyMusic();
        List<String> titles = new ArrayList<>();
        ElementsCollection myMusicCollection = $$(MUSIC_TRACK);
        for (SelenideElement music : myMusicCollection) {
            titles.add(getMusicTitle(music));
        }
        return titles;
    }

    public void deleteAllMyMusic() {
        goToMyMusic();
        ElementsCollection myMusic = $$(MUSIC_TRACK);
        for (SelenideElement music : myMusic) {
            music
                    .shouldBe(Condition.visible.because("Не отображается музыка"))
                    .hover()
                    .$(DELETE_MUSIC_BUTTON)
                    .shouldBe(Condition.visible.because("Не отображается кнопка удаления музыки"))
                    .click();
        }
    }

    public String getMusicTitle(SelenideElement music) {
        return music.$(MUSIC_TITLE)
                .shouldBe(Condition.visible.because("Не отображается название трека"))
                .text();
    }

    public String getMusicArtist(SelenideElement music) {
        return music.$(MUSIC_ARTIST)
                .shouldBe(Condition.visible.because("Не отображается исполнитель"))
                .text();
    }

    public void deleteMusic(String musicToDelete) {
        goToMyMusic();
        ElementsCollection myMusic = $$(MUSIC_TRACK);
        myMusic.shouldBe(CollectionCondition.sizeNotEqual(0).because("Ни одного трека не найдено"));
        for (SelenideElement music : myMusic) {
            if (getMusicTitle(music).equals(musicToDelete)) {
                music
                        .shouldBe(Condition.visible.because("Не отображается музыка"))
                        .hover()
                        .$(DELETE_MUSIC_BUTTON)
                        .shouldBe(Condition.visible.because("Не отображается кнопка удаления музыки"))
                        .click();
                music.hover();
                $(ROLLBACK).shouldBe(Condition.visible.because("Музыка не удалилась"));
                refresh();
                $(MY_MUSIC_BUTTON).shouldBe(Condition.visible.because("Страница не обновилась"));
            }
        }
    }

    public MainPage close() {
        $(CLOSE_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка Закрыть"))
                .click();
        return new MainPage();
    }
}
