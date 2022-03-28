import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    String nameFieldXPath = "//a[contains(@class, 'nav-side_i') and contains(@data-l, 'userPage')]";

    public MainPage() {
        isLoaded();
    }

    public String getName() {
        return $(byXpath(nameFieldXPath)).text();
    }

    private void isLoaded() {
        $(byXpath(nameFieldXPath)).shouldBe(Condition.visible);
    }
}
