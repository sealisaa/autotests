package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage {
    String nameFieldXPath = "//a[contains(@class, 'nav-side_i') and contains(@data-l, 'userPage')]";

    public MainPage() {
        check();
    }

    public String getName() {
        return $(byXpath(nameFieldXPath)).text();
    }

    protected void check() {
        $(byXpath(nameFieldXPath)).shouldBe(Condition.visible);
    }
}
