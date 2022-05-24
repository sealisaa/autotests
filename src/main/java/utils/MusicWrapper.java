package utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class MusicWrapper {

    private final SelenideElement music;
    private static final By MUSIC_TITLE = byXpath(".//a[@data-tsid='track_name']");
    private static final By MUSIC_ARTIST = byXpath(".//a[@data-tsid='track_artist']");
    private static final By ADD_MUSIC_BUTTON = byXpath(".//wm-track-add-button[@data-tsid='track_add']");
    private static final By DELETE_MUSIC_BUTTON = byXpath(".//wm-icon[@data-tsid='remove_track']");
    private static final By ROLLBACK = byXpath("//span[text()='Восстановить']");

    public MusicWrapper(SelenideElement music) {
        music.shouldBe(Condition.visible.because("Музыка не отображается"));
        this.music = music;
    }

    public String getMusicTitle() {
        return music.$(MUSIC_TITLE)
                .shouldBe(Condition.visible.because("Не отображается название трека"))
                .text();
    }

    public String getMusicArtist() {
        return music.$(MUSIC_ARTIST)
                .shouldBe(Condition.visible.because("Не отображается исполнитель"))
                .text();
    }

    public void addToMyMusic() {
        music
                .hover()
                .$(ADD_MUSIC_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка добавления музыки"))
                .click();
    }

    public void deleteFromMyMusic() {
        music
                .shouldBe(Condition.visible.because("Не отображается музыка"))
                .hover()
                .$(DELETE_MUSIC_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка удаления музыки"))
                .click();
        music.hover();
        $(ROLLBACK).shouldBe(Condition.visible.because("Музыка не удалилась"));
    }

}
